package com.iec.trabalhoFinal.homeBroker.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.iec.trabalhoFinal.homeBroker.domain.Acionista;

@Repository
public interface AcionistaRepository extends MongoRepository<Acionista, String>{
	
	List<Acionista> findAll();
	Optional<Acionista> findById(String id);
	Optional<Acionista> findByName(String name);
}
