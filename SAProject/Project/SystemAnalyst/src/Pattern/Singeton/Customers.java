package Pattern.Singeton;

public class Customers {
    private int customerid;
    private String identity;
    private int orderid;
    private String name,address,phone,hospital;
    private static Customers ourInstance = new Customers();

    public static Customers getInstance() {
        return ourInstance;
    }

    private Customers() {
    }

    public int getCustomerid() {
        return customerid;
    }

    public void setCustomerid(int customerid) {
        this.customerid = customerid;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public static void setOurInstance(Customers ourInstance) {
        Customers.ourInstance = ourInstance;
    }
}
