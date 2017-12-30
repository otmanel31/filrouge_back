package com.otmanel.filrouge_loncoto.dao;

import java.util.List;
import java.util.Optional;

import com.otmanel.filrouge_loncoto.metier.Client;
import com.otmanel.filrouge_loncoto.metier.Intervention;
import com.otmanel.filrouge_loncoto.metier.Site;

public interface IInterventionDao {
	List<Intervention> findAll();
	Intervention findById(int id);
	Intervention save(Intervention intervention);
	int delete(int id);
	List<Intervention> searchIntervention(
			Optional<String> status,
			Optional<String> client,
			Optional<String> site,
			Optional<String> date
			);
}
