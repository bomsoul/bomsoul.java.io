package Controller;

import TableModel.OrderDetail;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.DBConnection;
import sample.Main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class OrderDetailController implements Initializable {
    @FXML
    TableView<OrderDetail> tableView;
    @FXML
    TableColumn<Integer,OrderDetail> id;
    @FXML TableColumn<String,OrderDetail> name,status,customername,quantity;

    @FXML private ToggleButton packed,unpacked,all;

    ObservableList<OrderDetail> data = DBConnection.showOrderDetail();

    @FXML public void allBtn(){
        all.setSelected(true);
        unpacked.setSelected(false);
        packed.setSelected(false);

        data.clear();
        data = DBConnection.showOrderDetail();
        tableView.setItems(data);
    }

    @FXML public void packedBtn(){
        all.setSelected(false);
        unpacked.setSelected(false);
        packed.setSelected(true);

        ObservableList<OrderDetail> details = FXCollections.observableArrayList();
        for(OrderDetail x : data){
            if(x.getStatus().equals("packed")){
                details.add(x);
            }
        }
        tableView.setItems(details);
    }

    @FXML public void unpackedBtn(){
        all.setSelected(false);
        unpacked.setSelected(true);
        packed.setSelected(false);

        ObservableList<OrderDetail> details = FXCollections.observableArrayList();
        for(OrderDetail x : data){
            if(x.getStatus().equals("unpacked")){
                details.add(x);
            }
        }
        tableView.setItems(details);
    }

    @FXML public void BackBtn(){
        try {
            Main.changeScene("Menu.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        id.setStyle("-fx-alignment: CENTER-LEFT;");
        quantity.setCellValueFactory(new PropertyValueFactory<>("quantitys"));
        quantity.setStyle("-fx-alignment: CENTER-RIGHT;");
        status.setCellValueFactory(new PropertyValueFactory<>("status"));
        status.setStyle("-fx-alignment: CENTER-LEFT;");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        name.setStyle("-fx-alignment: CENTER-LEFT;");
        customername.setCellValueFactory(new PropertyValueFactory<>("customername"));
        customername.setStyle("-fx-alignment: CENTER-LEFT;");

        all.setSelected(true);
        unpacked.setSelected(false);
        packed.setSelected(false);

        tableView.setItems(data);
    }
}
