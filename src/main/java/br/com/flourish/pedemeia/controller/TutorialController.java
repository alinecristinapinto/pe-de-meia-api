package br.com.flourish.pedemeia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.flourish.pedemeia.controller.response.BuscarTutoriaisResponse;
import br.com.flourish.pedemeia.controller.response.ResponseDTO;
import br.com.flourish.pedemeia.exception.BusinessException;
import br.com.flourish.pedemeia.service.TutorialService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = { "Tutorial" })
@RequestMapping("/tutoriais")
public class TutorialController {

	@Autowired 
	private TutorialService service;
	
	private static final String SUCESSO = "Sucesso";
	
	@GetMapping(value="/buscar")
	@ApiOperation(value="Busca todos os tutoriais")
    public ResponseEntity<ResponseDTO<Object>> buscar() {
		ResponseDTO<Object> response = new ResponseDTO<>();
		BuscarTutoriaisResponse tutoriais = new BuscarTutoriaisResponse();
		
		try {
			tutoriais = service.buscar();
			response.setStatus(HttpStatus.OK.value());
			response.setMessage(SUCESSO);
			response.setResult(tutoriais);			
		} catch(BusinessException ex) {
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			response.setMessage(ex.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
