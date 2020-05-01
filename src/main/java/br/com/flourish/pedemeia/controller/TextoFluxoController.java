package br.com.flourish.pedemeia.controller;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.flourish.pedemeia.controller.response.TextoFluxoResponse;
import br.com.flourish.pedemeia.service.TextoFluxoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = { "Fluxos" })
@RequestMapping("/fluxos")
public class TextoFluxoController {
	
	@Autowired 
	private TextoFluxoService service;
	
	@GetMapping(value="/buscar/{nomeParametro}")
	@ApiOperation(value="Busca os textos relacionados a um fluxo determinado pelo parâmetro")
    public ResponseEntity<TextoFluxoResponse> buscar(@PathVariable @NotNull String nomeParametro) {
		
		return ResponseEntity.status(0).body(service.buscar(nomeParametro));
    }

}
