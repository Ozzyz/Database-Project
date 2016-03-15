package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import db.DBHandler;

import java.io.IOException;
import java.util.ArrayList;

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

    public ObservableList<String> populateListView(ArrayList<String> list, ListView<String> listView){
        ObservableList<String> obList = FXCollections.observableList(list);
        listView.setItems(obList);
        return obList;
    }
}
