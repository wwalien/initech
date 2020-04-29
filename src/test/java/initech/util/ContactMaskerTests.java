package initech.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ContactMaskerTests {
    @Test
    void given_AContactLongerThan80Characters_When_Masking_Expect_AnIllegalARgumentExceptionToBeThrown() {
        assertThrows(IllegalArgumentException.class, () -> ContactMasker.maskContact(  "0123456789" +
                                    "0123456789" +
                                    "0123456789" +
                                    "0123456789" +
                                    "0123456789" +
                                    "0123456789" +
                                    "0123456789" +
                                    "0123456789" +
                                    " 0", '*' ));
    }

    @Test
    void given_AWellFormedEMailAddress_When_Masking_Expect_AProperlyMaskedValueIsReturned() {
        assertEquals("t**t@example.com", ContactMasker.maskContact("test@example.com",'*'));
    }
    @Test
    void given_AWellFormedPhoneNumber_When_Masking_Expect_AProperlyMaskedValueIsReturned() {
        assertEquals("(012) ***-6789", ContactMasker.maskContact("012 345 6789",'*'));
    }

    @Test
    void given_AMalformedPhoneNUmber_When_Masking_Expect_IllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> ContactMasker.maskContact( "(800) GOTMILK",'*'));
    }

    @Test
    void given_AMalformedEMailAddress_When_Masked_Expect_IllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> ContactMasker.maskContact("a malformed email address", '*'));
    }

    @Test
    void given_ANullValue_When_Masked_ContactMaskerThrowsAnIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> ContactMasker.maskContact(null, '*'));
    }
}