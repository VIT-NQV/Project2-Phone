/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eProject2.fxui.Login;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import eProject2.dao.Account;
import eProject2.dao.AccountDAO;
import eProject2.dao.AccountDAOImp;
import eProject2.fxui.Navigator;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class SignUpUIController implements Initializable {

    private AccountDAO accountdao = new AccountDAOImp();

    @FXML
    private JFXTextField txtUserName;

    @FXML
    private JFXPasswordField txtPassword;

    @FXML
    private JFXButton btnSubmit;

    @FXML
    private JFXButton btnBack;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtPhoneNum;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private Label lbMessage1;
    
    @FXML
    private Label lbMessage2;
    
    @FXML
    private Label lbMessage3;
    
    @FXML
    private Label lbMessage4;
    
    @FXML
    private Label lbMessage5;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void btnSubmitClick(ActionEvent event) throws Exception {
        if (check()) {
            try {
                Account newMem = extractFromFields();
                System.out.println(newMem.getName());
                newMem = accountdao.insertNewMem(newMem);

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            Success();
            Navigator.getInstance().goToLogin();
        }
    }

    @FXML
    private void btnBackClick(ActionEvent event) throws IOException {
        Navigator.getInstance().goToLogin();
    }

    private Account extractFromFields() throws Exception {
        Account account = new Account();
        account.setUserName(txtUserName.getText());
        account.setPassword(txtPassword.getText());
        account.setName(txtName.getText());
        account.setNumberPhone(txtPhoneNum.getText());
        account.setEmail(txtEmail.getText());

        return account;
    }

    private void Success() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText("Thank you for Sign Up ");
        alert.showAndWait();
    }

    private boolean check() {
        
        boolean check = true;

        //UserName
        if (txtUserName.getText().isEmpty()) {
            lbMessage1.setText("B???n c???n nh???p t??i kho???n");
            check = false;
        } else if (txtUserName.getText().length() > 50) {
            lbMessage1.setText("T??i kho???n kh??ng ???????c nhi???u h??n 50 k?? t???");
            check = false; 
        } else if (!txtUserName.getText().isEmpty()) {
            lbMessage1.setText("");
            check = true;
        } else if (!(txtUserName.getText().length() > 50)) {
            lbMessage1.setText("");
            check = true;
        }

        //Password
        if (txtPassword.getText().isEmpty()) {
            lbMessage2.setText("B???n c???n nh???p m???t kh???u");
            check = false;
        } else if (!txtPassword.getText().isEmpty()) {
            lbMessage2.setText("");
            check = true;
        }

        //Name
        if (txtName.getText().isEmpty()) {
            lbMessage3.setText("B???n c???n nh???p H??? v?? t??n");
            check = false;
        } else if (txtName.getText().length() > 255) {
            lbMessage3.setText("H??? v?? t??n kh??ng ???????c nhi???u h??n 255 k?? t???");
            check = false;
        } else if (!txtName.getText().isEmpty()) {
            lbMessage3.setText("");
            check = true;
        } else if (!(txtName.getText().length() > 255)) {
            lbMessage3.setText("");
            check = true;
        }

        //PhoneNum
        if (txtPhoneNum.getText().isEmpty()) {
            lbMessage4.setText("B???n c???n nh???p s??? ??i???n tho???i");
            check = false;
        } else if (txtPhoneNum.getText().length() > 255) {
            lbMessage4.setText("S??? ??i???n tho???i kh??ng ???????c nhi???u h??n 255 k?? t???");
            check = false;
        } else if (chekcNumberPhone(txtPhoneNum.getText()) == false) {
            lbMessage4.setText("S??? ??i???n tho???i kh??ng ch??nh x??c");
            check = false;
        } else if (!txtPhoneNum.getText().isEmpty()) {
            lbMessage4.setText("");
            check = true;
        } else if (!(txtPhoneNum.getText().length() > 255)) {
            lbMessage4.setText("");
            check = true;
        } else if (!(chekcNumberPhone(txtPhoneNum.getText()) == false)) {
            lbMessage4.setText("");
            check = true;
        }
        
        //Email
        if (txtEmail.getText().isEmpty()) {
            lbMessage5.setText("B???n c???n nh???p Email");
            check = false;
        } else if (txtEmail.getText().length() > 255) {
            lbMessage5.setText("Email kh??ng ???????c nhi???u h??n 255 k?? t???");
            check = false;
        } else if (checkEmail(txtEmail.getText()) == false) {
            lbMessage5.setText("Email kh??ng ch??nh x??c");
            check = false;
        } else if (!txtEmail.getText().isEmpty()) {
            lbMessage5.setText("");
            check = true;
        } else if (!(txtEmail.getText().length() > 255)) {
            lbMessage5.setText("");
            check = true;
        } else if (!(checkEmail(txtEmail.getText()) == false)) {
            lbMessage5.setText("");
            check = true;
        }

        return check;
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
