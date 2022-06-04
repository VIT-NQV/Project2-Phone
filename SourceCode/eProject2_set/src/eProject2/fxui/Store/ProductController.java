/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eProject2.fxui.Store;

import eProject2.dao.SmartPhone;
import eProject2.dao.SmartPhoneDAO;
import eProject2.dao.SmartPhoneDAOImp;
import eProject2.fxui.Navigator;
import java.util.ArrayList;
import com.jfoenix.controls.JFXButton;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author Admin
 */
public class ProductController {

    private SmartPhoneDAO smartphonedao = new SmartPhoneDAOImp();

    ArrayList<SmartPhone> smartphone = smartphonedao.selectAlltoArray();

    @FXML
    private ImageView Img;

    @FXML
    private Label lbName;

    @FXML
    private JFXButton btnDetail;

    @FXML
    void btnDetailClick(ActionEvent event) throws IOException {
        String name = lbName.getText();
        Navigator.getInstance().goToProductView2(name);
    }

    public void initialize() {
        
    }

    @FXML
    public void setData(SmartPhone smartphone) {
        String link = smartphonedao.SelectImg(smartphone.getProductID().toString());
        File file = new File(link);
        String localUrl = file.toURI().toString();
        Image image = new Image(localUrl);
        Img.setImage(image);
        lbName.setText(smartphone.getName());
    }
}
