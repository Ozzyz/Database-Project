package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import db.DBHandler;

import java.io.IOException;

/**
 * Created by Ozzy on 14.03.2016.
 */
public class SceneController {
    DBHandler dbh = new DBHandler();
    public void changeScene(String fxml_resource, ActionEvent evt) throws IOException {

        // Change scene to where we register session
        Stage currentStage = (Stage) ((Node) evt.getSource()).getScene().getWindow();

        Parent newroot = FXMLLoader.load(getClass().getResource(fxml_resource));

        currentStage.setScene(new Scene(newroot, 600,600));
    }
    public void backButtonClicked(ActionEvent evt) {
        try {
            changeScene("sample.fxml", evt);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
