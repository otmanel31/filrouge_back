package com.otmanel.filrouge_loncoto.metier;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Fetch;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter @NoArgsConstructor @ToString(exclude= {"materiels"})
public class Article {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String name;
	private String description;
	@ManyToOne
	private SousFamille subfamilly;
	@OneToMany(mappedBy="article")
	@JsonIgnore
	private Set<Materiel> materiels;
	
	public Article(int id, String name, String description, SousFamille subfamilly) {
		super();
		this.id = id;
		this.name = name;
		this.subfamilly = subfamilly;
		this.description = description;
	}
	
	public Set<Materiel> getMateriels(){
		if (materiels == null ) materiels = new HashSet<>();
		return materiels;
	}
}
