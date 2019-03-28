package com.iec.trabalhoFinal.homeBroker.domain;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document
public class Prateleira {

	@Id
	private String id;
	private List<Acao> acoesAVenda;
	
	public void acoesAVendaAdd(Acao acao) {
		this.acoesAVenda.add(acao);		
	}	
	
	public void acoesAVendaRemove(Acao acao) {
		this.acoesAVenda.remove(acao);
	}
}
