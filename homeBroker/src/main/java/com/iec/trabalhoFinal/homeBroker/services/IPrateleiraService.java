package com.iec.trabalhoFinal.homeBroker.services;

import com.iec.trabalhoFinal.homeBroker.domain.Acao;
import com.iec.trabalhoFinal.homeBroker.domain.Prateleira;
import java.util.Set;

public interface IPrateleiraService {

	Prateleira createNewPrateleira(Prateleira prateleira);
	Set<Prateleira> getAllPrateleiras();
	Prateleira getById(String id);
	Set<Acao> obterAcoesAVenda(String prateleiraId);
	Set<Acao> obterAcoesAVendaPorEmpresaId(String prateleiraId, String empresaId);
	Acao createNewAcao(String prateleiraId, Acao acao);
	void colocarAcaoAVenda(String prateleiraId, String acionistaId, Acao acao);
	void solicitarCompra(String prateleiraId, String acionistaId, Acao acao);
	void concluirCompra(String prateleiraId, String acionistaId, Acao acao);	
}
