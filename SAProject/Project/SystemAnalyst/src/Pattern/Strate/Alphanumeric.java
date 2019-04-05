package Pattern.Strate;

public class Alphanumeric implements Validable {
    @Override
    public boolean validable(String text) {
        for (int i=0; i<text.length(); i++) {
            char c =text.charAt(i);
            if (c < 0x30 || (c >= 0x3a && c <= 0x40) || (c > 0x5a && c <= 0x60) || c > 0x7a)
                return false;
        }
        return true;
    }
}
