package com.smemoLab.webapp.dao;

import java.util.List;

import com.smemoLab.webapp.entity.Postit;
import com.smemoLab.webapp.entity.Utente;

public interface UtenteDao {
List<Utente> getAllUtenti();
boolean salvaUtente(Utente utente);
Utente findByUsernameAndPassword(String username, String password);
Utente findByUsername(String username);
boolean update(Utente utente);
boolean remove(int idUtente);
List <Postit> findAllbyUtente(int idUtente);
public Utente findById(int idDaRicercare);
}
