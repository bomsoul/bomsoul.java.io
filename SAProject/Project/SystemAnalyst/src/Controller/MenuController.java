package Controller;

import Pattern.Singeton.User;
import TableModel.ProductTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import sample.DBConnection;
import sample.Main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {
    @FXML private ImageView refresh,search;
    @FXML private Hyperlink productstock,logout,order;
    @FXML private Label title;
    @FXML private TextField searchbar;
    @FXML private TableView<ProductTable> tableView;
    @FXML private TableColumn<Integer,ProductTable> id,quantity;
    @FXML private TableColumn<String,ProductTable> name,price;
    @FXML private TableColumn<Button,ProductTable> detail;
    private ObservableList<ProductTable> productTables = DBConnection.showProduct();

    @FXML public void searchBtn(){
        ObservableList<ProductTable> product = FXCollections.observableArrayList();
        productTables = DBConnection.showProduct();
        CharSequence o = searchbar.getText();
        for(ProductTable x: productTables){
            if(x.getName().contains(o)){
                product.add(x);
            }
        }
        productTables.clear();
        productTables = product;
        tableView.setItems(productTables);
    }

    @FXML public void refreshBtn(){
        productTables.clear();
        productTables = DBConnection.showProduct();
        tableView.setItems(productTables);
        searchbar.clear();
    }

    @FXML public void changeStockProductBtn(){
        try {
            Main.changeScene("StockProduct.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML public void changeOrderDetailBtn(){
        try {
            Main.changeScene("OrderDetail.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML public void changeOrderBtn(){
        try {
            Main.changeScene("Order.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML public void changePackingBtn(){
        try {
            Main.changeScene("Packing.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML public void toSuplliersBtn(){
        try {
            Main.changeScene("Supplier.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML public void changeLoginBtn(){
        try {
            Main.changeScene("sample.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        User user =User.getInstance();
        title.setStyle("-fx-alignment: CENTER-RIGHT;");
        title.setText("Welcome : "+user.getName() + " " + user.getLastname());
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        id.setStyle("-fx-alignment: CENTER-LEFT;");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        name.setStyle("-fx-alignment: CENTER-LEFT;");
        price.setCellValueFactory(new PropertyValueFactory<>("prices"));
        price.setStyle("-fx-alignment: CENTER-RIGHT;");
        quantity.setCellValueFactory(new PropertyValueFactory<>("quan"));
        quantity.setStyle("-fx-alignment: CENTER-RIGHT;");
        detail.setCellValueFactory(new PropertyValueFactory<>("detail"));
        detail.setStyle("-fx-alignment: CENTER;");

        tableView.setItems(productTables);
    }
}
