package br.com.zeroquo.appPessoa.rest;

import br.com.zeroquo.appPessoa.PessoaApplication;
import br.com.zeroquo.appPessoa.domain.Pessoa;
import br.com.zeroquo.appPessoa.repository.PessoaRepository;
import br.com.zeroquo.appPessoa.service.PessoaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PessoaApplication.class)
public class PessoaControllerTest {

    @Mock
    private PessoaRepository pessoaRepository;
    @InjectMocks
    private PessoaService pessoaService;
    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;
    private MockMvc restPersonMockMvc;
    private Pessoa pessoa;

    @Before
    public void setup() {
        this.pessoa = Pessoa.builder()
                .id(1L)
                .email("test@test.com")
                .nome("Name Test")
                .cpf("111.222.333-44")
                .dtNascimento(LocalDate.now())
                .sexo('M')
                .nacionalidade("Brasileiro")
                .naturalidade("Sao Pedro")
                .build();

        MockitoAnnotations.initMocks(this);
        PessoaController personController = new PessoaController(pessoaService, new ModelMapper());
        this.restPersonMockMvc = MockMvcBuilders.standaloneSetup(personController)
                .setCustomArgumentResolvers(pageableArgumentResolver)
                .build();
    }

    @Test
    @Transactional
    public void getAll() throws Exception {

        List<Pessoa> expected = Arrays.asList(this.pessoa);
        Page foundPage = new PageImpl(expected);

        when(pessoaRepository.findAll(isA(Pageable.class))).thenReturn(foundPage);

        restPersonMockMvc.perform(get("/api/v1/pessoas?sort=id,desc")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.content.[*].pessoa.email").value(hasItem(pessoa.getEmail())))
                .andExpect(jsonPath("$.content.[*].pessoa.nome").value(hasItem(pessoa.getNome())));

        verify(pessoaRepository, times(1)).findAll(isA(Pageable.class));

    }

    /*@Test
    @Transactional
    public void getPerson() throws Exception {

        pessoaRepository.saveAndFlush(pessoa);

        restPersonMockMvc.perform(get("/api/people/{id}", pessoa.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.person.login").value(pessoa.getLogin()))
                .andExpect(jsonPath("$.person.name").value(pessoa.getName()))
                .andExpect(jsonPath("$.person.id").value(pessoa.getId()));
    }

    @Test
    @Transactional
    public void getNonExistingPerson() throws Exception {
        restPersonMockMvc.perform(get("/api/people/{id}", pessoa.getId()))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void savePerson() throws Exception {

        pessoaRepository.saveAndFlush(pessoa);

        restPersonMockMvc.perform(post("/api/people")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(new ObjectMapper().writeValueAsString(pessoa))
        )
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.person.login").value(pessoa.getLogin()))
                .andExpect(jsonPath("$.person.name").value(pessoa.getName()))
                .andExpect(jsonPath("$.person.id").value(pessoa.getId()));
    }

    @Test
    @Transactional
    public void putPerson() throws Exception {

        pessoaRepository.saveAndFlush(pessoa);

        var newValuePerson = Pessoa.builder()
                .id("1")
                .login("test2")
                .name("Name Test")
                .build();

        restPersonMockMvc.perform(put("/api/people/{id}", newValuePerson.getId())
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(new ObjectMapper().writeValueAsString(newValuePerson))
        )
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.person.login").value(newValuePerson.getLogin()))
                .andExpect(jsonPath("$.person.name").value(newValuePerson.getName()))
                .andExpect(jsonPath("$.person.id").value(newValuePerson.getId()));
    }

    @Test
    @Transactional
    public void putNoValidPerson() throws Exception {

        pessoaRepository.saveAndFlush(pessoa);

        var newValuePerson = Pessoa.builder()
                .id("2")
                .login("test2")
                .name("Name Test")
                .build();

        restPersonMockMvc.perform(put("/api/people/{id}", newValuePerson.getId())
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(new ObjectMapper().writeValueAsString(newValuePerson))
        )
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void deletePerson() throws Exception {

        pessoaRepository.saveAndFlush(pessoa);

        restPersonMockMvc.perform(delete("/api/people/{id}", pessoa.getId()))
                .andExpect(status().isNoContent());
    }

    @Test
    @Transactional
    public void deleteNoValidPerson() throws Exception {

        pessoaRepository.saveAndFlush(pessoa);

        restPersonMockMvc.perform(delete("/api/people/{id}", "2"))
                .andExpect(status().isNotFound());
    }*/

}
