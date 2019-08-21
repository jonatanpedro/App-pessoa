package br.com.zeroquo.appPessoa.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = GenderValidator.class)
@Documented
public @interface Gender {

    String message() default "Sexo deve ser 'M' ou 'F'!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
