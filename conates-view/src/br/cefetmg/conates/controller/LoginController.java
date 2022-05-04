package br.cefetmg.conates.controller;

import br.cefetmg.conates.Conates;
import conates.model.domain.Usuario;
import conates.model.service.IManterUsuario;
import conates.model.service.impl.ManterUsuario;
import conates.util.db.exception.NegocioException;
import conates.util.db.exception.PersistenciaException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class LoginController implements Initializable {

    @FXML
    private MenuButton btntipousuario;

    @FXML
    private TextField tfcodusuario;

    @FXML
    private PasswordField pfsenha;

    @FXML
    private Button btnentrar;

    @FXML
    private Button btnsobre;

    @FXML
    private Button btnajuda;

    @FXML
    private MenuItem menuItem1;

    @FXML
    private MenuItem menuItem2;

    @FXML
    private MenuItem menuItem3;

    public static Label luser;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Conates.janela = "Login.fxml";
        //ConatesView.janelaAnterior = (ConatesView.janela.remove(ConatesView.janela.size() - 1));
    }

    @FXML
    public void menu1() {

        btntipousuario.setText(menuItem1.getText());
    }

    @FXML
    public void menu2() {

        btntipousuario.setText(menuItem2.getText());
    }

    @FXML
    public void menu3() {

        btntipousuario.setText(menuItem3.getText());
    }

    @FXML
    public void ajuda(ActionEvent event) {
        try {
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/br/cefetmg/conates/view/Ajuda.fxml"));
            Scene novaCena = new Scene(root);
            window.setScene(novaCena);
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void sobre(ActionEvent event) {
        try {
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/br/cefetmg/conates/view/Sobre.fxml"));
            Scene novaCena = new Scene(root);
            window.setScene(novaCena);
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void entrar(ActionEvent event) throws NegocioException, IOException, PersistenciaException {

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        if (this.tfcodusuario.getText().isEmpty() || this.pfsenha.getText().isEmpty() || this.btntipousuario.getText().equals("TIPO DE USUÁRIO")) {
            JOptionPane.showMessageDialog(null, "Preencha todos os campos !!", "Atenção", JOptionPane.ERROR_MESSAGE, null);
        } else {
            IManterUsuario manterUsuario = new ManterUsuario();
            Usuario usr;

            usr = manterUsuario.getUserLogin(this.tfcodusuario.getText(), this.btntipousuario.getText(), this.pfsenha.getText());

            if (usr != null) {
                if (this.btntipousuario.getText().equals("RECEPCIONISTA")) {
                    Parent root = FXMLLoader.load(getClass().getResource("/br/cefetmg/conates/view/CadastroConsultas.fxml"));
                    Scene novaCena = new Scene(root);

                    window.setScene(novaCena);
                } 
                else if (this.btntipousuario.getText().equals("FARMACEUTICO")) {
                    Parent root = FXMLLoader.load(getClass().getResource("/br/cefetmg/conates/view/Estoque.fxml"));
                    Scene novaCena = new Scene(root);

                    window.setScene(novaCena);

                } 
                else if (this.btntipousuario.getText().equals("ENFERMEIRO")) {
                    Parent root = FXMLLoader.load(getClass().getResource("/br/cefetmg/conates/view/Estoque.fxml"));
                    Scene novaCena = new Scene(root);

                    window.setScene(novaCena);
                }
            } else {
//luser.setText("Olá , " + usr.getNome());

                JOptionPane.showMessageDialog(null, "Usuário não encontrado!", "Atenção", JOptionPane.ERROR_MESSAGE, null);
            }
            }
        }

    }
