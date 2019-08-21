package br.com.zeroquo.appPessoa.service;

import br.com.zeroquo.appPessoa.domain.Pessoa;
import br.com.zeroquo.appPessoa.repository.PessoaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PessoaService {

    private PessoaRepository pessoaRepository;

    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    public Pessoa save(Pessoa person){
        return pessoaRepository.save(person);
    }

    public Pessoa update(Pessoa person){
        return pessoaRepository.save(person);
    }

    public Optional<Pessoa> findById(Long id){
        return pessoaRepository.findById(id);
    }

    public Page<Pessoa> findAll(Pageable pageable){
        return pessoaRepository.findAll(pageable);
    }

    public void deleteById(Long id){
        pessoaRepository.deleteById(id);
    }

    public Page<Pessoa> findByName(String nome, Pageable pageable){
        return pessoaRepository.findByNomeContaining(nome, pageable);
    }
}
