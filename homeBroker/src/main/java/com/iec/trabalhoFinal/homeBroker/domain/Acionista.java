package com.iec.trabalhoFinal.homeBroker.domain;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document
public class Acionista {
	
	@Id
	@ApiModelProperty(hidden=true)
	private String id;
	private String name;
	private String Email;
	private Boolean ativo;	
	
	/*
	private List<Acao> acoes;
	
	public void acoesAdd(Acao acao) {
		this.acoes.add(acao);
	}
	
	public void acoesRemove(Acao acao) {
		this.acoes.remove(acao);
	}
	*/
}
