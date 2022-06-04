/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eProject2.fxui.Store;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import eProject2.UserName;
import eProject2.dao.SmartPhone;
import eProject2.dao.SmartPhoneDAO;
import eProject2.dao.SmartPhoneDAOImp;
import eProject2.fxui.Navigator;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author Admin
 */
public class ProductView2Controller implements Initializable {

    private SmartPhoneDAO smartphonedao = new SmartPhoneDAOImp();

    private SmartPhone SmartPhone = null;

    @FXML
    private ImageView Img;

    @FXML
    private TextField searchBar;

    @FXML
    private Label txtUserName;

    @FXML
    private JFXButton btnLogout;

    @FXML
    private JFXButton btnSearch;

    @FXML
    private JFXButton HomeBtn;

    @FXML
    private JFXButton btnBack;
    
    @FXML
    private JFXButton btnCart;

    @FXML
    private Label lbName;

    @FXML
    private Label lbPrice;

    @FXML
    private Label lbScreen;

    @FXML
    private Label lbCamera;

    @FXML
    private Label lbSystem;

    @FXML
    private Label lbChip;

    @FXML
    private Label lbMemory;

    @FXML
    private Label lbBattery;
    
    @FXML
    private JFXButton btnAddToCart;
    
    @FXML
    private Spinner<Integer> Spinner;
    
    @FXML
    private JFXButton btnCartClick;

    @FXML
    private JFXComboBox<String> cbbMfg;

    @FXML
    void HomeClick(ActionEvent event) throws IOException {
        Navigator.getInstance().goToStore("");
    }

    @FXML
    void btnBackClick(ActionEvent event) throws IOException {
        if (!UserName.search.isEmpty()) {
            Navigator.getInstance().goToStore(UserName.search);
        } else if (!UserName.sbb.isEmpty()) {
            Navigator.getInstance().goToStore("");
        } else {
            Navigator.getInstance().goToStore("");
        }
    }

    @FXML
    void btnLogOut(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cảnh báo");
        alert.setHeaderText("Bạn có muốn đăng xuất không?");
        Optional<ButtonType> ok = alert.showAndWait();
        if (ok.get() == ButtonType.OK) {
            UserName.sbb = "";
            Navigator.getInstance().goToLogin();
        }
    }

    @FXML
    void btnSearchClick(ActionEvent event) throws IOException {
        UserName.sbb = "";
        Navigator.getInstance().goToStore(searchBar.getText());
        UserName.search = searchBar.getText();
    }
    
    @FXML
    void cbbMfgClick(ActionEvent event) throws IOException {
        UserName.sbb = cbbMfg.getValue().toString();
        Navigator.getInstance().goToStore("");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        txtUserName.setText(UserName.username);
        cbbMfg.setItems(smartphonedao.selectmanu());
        cbbMfg.setValue(UserName.sbb);
        int initialValue = 1;
        SpinnerValueFactory<Integer> valueFactory
                = //
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 99999, initialValue);

        Spinner.setValueFactory(valueFactory);
        System.out.println("#ProductView2Controller initialized!");
    }

    public void initialize(String name) {
        ObservableList<SmartPhone> smartphone = smartphonedao.selectByName(name);

        File file = new File(smartphone.get(0).getLink());
        String localUrl = file.toURI().toString();
        Image image = new Image(localUrl);
        Img.setImage(image);

        UserName.id = smartphone.get(0).getProductID();
        lbName.setText(smartphone.get(0).getName());
        lbPrice.setText(smartphone.get(0).getPrice() + "$");
        lbScreen.setText(smartphone.get(0).getScreen());
        lbCamera.setText(smartphone.get(0).getCamera());
        lbSystem.setText(smartphone.get(0).getSystem());
        lbChip.setText(smartphone.get(0).getChip());
        lbMemory.setText(smartphone.get(0).getMemory());
        lbBattery.setText(smartphone.get(0).getBattery());

    }

    @FXML
    private void btnAddToCartClick(ActionEvent event) throws IOException {
        try {
            if (!smartphonedao.addamountifexit(UserName.CartID, UserName.id)) {
                smartphonedao.addtocartdetail(UserName.CartID, UserName.id, Spinner.getValue());
                warning4();
            } else {
                int newamount = smartphonedao.selectAmount(UserName.CartID, UserName.id);
                smartphonedao.updateCart(UserName.CartID, UserName.id, newamount + Spinner.getValue());
                warning4();
            };
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void warning4() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Sản phẩm đã được thêm vào giỏ hàng");
        alert.setTitle("Hoàn thành"); 
        alert.showAndWait();
    }

    @FXML
    private void btnCartClick(ActionEvent event) throws IOException {
        Navigator.getInstance().goToShoppingCart(UserName.CartID);
    }
}
