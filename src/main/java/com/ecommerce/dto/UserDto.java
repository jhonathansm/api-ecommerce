package com.ecommerce.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Embedded;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.ecommerce.domain.Endereco;
import com.ecommerce.domain.Pedidos;
import com.ecommerce.domain.enums.Permission;
import com.ecommerce.domain.Usuario;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserDto {

	@NotBlank(message = "Nome obrigatório")
	private String name;
	
	@Email(message = "E-mail obrigatório")
	private String email;
	
	@Size(min = 7, max = 15, message = "Password tamanho minímo 7 e máximo 15")
	private String password;
	
	@NotNull(message = "Permission é obrigatório")
	private Permission permission;

	@NotNull
	@Embedded
	private Endereco endereco;
	
	private List<Pedidos> pedidos = new ArrayList<Pedidos>();
	
	public Usuario transformToUser() {
		Usuario user = new Usuario(null, this.name, this.email, this.password, this.endereco, this.permission, null);
		return user;
	}
}
