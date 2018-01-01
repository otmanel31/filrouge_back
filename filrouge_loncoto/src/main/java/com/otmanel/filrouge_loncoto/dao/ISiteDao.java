package com.otmanel.filrouge_loncoto.dao;

import java.util.List;

import com.otmanel.filrouge_loncoto.metier.Intervention;
import com.otmanel.filrouge_loncoto.metier.Site;

public interface ISiteDao {
	List<Site> findAll();
	Site findById(int id);
	Site save(Site site);
	int delete(int id);
}
