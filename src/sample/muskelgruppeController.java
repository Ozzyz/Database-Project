package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class muskelgruppeController extends SceneController implements Initializable{

    @FXML
    TextField muskelgruppe;
    @FXML
    ComboBox<String> undergruppeComboBox;

    public void backButtonClickd(ActionEvent actionEvent) {
        super.backButtonClicked(actionEvent);
    }

    public void registerButtonClicked(ActionEvent actionEvent) {
        dbh.leggTilGruppe(muskelgruppe.getCharacters().toString(), undergruppeComboBox.getValue());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ArrayList<String> exercises = dbh.getMuscleGroupNames();
        ObservableList<String> list = FXCollections.observableList(exercises);
        undergruppeComboBox.setItems(list);
    }
}
