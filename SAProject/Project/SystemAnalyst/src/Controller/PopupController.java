package Controller;

import Pattern.Singeton.Order;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class PopupController {
    @FXML private Label show;

    public void initialize(){

        Order order = Order.getInstance();
        show.setText("Order ID is : " + order.getOrderid());
    }
}
