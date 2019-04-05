package Controller;

import Pattern.Singeton.Customers;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import java.net.URL;
import java.util.ResourceBundle;

public class SendingController implements Initializable {
    @FXML private Label show;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Customers customers =Customers.getInstance();
        String s ="";
        s += "\t\t\t\t\tPhone : " +customers.getPhone()+"\n";
        s += "Name : " + customers.getName() + "\n";
        String [] address = customers.getAddress().split(" ");
        if(address.length % 2 == 0){
            for(int i =0;i < address.length;i +=2){
                s+= address[i] + " "+address[i+1] + "\n";
            }
        }else{
            for(int i =0;i < address.length-1;i +=2){
                s+= address[i] + " "+address[i+1] + "\n";
            }
            s+= address[address.length-1] + "\n";
        }
        s += "Hospital : " +customers.getHospital();
        show.setText(s);
    }
}
