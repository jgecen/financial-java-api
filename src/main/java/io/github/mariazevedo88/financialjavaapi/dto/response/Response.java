package io.github.mariazevedo88.financialjavaapi.dto.response;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Class that implements a generic response to the API endpoints.
 * 
 * @author Mariana Azevedo
 * @since 01/04/2020
 *
 * @param <T>
 */
@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response<T> {

	private T data;
	private List<ResponseError> errors = new ArrayList<>();
	
	
	/**
	 * Method that formats an error message to the HTTP response.
	 * 
	 * @author Mariana Azevedo
	 * @since 01/04/2020
	 * 
	 * @param msgError
	 */
    public void addErrorMsgToResponse(String msgError) {
        ResponseError error = new ResponseError()
        		.setDetails(msgError)
                .setTimestamp(LocalDateTime.now());
        errors.add(error);
    }


	public void errors(Errors failErrors) {
		List<FieldError> fieldErrors = failErrors.getFieldErrors();
		
		for (FieldError objectError : fieldErrors) {
				System.out.println("objectError.getField() != null " + objectError.getField());
		        ResponseError error = new ResponseError()
		        		.setDetails(objectError.getDefaultMessage())
		                .setTimestamp(LocalDateTime.now())
		                .setField(objectError.getField());
		        errors.add(error);	
			
		}
		
	}
}
