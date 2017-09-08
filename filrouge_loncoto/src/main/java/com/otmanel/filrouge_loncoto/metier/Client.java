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
@Getter  @Setter @NoArgsConstructor @ToString(exclude= {"materiels"})
public class Client {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String name;
	@OneToMany(mappedBy="client")
	private Set<Materiel> materiels;
	
	public Client(int id, String name) {
		this.id = id;
		this.name = name;
	}
}
