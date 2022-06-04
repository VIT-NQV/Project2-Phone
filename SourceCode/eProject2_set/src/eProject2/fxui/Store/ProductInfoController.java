/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eProject2.fxui.Store;

import com.jfoenix.controls.JFXButton;
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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class ProductInfoController implements Initializable {

    private SmartPhoneDAO smartphonedao = new SmartPhoneDAOImp();

    @FXML
    private ImageView image;

    @FXML
    private Label name;

    @FXML
    Label price;

    @FXML
    Spinner<Integer> amount;

    @FXML
    Label total_price;

    @FXML
    private JFXButton deleteBtn;

    @FXML
    CheckBox checkbox;

    @FXML
    void deleteBtnClick(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText("Bạn có muốn xóa sản phẩm này khỏi giỏ hàng không?");
        Optional<ButtonType> ok = alert.showAndWait();
        if (ok.get() == ButtonType.OK) {
            smartphonedao.deleteCart(smartphonedao.selectProductIdByName(name.getText()));
            Navigator.getInstance().goToShoppingCart(UserName.CartID);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void initialize(int i) {
        int initialValue = i;
        SpinnerValueFactory<Integer> valueFactory
                = //
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 99999, initialValue);
        amount.setValueFactory(valueFactory);
    }

    public void setData(SmartPhone smartphone) {
        String link = smartphonedao.SelectImg(smartphone.getProductID().toString());
        File file = new File(link);
        String localUrl = file.toURI().toString();
        Image images = new Image(localUrl);
        image.setImage(images);
        name.setText(smartphone.getName());
        price.setText(smartphone.getPrice() + "$");
        total_price.setText(Integer.toString(Integer.parseInt(smartphone.getPrice()) * smartphone.getAmount()) + "$");
    }

}
