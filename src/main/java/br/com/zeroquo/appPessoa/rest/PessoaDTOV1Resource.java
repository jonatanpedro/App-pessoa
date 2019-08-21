package br.com.zeroquo.appPessoa.rest;

import br.com.zeroquo.appPessoa.domain.Pessoa;
import br.com.zeroquo.appPessoa.dto.PessoaDTOV1;
import lombok.Getter;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.core.Relation;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;


@Getter
@Relation("pessoa")
public class PessoaDTOV1Resource extends ResourceSupport {
    private final PessoaDTOV1 pessoa;

    public PessoaDTOV1Resource(PessoaDTOV1 pessoa) {
        this.pessoa = pessoa;
        add(linkTo(PessoaController.class).withRel("pessoa"));
        add(linkTo(methodOn(PessoaController.class).findById(this.pessoa.getId())).withSelfRel());
        add(linkTo(methodOn(PessoaController.class).findById(pessoa.getId())).withRel(Pessoa.class.getSimpleName()));
    }
}
