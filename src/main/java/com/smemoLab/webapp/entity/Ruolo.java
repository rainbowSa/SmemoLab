package com.smemoLab.webapp.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Ruolo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false)
	private String descrizione;
	
	@OneToMany(mappedBy = "ruolo")
	private List<Utente> utenti;
	
	//costruttore vuoto 
	public Ruolo () {
		
	}
	
	public Ruolo(int id) {
		super();
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public List<Utente> getUtenti() {
		return utenti;
	}

	public void setUtenti(List<Utente> utenti) {
		this.utenti = utenti;
	}
	
	
	
	
	
}
