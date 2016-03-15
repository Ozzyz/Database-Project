package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.ResourceBundle;

/**
 * Created by Ozzy on 14.03.2016.
 */
public class programController extends SceneController implements Initializable{
    @FXML public ListView programListView;
    public TextField programNameTextField;
    @FXML ListView<String> exerciseListView;
    ObservableList<String> ExerciseItems;
    ObservableList<String> ProgramItems;





    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Here we will fetch data from db

        try {
            ArrayList<String> exercises = dbh.getProgramNames();
            ExerciseItems = populateListView(exercises, exerciseListView);
            ProgramItems = populateListView(new ArrayList<>(), programListView);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addExerciseButtonClicked(ActionEvent actionEvent) {
        // Get what item the user has selected
        String selectedItem = exerciseListView.getSelectionModel().getSelectedItem();
        if(selectedItem != null) {
            System.out.println(selectedItem);
            ExerciseItems.remove(selectedItem);
            ProgramItems.add(selectedItem);
        }
    }

    public void removeExerciseButtonClicked(ActionEvent actionEvent) {
        // Get what item the user has selected
        String selectedItem = (String) programListView.getSelectionModel().getSelectedItem();
        if(selectedItem != null){
            ExerciseItems.add(selectedItem);
            ProgramItems.remove(selectedItem);
        }

    }

    public void saveButtonClicked(ActionEvent actionEvent) {
        // TODO: Write stuff to the database

        // Get all selected items
        ObservableList selectedItems = programListView.getItems();
        String programName = programNameTextField.getText();

        for(Object e: selectedItems){
            System.out.println(e.toString());
        }
    }
}
