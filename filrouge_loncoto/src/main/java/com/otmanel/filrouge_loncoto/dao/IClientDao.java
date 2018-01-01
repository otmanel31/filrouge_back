package com.otmanel.filrouge_loncoto.dao;

import java.util.List;
import java.util.Optional;

import com.otmanel.filrouge_loncoto.metier.Client;


public interface IClientDao {
	List<Client> findAll();
	Client findById(int id);
	Client save(Client client);
	int delete(int id);
}
