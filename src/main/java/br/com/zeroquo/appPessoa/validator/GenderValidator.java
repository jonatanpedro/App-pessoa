package br.com.zeroquo.appPessoa.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

public class GenderValidator implements ConstraintValidator<Gender, Character> {

    private List<Character> genders = Arrays.asList('M', 'F');

    @Override
    public boolean isValid(Character value, ConstraintValidatorContext context) {
        if(value == null)
            return true;
        return genders.contains(value);
    }
}
