package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class Controller extends SceneController{
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
            changeScene("exercise.fxml", actionEvent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    public void addNewProgramButtonClicked(ActionEvent actionEvent) {
        try {
            changeScene("program.fxml", actionEvent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void viewButtonClicked(ActionEvent actionEvent) {
        try {
            changeScene("view.fxml", actionEvent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void populateListView(ArrayList<String> list, ListView<String> listView){
        ObservableList<String> obList = FXCollections.observableList(list);
        listView.setItems(obList);
    }
}
