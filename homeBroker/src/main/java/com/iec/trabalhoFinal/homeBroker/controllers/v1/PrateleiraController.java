/*package com.iec.trabalhoFinal.homeBroker.controllers.v1;

import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.iec.trabalhoFinal.homeBroker.domain.Acao;
import com.iec.trabalhoFinal.homeBroker.domain.Prateleira;
import com.iec.trabalhoFinal.homeBroker.services.IAcaoService;
import com.iec.trabalhoFinal.homeBroker.services.IPrateleiraService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("This id User API")
@RestController
@RequestMapping(PrateleiraController.BASE_URL)
public class PrateleiraController {

	public static final String BASE_URL = "/api/v1/prateleira";

    private final IPrateleiraService prateleiraService;
    private final IAcaoService acaoService;

    public PrateleiraController(IPrateleiraService prateleiraService,
    		IAcaoService acaoService) {
        this.prateleiraService = prateleiraService;
        this.acaoService = acaoService;
    }
    
    @ApiOperation(value="Cria nova prateleira")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Prateleira Post(@RequestBody Prateleira prateleira)
    {
    	return prateleiraService.createNewPrateleira(prateleira);
    }
    
    @ApiOperation(value="Obtem todas as prateleiras")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Set<Prateleira> GetAll()
    {
    	return prateleiraService.getAllPrateleiras();
    }
    
    @ApiOperation(value="Obtem prateleira por id")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Prateleira Get(@PathVariable String id)
    {
    	return prateleiraService.getById(id);
    }
    
    @ApiOperation(value="Cria nova acao")
    @PostMapping({"/{prateleiraId}/acoes"})
    @ResponseStatus(HttpStatus.CREATED)
    public Acao Post(@PathVariable String prateleiraId, @RequestBody Acao acao)
    {
    	return acaoService.createNewAcao(prateleiraId, acao);
    }
    
    @ApiOperation(value="Obtem todas as acoes a venda na prateleira")
    @GetMapping({"/prateleira/{prateleiraId}/acoes"})
    @ResponseStatus(HttpStatus.OK)
    public Set<Acao> getAcoesByPrateleiraId(@PathVariable String prateleiraId)
    {
    	return prateleiraService.obterAcoesAVenda(prateleiraId);
    }
    
    @ApiOperation(value="Obtem todas as acoes a venda de uma empresa na prateleira")
    @GetMapping({"/{prateleiraId}/empresa/{empresaId}/acoes"})
    @ResponseStatus(HttpStatus.OK)
    public Set<Acao> getAcoesByEmpresaId(@PathVariable String prateleiraId, @PathVariable String empresaId)
    {
    	return prateleiraService.obterAcoesAVendaPorEmpresaId(prateleiraId, empresaId);
    }
    
    @ApiOperation(value="Coloca uma acao a venda")
    @PostMapping({"/{prateleiraId}/acionista/{acionistaId}/acoes/vender"})
    @ResponseStatus(HttpStatus.OK)
    public void vender(@PathVariable String prateleiraId,    		 
    		@PathVariable String acionistaId, 
    		@RequestBody Acao acao)
    {
    	prateleiraService.colocarAcaoAVenda(prateleiraId, acionistaId, acao);
    }
    
    @ApiOperation(value="Solicita a compra de uma acao")
    @PostMapping({"/{prateleiraId}/acionista/{acionistaId}/acoes/comprar"})
    @ResponseStatus(HttpStatus.OK)
    public void comprar(@PathVariable String prateleiraId,    		 
    		@PathVariable String acionistaId, 
    		@RequestBody Acao acao)
    {
    	// TODO: Endpoint que ira colocar itens na fila
    	prateleiraService.solicitarCompra(prateleiraId, acionistaId, acao);
    }
    
    @ApiOperation(value="Conclui a compra de uma acao")
    @PostMapping({"/{prateleiraId}/acionista/{acionistaId}/acoes/concluirCompra"})
    @ResponseStatus(HttpStatus.OK)
    public void concluirCompra(@PathVariable String prateleiraId,    		 
    		@PathVariable String acionistaId, 
    		@RequestBody Acao acao)
    {
    	// TODO: Endpoint que ira retirar itens da fila
    	prateleiraService.concluirCompra(prateleiraId, acionistaId, acao);
    }    
}
*/