package initech.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


/**
 * All invalid/valid email address samples lifted from
 *      https://en.wikipedia.org/wiki/Email_address#Examples
 */
public class EMailAddressTests {
    @Test
    void given_AValidEmailAddress_When_Parsed_ExpectAnEmailAddressObject() {
        assertEquals("simple@example.com", EMailAddress.parse("simple@example.com").toString());
        assertEquals("*impl*@example.com", EMailAddress.parse("simple@example.com").toMaskedString('*'));

        assertEquals( "very.common@example.com",EMailAddress.parse("very.common@example.com").toString());
        assertEquals( "*ery.commo*@example.com",EMailAddress.parse("very.common@example.com").toMaskedString('*'));

        /*
         Our EMail class is not comprehensive, if it were, here would some other examples we might test.
                EMailAddress.parse("disposable.style.email.with+symbol@example.com");
                EMailAddress.parse("other.email-with-hyphen@example.com");
                EMailAddress.parse("fully-qualified-domain@example.com" );
                EMailAddress.parse("user.name+tag+sorting@example.com"); // (may go to user.name@example.com inbox depending on mail server)
                EMailAddress.parse("x@example.com"); // (one-letter local-part)
                EMailAddress.parse("example-indeed@strange-example.com");
                EMailAddress.parse("admin@mailserver1"); //  (local domain name with no TLD, although ICANN highly discourages dotless email addresses[13])
                EMailAddress.parse("example@s.example"); // (see the List of Internet top-level domains)
                EMailAddress.parse("\" \"@example.org"); // (space between the quotes)
                EMailAddress.parse("\"john..doe\"@example.org"); // (quoted double dot)
                EMailAddress.parse("mailhost!username@example.org"); //  (bangified host route used for uucp mailers)
                EMailAddress.parse("user%example.com@example.org"); // (% escaped mail route to user@example.com via example.org)
        */
    }
    @Test
    void given_AnEmailAddressWithNoDomainDelimiter_When_Parsing_ThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> EMailAddress.parse("Abc.example.com"));    //  (no @ character)
    }

    @Test
    void given_AMalformedEmailAddress_Expect_AnIllegalArgumentExceptionToBeThrown() {
        assertThrows(IllegalArgumentException.class, () -> EMailAddress.parse("A@b@c@example.com"));  // (only one @ is allowed outside quotation marks)
        // Again, this not comprehensive.  Here are some more invalid examples that would likely test
        //        EMailAddress.parse("a\"b(c)d,e:f;g<h>i[j\\k]l@example.com"); // (none of the special characters in this local-part are allowed outside quotation marks)
        //        EMailAddress.parse("just\"not\"right@example.com"); // (quoted strings must be dot separated or the only element making up the local-part)
        //        EMailAddress.parse("this is\"not\\allowed@example.com"); //" (spaces, quotes, and backslashes may only exist when within quoted strings and preceded by a backslash)
        //        EMailAddress.parse("this\\ still\\\"not\\allowed@example.com" ); // (even if escaped (preceded by a backslash), spaces, quotes, and backslashes must still be contained by quotes)
        //        EMailAddress.parse("1234567890123456789012345678901234567890123456789012345678901234+x@example.com" );//  (local part is longer than 64 characters)
    }

    @Test
    void given_ANullValue_When_Masked_AnIllegalArgumentIsThrows() {
        assertThrows(IllegalArgumentException.class, () -> EMailAddress.parse(null));
    }

    @Test
    void given_AnEmailAddressesMissingTheDomainComponent_When_Masking_ThrowsAnIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> EMailAddress.parse("joe@"));
    }

    @Test
    void given_AnEmailAddressMissingTheLocalComponent_When_Masking_ThrowsAnIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> EMailAddress.parse("@example.com"));
    }
}