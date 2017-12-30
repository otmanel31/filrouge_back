package com.otmanel.filrouge_loncoto.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.otmanel.filrouge_loncoto.metier.Client;
import com.otmanel.filrouge_loncoto.metier.Intervention;
import com.otmanel.filrouge_loncoto.metier.Site;

@Service
public class InterventionDao implements IInterventionDao {

	private EntityManager em;
	
	@PersistenceContext
	public void setEm(EntityManager e) {
		this.em = em;
	}
	
	@Override
	@Transactional
	public List<Intervention> findAll() {
		return this.em.createQuery("from Intervention", Intervention.class).getResultList();
	}

	@Override
	@Transactional
	public Intervention findById(int id) {
		return this.em.find(Intervention.class, id);
	}

	@Override
	@Transactional
	public Intervention save(Intervention intervention) {
		if (intervention.getId() == 0) {
			//create
			this.em.persist(intervention);
		}else {
			//update
			intervention = this.em.merge(intervention);
		}
		return intervention;
	}

	@Override
	@Transactional
	public int delete(int id) {
		Intervention i = this.em.find(Intervention.class, id);
		if (i != null) {
			this.em.remove(i);
			return 1;
		}else {
			return 0;			
		}
	}

	@Override
	@Transactional
	public List<Intervention> searchIntervention(Optional<String> status, 
			Optional<String> client, Optional<String> site, Optional<String> date) {
		StringBuilder request = new StringBuilder("SELECT i from Intervention as i where");
		if (status.isPresent()) {
			request.append(" i.status like :status");
		}
		if (client.isPresent()) {
			if (status.isPresent()) request.append(" and");
			request.append(" i.equipment.client.name like :client");
		}
		if (site.isPresent()) {
			if (client.isPresent() || status.isPresent()) request.append(" and");
			request.append(" i.equipment.client.site.name like :site");
		}
		if (date.isPresent()) {
			if (site.isPresent() || client.isPresent() || status.isPresent()) request.append(" and");
			request.append(" i.interventionDate like :date");
		}
		if (!status.isPresent() && !client.isPresent() && !site.isPresent() 
				&& !date.isPresent()) return findAll();
		TypedQuery<Intervention> query = em.createQuery(request.toString(), Intervention.class);
		if (status.isPresent()) query.setParameter("status", "%"+status.get()+"%");
		if (client.isPresent()) query.setParameter("client", "%"+client.get()+"%");
		if (site.isPresent()) query.setParameter("site", "%"+site.get()+"%");
		if (date.isPresent()) query.setParameter("date", "%"+date.get()+"%");
		
		return query.getResultList();
	}
}
