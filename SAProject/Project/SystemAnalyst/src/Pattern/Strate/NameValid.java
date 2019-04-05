package Pattern.Strate;

public class NameValid implements Validable {
    @Override
    public boolean validable(String text) {
        return text.matches( "[A-Z][a-zA-Z]*" );
    }
}
