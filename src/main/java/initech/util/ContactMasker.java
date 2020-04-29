package initech.util;

public class ContactMasker {
    static String maskContact(String contact, char maskChar ) {
        if ( contact == null ) {
            throw new IllegalArgumentException();
        }

        // First verify the length.  Go ahead and trim it and test
        contact = contact.trim();
        if ( contact.length() > 80 ) {
            throw new IllegalArgumentException("Invalid contact info.  Too long");
        }

        // @ is not part of a valid phone number and is required for a valid email address, so testing for the
        // presence should be enough.
        //
        if (contact.contains("@")) {
            return EMailAddress.parse(contact).toMaskedString(maskChar);
        }
        else {
            return PhoneNumber.parse(contact).toMaskedString(maskChar);
        }
    }
}
