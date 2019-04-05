package TableModel;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class OrderDetail {
    private SimpleIntegerProperty id,quantity;
    private SimpleStringProperty name,status,customername,quantitys;

    public OrderDetail(int id, Integer quantity, String name, String status,String customername) {
        this.id = new SimpleIntegerProperty(id);
        this.quantity = new SimpleIntegerProperty(quantity);
        this.name = new SimpleStringProperty(name);
        this.quantitys = new SimpleStringProperty(String.format("%,2d",quantity));
        this.status = new SimpleStringProperty(status);
        this.customername = new SimpleStringProperty(customername);
    }

    public int getId() {
        return id.get();
    }

    public int getQuantity() {
        return quantity.get();
    }

    public String getName() {
        return name.get();
    }

    public String getStatus() {
        return status.get();
    }

    public String getCustomername() {
        return customername.get();
    }

    public String getQuantitys() {
        return quantitys.get();
    }
}
