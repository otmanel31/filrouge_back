package com.otmanel.filrouge_loncoto.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.otmanel.filrouge_loncoto.metier.Article;

@Service
public class ArticleDao implements IarticleDao {

	private EntityManager em;
	
	@PersistenceContext
	public void setEm(EntityManager em) {this.em = em;}
	
	@Override
	@Transactional
	public List<Article> findAll() {
		return em.createQuery("from Article", Article.class).getResultList();
	}

	@Override
	@Transactional
	public Article findById(int id) {
		return em.find(Article.class, id);
	}

	@Override
	@Transactional
	public Article save(Article article) {
		if (article.getId() == 0) {
			em.persist(article);
		}else {
			article = em.merge(article);
		}
		return article;
	}

	@Override
	@Transactional
	public int delete(int id) {
		Article a = em.find(Article.class, id);
		if (a != null) {
			em.remove(a);
			return 1;
		}else {
			return 0;
		}
	}

	@Override
	@Transactional
	public List<Article> searchArticle(Optional<String> nameArticle) {
		StringBuilder request = new StringBuilder("select a from Article as a where");
		if (nameArticle.isPresent()) {
			request.append(" a.name like :nameart");
		}
		if (!nameArticle.isPresent()) return findAll();
		TypedQuery<Article> q = em.createQuery(request.toString(), Article.class);
		if (nameArticle.isPresent()) {
			q.setParameter("nameart", "%"+nameArticle.get()+"%");
		}
		return q.getResultList();
	}

}
