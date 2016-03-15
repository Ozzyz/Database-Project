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
    @FXML
    ListView<String> sessionListView;
    @FXML
    TableView<String> infoTableView;

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
        String sessionID = a[0];
        System.out.println("SessionID" + sessionID);
        ArrayList<String> sessionData = dbh.getSessionFormAndNotes(Integer.parseInt(sessionID));
        formålTextArea.setText(sessionData.get(0));
        notatTextArea.setText(sessionData.get(1));
        // When a session is clicked, we need to populate the combobox with all exercise that was done in the session

    }
}
