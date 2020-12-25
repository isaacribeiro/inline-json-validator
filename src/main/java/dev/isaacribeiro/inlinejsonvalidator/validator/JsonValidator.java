package dev.isaacribeiro.inlinejsonvalidator.validator;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.isaacribeiro.inlinejsonvalidator.annotation.Json;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Set;

public class JsonValidator implements ConstraintValidator<Json, String> {

    private Set<String> mandatoryFields;

    public void initialize(Json constraintAnnotation) {
        this.mandatoryFields = Collections.emptySet();
        this.mandatoryFields.addAll(Arrays.asList(constraintAnnotation.mandatoryFields()));
    }

    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return isJsonValid(value);
    }

    private boolean isJsonValid(String value) {
        if (StringUtils.isEmpty(value)) {
            return false;
        }

        try {
            JsonParser parser = new ObjectMapper().getFactory().createParser(value);
            while (parser.nextToken() != null){}
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
