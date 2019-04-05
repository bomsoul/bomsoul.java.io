package Pattern.Strate;

public class AddressValid implements Validable {
    @Override
    public boolean validable(String text) {
        return text.matches("\\d+\\s+([a-zA-Z]+|[a-zA-Z]+\\s[a-zA-Z]+)" );
    }
}
