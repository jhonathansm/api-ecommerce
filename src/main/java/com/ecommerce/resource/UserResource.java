package com.ecommerce.resource;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.security.core.userdetails.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.domain.Usuario;
import com.ecommerce.dto.UserDto;
import com.ecommerce.dto.UserLoginDto;
import com.ecommerce.dto.UserLoginResponsedDto;
import com.ecommerce.security.JwtManager;
import com.ecommerce.service.UserService;

@RestController
@RequestMapping("users")
public class UserResource {

	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthenticationManager auth;
	
	@Autowired
	private JwtManager jwtManager;
	
	
	@PostMapping
	public ResponseEntity<Usuario> save(@Valid @RequestBody UserDto userDto) {
		Usuario user = userDto.transformToUser();
		Usuario createdUser = userService.save(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
	}
	
	@GetMapping
	public ResponseEntity<List<Usuario>> listAll() {
		List<Usuario> users = userService.listAll();
		return ResponseEntity.ok(users);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> getById(@PathVariable Long id){
		Usuario user = userService.getById(id);
		return ResponseEntity.ok(user);
	}
	
	@PostMapping("/login")
	public ResponseEntity<UserLoginResponsedDto> login(@Valid @RequestBody UserLoginDto user) {
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
		Authentication auths = auth.authenticate(token);
		SecurityContextHolder.getContext().setAuthentication(auths);
		
		User  userSpring = 
				(User) auths.getPrincipal();
		
		String email = userSpring.getUsername();
		List<String> roles = userSpring.getAuthorities().stream().map(authority -> authority.getAuthority())
				.collect(Collectors.toList());
		
		return ResponseEntity.ok(jwtManager.createdToken(email, roles));
	}
	
}
