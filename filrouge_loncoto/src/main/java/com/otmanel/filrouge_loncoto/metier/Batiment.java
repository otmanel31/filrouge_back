package com.otmanel.filrouge_loncoto.metier;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter @NoArgsConstructor @ToString(exclude = {"etages"})
public class Batiment {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String name;
	@ManyToOne
	private Site site;
	@OneToMany(mappedBy="batiment")
	private Set<Etage> etages;
	
	public Batiment(int id, String name) {
		this.id = id;
		this.name = name;
	}
}
