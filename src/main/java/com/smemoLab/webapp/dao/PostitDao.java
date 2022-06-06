package com.smemoLab.webapp.dao;

import java.sql.Date;
import java.util.List;

import com.smemoLab.webapp.entity.Postit;

public interface PostitDao {
List<Postit>  getAllPostIt();
void AddPost(Postit post);
boolean aggiornaPostIt(Postit postIt);
boolean removeByTitolo(String titolo);
void eliminaPostIt(String titolo);
Postit findByTitolo(String titolo);
List <Postit> findByData(Date dataCreazione);
List <Postit> findByScadenza(Date dataScadenza);
List<Postit> getAllPostItTitolo(String titolo);
List<Postit> getAllPostItScadenza(Date dataScadenza);
List<Postit> findByCreazione(Date dataCreazione, int id);
List<Postit> findByScadenza(Date oggi, Date dataScadenza, int id);
List<Postit> findByTitolo(String titolo, int id);

}
