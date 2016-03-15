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
import java.net.URL;
import java.util.*;
import java.sql.Date;

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

    public void nextButtonClicked(ActionEvent actionEvent) {

        String dato = this.dato.getValue().toString();
        String program = programComboBox.getValue();
        Double varighet = Double.parseDouble(this.varighet.getCharacters().toString());
        String formål = this.formål.getText();
        String notat = this.notat.getText();

        Date date = dbh.stringToDateConverter(dato);
        System.out.println(date.toString());

        //dbh.leggTilØkt(formål, varighet, date, notat, 1);


        try{
            changeScene("sessionInfo.fxml", actionEvent);
        } catch (Exception e){
            System.out.println(e);
        }
    }
}