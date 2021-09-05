package com.ecommerce.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;

import com.ecommerce.domain.Usuario;
import com.ecommerce.repository.UserRepository;
import com.ecommerce.service.util.HashUtil;
import com.ecommerce.exception.NotFoundException;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	public Usuario save(Usuario user) {
		String hash = HashUtil.getSecureHash(user.getPassword());
		user.setPassword(hash);
		Usuario createdUser = userRepository.save(user);
		return createdUser;
	}
	
	public List<Usuario> listAll() {
		List<Usuario> listUsers = userRepository.findAll();
		return listUsers;
	}
	
	public Usuario getById(Long id) {
		Optional<Usuario> result = userRepository.findById(id);
		return result.orElseThrow(() -> new NotFoundException("Usuário não encontrado com este ID = " + id));
	}
	
	public Usuario getByEmail(String email) {
		Optional<Usuario> result = userRepository.findByEmail(email);
		System.out.println(result);
		return result.orElseThrow(() -> new NotFoundException("Usuário não encontrado com este E-mail = " + email));

	}
	
	public Usuario login(String email, String password) {
		password = HashUtil.getSecureHash(password);
		Optional<Usuario> userResult = userRepository.login(email, password);
		return userResult.get();
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Usuario> result = userRepository.findByEmail(username);
		
		if(!result.isPresent()) throw new UsernameNotFoundException("Usuário não existe");
		
		Usuario user = result.get();
		List<GrantedAuthority> authorites = Arrays.asList(new SimpleGrantedAuthority("ROLE_" + user.getPermission().name()));
		User userSpring = new User(user.getEmail(), user.getPassword(), authorites);
		
		return userSpring;
	}
}
