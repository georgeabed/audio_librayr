package com.ipiecoles.java.audio.exception;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(EntityNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String handleEntityNotFoundException(
			EntityNotFoundException entityNotFoundException) {
		return entityNotFoundException.getMessage();
	}

	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String handleIllegalArgumentException(
			IllegalArgumentException illegalArgumentException) {
		return illegalArgumentException.getMessage();
	}

	@ExceptionHandler(ArtistException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String handleArtistException(
			ArtistException artistException) {
		return artistException.getMessage();
	}

	@ExceptionHandler(ConflictException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	public String handleConflictException(
			ConflictException conflictException) {
		return conflictException.getMessage();
	}


}


