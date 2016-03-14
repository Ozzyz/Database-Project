package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.ResourceBundle;

/**
 * Created by Ozzy on 14.03.2016.
 */


public class sessionController extends SceneController implements Initializable{

    @FXML
    ComboBox<String> programComboBox;

    @Override
    public void initialize(URL url, ResourceBundle res) {
        populateComboBox();
    }

// TODO: Get a list of all programs, add it to combobox
    public void populateComboBox(){
        ArrayList<String> list = new ArrayList<String>();
        list.add("Item1");
        list.add("Item2");
        list.add("Item3");
        ObservableList<String> obList = FXCollections.observableList(list);
        programComboBox.setItems(obList);
    }

    @Override
    public void backButtonClicked(ActionEvent evt){
        super.backButtonClicked(evt);
    }

    public void addRegisterButtonClicked(ActionEvent actionEvent) {
    }
}