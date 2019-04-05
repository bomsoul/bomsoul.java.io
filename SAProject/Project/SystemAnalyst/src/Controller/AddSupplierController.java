package Controller;

import Pattern.Strate.NameValid;
import Pattern.Strate.Numeric;
import Pattern.Strate.ValidSystem;
import TableModel.Supplier;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import sample.DBConnection;
import sample.Main;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class AddSupplierController implements Initializable {
    @FXML private TextField name,city,phone,fax,country;
    @FXML private TextArea address;
    @FXML private Label warning;
    private ValidSystem validSystem = new ValidSystem();

    public void AddSupplierBtn(){
        if(!name.getText().equals("") && !address.getText().equals("") && !city.getText().equals("") && !phone.getText().equals("") && !fax.getText().equals("") && !country.getText().equals("")){
            if(validSystem.isValid(fax.getText(),new Numeric()) && validSystem.isValid(phone.getText(),new Numeric())){
                if(validSystem.isValid(country.getText(),new NameValid()) && validSystem.isValid(city.getText(),new NameValid())){
                    Alert dg = new Alert(Alert.AlertType.CONFIRMATION);
                    dg.setTitle("Add Supplier");
                    dg.setHeaderText("Are you sure add this supplier?");
                    dg.setContentText("OK to add supplier");
                    Optional<ButtonType> result = dg.showAndWait();
                    ButtonType button = result.orElse(ButtonType.CANCEL);

                    if (button == ButtonType.OK){
                        Supplier supplier = new Supplier(0,name.getText(),address.getText(),city.getText(),phone.getText(),fax.getText(),country.getText());
                        DBConnection.addSupplier(supplier);
                        try {
                            Main.changeScene("Supplier.fxml");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                else{
                    warning.setText("City and Country must be \nAlphabet with Capital ");
                }
            }
            else{
                warning.setText("Fax and Phone must be digit");
            }
        }
        else{
            warning.setText("Please fill all the information.");
        }
    }

    public void toSupplierBtn(){
        try {
            Main.changeScene("Supplier.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
