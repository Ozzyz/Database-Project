package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Collections;

/**
 * Created by Ozzy on 14.03.2016.
 */

// TODO: Get a list of all programs, add it to combobox

public class sessionController extends SceneController{
    @FXML
    ComboBox<String> programComboBox;
    @Override
    public void backButtonClicked(ActionEvent evt){
        super.backButtonClicked(evt);
    }

    public void addRegisterButtonClicked(ActionEvent actionEvent) {
    }

    public void populateComboBox(Collection<String> programList){
        programComboBox.getItems().addAll(programList);
    }

    public void addCancelButtonClicked(ActionEvent actionEvent) {

    }
}