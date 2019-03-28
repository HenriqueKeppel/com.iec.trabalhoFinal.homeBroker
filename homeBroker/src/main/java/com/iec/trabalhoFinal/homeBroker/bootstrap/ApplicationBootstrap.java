package com.iec.trabalhoFinal.homeBroker.bootstrap;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.iec.trabalhoFinal.homeBroker.domain.Acao;
import com.iec.trabalhoFinal.homeBroker.domain.Acionista;
import com.iec.trabalhoFinal.homeBroker.domain.Empresa;
import com.iec.trabalhoFinal.homeBroker.repository.AcaoRepository;
import com.iec.trabalhoFinal.homeBroker.repository.AcionistaRepository;
import com.iec.trabalhoFinal.homeBroker.repository.EmpresaRepository;

@Component
public class ApplicationBootstrap implements ApplicationListener<ContextRefreshedEvent> {

	private EmpresaRepository empresaRepository;
	private AcaoRepository acaoRepository;
	private AcionistaRepository acionistaRepository;
	
	public ApplicationBootstrap(EmpresaRepository empresaRepository,
			AcaoRepository acaoRepository,
			AcionistaRepository acionistaRepository)
	{
		this.empresaRepository = empresaRepository;
		this.acaoRepository = acaoRepository;
		this.acionistaRepository = acionistaRepository;
	}
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		if (empresaRepository.count() == 0L) {
			empresaRepository.deleteAll();
			acaoRepository.deleteAll();
			loadEmpresa();
		}					
				
		if (acionistaRepository.count() == 0L) {
			acionistaRepository.deleteAll();
			loadAcionista();
		}	
	}
	
	private void loadEmpresa() {
		
		Empresa emp1 = new Empresa();
		
		emp1.setAtivo(true);
		emp1.setName("AWS");
		emp1.setId("1234566789");
		
		empresaRepository.save(emp1);		
		
		for(int i = 0; i < 5; i++) {
			Acao acao = new Acao();
			
			acao.setAcionistaId("");
			acao.setAVenda(true);
			acao.setEmpresaId("1234566789");
			acao.setValor((long)10);
			
			acaoRepository.save(acao);
		}
	}
	
	private void loadAcionista() {
		
		Acionista ac1 = new Acionista();
		ac1.setAtivo(true);
		ac1.setEmail("emailExample@mail.com");
		ac1.setName("Exemplo");
		
		acionistaRepository.save(ac1);
	}
	
}
