package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;

import java.awt.datatransfer.StringSelection;
import java.net.URL;
import java.sql.Array;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class viewController extends SceneController implements Initializable{

    public TextArea repTextArea;
    public TextArea setTextArea;
    public TextArea weightTextArea;
    public TextArea notatTextArea;
    public TextArea formålTextArea;
    public ListView exerciseListView;
    @FXML
    ListView<String> sessionListView;

    private int currentSelectedSession;

    public void backButtonClickd(ActionEvent actionEvent) {
        super.backButtonClicked(actionEvent);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //TODO Fetch data from db
        ArrayList<String> sessionNames = dbh.getSessionNames();
        super.populateListView(sessionNames, sessionListView);

    }


    public void listViewClicked(Event event) {
        String selectedItem = sessionListView.getSelectionModel().getSelectedItem();
        // Formål and Notat should be updated
        String[] a = selectedItem.split("\\."); // Items are on the form sessionID. Date
        int sessionID = Integer.parseInt(a[0]);

        // Save sessionID so we can use it later
        currentSelectedSession = sessionID;


        ArrayList<String> sessionData = dbh.getSessionFormAndNotes(sessionID);
        formålTextArea.setText(sessionData.get(0));
        notatTextArea.setText(sessionData.get(1));
        // When a session is clicked, we need to populate the other listview with all exercises that were done in the session
        // Find out what program we are doing this session
        int programID = dbh.getProgramIDBySessionID(sessionID);
        System.out.println("ProgramID: " + programID);
        // Get the exercises we are doing
        ArrayList<String> exercises = dbh.getExercisesByProgramID(programID);
        // Populate the listview
        System.out.println(exercises.size());
        populateListView(exercises, exerciseListView);

        //Clear info fields
        repTextArea.clear();
        setTextArea.clear();
        weightTextArea.clear();

    }

    public void exerciseListViewClicked(Event event) {
        // Get reps and set results from db, add them to the appropriate text areas

        String selectedExercise = (String) exerciseListView.getSelectionModel().getSelectedItem();
        System.out.println(currentSelectedSession);
        System.out.println(selectedExercise);
        ArrayList<String> results = dbh.getPerformance(currentSelectedSession, selectedExercise);
        // The results are in order: Repetitions, Set, Weight
        //Update the textfields
        repTextArea.setText(results.get(0));
        setTextArea.setText(results.get(1));
        weightTextArea.setText(results.get(2));
    }
}
