package com.iec.trabalhoFinal.homeBroker.domain;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Message implements Serializable{

	private static final long serialVersionUID = 1;
	
	private String acionistaId;
	private String empresaId;
	private int qtd;
}
