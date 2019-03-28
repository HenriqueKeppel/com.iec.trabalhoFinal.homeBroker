package com.iec.trabalhoFinal.homeBroker.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.iec.trabalhoFinal.homeBroker.domain.Prateleira;

@Repository
public interface PrateleiraRepository extends MongoRepository<Prateleira, String>{
	
	Optional<Prateleira> findById(String id);
}
