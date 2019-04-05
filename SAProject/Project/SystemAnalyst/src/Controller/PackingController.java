package Controller;

import Pattern.Strate.Numeric;
import Pattern.Strate.ValidSystem;
import Pattern.Singeton.Customers;
import TableModel.ProductTable;
import javafx.collections.ObservableList;
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
import java.util.Optional;
import java.util.ResourceBundle;

public class PackingController implements Initializable {
    @FXML private TextField identity,orderid;
    @FXML private Button check,clear,detail,submit;
    @FXML private Label showmoney,showtotal,warning;
    @FXML private TableView<ProductTable> tableView;
    @FXML private TableColumn<String,ProductTable> name,price,quantity;
    ValidSystem validSystem;
    ObservableList<ProductTable> data ;


    @FXML public void BacktoMenuBtn(){
        try {
            Main.changeScene("Menu.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML public void checkBtn(){
        if(identity.getText().equals("")){
            showmoney.setText("Please Enter identity.");
        }
        else if(!DBConnection.isHave(identity.getText())){
            warning.setText("There is no this identity");
            identity.clear();
        }
        else if(!validSystem.isValid(identity.getText(),new Numeric()) || identity.getText().length() !=13){
            showmoney.setText("Identity must be 13 digit integer.");
            identity.clear();
        }
        else{
            showmoney.setText("Money payment is " + String.format("%,2d",DBConnection.getStatus(identity.getText())));
            showmoney.setStyle("-fx-text-fill: BLACK;");
            clear.setVisible(true);
        }
    }

    @FXML public void clearBtn(){
        Customers customers = Customers.getInstance();
        customers.setIdentity(identity.getText());
        DBConnection.settoZeroPayment(0,identity.getText());
        orderid.setVisible(true);
        detail.setVisible(true);
        showmoney.setText("Money payment is " + 0);
    }

    @FXML public void detailBtn(){
        if(orderid.getText().equals("")){
            warning.setText("Please Input your OrderID.");
            orderid.clear();
        }
        else if(!validSystem.isValid(orderid.getText(),new Numeric())){
            warning.setText("Order ID must be Integer.");
            orderid.clear();
        }
        else if(!DBConnection.checkOrderTracking(identity.getText(),Integer.parseInt(orderid.getText()))){
            warning.setText("Identity ID and Order ID don't Match.");
            orderid.clear();
        }
        else if(DBConnection.checkPacked(Integer.parseInt(orderid.getText()))){
            warning.setText("This Order already packed.");
        }
        else{
            tableView.setVisible(true);
            warning.setText("");
            data = DBConnection.showbyDetail(Integer.parseInt(orderid.getText()));
            tableView.setItems(data);
            Integer cost = 0;
            for(ProductTable x : data){
                cost += x.getQuan()*x.getPrice();
            }
            showtotal.setVisible(true);
            showtotal.setText("Total : " + String.format("%,2d",cost));
            submit.setVisible(true);
        }
    }

    @FXML public void SubmitBtn(){
        Customers customers = Customers.getInstance();
        customers.setOrderid(Integer.parseInt(orderid.getText()));
        DBConnection.PackingAddress();
        Alert dg = new Alert(Alert.AlertType.CONFIRMATION);
        dg.setTitle("Packed?");
        dg.setHeaderText("Are you sure to pack this order?");
        dg.setContentText("OK for packed ,Cancel for Cancel the packed");
        Optional<ButtonType> result = dg.showAndWait();
        ButtonType button = result.orElse(ButtonType.CANCEL);

        if (button == ButtonType.OK){
            DBConnection.setStatusPack(Integer.parseInt(orderid.getText()),"packed");
            for(ProductTable x : data){
                DBConnection.decreaseProduct(x);
            }
            try {
                Main.changeScene("Menu.fxml");
                OrderInstrution(new Stage(),"OrderInstruction.fxml",300,500);
                OrderInstrution(new Stage(),"Sending.fxml",400,200);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if( button == ButtonType.CANCEL){
            DBConnection.setStatusPack(Integer.parseInt(orderid.getText()),"canceled");
            try {
                Main.changeScene("Menu.fxml");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showmoney.setText("");
        clear.setVisible(false);
        orderid.setVisible(false);
        detail.setVisible(false);
        tableView.setVisible(false);
        submit.setVisible(false);
        showtotal.setVisible(false);

        warning.setText("");

        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        name.setStyle("-fx-alignment: CENTER-LEFT;");
        price.setCellValueFactory(new PropertyValueFactory<>("prices"));
        price.setStyle("-fx-alignment: CENTER-RIGHT;");
        quantity.setCellValueFactory(new PropertyValueFactory<>("quans"));
        quantity.setStyle("-fx-alignment: CENTER-RIGHT;");

        validSystem = new ValidSystem();
    }

    public void OrderInstrution(Stage stage,String scene,int width,int height){
        FXMLLoader fxmlLoader =new FXMLLoader(getClass().getResource(scene));
        try {
            stage.setScene(new Scene(fxmlLoader.load(),width,height));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
