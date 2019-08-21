package br.com.zeroquo.appPessoa.repository;

import br.com.zeroquo.appPessoa.domain.Pessoa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    Page<Pessoa> findByNomeContaining(String nome, Pageable pageable);
}
