package com.iec.trabalhoFinal.homeBroker.controllers.v1;

import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.iec.trabalhoFinal.homeBroker.domain.Acionista;
import com.iec.trabalhoFinal.homeBroker.services.IAcionistaService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("This id User API")
@RestController
@RequestMapping(AcionistaController.BASE_URL)
public class AcionistaController {

	public static final String BASE_URL = "/api/v1/acionistas";

    private final IAcionistaService acionistaService;

    public AcionistaController(IAcionistaService acionistaService) {
        this.acionistaService = acionistaService;
    }    
    
    @ApiOperation(value="Cria novo acionista")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Acionista Post(@RequestBody Acionista acionista)
    {
    	return acionistaService.createNewAcionista(acionista);
    }
    
    @ApiOperation(value="Atualiza dados de um acionista")
    @PatchMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public Acionista Patch(@PathVariable String id, @RequestBody Acionista acionista)
    {
    	return acionistaService.saveAcionista(id, acionista);
    }
    
    @ApiOperation(value="Remove um acionista")
    @DeleteMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public void Delete(@PathVariable String id)
    {
    	acionistaService.deleteById(id);
    }
    
    @ApiOperation(value="Obtem um acionista")
    @GetMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public Acionista Get(@PathVariable String id)
    {
    	return acionistaService.getById(id);
    }
    
    @ApiOperation(value="Obtem todos os acionistas")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Set<Acionista> GetAll()
    {
    	return acionistaService.getAllAcionistas();
    }
}
