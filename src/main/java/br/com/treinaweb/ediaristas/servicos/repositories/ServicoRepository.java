package br.com.treinaweb.ediaristas.servicos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.treinaweb.ediaristas.servicos.models.Servico;

public interface ServicoRepository extends JpaRepository<Servico, Long> {

}
