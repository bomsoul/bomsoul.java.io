package Controller;

import Pattern.Strate.Numeric;
import Pattern.Strate.ValidSystem;
import TableModel.ProductTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.DBConnection;
import sample.Main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class OrderProductController implements Initializable {
    @FXML private TableView<ProductTable> product,order;
    @FXML private TableColumn<String,ProductTable> n1,n2,p1,p2,q1,q2;
    @FXML private TextField amount;
    @FXML private Label warning,total;
    @FXML private Button add,submit;
    ObservableList<ProductTable> data1 = DBConnection.showProduct();
    ObservableList<ProductTable> data2 = FXCollections.observableArrayList();
    private ValidSystem validSystem;
    private Integer sum;

    @FXML public void addBtn(){
        if(product.getSelectionModel().isEmpty()){
            warning.setText("Please select a row to add order.");
        }
        else if(amount.getText().equals("")){
            warning.setText("Please enter amount");
        }
        else if(!validSystem.isValid(amount.getText(),new Numeric())){
            warning.setText("Amount must be positive integer.");
        }
        else if(Integer.parseInt(amount.getText()) <1){
            warning.setText("Amount must more than 0");
        }
        else if(Integer.parseInt(amount.getText()) > product.getSelectionModel().getSelectedItem().getQuan()){
            warning.setText("Don't have enough quantity");
        }
        else{
            String name = product.getSelectionModel().getSelectedItem().getName();
            int quantity = product.getSelectionModel().getSelectedItem().getQuan();
            int price = product.getSelectionModel().getSelectedItem().getPrice();
            data2.add(new ProductTable(product.getSelectionModel().getSelectedItem().getId(),name,Integer.parseInt(amount.getText()),price));
            product.getSelectionModel().getSelectedItem().setQuan(quantity-Integer.parseInt(amount.getText()));
            warning.setText("");
            ObservableList<ProductTable> product1= FXCollections.observableArrayList();
            sum = 0;
            for(ProductTable x: data1){
                product1.add(x);
            }
            for(ProductTable o : data2){
                sum += o.getPrice()*o.getQuan();
            }
            amount.clear();
            total.setText("Total : "+String.format("%,2d",sum));
            data1.clear();
            data1 = product1;
            product.setItems(data1);
        }
    }

    @FXML public void submitBtn(){
        if(data2.size() > 0){
            DBConnection.createNewOrder();
            DBConnection.setPayment(sum);
            DBConnection.orderID();
            try{
                Main.changeScene("Menu.fxml");
                Popup(new Stage());
            }catch (Exception e){
                e.printStackTrace();
            }
            for(ProductTable x: data2){
                DBConnection.addOrderDetail(x);
            }
        }
        else{
            warning.setText("There is no product in order table.");
        }

    }

    @FXML public void toMenuBtn(ActionEvent event){
        try {
            Main.changeScene("Menu.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        warning.setText("");
        validSystem = new ValidSystem();
        sum = 0;
        n1.setCellValueFactory(new PropertyValueFactory<>("name"));
        n1.setStyle("-fx-alignment: CENTER-LEFT;");
        p1.setCellValueFactory(new PropertyValueFactory<>("prices"));
        p1.setStyle("-fx-alignment: CENTER-RIGHT;");
        q1.setCellValueFactory(new PropertyValueFactory<>("quans"));
        q1.setStyle("-fx-alignment: CENTER-RIGHT;");

        n2.setCellValueFactory(new PropertyValueFactory<>("name"));
        n2.setStyle("-fx-alignment: CENTER-LEFT;");
        p2.setCellValueFactory(new PropertyValueFactory<>("prices"));
        p2.setStyle("-fx-alignment: CENTER-RIGHT;");
        q2.setCellValueFactory(new PropertyValueFactory<>("quans"));
        q2.setStyle("-fx-alignment: CENTER-RIGHT;");
        product.setItems(data1);
        order.setItems(data2);
        total.setText("");
    }

    public void Popup(Stage stage){
        FXMLLoader fxmlLoader =new FXMLLoader(getClass().getResource("Popup.fxml"));
        try {
            stage.setScene(new Scene(fxmlLoader.load(),200,200));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
