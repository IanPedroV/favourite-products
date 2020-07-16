package br.com.luizalabs.favourite.favouriteproducts.config.validation;

public class ValidationErrorDto {
	
	private final String field;
	private final String error;
	
	public ValidationErrorDto(String field, String error) {
		this.field = field;
		this.error = error;
	}

	public String getField() {
		return field;
	}

	public String getError() {
		return error;
	}
	
	

}
