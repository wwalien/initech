package initech.util;

/**
 * N.B.: For the purposes of this this exercise, this class is intended to represent a comprehensive email address
 * class/validator as I've determined that that goes beyond the scope of the exercise.
 *
 */

public class EMailAddress {
    private final String domain;
    private final String local;
    public final static String DomainDelimiter="@";

    public static EMailAddress parse(String address) {
        if ( address == null ) {
            throw new IllegalArgumentException();
        }
        if (!address.contains("@")) {
            throw new IllegalArgumentException("Invalid format");
        }

        String[] parts = address.split(DomainDelimiter);
        // Missing domain component is trapped here
        if (parts.length != 2) {
            throw new IllegalArgumentException();
        }
        String local = parts[0];
        if (local.length() == 0 )
            throw new IllegalArgumentException();

        String domain = parts[1];
        return new EMailAddress(local,domain);
    }

    private EMailAddress( String local, String domain ) throws IllegalArgumentException {
        this.local = local;
        this.domain = domain;
    }

    @Override
    public String toString() {
        return local + DomainDelimiter + domain;
    }

    public String toMaskedString(char maskChar ) {
        return "*"+ local.substring(1,local.length()-1) + maskChar + DomainDelimiter + domain;
    }
}