package initech.util;

public class PhoneNumber {
    private final String areaCode;
    private final String exchange;
    private final String number;

    private PhoneNumber(String areaCode, String exchange, String number ) {
        this.areaCode = areaCode;
        this.exchange = exchange;
        this.number = number;
    }

    @Override
    public String toString() {
        return "(" + areaCode + ") " + exchange +"-" + number;
    }

    public String toMaskedString(char maskChar ) {
        return "(" + areaCode + ") " + maskChar + maskChar + maskChar +"-" + number;
    }

    public static PhoneNumber parse(String phoneNumber )  {
        if ( phoneNumber == null ) {
            throw new IllegalArgumentException();
        }

        StringBuilder sb = new StringBuilder();
        char[] chars = phoneNumber.toCharArray();
        for (char aChar : chars) {
            if (Character.isDigit(aChar)) {
                sb.append(aChar);
            }
        }
        String s = sb.toString();
        if ( s.length() == 10 ) {
            return new PhoneNumber(s.substring(0,3), s.substring(3,6),s.substring(6) );
        }
        else{
            throw new IllegalArgumentException();
        }
    }
}