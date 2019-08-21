package br.com.zeroquo.appPessoa.service;

import br.com.zeroquo.appPessoa.PessoaApplication;
import br.com.zeroquo.appPessoa.domain.Pessoa;
import br.com.zeroquo.appPessoa.repository.PessoaRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PessoaApplication.class)
@Transactional
public class PessoaServiceTest {

    @Mock
    private PessoaRepository pessoaRepository;

    @InjectMocks
    private PessoaService pessoaService;

    private Pessoa pessoa;

    @Before
    public void init() {
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
    }

    @Test
    @Transactional
    public void savePessoa(){
        when(pessoaRepository.save(pessoa)).thenReturn(pessoa);
        assertThat(pessoaService.save(pessoa)).isEqualTo(pessoa);
    }
}
