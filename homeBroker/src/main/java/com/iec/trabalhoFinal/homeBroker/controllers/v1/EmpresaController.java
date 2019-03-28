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

import com.iec.trabalhoFinal.homeBroker.domain.Empresa;
import com.iec.trabalhoFinal.homeBroker.services.IEmpresaService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("This id User API")
@RestController
@RequestMapping(EmpresaController.BASE_URL)
public class EmpresaController {

	public static final String BASE_URL = "/api/v1/empresas";

    private final IEmpresaService empresaService;

    public EmpresaController(IEmpresaService empresaService) {
        this.empresaService = empresaService;
    }    
    
    @ApiOperation(value="Cria nova empresa")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Empresa Post(@RequestBody Empresa empresa)
    {
    	return empresaService.createNewEmpresa(empresa);
    }
    
    @ApiOperation(value="Atualiza dados de uma empresa")
    @PatchMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public Empresa Patch(@PathVariable String id, @RequestBody Empresa empresa)
    {
    	return empresaService.saveEmpresa(id, empresa);
    }
    
    @ApiOperation(value="Remove uma empresa")
    @DeleteMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public void Delete(@PathVariable String id)
    {
    	empresaService.deleteById(id);
    }
    
    @ApiOperation(value="Obtem uma empresa")
    @GetMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public Empresa Get(@PathVariable String id)
    {
    	return empresaService.getById(id);
    }
    
    @ApiOperation(value="Obtem todos as empresas")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Set<Empresa> GetAll()
    {
    	return empresaService.getAllEmpresas();
    }	
}
