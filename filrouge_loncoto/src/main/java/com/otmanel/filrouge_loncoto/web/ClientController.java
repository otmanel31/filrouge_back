package com.otmanel.filrouge_loncoto.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpClientErrorException;

import com.otmanel.filrouge_loncoto.dao.IClientDao;
import com.otmanel.filrouge_loncoto.metier.Client;

@Controller
@RequestMapping(value="/api")
public class ClientController {

	Logger log = LogManager.getLogger();
	
	@Autowired
	IClientDao clientDao;
	
	@RequestMapping(value="/clients", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Client> liste(){
		return this.clientDao.findAll();
	}
	
	@RequestMapping(value="/clients/{id:[0-9]+}", method=RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Client findById(@PathVariable("id") int id) {
		Client i = this.clientDao.findById(id);
		if (i == null) {
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
		}
		return i;
	}
	
	@RequestMapping(value="/clients/{id:[0-9]+}", method=RequestMethod.GET,
				produces=MediaType.APPLICATION_JSON_VALUE
			)
	@ResponseBody
	public Map<String, Object> delete(@PathVariable("id") int id){
		Map<String, Object> result = new HashMap<>();
		result.put("nb lignes supp", clientDao.delete(id));
		return result;
	}
	
	@RequestMapping(value="/interventions", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Client save(@RequestBody Client i) {
		return clientDao.save(i);
	}
}
