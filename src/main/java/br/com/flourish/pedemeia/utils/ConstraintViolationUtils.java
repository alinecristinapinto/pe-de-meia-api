package br.com.flourish.pedemeia.utils;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public final class ConstraintViolationUtils {
	
	private static final String ERRO_REQUEST_NULL = "Request nula.";

	private ConstraintViolationUtils() {
	}

	public static String verificar(Object request) {
		if(request == null)
			return ERRO_REQUEST_NULL;
			
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();

		Set<ConstraintViolation<Object>> violacoes = validator.validate(request);

		return preencher(violacoes);
	}

	private static String preencher(Set<ConstraintViolation<Object>> violacoes) {
		StringBuilder observacoes = new StringBuilder();	
		violacoes.stream().forEach(violacao -> observacoes.append(violacao.getMessage()));

		return observacoes.toString();
	}

}
