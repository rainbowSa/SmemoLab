package com.smemoLab.webapp.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Categoria {
	


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false)
	private String descrzione;
	
	@ManyToMany(mappedBy = "categorie")
	private List<Postit> postIt;
	
	
	//costruttore vuoto 
	public Categoria() {
		
	}


	public int getId() {
		return id;
	}


	public String getDescrzione() {
		return descrzione;
	}


	public void setId(int id) {
		this.id = id;
	}


	public void setDescrzione(String descrzione) {
		this.descrzione = descrzione;
	}


	public List<Postit> getPostIt() {
		return postIt;
	}


	public void setPostIt(List<Postit> postIt) {
		this.postIt = postIt;
	}
	
	@Override
	public String toString() {
		return "Categoria [descrzione=" + descrzione + "]";
	}


}
