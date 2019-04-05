package Controller;

import TableModel.Supplier;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.DBConnection;
import sample.Main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SupplierController implements Initializable {
    @FXML private TableView<Supplier> tableView;
    @FXML private TableColumn<Integer,Supplier> id;
    @FXML private TableColumn<String,Supplier>name,address,phone,fax;

    ObservableList<Supplier> data = DBConnection.showSupplier();

    public void toMenuBtn(){
        try {
            Main.changeScene("Menu.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addSupplierBtn(){
        try {
            Main.changeScene("AddSupplier.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        id.setStyle("-fx-alignment: CENTER-LEFT;");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        name.setStyle("-fx-alignment: CENTER-LEFT;");
        address.setCellValueFactory(new PropertyValueFactory<>("address"));
        address.setStyle("-fx-alignment: CENTER-LEFT;");
        phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        phone.setStyle("-fx-alignment: CENTER-LEFT;");
        fax.setCellValueFactory(new PropertyValueFactory<>("fax"));
        fax.setStyle("-fx-alignment: CENTER-LEFT;");
        tableView.setItems(data);
    }
}
