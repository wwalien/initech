package initech.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * TODO: Bill, you're description of the problem did specify what phone number formats we need to support.
 */
public class PhoneNumberTests {
    @Test
    void given_AWellFormedPhoneNumber_When_Parsing_Expect_AProperlyParsedAndMaskedContact() {
        assertEquals("(123) 456-7890", PhoneNumber.parse("(123) 456-7890").toString());
        assertEquals("(123) ***-7890", PhoneNumber.parse("(123) 456-7890").toMaskedString('*'));

        assertEquals("(123) 456-7890", PhoneNumber.parse("123 456-7890").toString());
        assertEquals("(123) ***-7890", PhoneNumber.parse("123 456-7890").toMaskedString('*'));

        assertEquals("(123) 456-7890", PhoneNumber.parse("123 456 7890").toString());
        assertEquals("(123) ***-7890", PhoneNumber.parse("123 456 7890").toMaskedString('*'));

        assertEquals("(123) 456-7890", PhoneNumber.parse("123 4567890").toString());
        assertEquals("(123) ***-7890", PhoneNumber.parse("123 4567890").toMaskedString('*'));

        assertEquals("(123) 456-7890", PhoneNumber.parse("1234567890").toString());
        assertEquals("(123) ***-7890", PhoneNumber.parse("1234567890").toMaskedString('*'));

        assertEquals("(123) 456-7890", PhoneNumber.parse("123.456.7890").toString());
        assertEquals("(123) ***-7890", PhoneNumber.parse("123.456.7890").toMaskedString('*'));
    }

    @Test
    void given_AnElevenDigitPhoneNumber_Expect_AnIllegalArgumentExceptionToBeThrown()  {
        assertThrows(IllegalArgumentException.class, () -> PhoneNumber.parse("1-123 456-7890"));
    }

    @Test
    void given_APhoneNumberContainAlphaCharacters_Expect_AnIllegalArgumentExceptionToBeThrown()  {
        assertThrows(IllegalArgumentException.class, () -> PhoneNumber.parse("(123) GOT-MILK"));
    }

    @Test
    void given_ANullValue_When_Parsing_Expect_AnIllegalExceptionToBeThrown() {
        assertThrows(IllegalArgumentException.class, () -> PhoneNumber.parse(null));
    }
}