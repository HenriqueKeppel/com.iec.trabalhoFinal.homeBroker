package com.iec.trabalhoFinal.homeBroker.services;

import java.util.Set;

import com.iec.trabalhoFinal.homeBroker.domain.Acao;

public interface IAcaoService {

	Set<Acao> getAllAcoes();
	Acao getById(String id);	
	Set<Acao> getAcoesByEmpresaId(String empresaId);
	Set<Acao> getAcoesByAcionistaId(String acionistaId);	
	//Acao createNewAcao(String prateleiraId, Acao acao);	
	Acao createNewAcao(Acao acao);
	Acao saveAcao(String id, Acao acao);	
}
