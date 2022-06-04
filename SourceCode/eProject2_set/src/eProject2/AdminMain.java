package eProject2;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import eProject2.fxui.Navigator;
import java.io.IOException;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 * @author Admin
 */
public class AdminMain extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        Navigator.getInstance().setStage(primaryStage);
        Navigator.getInstance().getStage().setTitle("Admin");       
        Navigator.getInstance().goToLogin();
        Navigator.getInstance().getStage().show();
        primaryStage.setResizable(false);
    }
    
    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
