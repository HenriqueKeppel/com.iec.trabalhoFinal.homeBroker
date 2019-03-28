package com.iec.trabalhoFinal.homeBroker.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import com.iec.trabalhoFinal.homeBroker.domain.Acionista;
import com.iec.trabalhoFinal.homeBroker.repository.AcionistaRepository;
import com.iec.trabalhoFinal.homeBroker.services.IAcionistaService;

@Service
public class AcionistaServiceImpl implements IAcionistaService {
	
	private AcionistaRepository acionistaRepository;
	
	public AcionistaServiceImpl(AcionistaRepository acionistaRepository) {
		this.acionistaRepository = acionistaRepository;
	}	
		
	@Override
	public Set<Acionista> getAllAcionistas() {
		
		Set<Acionista> acionistas = new HashSet<>();
		this.acionistaRepository.findAll().iterator().forEachRemaining(acionistas::add);			
		return acionistas;			
	}
	
	@Override
	public Acionista getById(String id) {
		
		Optional<Acionista> acionistaOptional = acionistaRepository.findById(id);
		
		if (!acionistaOptional.isPresent()) {
			throw new IllegalArgumentException("Acionista não encontrado para id de valor: " + id.toString());
		}
		
		return acionistaOptional.get();		
	}
	
	@Override
	public Acionista getByName(String name) {
		
		Optional<Acionista> acionistaOptional = acionistaRepository.findByName(name);
		
		if (!acionistaOptional.isPresent()) {
			throw new IllegalArgumentException("Acionista não encontrado para o nome: " + name);
		}
		
		return acionistaOptional.get();		
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public Acionista createNewAcionista(Acionista acionista) {
		
		if (!acionistaRepository.findByName(acionista.getName()).isPresent()) {
			return acionistaRepository.save(acionista);
		}
		else {
			throw new IllegalArgumentException("Acionista já existe com o nome: " + acionista.getName());
		}		
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public Acionista saveAcionista(String id, Acionista acionista) {
		
		acionista.setId(id);
		Acionista acionistaSaved = acionistaRepository.save(acionista);
		return acionistaSaved;
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void deleteById(String id) {
		acionistaRepository.deleteById(id);
	}	
}
