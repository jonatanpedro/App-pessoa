package br.com.zeroquo.appPessoa.rest;

import br.com.zeroquo.appPessoa.domain.Auditable;
import br.com.zeroquo.appPessoa.domain.Pessoa;
import br.com.zeroquo.appPessoa.dto.PessoaDTOV1;
import br.com.zeroquo.appPessoa.service.PessoaService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.data.web.SortDefault;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/api/v1/pessoas", produces = "application/json")
@Validated
public class PessoaController {

    private PessoaService pessoaService;
    private ModelMapper modelMapper;

    public PessoaController(PessoaService pessoaService, ModelMapper modelMapper) {
        this.pessoaService = pessoaService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/{id}")
    public HttpEntity findById(@PathVariable Long id) {
        return pessoaService
                .findById(id)
                .map(pessoa -> ResponseEntity.ok(new PessoaDTOV1Resource(convertToDto(pessoa))))
                .orElse(ResponseEntity.notFound().build());
    }

    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public HttpEntity findByName(PagedResourcesAssembler<PessoaDTOV1> assembler, @SortDefault("name") Pageable pageable, @RequestParam String name) {

        Page<Pessoa> pageResult = pessoaService.findByName(name, pageable);

        PagedResources<PessoaDTOV1Resource> resource = assembler.toResource(pageResult.map(this::convertToDto), PessoaDTOV1Resource::new);

        resource.add(linkTo(methodOn(PessoaController.class).findByName(assembler, pageable, name)).withSelfRel());
        return ResponseEntity.ok(resource);
    }

    @PostMapping
    public HttpEntity save(@Valid @RequestBody PessoaDTOV1 pessoaDTOV1) {

        final var result = pessoaService.save(convertToEntity(pessoaDTOV1));

        final var uri = MvcUriComponentsBuilder.fromController(getClass())
                .path("/id")
                .buildAndExpand(result.getId())
                .toUri();

        return ResponseEntity.created(uri).body(new PessoaDTOV1Resource(convertToDto(result)));
    }

    @GetMapping
    public HttpEntity all(PagedResourcesAssembler<PessoaDTOV1> assembler, @SortDefault("id") Pageable pageable) {

        Page<Pessoa> pageResult = pessoaService.findAll(pageable);
        PagedResources<PessoaDTOV1Resource> resource = assembler.toResource(pageResult.map(this::convertToDto), PessoaDTOV1Resource::new);

        resource.add(linkTo(methodOn(PessoaController.class).all(assembler, pageable)).withSelfRel());
        return ResponseEntity.ok(resource);
    }

    @PutMapping("/{id}")
    public HttpEntity put(@PathVariable Long id, @RequestBody PessoaDTOV1 pessoaFromRequest) {

        final var uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .build()
                .toUri();

        return pessoaService
                .findById(id)
                .map(pessoa -> {
                    pessoaFromRequest.setId(pessoa.getId());
                    var personEntity = convertToEntity(pessoaFromRequest);
                    Auditable.updateAuditable(pessoa, personEntity);
                    var returnedPerson = convertToDto(pessoaService.update(personEntity));
                    return ResponseEntity.created(uri).body(new PessoaDTOV1Resource(returnedPerson));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public HttpEntity delete(@PathVariable Long id) {
        return pessoaService
                .findById(id)
                .map(person -> {
                    pessoaService.deleteById(id);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    private Pessoa convertToEntity(PessoaDTOV1 pessoaDtoV1) {
        return modelMapper.map(pessoaDtoV1, Pessoa.class);
    }

    private PessoaDTOV1 convertToDto(Pessoa pessoa) {
        return modelMapper.map(pessoa, PessoaDTOV1.class);
    }
}
