package com.otmanel.filrouge_loncoto.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.otmanel.filrouge_loncoto.dao.ISiteDao;
import com.otmanel.filrouge_loncoto.metier.Site;

import lombok.val;

@Controller
@RequestMapping(value="/api")
public class SiteController {
	Logger log = LogManager.getLogger();
	
	@Autowired
	public ISiteDao siteDao;
	
	@RequestMapping(value="/sites", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Site> liste(){
		//log.info('in getAll site api: '+ siteDao.findAll())
		return siteDao.findAll();
	}
	
	@RequestMapping(value="/sites/{id:[0-9]+}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Site findById(@PathVariable("id") int id) {
		return this.siteDao.findById(id);
	}
	
	@RequestMapping(value="/sites", method=RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Map<String, Object> delete(@PathVariable("id") int id){
		Map<String, Object> result = new HashMap<>();
		
		result.put("nb lignes supprimées", this.siteDao.delete(id));
		return result;
	}
	
	@RequestMapping(value="/sites", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Site save(@RequestBody Site s) {
		return this.siteDao.save(s);
	}
}
