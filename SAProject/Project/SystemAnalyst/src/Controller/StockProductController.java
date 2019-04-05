package Controller;

import Pattern.Observer.Observer;
import Pattern.Strate.Numeric;
import Pattern.Strate.ValidSystem;
import TableModel.ProductTable;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.DBConnection;
import sample.Main;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class StockProductController implements Initializable{
    @FXML private TextField quantity;
    @FXML private Hyperlink newproduct,back;
    @FXML private Button editstock,accept;
    @FXML private Label warning,price,productname;
    @FXML
    TableView <ProductTable>tableView;
    @FXML TableColumn <Integer,ProductTable>pid;
    @FXML TableColumn<String,ProductTable> pname,prices,quan;
    ValidSystem validSystem;

    ObservableList<ProductTable> data = DBConnection.showProduct();

    @FXML public void Accepted(){
        DBConnection.ModifiedStock(tableView.getSelectionModel().getSelectedItem().getId(),Integer.parseInt(quantity.getText()));
        warning.setText("");
        data.clear();
        data = DBConnection.showProduct();
        tableView.setItems(data);
        quantity.clear();
        productname.setText("");
        quantity.setDisable(true);
        price.setText("");
    }

    @FXML public void AcceptBtn(){
        if(!productname.getText().equals("") && !quantity.getText().equals("") && !price.getText().equals("")){
            if( validSystem.isValid(quantity.getText(),new Numeric())  && Integer.parseInt(quantity.getText()) > 0){
                Alert dg = new Alert(Alert.AlertType.CONFIRMATION);
                dg.setTitle("Accepted");
                dg.setHeaderText("Product Name :" +productname.getText()+"\n"+
                        "Quantity : "+ quantity.getText());
                dg.setContentText("Are you sure to edit your Stock?");
                Optional<ButtonType> result = dg.showAndWait();
                ButtonType button = result.orElse(ButtonType.CANCEL);

                if (button == ButtonType.OK) {
                    Accepted();
                }
                else{
                    quantity.clear();
                    quantity.setDisable(true);
                }

            }
            else{
                warning.setText("Quantity must be Integer \nand greater than 0.");
            }
        }
        else{
            warning.setText("Please Clicked Edit before \nModify Product Stock.");
        }
    }

    @FXML public void EditStockBtn(){
        if(tableView.getSelectionModel().isEmpty()){
            warning.setText("Please Select a row to edit.");
        }
        else{
            quantity.setDisable(false);
            productname.setText(tableView.getSelectionModel().getSelectedItem().getName());
            quantity.setText(tableView.getSelectionModel().getSelectedItem().getQuan()+"");
            price.setText(tableView.getSelectionModel().getSelectedItem().getPrice()+"");
        }
    }

    @FXML public void AddProductBtn(){
        try {
            Main.changeScene("AddProduct.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML public void toMenuBtn(){
        try {
            Main.changeScene("Menu.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        warning.setText("");
        warning.setStyle("-fx-alignment: CENTER;");
        validSystem = new ValidSystem();
        quantity.setDisable(true);

        pid.setCellValueFactory(new PropertyValueFactory<>("id"));
        pid.setStyle("-fx-alignment: CENTER-LEFT;");
        pname.setCellValueFactory(new PropertyValueFactory<>("name"));
        pname.setStyle("-fx-alignment: CENTER-LEFT;");
        quan.setCellValueFactory(new PropertyValueFactory<>("quans"));
        quan.setStyle("-fx-alignment: CENTER-RIGHT;");
        prices.setCellValueFactory(new PropertyValueFactory<>("prices"));
        prices.setStyle("-fx-alignment: CENTER-RIGHT;");
        tableView.setItems(data);
    }

}
