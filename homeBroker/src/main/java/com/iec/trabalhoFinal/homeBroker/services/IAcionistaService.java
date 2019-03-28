package com.iec.trabalhoFinal.homeBroker.services;

import java.util.Set;

import com.iec.trabalhoFinal.homeBroker.domain.Acionista;

public interface IAcionistaService {
	
	Set<Acionista> getAllAcionistas();	
	Acionista getById(String id);
	Acionista getByName(String name);
	Acionista createNewAcionista(Acionista acionista);	
	Acionista saveAcionista(String id, Acionista acionista);
	void deleteById(String id);	
}
