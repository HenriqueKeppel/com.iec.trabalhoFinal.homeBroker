package com.iec.trabalhoFinal.homeBroker.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.iec.trabalhoFinal.homeBroker.domain.Empresa;
import com.iec.trabalhoFinal.homeBroker.repository.EmpresaRepository;
import java.util.Optional;

@Service
public class EmpresaServiceImpl implements IEmpresaService {
	
	private EmpresaRepository empresaRepository;
	
	public EmpresaServiceImpl(EmpresaRepository empresaRepository) {
		this.empresaRepository = empresaRepository;
	}	
	
	@Override
	public Set<Empresa> getAllEmpresas() {
		Set<Empresa> empresas = new HashSet<>();		
		this.empresaRepository.findAll().iterator().forEachRemaining(empresas::add);
		return empresas;		
	}
	
	@Override
	public Empresa getById(String id) {
		
		Optional<Empresa> empresaOptional = empresaRepository.findById(id);
		if(!empresaOptional.isPresent()) {
			throw new IllegalArgumentException("Empresa não encontrada para o Id: " + id);
		}
		return empresaOptional.get();
	}
	
	@Override
	public Empresa getByName(String name) {
		
		Optional<Empresa> empresaOptional = empresaRepository.findByName(name);
		if(!empresaOptional.isPresent()) {
			throw new IllegalArgumentException("Empresa não encontrada para o nome: " + name);
		}
		return empresaOptional.get();
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public Empresa createNewEmpresa(Empresa empresa) {
				
		if (!empresaRepository.findByName(empresa.getName()).isPresent()) {
			return empresaRepository.save(empresa);
		}
		else {
			throw new IllegalArgumentException("Empresa já existe com o nome: " + empresa.getName());
		}		
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public Empresa saveEmpresa(String id, Empresa empresa) {
		
		empresa.setId(id);
		Empresa empresaSaved = empresaRepository.save(empresa);
		return empresaSaved;
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void deleteById(String id) {
		empresaRepository.deleteById(id);
	}
}
