package com.otmanel.filrouge_loncoto.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.otmanel.filrouge_loncoto.metier.Client;

@Service
public class ClientDao implements IClientDao {
	
	private EntityManager em;
	
	@PersistenceContext
	public void setEm(EntityManager em) {
		this.em = em;
	}

	@Override
	@Transactional
	public List<Client> findAll() {
		return this.em.createQuery("from Client", Client.class).getResultList();
	}

	@Override
	@Transactional
	public Client findById(int id) {
		return this.em.find(Client.class, id);
	}

	@Override
	@Transactional
	public Client save(Client client) {
		if (client.getId() == 0) {
			// create
			this.em.persist(client);
		}else {
			//update
			client = this.em.merge(client);
		}
		return client;
	}

	@Override
	@Transactional
	public int delete(int id) {
		Client c = this.em.find(Client.class, id);
		if (c != null) {
			this.em.remove(c);
			return 1;
		}
		return 0;
	}
}
