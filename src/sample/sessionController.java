package sample;

import javafx.event.ActionEvent;
<<<<<<< HEAD
import javafx.fxml.FXML;

import java.io.IOException;
=======
>>>>>>> d0b50290d4ed4325bd29c18af3f25297e7ae1b6c

/**
 * Created by Ozzy on 14.03.2016.
 */
<<<<<<< HEAD
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
=======
public class sessionController extends SceneController{
    @Override
    public void backButtonClicked(ActionEvent evt){
        super.backButtonClicked(evt);
>>>>>>> d0b50290d4ed4325bd29c18af3f25297e7ae1b6c
    }
}
