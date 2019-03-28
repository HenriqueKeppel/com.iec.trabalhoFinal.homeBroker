package com.iec.trabalhoFinal.homeBroker.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.iec.trabalhoFinal.homeBroker.domain.Acao;

@Repository
public interface AcaoRepository extends MongoRepository<Acao, String>{
	
	List<Acao> findAll();
	Optional<Acao> findById(String id);
	List<Acao> findByEmpresaId(String id);
	List<Acao> findByAcionistaId(String id);
	//List<Acao> findForSale();
}
