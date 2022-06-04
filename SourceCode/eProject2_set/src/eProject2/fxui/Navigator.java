/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eProject2.fxui;

import eProject2.fxui.Admin.AdminEditController;
import eProject2.dao.Admin;
import eProject2.dao.Manufacturer;
import eProject2.dao.SmartPhone;
import eProject2.fxui.SmartPhone.SmartPhoneEditController;
import eProject2.fxui.Store.ProductView2Controller;
import eProject2.fxui.Store.ShoppingCartController;
import eProject2.fxui.Store.StoreUIController;
import eProject2.fxui.manufacturer.ManufacturerEditController;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Admin
 */
public class Navigator {

    public static final String ADMIN_INDEX = "Admin/AdminIndex.fxml";
    public static final String ADMIN_EDIT = "Admin/AdminEdit.fxml";

    public static final String SMARTPHONE_INDEX = "SmartPhone/SmartPhoneIndex.fxml";
    public static final String SMARTPHONE_EDIT = "SmartPhone/SmartPhoneEdit.fxml";

    public static final String MANUFACTURER_INDEX = "manufacturer/ManufacturerIndex.fxml";
    public static final String MANUFACTURER_EDIT = "manufacturer/ManufacturerEdit.fxml";
    
    public static final String ORDER_FXML = "Order/Order.fxml";

    public static final String LOGIN_FXML = "Login/LoginUI.fxml";
    public static final String SIGNUP_FXML = "Login/SignUpUI.fxml";
    public static final String WELCOME_FXML = "Login/Welcome.fxml";

    public static final String STORE_FXML = "Store/Store.fxml";
    public static final String PRODUCT_VIEW2 = "Store/ProductView2.fxml";
    public static final String SHOPPINGCART_FXML = "Store/ShoppingCart.fxml";
    

    private FXMLLoader loader;
    private Stage stage = null;

    private static Navigator nav = null;

    private Navigator() {

    }

    public static Navigator getInstance() {
        if (nav == null) {
            nav = new Navigator();
        }
        return nav;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Stage getStage() {
        return this.stage;
    }

    private void goTo(String fxml) throws IOException {
        this.loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxml));
//        loader.setResources(Translator.getResource());
        Parent root = loader.load();
        Scene scene = new Scene(root);
        this.stage.setScene(scene);
        this.stage.centerOnScreen();
    }

    public void goToAdminIndex() throws IOException {
        this.goTo(ADMIN_INDEX);
    }

    public void goToAdminEdit(Admin editAdmin) throws IOException {
        this.goTo(ADMIN_EDIT);
        AdminEditController ctrl = loader.getController();
        ctrl.initialize(editAdmin);
    }

    public void goToSmartPhoneIndex() throws IOException {
        this.goTo(SMARTPHONE_INDEX);
    }

    public void goToSmartPhoneEdit(SmartPhone editSmartPhone) throws IOException {
        this.goTo(SMARTPHONE_EDIT);
        SmartPhoneEditController ctrl = loader.getController();
        ctrl.initialize(editSmartPhone);
    }

    public void goToManufacturerIndex() throws IOException {
        this.goTo(MANUFACTURER_INDEX);
    }

    public void goToManufacturerEdit(Manufacturer editm) throws IOException {
        this.goTo(MANUFACTURER_EDIT);
        ManufacturerEditController ctrl = loader.getController();
        ctrl.initialize(editm);
    }

    public void goToLogin() throws IOException {
        this.goTo(LOGIN_FXML);
    }

    public void goToStore(String a) throws IOException {
        this.goTo(STORE_FXML);
        StoreUIController ctrl = loader.getController();
        ctrl.initialize(a);
    }

    public void goToSignUp() throws IOException {
        this.goTo(SIGNUP_FXML);
    }

    public void goToProductView2(String name) throws IOException {
        this.goTo(PRODUCT_VIEW2);
        ProductView2Controller ctrl = loader.getController();
        ctrl.initialize(name);
    }
    
    public void goToShoppingCart(int id) throws IOException{
        this.goTo(SHOPPINGCART_FXML);
        ShoppingCartController ctrl = loader.getController();
        ctrl.initialize(id);
    }
    
    public void goToOrder() throws IOException {
        this.goTo(ORDER_FXML);
    }
    
    public void goToWelcome() throws IOException {
        this.goTo(WELCOME_FXML);
    }
}
