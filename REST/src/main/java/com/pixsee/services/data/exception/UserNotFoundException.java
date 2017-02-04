package com.pixsee.services.data.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Tudor on 29-Oct-16.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND,reason = "User not found!")
public class UserNotFoundException extends RuntimeException{
}
