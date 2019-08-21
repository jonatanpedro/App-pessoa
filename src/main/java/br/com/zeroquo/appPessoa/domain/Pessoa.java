package br.com.zeroquo.appPessoa.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.ZonedDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pessoa implements Auditable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String cpf;

    @Column(length = 100, unique = true, nullable = false)
    private String nome;

    private Character sexo;

    @Column(length = 100, unique = true)
    private String email;

    @Column(nullable = false)
    private LocalDate dtNascimento;

    private String naturalidade;

    private String nacionalidade;

    @Column(nullable = false)
    private String endereco;

    @Column(name = "modification_time")
    @LastModifiedDate
    private ZonedDateTime modificationTime;

    @Column(name = "creation_time", nullable = false)
    @CreatedDate
    private ZonedDateTime creationTime;
}
