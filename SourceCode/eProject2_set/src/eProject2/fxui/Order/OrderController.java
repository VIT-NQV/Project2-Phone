/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eProject2.fxui.Order;

import com.jfoenix.controls.JFXButton;
import eProject2.UserName;
import eProject2.fxui.Navigator;
import java.io.IOException;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 *
 * @author Nguyễn Quốc Việt
 */
public class OrderController {

    @FXML
    private JFXButton btnAdmin;

    @FXML
    private JFXButton btnSmartPhone;

    @FXML
    private JFXButton btnMfg;

    @FXML
    private JFXButton LogOut;

    @FXML
    private Label txtUsername;

    @FXML
    private JFXButton btnOrder;

    @FXML
    private TableView<?> tvOrder;
    
    @FXML
    private TableColumn<?, ?> tcOrderID;

    @FXML
    private TableColumn<?, ?> tcCustomerName;

    @FXML
    private TableColumn<?, ?> tcProductName;

    @FXML
    private TableColumn<?, ?> tcAmount;

    @FXML
    private TableColumn<?, ?> tcTotal;
    
    @FXML
    private TableColumn<?, ?> tcDate;

    @FXML
    private JFXButton btnReset;

    @FXML
    void btnAdminClick(ActionEvent event) throws IOException {
        Navigator.getInstance().goToAdminIndex();
    }

    @FXML
    void btnLogoutClick(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cảnh báo");
        alert.setHeaderText("Bạn có muốn đăng xuất không?");
        Optional<ButtonType> ok = alert.showAndWait();
        if (ok.get() == ButtonType.OK) {
            Navigator.getInstance().goToLogin();
        }
    }

    @FXML
    void btnMfgClick(ActionEvent event) throws IOException {
        Navigator.getInstance().goToManufacturerIndex();
    }

    @FXML
    void btnOrderClick(ActionEvent event) throws IOException {
        Navigator.getInstance().goToOrder();
    }

    @FXML
    void btnResetClick(ActionEvent event) throws IOException {
        Navigator.getInstance().goToOrder();
    }

    @FXML
    void btnSmartPhoneClick(ActionEvent event) throws IOException {
        Navigator.getInstance().goToSmartPhoneIndex();
    }

    public void initialize() {
        txtUsername.setText(UserName.username);
    }
}
