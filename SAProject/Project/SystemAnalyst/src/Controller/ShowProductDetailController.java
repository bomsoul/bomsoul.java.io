package Controller;

import Model.Product;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sample.DBConnection;
import sample.Main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ShowProductDetailController implements Initializable {
    @FXML
    TextField a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p;

    Product productDetail = new Product();


    private void format(){
        a.setText(productDetail.getName());
        b.setText(productDetail.getPrice()+ "");
        d.setText(productDetail.getQuantity()+"");
        c.setText(productDetail.getSupplierid()+"");
        e.setText(productDetail.getTubesize() + "");
        f.setText(productDetail.getWeight() +"");
        g.setText(productDetail.getBodydimension() +"");
        h.setText(productDetail.getSetpointtemp() +"");
        i.setText(productDetail.getDisplay() +"");
        j.setText(productDetail.getUsewith() +"");
        k.setText(productDetail.getCpu() +"");
        l.setText(productDetail.getMemory() +"");
        m.setText(productDetail.getConnected() +"");
        n.setText(productDetail.getPower() +"");
        o.setText(productDetail.getFlowrate() +"");
        p.setText(productDetail.getGaranteeyear());

    }

    public void backBtn(){
        try {
            Main.changeScene("Menu.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void toModifiedProductBtn(){
        try {
            Main.changeScene("ModifieddProduct.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        productDetail = DBConnection.getProductDetail(productDetail.getId());
        format();
    }
}
