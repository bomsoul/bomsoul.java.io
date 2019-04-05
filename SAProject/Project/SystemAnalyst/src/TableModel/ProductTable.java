package TableModel;

import Model.Product;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;
import sample.Main;

import java.io.IOException;

public class ProductTable {
    private SimpleIntegerProperty id,quan,price;
    private SimpleStringProperty name,prices,quans;
    private Button detail;

    public ProductTable(int id, String name,int quan, int price) {
        this.id = new SimpleIntegerProperty(id);
        this.quan = new SimpleIntegerProperty(quan);
        this.price = new SimpleIntegerProperty(price);
        this.name = new SimpleStringProperty(name);
        this.prices = new SimpleStringProperty(String.format("%,2d",price));
        this.quans = new SimpleStringProperty(String.format("%,2d",quan));
        this.detail = new Button("Info");
        this.detail.setOnAction(event -> {
            Product productDetail = new Product();
            productDetail.setId(id);
            productDetail.setPrice(price);
            productDetail.setQuantity(quan);
            productDetail.setName(name);
            try {
                Main.changeScene("ShowProductDetail.fxml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void setQuan(int quan) {
        this.quan.set(quan);
    }

    public void setPrice(int price) {
        this.price.set(price);
    }

    public int getId() {
        return id.get();
    }

    public int getQuan() {
        return quan.get();
    }

    public int getPrice() {
        return price.get();
    }

    public String getName() {
        return name.get();
    }

    public Button getDetail() {
        return detail;
    }

    public String getPrices() {
        return prices.get();
    }

    public String getQuans() {
        return quans.get();
    }

}
