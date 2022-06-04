/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eProject2.fxui.SmartPhone;

//import com.sun.java.accessibility.util.Translator;
//import static com.sun.media.jfxmediaimpl.MediaUtils.error;
//import com.sun.xml.internal.ws.api.server.Module;
//import static java.awt.SystemColor.info;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import eProject2.UserName;
import eProject2.dao.SmartPhone;
import eProject2.dao.SmartPhoneDAO;
import eProject2.dao.SmartPhoneDAOImp;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;

/**
 * FXML Controller class
 *
 * @author anhoa
 */
public class SmartPhoneEditController implements Initializable {

//    private static Logger logger = LogManager.getLogger(DemoSmartPhoneEditController.class);

    private SmartPhoneDAO smartphonedao = new SmartPhoneDAOImp();

    private SmartPhone editphone = null;

    @FXML
    private TableView<SmartPhone> tvSmartPhone;

    @FXML
    private TableColumn<SmartPhone, String> tcName;

    @FXML
    private TableColumn<SmartPhone, String> tcMfg;

    @FXML
    private TableColumn<SmartPhone, String> tcPrice;

    @FXML
    private TableColumn<SmartPhone, String> tcScreen;

    @FXML
    private TableColumn<SmartPhone, String> tcSystem;

    @FXML
    private TableColumn<SmartPhone, String> tcCamera;

    @FXML
    private TableColumn<SmartPhone, String> tcChip;

    @FXML
    private TableColumn<SmartPhone, String> tcMemory;

    @FXML
    private TableColumn<SmartPhone, String> tcBattery;

    @FXML
    private JFXComboBox cbbMfg;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtPrice;

    @FXML
    private JFXTextField txtScreen;

    @FXML
    private JFXTextField txtSystem;

    @FXML
    private Label lbMessage;

    @FXML
    private JFXButton btnClear;

    @FXML
    private JFXButton btnBackToIndex;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXTextField txtCamera;

    @FXML
    private JFXTextField txtChip;

    @FXML
    private JFXTextField txtBattery;

    @FXML
    private JFXTextField txtMemory;

    @FXML
    private JFXButton btnsmartphone;

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
    void btnClear(ActionEvent event) {
        txtName.setText("");
        txtPrice.setText("");
        txtScreen.setText("");
        txtSystem.setText("");
        txtCamera.setText("");
        txtChip.setText("");
        txtMemory.setText("");
        txtBattery.setText("");
    }

    @FXML
    void btnBackToIndexClick(ActionEvent event) throws IOException {
        Navigator.getInstance().goToSmartPhoneIndex();
    }

    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("#EditUIController initialized!");

        cbbMfg.setItems(smartphonedao.selectmanu());
        ObservableList<SmartPhone> smartphone = smartphonedao.selectAll();
        tvSmartPhone.setItems(smartphone);

        tcName.setCellValueFactory((phone) -> {
            return phone.getValue().getNameProperty();
        });

        tcMfg.setCellValueFactory((phone) -> {
            return phone.getValue().getMfgNameProperty();
        });

        tcPrice.setCellValueFactory((phone) -> {
            return phone.getValue().getPriceProperty();
        });

        tcScreen.setCellValueFactory((phone) -> {
            return phone.getValue().getScreenProperty();
        });

        tcSystem.setCellValueFactory((phone) -> {
            return phone.getValue().getSystemProperty();
        });

        tcCamera.setCellValueFactory((phone) -> {
            return phone.getValue().getCameraProperty();
        });

        tcChip.setCellValueFactory((phone) -> {
            return phone.getValue().getChipProperty();
        });

        tcMemory.setCellValueFactory((phone) -> {
            return phone.getValue().getMemoryProperty();
        });

        tcBattery.setCellValueFactory((phone) -> {
            return phone.getValue().getBatteryProperty();
        });
    }

    public void initialize(SmartPhone editSmartPhone) {
        this.editphone = editSmartPhone;
        txtUsername.setText(UserName.username);

        String msg = "";

        msg = "update Phone";
        txtName.setText(editphone.getName());
        cbbMfg.setValue(editphone.getMfgName());
        txtPrice.setText(editphone.getPrice());
        txtScreen.setText(editphone.getScreen());
        txtSystem.setText(editphone.getSystem());
        txtCamera.setText(editphone.getCamera());
        txtChip.setText(editphone.getChip());
        txtBattery.setText(editphone.getBattery());
        txtMemory.setText(editphone.getMemory());

    }

//    private SmartPhone extractSmartPhoneFromFields() {
//    }
    @FXML
    void btnSaveClick(ActionEvent event) {
        if (check()) {
            try {
                SmartPhone edit = news();
                edit.setProductID(this.editphone.getProductID());

                boolean result = smartphonedao.update(edit);

                if (result) {
                    lbMessage.setText("update successfully");
                    Navigator.getInstance().goToSmartPhoneIndex();
                } else {
                    lbMessage.setText("update unsuccessfully");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
       
    }

    @FXML
    void btnMfgClick(ActionEvent event) throws IOException {
        Navigator.getInstance().goToManufacturerIndex();
    }

    @FXML
    void btnSmartPhoneClick(ActionEvent event) throws IOException {
        Navigator.getInstance().goToSmartPhoneIndex();
    }

    @FXML
    void btnAdminClick(ActionEvent event) throws IOException {
        Navigator.getInstance().goToAdminIndex();
    }

    private SmartPhone news() {
        SmartPhone smartphone = new SmartPhone();
        smartphone.setMfgID(smartphonedao.selectIdByManuName(cbbMfg.getValue().toString()));
        smartphone.setName(txtName.getText());
        smartphone.setPrice(txtPrice.getText());
        smartphone.setSystem(txtSystem.getText());
        smartphone.setScreen(txtScreen.getText());
        smartphone.setCamera(txtCamera.getText());
        smartphone.setChip(txtChip.getText());
        smartphone.setMemory(txtMemory.getText());
        smartphone.setBattery(txtBattery.getText());
        return smartphone;
    }
    
        private boolean check() {
        
        if (txtName.getText().isEmpty()) {
            lbMessage.setText("Name cannot be empty");
            return false;
        } 
        
        if (txtName.getText().length() > 50) {
            lbMessage.setText("Name cannot be larger than 50 characters");
            return false;
        }

        if (cbbMfg.getValue().toString() == null) {
            lbMessage.setText("Price cannot be empty");
            return false;
        }

        if (txtPrice.getText().isEmpty()) {
            lbMessage.setText("Price cannot be empty");
            return false;
        }

        if (txtScreen.getText().isEmpty()) {
            lbMessage.setText("Screen cannot be empty");
            return false;
        }

        if (txtSystem.getText().isEmpty()) {
            lbMessage.setText("System cannot be empty");
            return false;
        }

        if (txtCamera.getText().isEmpty()) {
            lbMessage.setText("Camera cannot be empty");
            return false;
        }

        if (txtChip.getText().isEmpty()) {
            lbMessage.setText("Chip cannot be empty");
            return false;
        }

        if (txtMemory.getText().isEmpty()) {
            lbMessage.setText("Memory cannot be empty");
            return false;
        }

        if (txtBattery.getText().isEmpty()) {
            lbMessage.setText("Battery cannot be empty");    
            return false;
        }
        return true;
    }
}
