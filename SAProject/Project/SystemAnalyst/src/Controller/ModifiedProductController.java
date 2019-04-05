package Controller;

import Model.Product;
import Pattern.Strate.Numeric;
import Pattern.Strate.ValidSystem;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sample.DBConnection;
import sample.Main;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class ModifiedProductController implements Initializable {
    @FXML
    private TextField price,name,supplier,tubesize,weight,bodydimension,temperature,display,usewith,cpu,memory,connect,powerrequirement,rate,garuntee;
    @FXML private Label warning;
    ValidSystem validSystem = new ValidSystem();

    Product productDetail = new Product();


    public void SubmitBtn(){
        try{
            Integer.parseInt(price.getText());
            if(tubesize.getText().equals("") || weight.getText().equals("") || bodydimension.getText().equals("") ||temperature.getText().equals("") || display.getText().equals("")
                    || usewith.getText().equals("") || cpu.getText().equals("") || memory.getText().equals("") || connect.getText().equals("") || powerrequirement.getText().equals("") ||
                    rate.getText().equals("") || garuntee.getText().equals("")){
                warning.setText("Please fill all the Information.");
                warning.setVisible(true);

            }
            else if(!validSystem.isValid(supplier.getText(),new Numeric())){
                warning.setText("supplier ID must be Integer");
            }
            else if(!DBConnection.checkSupllierID(Integer.parseInt(supplier.getText()))){
                warning.setText("There is no supplier with this id");
                warning.setVisible(true);
            }
            else if(!validSystem.isValid(price.getText(),new Numeric())){
                warning.setText("Price must be Integer");
                warning.setVisible(true);
            }
            else if(Integer.parseInt(price.getText()) > 10000000){
                warning.setText("Price out of range(maximum price is 10,000,000)");
                warning.setVisible(true);
            }
            else if(Integer.parseInt(price.getText()) < 1){
                warning.setText("Price must be positive integer");
                warning.setVisible(true);
            }
            else {
                Alert dg = new Alert(Alert.AlertType.CONFIRMATION);
                dg.setTitle("Modified");
                dg.setHeaderText("Are you sure to modified this product?");
                dg.setContentText("Click OK to modified");
                Optional<ButtonType> result = dg.showAndWait();
                ButtonType button = result.orElse(ButtonType.CANCEL);

                if (button == ButtonType.OK){
                    toProductDetal();
                    Product productDetail = new Product();
                    DBConnection.setProductDetail(productDetail);
                    toDefault();
                    try {
                        Main.changeScene("Menu.fxml");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        }catch (Exception e){
            warning.setText("Price out of range(maximum price is 10,000,000)");
            warning.setVisible(true);
        }
    }

    @FXML public void BackToDetailBtn(){
        try {
            Main.changeScene("ShowProductDetail.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void toDefault(){
        Product productDetail = new Product();
        productDetail.setName("");
        productDetail.setQuantity(0);
        productDetail.setPrice(0);
        productDetail.setSupplierid(0);
        productDetail.setTubesize("");
        productDetail.setWeight("");
        productDetail.setBodydimension("");
        productDetail.setSetpointtemp("");
        productDetail.setDisplay("");
        productDetail.setUsewith("");
        productDetail.setCpu("");
        productDetail.setMemory("");
        productDetail.setConnected("");
        productDetail.setPower("");
        productDetail.setFlowrate("");
        productDetail.setGaranteeyear("");
    }

    private void toProductDetal(){
        productDetail.setName(name.getText());
        productDetail.setSupplierid(Integer.parseInt(supplier.getText()));
        productDetail.setTubesize(tubesize.getText());
        productDetail.setWeight(weight.getText());
        productDetail.setBodydimension(bodydimension.getText());
        productDetail.setSetpointtemp(temperature.getText());
        productDetail.setDisplay(display.getText());
        productDetail.setUsewith(usewith.getText());
        productDetail.setCpu(cpu.getText());
        productDetail.setMemory(memory.getText());
        productDetail.setConnected(connect.getText());
        productDetail.setPower(powerrequirement.getText());
        productDetail.setFlowrate(rate.getText());
        productDetail.setGaranteeyear(garuntee.getText());
        productDetail.setPrice(Double.parseDouble(price.getText()));
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        name.setText(productDetail.getName());
        supplier.setText(productDetail.getSupplierid()+"");
        tubesize.setText(productDetail.getTubesize());
        weight.setText(productDetail.getWeight());
        bodydimension.setText(productDetail.getBodydimension());
        temperature.setText(productDetail.getSetpointtemp());
        display.setText(productDetail.getDisplay());
        usewith.setText(productDetail.getUsewith());
        cpu.setText(productDetail.getCpu());
        memory.setText(productDetail.getMemory());
        connect.setText(productDetail.getConnected());
        powerrequirement.setText(productDetail.getPower());
        rate.setText(productDetail.getFlowrate());
        garuntee.setText(productDetail.getGaranteeyear());
        price.setText(productDetail.getPrice() + "");
        warning.setText("");
    }
}
