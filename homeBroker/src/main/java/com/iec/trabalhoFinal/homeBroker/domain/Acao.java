package com.iec.trabalhoFinal.homeBroker.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document
public class Acao {

	@Id
	@ApiModelProperty(hidden=true)
	private String id;
	private String empresaId;
	private String acionistaId;
	private Long valor;	
	private Boolean aVenda;	
}
