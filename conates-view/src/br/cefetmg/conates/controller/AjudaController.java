package br.cefetmg.conates.controller;

import br.cefetmg.conates.Conates;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.concurrent.Worker.State;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.web.WebEngine;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.web.WebHistory;
import javax.swing.JOptionPane;

public class AjudaController implements Initializable {

    @FXML
    private Button btnrecepcionista;

    @FXML
    private Button btnenfermeiro;

    @FXML
    private Button btnfarmaceutico;

    @FXML
    private TextArea taAjuda;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
       Conates.janelaAnterior = Conates.janela;
       Conates.janela = "Ajuda.fxml";
        //
    }

    @FXML
    public void msgAjudaRecepcionista() {
        taAjuda.setText("Textim da recepcionista");
        btnrecepcionista.setDisable(true);
        btnenfermeiro.setDisable(false);
        btnfarmaceutico.setDisable(false);
    }

    @FXML
    public void msgAjudaFarmaceutico() {
        taAjuda.setText("Textim do farmacêutico");
        btnrecepcionista.setDisable(false);
        btnenfermeiro.setDisable(false);
        btnfarmaceutico.setDisable(true);
    }

    @FXML
    public void msgAjudaEnfermeiro() {
        taAjuda.setText("Textim do enfermeiro");
        btnrecepcionista.setDisable(false);
        btnenfermeiro.setDisable(true);
        btnfarmaceutico.setDisable(false);
    }

    @FXML
    public void voltar(ActionEvent event) {
        try {
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/br/cefetmg/conates/view/"+Conates.janelaAnterior));
            Scene novaCena = new Scene(root);
            window.setScene(novaCena);
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
