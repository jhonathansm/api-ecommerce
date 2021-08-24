package com.ecommerce.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.ecommerce.domain.Usuario;
import com.ecommerce.exception.NotFoundException;
import com.ecommerce.repository.UserRepository;


@Component("accessManager")
public class AccessManager {

	@Autowired
	private UserRepository userRepository;
	
	public boolean isOwner(Long id) {
		String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Optional<Usuario> result = userRepository.findByEmail(email);
		
		if(!result.isPresent()) throw new NotFoundException("Usuário não encontrado com o email " + email);
		
		Usuario user = result.get();
		
		return user.getId() == id; 
	}
	
}
