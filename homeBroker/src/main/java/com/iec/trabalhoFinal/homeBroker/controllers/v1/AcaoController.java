package com.iec.trabalhoFinal.homeBroker.controllers.v1;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.iec.trabalhoFinal.homeBroker.domain.Acao;
import com.iec.trabalhoFinal.homeBroker.domain.Message;
import com.iec.trabalhoFinal.homeBroker.services.IAcaoService;
import com.iec.trabalhoFinal.homeBroker.services.IMessageService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("This id User API")
@RestController
@RequestMapping(AcaoController.BASE_URL)
public class AcaoController {

	public static final String BASE_URL = "/api/v1/acoes";

    private final IAcaoService acaoService;
    private final IMessageService messageService;

    public AcaoController(IAcaoService acaoService, IMessageService messageService) {
        this.acaoService = acaoService;
        this.messageService = messageService;
    }
    
    @ApiOperation(value="Cria nova acao")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Acao Post(@RequestBody Acao acao)
    {
    	return acaoService.createNewAcao(acao);
    }
    
    @ApiOperation(value="Cria novas acoes")
    @PostMapping("/createRange/{qtd}")
    @ResponseStatus(HttpStatus.CREATED)
    public List<Acao> createRange(@RequestBody Acao acao,@PathVariable int qtd) {
    	
    	List<Acao> acoes = new ArrayList<Acao>();
    	
    	for(int i = 0; i < qtd; i++) {
    		
    		// copia a acao
    		Acao novaAcao = new Acao();
    		novaAcao.setAcionistaId("");
    		novaAcao.setAVenda(acao.getAVenda());
    		novaAcao.setEmpresaId(acao.getEmpresaId());
    		novaAcao.setValor(acao.getValor());
    		
    		Acao acaoCriada = acaoService.createNewAcao(novaAcao);
    		acoes.add(acaoCriada);
    	}    	
    	return acoes;
    }
    
    @ApiOperation(value="Obtem todas as acoes")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Set<Acao> GetAll()
    {
    	return acaoService.getAllAcoes();
    }
    
    @ApiOperation(value="Obtem todas as acoes de uma empresa")
    @GetMapping({"/empresa/{empresaId}"})
    @ResponseStatus(HttpStatus.OK)
    public Set<Acao> getAcoesByEmpresaId(@PathVariable String empresaId)
    {
    	return acaoService.getAcoesByEmpresaId(empresaId);
    }
    
    @ApiOperation(value="Obtem todas as acoes de um acionista")
    @GetMapping({"/acionista/{acionistaId}"})
    @ResponseStatus(HttpStatus.OK)
    public Set<Acao> getAcoesByAcionistaId(@PathVariable String acionistaId)
    {
    	return acaoService.getAcoesByAcionistaId(acionistaId);
    }
    
    // colocar acao a venda
    @ApiOperation(value="Coloca ações a venda")
    @PatchMapping({"/acionista/{acionistaId}/empresa/{empresaId}/vender/{qtd}"})
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void venderAcoes(@PathVariable String acionistaId, @PathVariable String empresaId, @PathVariable int qtd) {
    	
    	// obtem todas as ações de um acionista que são de uma empresa
    	Set<Acao> acoes = acaoService.getAcoesByAcionistaId(acionistaId);
    	int qtdAVenda = 0;
    	
    	for (Acao acao : acoes) {
    		if (qtdAVenda == qtd)
    			break;
    		
    		if (acao.getEmpresaId().equals(empresaId)) {
    			acao.setAVenda(true);    		
        		acaoService.saveAcao(acao.getId(), acao);
        		qtdAVenda++;
    		}	
    	}    		
    }    
   
    // Concluir Compra de ação
    @ApiOperation(value="Comprar ações a venda")
    @PatchMapping({"/acionista/{acionistaId}/empresa/{empresaId}/comprar/{qtd}"})
    @ResponseStatus(HttpStatus.CREATED)
    public String Comprar(@PathVariable String acionistaId, @PathVariable String empresaId, @PathVariable int qtd) {
    	
    	// enfileira no rabbitMq 
    	// a logica pro listener
    	Message message = new Message();
    	message.setAcionistaId(acionistaId);
    	message.setEmpresaId(empresaId);
    	message.setQtd(qtd);
    	
    	messageService.sendMessage(message);
    	return "Message sent";
    	
    	/*
    	// procura por acoes da empresa a venda -- metodo a ser executado ao desenfileirar
    	Set<Acao> acoes = acaoService.getAcoesByEmpresaId(empresaId);
    	int qtdComprada = 0;
    	
    	for (Acao acao : acoes) {    		
    		if (qtdComprada == qtd)
    			break;
    		
    		if (acao.getAVenda() == true) {
    			acao.setAcionistaId(acionistaId);
    			acao.setAVenda(false);
    			
    			acaoService.saveAcao(acao.getId(), acao);
    			qtdComprada++;
    		}    		
    	}  	    	
    	return qtdComprada;
    	*/
    }
}
