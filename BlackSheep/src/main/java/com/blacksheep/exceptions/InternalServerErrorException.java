package com.blacksheep.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Internal Server Error")
public class InternalServerErrorException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
