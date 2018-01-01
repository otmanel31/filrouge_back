package com.otmanel.filrouge_loncoto.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

import com.otmanel.filrouge_loncoto.dao.IarticleDao;
import com.otmanel.filrouge_loncoto.metier.Article;

@Controller
@RequestMapping(value="/api")
public class ArticleController {
	Logger log = LogManager.getLogger(ArticleController.class);
	
	@Autowired
	private IarticleDao articleDao;
	
	@RequestMapping(value="/articles", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Article> liste(@RequestParam("namearticle") Optional<String> nameArticle){
		log.info("namearticle: " + nameArticle.orElse("rien"));
		log.info(articleDao.searchArticle(nameArticle));
		return articleDao.searchArticle(nameArticle);
	}
	
	@RequestMapping(value="/articles/{id:[0-9]+}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Article findById(@PathVariable("id") int id) {
		Article a = this.articleDao.findById(id);
		if (a == null) {
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
		}
		return a;
	}
	
	@RequestMapping(value="/articles/{id:[0-9]+}", method=RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Map<String, Object> delete(@PathVariable("id") int id){
		Article a = articleDao.findById(id);
		Map<String, Object> result = new HashMap<>();
		result.put("nb lignes supprimées", this.articleDao.delete(id));
		return result;
	}
	
	@RequestMapping(value="/articles", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Article save(@RequestBody Article a) {
		return this.articleDao.save(a);
 	}
}
