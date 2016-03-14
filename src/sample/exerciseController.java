package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class exerciseController extends SceneController implements Initializable {

    @FXML
    ComboBox<String> gruppeComboBox;

    @Override
    public void initialize(URL url, ResourceBundle res) {
        populateComboBox();
    }

    public void populateComboBox(){
        // TODO: Make DBHandler populate the combobox with saved exercises

        ArrayList<String> list = new ArrayList<String>();
        list.add("Item1");
        list.add("Item2");
        list.add("Item3");
        ObservableList<String> obList = FXCollections.observableList(list);
        gruppeComboBox.setItems(obList);
    }

    public void registerExercise(ActionEvent actionEvent) {
    }
}
