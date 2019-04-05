package Pattern.Strate;

public class Numeric implements Validable {
    @Override
    public boolean validable(String text) {
        for(int i = 0;i < text.length();i++){
            if(text.charAt(i) >'9' || text.charAt(i) < '0'){
                return false;
            }
        }
        return true;
    }
}
