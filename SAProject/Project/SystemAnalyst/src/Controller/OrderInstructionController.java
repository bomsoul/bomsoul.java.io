package Controller;

import Pattern.Singeton.Customers;
import Pattern.Singeton.User;
import TableModel.ProductTable;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import sample.DBConnection;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class OrderInstructionController implements Initializable {
    @FXML private Label Ordercard,orderprice;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        User user = User.getInstance();
        Customers customers = Customers.getInstance();
        String s ="Customer Name : "+customers.getName() +
                "\nHospital :" +  customers.getHospital() + "\n";
        String t ="\n\n" + LocalDate.now().toString()+"\n\n";
        Integer price = 0;

        s += "Employee Name : " + user.getName() +"   Date: \n";
        s += "Order ID : "+ customers.getOrderid() + "\n";
        ObservableList<ProductTable> productTables = DBConnection.showbyDetail(customers.getOrderid());
        for(ProductTable x : productTables){
            s += x.getName() + "\t x" + String.format("%,2d",x.getQuan()) + "\n";
            String p = String.format("%-,2d",x.getPrice());
            t += String.format("%10s",p) + "\n";
            price += x.getPrice()*x.getQuan();
        }
        s += "\n\n" +"Total prices : ";
        t += "\n\n" + String.format("%,2d",price);
        Ordercard.setText(s);
        orderprice.setText(t);

    }
}
