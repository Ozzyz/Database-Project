package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {
    public void addSessionButtonClicked(ActionEvent actionEvent) {
        // Change scene to where we register session
        Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        try {
            Parent newscene = FXMLLoader.load(getClass().getResource("session.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addExerciseButtonClicked(ActionEvent actionEvent) {
    }

    public void changeScene(){

    }
}
