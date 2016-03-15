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
import java.util.ArrayList;
import java.util.ResourceBundle;

public class viewController extends SceneController implements Initializable{

    public TextArea repTextArea;
    public TextArea setTextArea;
    public TextArea weightTextArea;
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
        // When a session is clicked, we need to populate the combobox with all exercise that was done in the session

    }
}
