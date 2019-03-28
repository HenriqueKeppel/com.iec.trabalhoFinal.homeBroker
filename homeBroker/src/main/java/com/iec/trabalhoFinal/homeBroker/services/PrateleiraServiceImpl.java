package com.iec.trabalhoFinal.homeBroker.services;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.iec.trabalhoFinal.homeBroker.domain.Acao;
import com.iec.trabalhoFinal.homeBroker.domain.Acionista;
import com.iec.trabalhoFinal.homeBroker.domain.Prateleira;
import com.iec.trabalhoFinal.homeBroker.repository.PrateleiraRepository;
import com.iec.trabalhoFinal.homeBroker.repository.AcionistaRepository;

public class PrateleiraServiceImpl implements IPrateleiraService {

	private PrateleiraRepository prateleiraRepository;
	private AcionistaRepository acionistaRepository;
	
	public PrateleiraServiceImpl(PrateleiraRepository prateleiraRepository,
			AcionistaRepository acionistaRepository) {
		this.prateleiraRepository = prateleiraRepository;
		this.acionistaRepository = acionistaRepository;
	}
	
	public Prateleira createNewPrateleira(Prateleira prateleira) {
		if (!prateleiraRepository.findById(prateleira.getId()).isPresent())
			return prateleiraRepository.save(prateleira);
		else
			throw new IllegalArgumentException("Prateleira já existe com o id: " + prateleira.getId());
	}
	
	public Set<Prateleira> getAllPrateleiras(){
		Set<Prateleira> prateleiras = new HashSet<>();		
		this.prateleiraRepository.findAll().iterator().forEachRemaining(prateleiras::add);
		return prateleiras;
	}
	
	@Override
	public Prateleira getById(String id) {
		
		Optional<Prateleira> prateleiraOptional = prateleiraRepository.findById(id);
		if(!prateleiraOptional.isPresent()) {
			throw new IllegalArgumentException("Prateleira não encontrada para o Id: " + id);
		}
		return prateleiraOptional.get();
	}
	
	public Set<Acao> obterAcoesAVenda(String prateleiraId){
		
		Set<Acao> acoes = new HashSet<>();
		Optional<Prateleira> prateleiraOptional = prateleiraRepository.findById(prateleiraId);		
		
		if (!prateleiraOptional.isPresent())
			throw new IllegalArgumentException("Prateleira com o id não encontrado: " + prateleiraId);
		else {
			prateleiraOptional.get().getAcoesAVenda().
					iterator().forEachRemaining(acoes::add);
		}		
		return acoes;
	}
	
	public Set<Acao> obterAcoesAVendaPorEmpresaId(String prateleiraId, String empresaId){
		
		Set<Acao> acoes = new HashSet<>();
		Optional<Prateleira> prateleiraOptional = prateleiraRepository.findById(prateleiraId);
		
		if (!prateleiraOptional.isPresent())
			throw new IllegalArgumentException("Prateleira com o id não encontrado: " + prateleiraId);
		else {
			prateleiraOptional.get().getAcoesAVenda().
					stream().filter(x -> x.getEmpresaId() == empresaId).
					iterator().forEachRemaining(acoes::add);
		}
		return acoes;
	}
	
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
		
	public void colocarAcaoAVenda(String prateleiraId, String acionistaId, Acao acao) {
		
		Optional<Prateleira> prateleiraOptional = prateleiraRepository.findById(prateleiraId);		
		Optional<Acionista> acionistaOptional = acionistaRepository.findById(acionistaId);
		
		if (!prateleiraOptional.isPresent())
			throw new IllegalArgumentException("Prateleira com o id não encontrado: " + prateleiraId);
		else {
			Acionista acionista = acionistaOptional.get();
			Prateleira prateleira = prateleiraOptional.get();
			
			//acionista.acoesRemove(acao);
			
			acionistaRepository.save(acionista);
			
			prateleira.acoesAVendaAdd(acao);
			prateleiraRepository.save(prateleira);
		}
	}
	
	public void concluirCompra(String prateleiraId, String acionistaId, Acao acao) {
		
		Optional<Prateleira> prateleiraOptional = prateleiraRepository.findById(prateleiraId);
		Optional<Acionista> acionistaOptional = acionistaRepository.findById(acionistaId); 
		
		if (!prateleiraOptional.isPresent())
			throw new IllegalArgumentException("Prateleira com o id não encontrado: " + prateleiraId);
		else {
			Acionista acionista = acionistaOptional.get(); 						
			Prateleira prateleira = prateleiraOptional.get();
			
			prateleira.acoesAVendaRemove(acao);
			
			prateleiraRepository.save(prateleira);
			
			acao.setAcionistaId(acionistaId);
			//acionista.acoesAdd(acao);
			
			acionistaRepository.save(acionista);
			
			// TODO
			// Enviar o email confirmando
		}
	}
	
	public void solicitarCompra(String prateleiraId, String acionistaId, Acao acao) {
		
		throw new UnsupportedOperationException();
	}
}
