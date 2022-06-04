/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eProject2.fxui.Login;

import eProject2.fxui.Navigator;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;

/**
 *
 * @author Nguyễn Quốc Việt
 */
public class WelcomeController {

    public void initialize() throws IOException, InterruptedException {
        loading();
    }

    private void loading() throws IOException, InterruptedException {
        Timer myTimer = new Timer();
        myTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    try {
                        Navigator.getInstance().goToStore("");
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage() + " WELCOME_FXML");
                    }
                });
            }
        }, 2500);
    }
}
