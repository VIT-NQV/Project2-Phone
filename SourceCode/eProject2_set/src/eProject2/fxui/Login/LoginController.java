/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eProject2.fxui.Login;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import eProject2.UserName;
import eProject2.dao.Account;
import eProject2.dao.AccountDAO;
import eProject2.dao.AccountDAOImp;
import eProject2.fxui.Navigator;
import java.io.IOException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author Admin
 */
public class LoginController implements Initializable {

    private AccountDAO accountdao = new AccountDAOImp();

    ObservableList<String> options
            = FXCollections.observableArrayList(
                    "Quản trị viên",
                    "Khách hàng"
            );

    @FXML
    private JFXTextField txtUserName;

    @FXML
    JFXComboBox<String> ComboBox;

    @FXML
    private JFXButton btnSignUp;

    public void initialize(URL location, ResourceBundle resources) {
        ComboBox.setItems(options);
//        txtUserName.setText("viet.nq.267");
//        txtPassword.setText("123");
    }

    @FXML
    private JFXPasswordField txtPassword;

    @FXML
    private JFXButton btnLogin;

    @FXML
    void btnLoginClick(ActionEvent event) throws SQLException, IOException, Exception {
        try {
            Account account = extractFromFields();
            Account checkedAcc = accountdao.check(account.getUserName());

            if (!checkedAcc.getUserName().isEmpty()
                    && CheckPass(account.getPassword(), checkedAcc.getPassword())) {
                if (checkedAcc.getLogBy() == 1) {
                    if (account.getName().equals("Quản trị viên")) {
                        UserName.username = account.getUserName();
                        UserName.name = account.getUserName();
                        Navigator.getInstance().goToAdminIndex();
                    } else if (account.getName().equals("Khách hàng")) {
                        UserName.username = account.getUserName();
                        UserName.name = account.getUserName();
                        Navigator.getInstance().goToWelcome();
                    }
                } else if (checkedAcc.getLogBy() == 2) {
                    if (account.getName().equals("Quản trị viên")) {
                        warning4();
                    } else if (account.getName().equals("Khách hàng")) {
                        UserName.username = account.getUserName();
                        UserName.name = account.getUserName();
                        Navigator.getInstance().goToWelcome();
                    }
                }
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            Account account = extractFromFields();
            if (account.getUserName().isEmpty() || account.getPassword().isEmpty()) {
                warning1();
            } else if (account.getName() == null) {
                warning3();
            } else {
                warning2();
            }

        }
    }

    private Account extractFromFields() throws Exception {
        Account account = new Account();
        account.setUserName(txtUserName.getText());
        account.setPassword(txtPassword.getText());
        account.setName(ComboBox.getValue());

        return account;
    }

    private void warning1() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText("Hãy nhập tài khoản và mật khẩu");
        alert.setTitle("WARNING");
        alert.showAndWait();
    }

    private void warning2() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText("Bạn đã nhập sai tài khoản hoặc mật khẩu. Vui lòng nhập lại!");
        alert.setTitle("WARNING");
        alert.showAndWait();
    }

    private void warning3() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText("Vui lòng chọn đăng nhập bởi Quản trị viên hoặc Khách hàng");
        alert.setTitle("WARNING");
        alert.showAndWait();
    }

    private void warning4() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText("Bạn không phải là quản trị viên");
        alert.setTitle("WARNING");
        alert.showAndWait();
    }

    private boolean CheckPass(String inputPassword, String hashPassWord) {
        String myChecksum = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(inputPassword.getBytes());
            byte[] digest = md.digest();
            myChecksum = DatatypeConverter
                    .printHexBinary(digest).toUpperCase();
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e.getMessage());
        }

        return hashPassWord.equals(myChecksum);
    }

    @FXML
    private void btnSignUpClick(ActionEvent event) throws IOException {
        Navigator.getInstance().goToSignUp();
    }

//    private void loading() throws IOException, InterruptedException {
//        FXMLLoader fxmlloader = new FXMLLoader();
//        fxmlloader.setLocation(getClass().getResource("Splash.fxml"));
//        AnchorPane anchorpane = fxmlloader.load();
//        SplashController sp = fxmlloader.getController();
//        splash.add(anchorpane, 0, 1);
//        GridPane.setMargin(anchorpane, new Insets(10));
//
//        sp.initialize();
//        Timer myTimer = new Timer();
//        myTimer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                Platform.runLater(() -> {
//                    try {
//                        Navigator.getInstance().goToStore("");
//                    } catch (Exception ex) {
//                        System.out.println(ex.getMessage());
//                    }
//                });
//            }
//        }, 1000);
//    }
}
