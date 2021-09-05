package com.ecommerce.dto;

import java.io.Serializable;

import com.ecommerce.domain.Usuario;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserLoginResponsedDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String token;
	private Long expireIn;
	private String tokenProvider;
	private Usuario username;
}
