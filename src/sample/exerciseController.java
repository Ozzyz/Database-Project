package sample;

import javafx.event.ActionEvent;

import java.io.IOException;

/**
 * Created by Ozzy on 14.03.2016.
 */
public class exerciseController extends SceneController {
    public void backButtonClicked(ActionEvent actionEvent) {
        // Go back to main menu
        try {
            changeScene("sample.fxml", actionEvent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
