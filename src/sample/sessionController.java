package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

/**
 * Created by Ozzy on 14.03.2016.
 */
public class sessionController {
    @FXML
    public void addCancelButtonClicked(ActionEvent actionEvent){
        try {
            Controller.changeScene("sample.fxml", actionEvent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void addRegisterButtonClicked(ActionEvent actionEvent){
        try {
            changeScene("session.fxml", actionEvent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
