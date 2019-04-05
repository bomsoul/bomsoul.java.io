package Pattern.Strate;

public class ValidSystem {
    private Validable validable;

    public boolean isValid(String x,Validable validable){
        this.validable = validable;
        return validable.validable(x);
    }
}
