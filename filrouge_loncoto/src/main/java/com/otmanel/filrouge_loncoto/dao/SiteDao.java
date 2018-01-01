package com.otmanel.filrouge_loncoto.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.otmanel.filrouge_loncoto.metier.Intervention;
import com.otmanel.filrouge_loncoto.metier.Site;

@Service
public class SiteDao implements ISiteDao{

	private EntityManager em;
	
	@PersistenceContext
	public void setEm(EntityManager em) {
		this.em = em;
	}
	
	@Override
	@Transactional
	public List<Site> findAll() {
		return this.em.createQuery("from Site", Site.class).getResultList();
	}

	@Override
	@Transactional
	public Site findById(int id) {
		return this.em.find(Site.class, id);
	}

	@Override
	@Transactional
	public Site save(Site site) {
		if (site.getId() == 0) {
			// post
			this.em.persist(site);
		}else {
			// put
			site = this.em.merge(site);
		}
		return site;
	}

	@Override
	@Transactional
	public int delete(int id) {
		Site site = this.em.find(Site.class, id);
		if (site != null) {
			this.em.remove(site);
			return 1;
		}
		return 0;
	}

}
