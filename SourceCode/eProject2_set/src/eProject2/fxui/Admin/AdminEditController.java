/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eProject2.fxui.Admin;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import eProject2.UserName;
import eProject2.dao.Account;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import eProject2.dao.Admin;
import eProject2.dao.AdminDAO;
import eProject2.dao.AdminDAOImp;
import eProject2.fxui.Navigator;
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

/**
 *
 * @author Admin
 */
public class AdminEditController implements Initializable {

    private AdminDAO admindao = new AdminDAOImp();
    
    private Account a = new Account();

    private Admin editAdmin = null;

    @FXML
    private TableView<Admin> tvAdmin;

    @FXML
    private TableColumn<Admin, String> tcAccount;

    @FXML
    private TableColumn<Admin, String> tcPassword;

    @FXML
    private TableColumn<Admin, String> tcName;

    @FXML
    private TableColumn<Admin, String> tcEmail;

    @FXML
    private TableColumn<Admin, String> tcNumberPhone;

    @FXML
    private JFXTextField txtAccount;

    @FXML
    private JFXPasswordField txtNewPassword;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtNumberPhone;

    @FXML
    private Label lbMessage;

    @FXML
    private JFXButton btnClear;

    @FXML
    private JFXButton btnNew;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnReset;

    @FXML
    private JFXPasswordField txtOldPassword;

    @FXML
    private JFXButton btnAdmin;

    @FXML
    private JFXButton btnSmartPhone;

    @FXML
    private JFXButton btnMfg;

    @FXML
    private JFXButton btnImage;
    
    @FXML
    private JFXButton btnOrder;

    @FXML
    private JFXButton LogOut;
    
    @FXML
    private Label txtUsername;
    
    @FXML
    void btnOrderClick(ActionEvent event) throws IOException {
        Navigator.getInstance().goToOrder();
    }

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
    void btnClear(ActionEvent event) {
        txtAccount.setText("");
        txtOldPassword.setText("");
        txtNewPassword.setText("");
        txtName.setText("");
        txtEmail.setText("");
        txtNumberPhone.setText("");
    }

    @FXML
    void btnNewClick(ActionEvent event) throws IOException {
        Navigator.getInstance().goToAdminIndex();
    }

    @FXML
    void btnMfgClick(ActionEvent event) throws IOException {
        Navigator.getInstance().goToManufacturerIndex();
    }

    @FXML
    void btnImageClick(ActionEvent event) {

    }

    @FXML
    void btnResetClick(ActionEvent event) throws IOException {
        Navigator.getInstance().goToAdminIndex();
    }

    @FXML
    void btnSaveClick(ActionEvent event) {
        if (check()) {
            try {
                Admin updateAdmin = news();
                updateAdmin.setId(this.editAdmin.getId());

                boolean result = admindao.update(updateAdmin);

                if (result) {
                    lbMessage.setText("update successfully");
                    Navigator.getInstance().goToAdminIndex();
                } else {
                    lbMessage.setText("update unsuccessfully");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    @FXML
    void btnSmartPhoneClick(ActionEvent event) throws IOException {
        Navigator.getInstance().goToSmartPhoneIndex(); 
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        System.out.println("#IndexUIController initialized!");
        ObservableList<Admin> admin = admindao.selectAll();
        tvAdmin.setItems(admin);
        txtUsername.setText(UserName.username);

        tcAccount.setCellValueFactory((a) -> {
            return a.getValue().getAccountProperty();
        });

        tcPassword.setCellValueFactory((a) -> {
            return a.getValue().getPasswordProperty();
        });

        tcName.setCellValueFactory((a) -> {
            return a.getValue().getNameProperty();
        });

        tcEmail.setCellValueFactory((a) -> {
            return a.getValue().getEmailProperty();
        });

        tcNumberPhone.setCellValueFactory((a) -> {
            return a.getValue().getNumberPhoneProperty();
        });
    }

    public void initialize() {

    }

    public void initialize(Admin editAdmin) {
        this.editAdmin = editAdmin;

        txtAccount.setText(editAdmin.getAccount());
        txtName.setText(editAdmin.getName());
        txtEmail.setText(editAdmin.getEmail());
        txtNumberPhone.setText(editAdmin.getNumberPhone());

        lbMessage.setText("Update SmartPhone");
    }

    private Admin news() {
        Admin admin = new Admin();
        admin.setAccount(txtAccount.getText());
        admin.setPassword(txtNewPassword.getText());
        admin.setName(txtName.getText());
        admin.setEmail(txtEmail.getText());
        admin.setNumberPhone(txtNumberPhone.getText());
        return admin;
    }

    private boolean check() {

        if (txtAccount.getText().isEmpty()) {
            lbMessage.setText("Account cannot be empty");
            return false;
        } else if (txtAccount.getText().length() > 50) {
            lbMessage.setText("Account cannot be larger than 50 characters");
            return false;
        }

        if (txtOldPassword.getText().isEmpty()) {
            lbMessage.setText("Password cannot be empty");
            return false;
        }
        
        if (txtNewPassword.getText().isEmpty()) {
            lbMessage.setText("Password cannot be empty");
            return false;
        }

        if (txtName.getText().isEmpty()) {
            lbMessage.setText("Name cannot be empty");
            return false;
        } else if (txtName.getText().length() > 255) {
            lbMessage.setText("Name cannot be larger than 255 characters");
            return false;
        }

        if (txtEmail.getText().isEmpty()) {
            lbMessage.setText("Email cannot be empty");
            return false;
        } else if (txtEmail.getText().length() > 255) {
            lbMessage.setText("Email cannot be larger than 255 characters");
            return false;
        } else if (checkEmail(txtEmail.getText()) == false) {
            lbMessage.setText("Email is incorrect");
            return false;
        }

        if (txtNumberPhone.getText().isEmpty()) {
            lbMessage.setText("Number Phone cannot be empty");
            return false;
        } else if (txtNumberPhone.getText().length() > 255) {
            lbMessage.setText("Number cannot be larger than 255 characters");
            return false;
        } else if (chekcNumberPhone(txtNumberPhone.getText()) == false) {
            lbMessage.setText("Number Phone is incorrect");
            return false;
        }

        return true;
    }

    private boolean checkEmail(String email) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }

    private boolean chekcNumberPhone(String numberPhone) {
        String regex = "^(03|05|07|08|09)+([0-9]{8})$";
        return numberPhone.matches(regex);
    }

}
