package com.iec.trabalhoFinal.homeBroker.services;

import java.util.HashSet;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.iec.trabalhoFinal.homeBroker.repository.AcaoRepository;
import com.iec.trabalhoFinal.homeBroker.repository.PrateleiraRepository;
import com.iec.trabalhoFinal.homeBroker.domain.Acao;
import com.iec.trabalhoFinal.homeBroker.domain.Prateleira;

import java.util.Optional;
import java.util.Set;

@Service
public class AcaoServiceImpl implements IAcaoService {

	private AcaoRepository acaoRepository;
	//private PrateleiraRepository prateleiraRepository;
	
	public AcaoServiceImpl(AcaoRepository acaoRepository) {			
			//PrateleiraRepository prateleiraRepository) {
		this.acaoRepository = acaoRepository;
//		this.prateleiraRepository = prateleiraRepository;
	}
	
	@Override
	public Set<Acao> getAllAcoes() {
		Set<Acao> acoes = new HashSet<>();		
		this.acaoRepository.findAll().iterator().forEachRemaining(acoes::add);
		return acoes;
	}
	
	@Override
	public Acao getById(String id) {
		
		Optional<Acao> acaoOptional = acaoRepository.findById(id);
		if(!acaoOptional.isPresent()) {
			throw new IllegalArgumentException("Acao não encontrada para o Id: " + id);
		}
		return acaoOptional.get();
	}
	
	@Override
	public Set<Acao> getAcoesByEmpresaId(String empresaId) {
		
		Set<Acao> acoes = new HashSet<>();		
		this.acaoRepository.findByEmpresaId(empresaId).iterator().forEachRemaining(acoes::add);
		return acoes;
	}
	
	@Override
	public Set<Acao> getAcoesByAcionistaId(String acionistaId) {
		
		Set<Acao> acoes = new HashSet<>();		
		this.acaoRepository.findByAcionistaId(acionistaId).iterator().forEachRemaining(acoes::add);
		return acoes;
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public Acao createNewAcao(Acao acao) {
		return acaoRepository.save(acao);		
	}
	
	/*
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public Acao createNewAcao(String prateleiraId, Acao acao) {
		
		Optional<Prateleira> prateleiraOpt = prateleiraRepository.findById(prateleiraId);
		
		if (prateleiraOpt.isPresent()) {
			Prateleira prateleira = prateleiraOpt.get();
			
			prateleira.acoesAVendaAdd(acao);
			
			prateleiraRepository.save(prateleira);
		}
		else
			throw new IllegalArgumentException("Prateleira com o id não encontrado: " + prateleiraId);
		return acao;
	}
	*/
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public Acao saveAcao(String id, Acao acao) {
		
		acao.setId(id);
		Acao acaoSaved = acaoRepository.save(acao);
		return acaoSaved;
	}		
}
