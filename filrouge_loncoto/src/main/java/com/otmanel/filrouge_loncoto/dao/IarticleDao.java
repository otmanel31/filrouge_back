package com.otmanel.filrouge_loncoto.dao;

import java.util.List;
import java.util.Optional;

import javax.naming.OperationNotSupportedException;

import com.otmanel.filrouge_loncoto.metier.Article;

public interface IarticleDao {
	List<Article> findAll();
	Article findById(int id);
	Article save(Article article);
	int delete(int id);
	List<Article> searchArticle(Optional<String> nameArticle);
}
