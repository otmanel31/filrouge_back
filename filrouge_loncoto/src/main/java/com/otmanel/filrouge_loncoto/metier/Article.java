package com.otmanel.filrouge_loncoto.metier;

import java.util.Set;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import antlr.collections.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter  @Setter @NoArgsConstructor @ToString(exclude= {"materiels"})
public class Article {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String name;
	private SousFamille subfamilly;
	@OneToMany(mappedBy="article")
	private Set<Materiel> materiels;
	
	public Article(int id, String name, SousFamille subfamilly) {
		super();
		this.id = id;
		this.name = name;
		this.subfamilly = subfamilly;
	}
}
