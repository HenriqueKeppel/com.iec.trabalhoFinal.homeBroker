package com.iec.trabalhoFinal.homeBroker.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.iec.trabalhoFinal.homeBroker.domain.Empresa;

@Repository
public interface EmpresaRepository extends MongoRepository<Empresa, String>{

	List<Empresa> findAll();
	Optional<Empresa> findById(String id);
	Optional<Empresa> findByName(String name);	
}
