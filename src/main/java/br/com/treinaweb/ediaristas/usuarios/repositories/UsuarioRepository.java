package br.com.treinaweb.ediaristas.usuarios.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.treinaweb.ediaristas.usuarios.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
