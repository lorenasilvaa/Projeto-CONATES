package br.cefetmg.conates.controller;

import br.cefetmg.conates.Conates;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class SobreController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //ConatesView.janela.add("Sobre.fxml");
        Conates.janelaAnterior = Conates.janela;
       Conates.janela = "Sobre.fxml";
        

    }

@FXML
public void voltar(ActionEvent event) {
    try {
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/br/cefetmg/conates/view/" + Conates.janelaAnterior));
        Scene novaCena = new Scene(root);
        window.setScene(novaCena);
    } catch (IOException ex) {
        Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
    }
}
}
