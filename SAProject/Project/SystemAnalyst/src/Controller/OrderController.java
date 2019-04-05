package Controller;

import Model.Customer;
import Pattern.Singeton.Customers;
import Pattern.Strate.NameValid;
import Pattern.Strate.Numeric;
import Pattern.Strate.PhoneValid;
import Pattern.Strate.ValidSystem;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sample.DBConnection;
import sample.Main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class OrderController implements Initializable {
    @FXML
    private TextField identity,cusname,cusphone,cushos;
    @FXML private TextArea address;
    @FXML private Button newcustomer,orderproduct,checkstatus;
    @FXML private ImageView status;
    @FXML private Label statuslabel,warning;

    private ValidSystem validSystem;

    @FXML public void checkCustomer(){
        if(validSystem.isValid(identity.getText(),new Numeric()) && identity.getText().length() ==13){
            if(DBConnection.isNewCustomer(identity.getText())){
                warning.setText("");
                Image im = new Image("/image/tickicon.png");
                status.setImage(im);
                status.setVisible(true);
                statuslabel.setText("This is New Customer");
                newcustomer.setVisible(true);
            }
            else{
                if(DBConnection.isAbleToBuy(identity.getText())){
                    Image im = new Image("/image/tickicon.png");
                    status.setImage(im);
                    status.setVisible(true);
                    statuslabel.setText("This Customer is Ready");
                    Customer customer = DBConnection.CustomerDetail(identity.getText());
                    cusname.setText(customer.getName());
                    cushos.setText(customer.getHospital());
                    cusphone.setText(customer.getPhone());
                    address.setText(customer.getAddress());
                    orderproduct.setVisible(true);
                }
                else{
                    Image im = new Image("/image/Crossicon.png");
                    status.setImage(im);
                    status.setVisible(true);
                    statuslabel.setText("This Customer can't order");
                }
            }
        }
        else{
            warning.setText("Identity must be all Integer with 13 digit");
        }

    }

    @FXML public void addnewCus() {
        if(!identity.getText().equals("") && !cusname.getText().equals("") && !cusphone.getText().equals("") && !address.getText().equals("") && !cushos.getText().equals(""))
            if (validSystem.isValid(cusname.getText(), new NameValid())) {
                if (validSystem.isValid(cusphone.getText(), new PhoneValid())) {
                    warning.setText("Add new customer complete");
                    Customer customer = new Customer();
                    customer.setIdentity(identity.getText());
                    customer.setName(cusname.getText());
                    customer.setPhone(cusphone.getText());
                    customer.setAddress(address.getText());
                    customer.setHospital(cushos.getText());
                    DBConnection.addNewCutomer(customer);
                    orderproduct.setVisible(true);
                    newcustomer.setVisible(false);
                } else {
                    warning.setText("Phone must be in Pattern xxx-xxx-xxxx");
                }
            } else {
                warning.setText("Name Must be all alphabet and Capital letter.");
            }
        else{
            warning.setText("Please fill all the information.");
        }
    }

    @FXML public void toOrderProductBtn () {
        Customers customers = Customers.getInstance();
        Customer customer = new Customer();
        customers.setCustomerid(DBConnection.CustomerID(identity.getText()));
        customer.setHospital(cushos.getText());
        customer.setAddress(address.getText());
        customer.setName(cusname.getText());
        customer.setPhone(cusphone.getText());
        customer.setIdentity(identity.getText());
        DBConnection.upDateCustomer(customer);
        try {
            Main.changeScene("OrderProduct.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void toMenuBtn(){
        try {
            Main.changeScene("Menu.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        validSystem = new ValidSystem();
        newcustomer.setVisible(false);
        orderproduct.setVisible(false);
        status.setVisible(false);
        statuslabel.setText("");
        warning.setText("");
    }
}
