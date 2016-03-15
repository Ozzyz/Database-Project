package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;

/**
 * Created by Ozzy on 14.03.2016.
 */


public class sessionController extends SceneController implements Initializable{

    @FXML
    ComboBox<String> programComboBox;
    @FXML
    TextField varighet;
    @FXML
    DatePicker dato;
    @FXML
    TextArea formål;
    @FXML
    TextArea notat;

    @Override
    public void initialize(URL url, ResourceBundle res) {
        populateComboBox();
    }

    public void populateComboBox(){
        ArrayList<String> list = dbh.getProgramNames();
        ObservableList<String> obList = FXCollections.observableList(list);
        programComboBox.setItems(obList);
    }

    @Override
    public void backButtonClicked(ActionEvent evt){
        super.backButtonClicked(evt);
    }

    public void registerButtonClicked(ActionEvent actionEvent) {
        // Get all selected items
        LocalDate dato = this.dato.getValue();
        String program = programComboBox.getValue();
        Double varighet = Double.parseDouble(this.varighet.getCharacters().toString());
        String formål = this.formål.getText();
        String notat = this.notat.getText();

        // TODO: Write stuff to the database
        System.out.println("Dato: " + dato);
        System.out.println("Program: " + program);
        System.out.println("Varighet: " + varighet);
        System.out.println("Formål:" + formål);
        System.out.println("Notat: " + notat);
    }
}