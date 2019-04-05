package Pattern.Singeton;

public class Order {

    private int orderid;
    private static Order ourInstance = new Order();

    public static Order getInstance() {
        return ourInstance;
    }

    private Order() {
    }

    public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }
}
