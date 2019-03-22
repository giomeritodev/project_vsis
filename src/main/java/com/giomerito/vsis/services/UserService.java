package com.giomerito.vsis.services;

import org.springframework.security.core.context.SecurityContextHolder;

import com.giomerito.vsis.security.UserSS;

public class UserService {

	/**
	 * Método para retornar o usuario que esta logado
	 * 
	 * @return
	 */
	public static UserSS authenticated() {
		try {
			return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			return null;
		}
	}
}
