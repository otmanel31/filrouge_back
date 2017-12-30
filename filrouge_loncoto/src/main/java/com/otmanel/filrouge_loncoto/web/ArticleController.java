package com.otmanel.filrouge_loncoto.web;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
}
