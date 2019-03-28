package com.iec.trabalhoFinal.homeBroker.services;

import java.util.Set;

import com.iec.trabalhoFinal.homeBroker.domain.Empresa;

public interface IEmpresaService {
	
	Set<Empresa> getAllEmpresas();
	Empresa getById(String id);	
	Empresa getByName(String nome);
	Empresa createNewEmpresa(Empresa empresa);
	Empresa saveEmpresa(String id, Empresa empresa);
	void deleteById(String id);	
}
