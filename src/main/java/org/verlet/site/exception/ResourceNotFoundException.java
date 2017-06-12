package org.verlet.site.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 该注解告诉spring，当抛出此异常会返回一个404 Not Found状态码
 * 没有添加此注解，抛出此异常通常会导致一个500 Internal Server Error
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1572849207618179911L;

}
