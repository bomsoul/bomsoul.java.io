package TableModel;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Supplier {
    private SimpleIntegerProperty id;
    private SimpleStringProperty name,address,city,phone,fax,country;

    public Supplier(int id, String name, String address, String city, String phone, String fax, String country) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.address = new SimpleStringProperty(address);
        this.city = new SimpleStringProperty(city);
        this.phone = new SimpleStringProperty(phone);
        this.fax = new SimpleStringProperty(fax);
        this.country = new SimpleStringProperty(country);
    }

    public int getId() {
        return id.get();
    }

    public String getName() {
        return name.get();
    }

    public String getAddress() {
        return address.get();
    }

    public String getCity() {
        return city.get();
    }

    public String getPhone() {
        return phone.get();
    }

    public String getFax() {
        return fax.get();
    }

    public String getCountry() {
        return country.get();
    }
}
