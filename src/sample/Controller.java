package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {
    @FXML
    public void addSessionButtonClicked(ActionEvent actionEvent) {
        try {
            changeScene("session.fxml", actionEvent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void addExerciseButtonClicked(ActionEvent actionEvent) {
        try {
            changeScene("session.fxml", actionEvent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void changeScene(String fxml_resource, ActionEvent evt) throws IOException {

        // Change scene to where we register session
        Stage currentStage = (Stage) ((Node) evt.getSource()).getScene().getWindow();

        Parent newroot = FXMLLoader.load(getClass().getResource("session.fxml"));

        currentStage.setScene(new Scene(newroot, 600,600));

    }

    public void addNewProgramButtonClicked(ActionEvent actionEvent) {
        try {
            changeScene("program.fxml", actionEvent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
