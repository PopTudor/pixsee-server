package com.pixsee.services.data.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Tudor on 28-Oct-16.
 */
@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY,reason = "Invalid password!")
public class InvalidPasswordException extends RuntimeException {}
