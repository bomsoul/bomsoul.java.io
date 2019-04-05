package Pattern.Strate;

public class PhoneValid implements Validable {

    @Override
    public boolean validable(String text) {
        if(text.matches("\\d{3}[-\\.\\s]\\d{3}[-\\.\\s]\\d{4}"))
            return true;
        return false;
    }
}
