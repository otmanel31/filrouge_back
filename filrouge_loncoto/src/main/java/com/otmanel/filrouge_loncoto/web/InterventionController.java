package com.otmanel.filrouge_loncoto.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.xml.ws.http.HTTPException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpClientErrorException;

import com.otmanel.filrouge_loncoto.dao.IInterventionDao;
import com.otmanel.filrouge_loncoto.metier.Intervention;

@Controller
@RequestMapping(value="/api")
public class InterventionController {
	Logger log = LogManager.getLogger(Intervention.class);
	
	// repositories
	@Autowired
	public IInterventionDao interventionDao;

	// api
	@RequestMapping(value="/interventions", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Intervention> liste(
			@RequestParam("status") Optional<String> status,
			@RequestParam("client") Optional<String> client,
			@RequestParam("site") Optional<String> site,
			@RequestParam("date") Optional<String> date
			){
		log.info("status intervention: " + status.orElse("no status"));
		log.info("client intervention: " + client.orElse("no client"));
		log.info("site intervention: " + site.orElse("no site"));
		log.info("date intervention: " + date.orElse("no datetime"));
		log.info(interventionDao.searchIntervention(status, client, site, date));
		
		return interventionDao.searchIntervention(status, client, site, date);
	}
	
	@RequestMapping(value="/interventions/{id:[0-9]+}", method=RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Intervention findById(@PathVariable("id") int id) {
		Intervention i = interventionDao.findById(id);
		if (i == null) {
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
		}
		return i;
	}
	
	@RequestMapping(value="/interventions/{id:[0-9]+}", method=RequestMethod.GET,
				produces=MediaType.APPLICATION_JSON_VALUE
			)
	@ResponseBody
	public Map<String, Object> delete(@PathVariable("id") int id){
		Map<String, Object> result = new HashMap<>();
		result.put("nb lignes supp", interventionDao.delete(id));
		return result;
	}
	
	@RequestMapping(value="/interventions", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Intervention save(@RequestBody Intervention i) {
		return interventionDao.save(i);
	}
}
