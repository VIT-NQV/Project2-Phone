package eProject2.fxui.manufacturer;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import eProject2.UserName;
import eProject2.dao.Manufacturer;
import eProject2.dao.ManufacturerDAO;
import eProject2.dao.ManufacturerDAOImp;
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

public class ManufacturerEditController implements Initializable {

    private ManufacturerDAO mfgdao = new ManufacturerDAOImp();

    private Manufacturer editMfg = null;

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
    private JFXButton btnSave;

    @FXML
    private JFXButton btnBack;

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
    void btnBackClick(ActionEvent event) throws IOException {
        Navigator.getInstance().goToManufacturerIndex();
    }

    @FXML
    void btnClearClick(ActionEvent event) {
        txtName.setText("");
        txtCountry.setText("");
    }


    @FXML
    void btnMfgClick(ActionEvent event) throws IOException {
        Navigator.getInstance().goToManufacturerIndex();
    }

    @FXML
    void btnSaveClick(ActionEvent event) {
        if (check()) {
            try {
                Manufacturer updateM = news();
                updateM.setMfgID(this.editMfg.getMfgID());

                boolean result = mfgdao.update(updateM);

                if (result) {
                    lbMessage.setText("update successfully");
                    Navigator.getInstance().goToManufacturerIndex();
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
    
    public void initialize() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        System.out.println("#IndexUIController initialized!");
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
    
    public void initialize(Manufacturer editM) {
        this.editMfg = editM;
        String msg = "";

        msg = "update Manufacturer";

        txtName.setText(editM.getName());
        txtCountry.setText(editM.getCountry());

        lbMessage.setText(msg);
    }

    private Manufacturer news() {
        Manufacturer mfg = new Manufacturer();
        mfg.setName(txtName.getText());
        mfg.setCountry(txtCountry.getText());
        return mfg;
    }

    private boolean check() {
        String msg = "";
        boolean check = true;

        if (txtName.getText().isEmpty()) {
            msg = "Name cannot be empty \n";
            check = false;
        } else if (txtName.getText().length() > 255){
            msg += "Name cannot be larger than 50 characters \n";
            check = false;
        }

        if (txtCountry.getText().isEmpty()) {
            msg += "Country cannot be empty \n";
            check = false;
        } else if (txtCountry.getText().length() > 255){
            msg += "Country cannot be larger than 50 characters \n";
            check = false;
        }
        
        lbMessage.setText(msg);
        return check;
    }
}
