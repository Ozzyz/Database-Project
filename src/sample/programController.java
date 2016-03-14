package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;

/**
 * Created by Ozzy on 14.03.2016.
 */
public class programController extends SceneController implements Initializable{
    @FXML public ListView programListView;
    public TextField programNameTextField;
    @FXML ListView<String> exerciseListView;
    ObservableList<String> ExerciseItems = FXCollections.observableArrayList (
            "Test1", "Test2", "ASD", "123");
    ObservableList<String> ProgramItems = FXCollections.observableArrayList();

    public void populateListView(){
        //TODO: This should be done by db-handler
        exerciseListView.setItems(ExerciseItems);
        programListView.setItems(ProgramItems);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Here we will fetch data from db
        populateListView();
    }

    public void addExerciseButtonClicked(ActionEvent actionEvent) {
        // Get what item the user has selected
        String selectedItem = exerciseListView.getSelectionModel().getSelectedItem();
        if(selectedItem != null) {
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
