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
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;

/**
 *
 * @author Admin
 */
public class StoreUIController implements Initializable {

    private SmartPhoneDAO smartphonedao = new SmartPhoneDAOImp();

    private List<SmartPhone> neww;

    @FXML
    private Label txtUserName;

    @FXML
    private GridPane GpPhone;

    @FXML
    private JFXButton btnLogout;

    @FXML
    private TextField searchBar;

    @FXML
    private JFXButton btnSearch;

    @FXML
    private JFXButton HomeBtn;

    @FXML
    private JFXComboBox<String> cbbMfg;

    @FXML
    private JFXButton btnCart;

    @FXML
    void HomeClick(ActionEvent event) throws IOException {
        UserName.sbb = "";
        Navigator.getInstance().goToStore("");
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
//        Navigator.getInstance().goToStore(cbbMfg.getValue().toString());

    }

    public void initialize(URL location, ResourceBundle resources) {
        txtUserName.setText(UserName.username);
        cbbMfg.setItems(smartphonedao.selectmanu());
        cbbMfg.setValue(UserName.sbb);

        int id = smartphonedao.selectIdUser(UserName.username);
        if (!smartphonedao.ifnotexits(id)) {
            smartphonedao.addtocartforuser(id);
        }
        UserName.CartID = smartphonedao.selectCart(id);
    }

    public void initialize(String a) throws IOException {

        if (!UserName.sbb.isEmpty()) {
            ObservableList<SmartPhone> newP = FXCollections.observableArrayList();
            ObservableList<SmartPhone> smartphone = smartphonedao.selectByManu(UserName.sbb);

            for (int i = 0; i < smartphone.size(); i++) {
                SmartPhone phone = new SmartPhone();
                phone.setProductID(smartphone.get(i).getProductID());
                phone.setName(smartphone.get(i).getName());
                phone.setLink(smartphone.get(i).getLink());

                newP.add(phone);
            }

            fill(newP);

        } else {
            if (a.isEmpty()) {
                ObservableList<SmartPhone> neww = phone(a);
                fill(neww);
            } else if (!a.isEmpty()) {
                searchBar.setText(a);
                ObservableList<SmartPhone> neww = phone(a);
                if (neww.isEmpty()) {
                    Warning(a);
                }
                Navigator.getInstance().goToStore("");
//                fill(neww);
            }
        }
        UserName.search = searchBar.getText();

    }

    private ObservableList<SmartPhone> phone(String a) {

        ObservableList<SmartPhone> newP = FXCollections.observableArrayList();
        if (a.isEmpty()) {
            ArrayList<SmartPhone> smartphone = smartphonedao.selectAlltoArray();

            for (int i = 0; i < smartphone.size(); i++) {
                SmartPhone phone = new SmartPhone();
                phone.setProductID(smartphone.get(i).getProductID());
                phone.setName(smartphone.get(i).getName());
                phone.setLink(smartphone.get(i).getLink());

                newP.add(phone);
            }
        }
        if (!a.isEmpty()) {
            ObservableList<SmartPhone> smartphone = smartphonedao.selectByName(a);

            for (int i = 0; i < smartphone.size(); i++) {
                SmartPhone phone = new SmartPhone();
                phone.setProductID(smartphone.get(i).getProductID());
                phone.setName(smartphone.get(i).getName());
                phone.setLink(smartphone.get(i).getLink());

                newP.add(phone);
            }
        }
        return newP;
    }

    private void fill(ObservableList<SmartPhone> neww) {
        int columns = 0;
        int row = 0;

        try {
            for (int i = 0; i < neww.size(); i++) {
                FXMLLoader fxmlloader = new FXMLLoader();
                fxmlloader.setLocation(getClass().getResource("Product.fxml"));
                AnchorPane anchorpane = fxmlloader.load();
                ProductController productController = fxmlloader.getController();
                productController.setData(neww.get(i));

                if (columns == 5) {
                    columns = 0;
                    ++row;
                }

                GpPhone.add(anchorpane, columns++, row);
                GridPane.setMargin(anchorpane, new Insets(9));

                GpPhone.setMinWidth(Region.USE_COMPUTED_SIZE);
                GpPhone.setPrefWidth(Region.USE_COMPUTED_SIZE);
                GpPhone.setMaxWidth(Region.USE_PREF_SIZE);

                GpPhone.setMinHeight(Region.USE_COMPUTED_SIZE);
                GpPhone.setPrefHeight(Region.USE_COMPUTED_SIZE);
                GpPhone.setMaxHeight(Region.USE_PREF_SIZE);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void btnCartClick(ActionEvent event) throws IOException {
        Navigator.getInstance().goToShoppingCart(UserName.CartID);
    }

    private void Warning(String name) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("không tìm thấy kết quả nào phù hợp với từ khóa " + name);
        alert.setTitle("Thông báo");
        alert.showAndWait();
    }

}
