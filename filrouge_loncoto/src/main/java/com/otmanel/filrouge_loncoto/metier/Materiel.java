package com.otmanel.filrouge_loncoto.metier;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter @NoArgsConstructor @ToString(exclude= {"article", "intervention", "client", "salle"})
public class Materiel {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String serialNumber;
	@OneToOne
	private Intervention intervention;
	@ManyToOne
	private Article article;
	@ManyToOne
	private Client client;
	@ManyToOne
	private Salle salle;
	
	public Materiel(int id, String serialNumber) {
		this.id = id;
		this.serialNumber = serialNumber;
	}
	
}
