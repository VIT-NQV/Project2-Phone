/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eProject2.fxui.manufacturer;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import eProject2.UserName;
import eProject2.dao.Manufacturer;
import eProject2.dao.ManufacturerDAO;
import eProject2.dao.ManufacturerDAOImp;
import eProject2.fxui.Navigator;
import java.io.IOException;
import java.util.Optional;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 *
 * @author
 */
public class ManufacturerIndexController {

    private ManufacturerDAO mfgdao = new ManufacturerDAOImp();

    @FXML
    private TableView<Manufacturer> tvManufacturer;

    @FXML
    private TableColumn<Manufacturer, String> tcName;

    @FXML
    private TableColumn<Manufacturer, String> tcCountry;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtCountry;

    @FXML
    private Label lbMessage;

    @FXML
    private JFXButton btnClear;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnEdit;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnReset;

    @FXML
    private JFXButton btnAdmin;

    @FXML
    private JFXButton btnSmartPhone;

    @FXML
    private JFXButton btnMfg;

    @FXML
    private JFXButton btnImage;

    @FXML
    private JFXButton btnMfg11;
    
    @FXML
    private JFXButton LogOut;
    
    @FXML
    private Label txtUsername;
    
    @FXML
    private JFXButton btnOrder;
    
    @FXML
    void btnOrderClick(ActionEvent event) throws IOException {
        Navigator.getInstance().goToOrder();
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
    void btnAdminClick(ActionEvent event) throws IOException {
        Navigator.getInstance().goToAdminIndex();
    }

    @FXML
    void btnClear(ActionEvent event) {
        txtName.setText("");
        txtCountry.setText("");
    }

    @FXML
    void btnDeleteClick(ActionEvent event) {
        Manufacturer selectM = tvManufacturer.getSelectionModel().getSelectedItem();

        if (selectM == null) {
            selectAdminWarning();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Delete a manufacturer");
            alert.setHeaderText("Are you sure you want to delete the selected manufacturer?");
            Optional<ButtonType> ok = alert.showAndWait();
            if (ok.get() == ButtonType.OK) {
                Manufacturer deleteM = selectM;
                boolean result = mfgdao.delete(deleteM);
                if (result) {
                    tvManufacturer.getItems().remove(deleteM);
                    System.out.println("Manufacturer is deleted");
//                    logger.info("Delete completed");
                } else {
                    System.out.println("No Manufacturer is deleted");
//                    logger.info("Delete not completed");
                }
            }
        }
    }

    @FXML
    void btnEditClick(ActionEvent event) throws IOException {
        Manufacturer editM = tvManufacturer.getSelectionModel().getSelectedItem();
        if (editM == null) {
            selectAdminWarning();
        } else {
            Navigator.getInstance().goToManufacturerEdit(editM);
        }
    }


    @FXML
    void btnMfgClick(ActionEvent event) throws IOException {
        Navigator.getInstance().goToManufacturerIndex();
    }

    @FXML
    void btnResetClick(ActionEvent event) throws IOException {
        Navigator.getInstance().goToManufacturerIndex();
    }

    @FXML
    void btnSaveClick(ActionEvent event) throws IOException {
        if (check()) {
            Manufacturer insertM = news();
            insertM = mfgdao.insert(insertM);
            lbMessage.setText("Insert Successful");
            Navigator.getInstance().goToManufacturerIndex();
        }

    }

    @FXML
    void btnSmartPhoneClick(ActionEvent event) throws IOException {
        Navigator.getInstance().goToSmartPhoneIndex();
    }

    public void initialize() {
        System.out.println("#Manufacturer IndexUIController initialized!");
        ObservableList<Manufacturer> mfg = mfgdao.selectAll();
        tvManufacturer.setItems(mfg);
        txtUsername.setText(UserName.username);

        tcName.setCellValueFactory((a) -> {
            return a.getValue().getNameProperty();
        });

        tcCountry.setCellValueFactory((a) -> {
            return a.getValue().getCountryProperty();
        });
    }

    private Manufacturer news() {
        Manufacturer mfg = new Manufacturer();
        mfg.setName(txtName.getText());
        mfg.setCountry(txtCountry.getText());
        return mfg;
    }

    private void selectAdminWarning() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Please select a Manufacturer");
        alert.setHeaderText("A Manufacturer must be selected for the operation");
        alert.showAndWait();
    }

    private boolean check() {
        String msg = "";
        boolean check = true;

        if (txtName.getText().isEmpty()) {
            msg = "Name cannot be empty \n";
            check = false;
        } else if (txtName.getText().length() > 255) {
            msg += "Name cannot be larger than 50 characters \n";
            check = false;
        }

        if (txtCountry.getText().isEmpty()) {
            msg += "Country cannot be empty \n";
            check = false;
        } else if (txtCountry.getText().length() > 255) {
            msg += "Country cannot be larger than 50 characters \n";
            check = false;
        }

        lbMessage.setText(msg);
        return check;
    }
}
