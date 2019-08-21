package br.com.zeroquo.appPessoa.dto;

import br.com.zeroquo.appPessoa.validator.Gender;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.ZonedDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PessoaDTOV2 {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotNull
    @CPF
    private String cpf;

    @NotNull
    @Size(min = 1, max = 100)
    private String nome;

    @Gender
    private Character sexo;

    @Email
    private String email;

    @NotNull
    @DateTimeFormat(pattern = "dd/MM/YYYY")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="dd/MM/yyyy")
    @Past
    private LocalDate dtNascimento;

    @Size(max = 100)
    private String naturalidade;

    @Size(max = 100)
    private String nacionalidade;

    @NotNull
    @Size(min = 1, max = 255)
    private String endereco;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private ZonedDateTime modificationTime;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private ZonedDateTime creationTime;
}
