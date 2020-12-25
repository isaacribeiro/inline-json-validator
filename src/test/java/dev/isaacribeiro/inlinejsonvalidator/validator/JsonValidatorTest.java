package dev.isaacribeiro.inlinejsonvalidator.validator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JsonValidatorTest {

    private JsonValidator cut = new JsonValidator();

    @Test
    public void whenInputIsAValidJson_thenIsValid(){
        assertTrue(cut.isValid("{\"key\": \"value\"}", null));
    }

}