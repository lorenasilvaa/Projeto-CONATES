package br.cefetmg.conates.controller;

import java.util.Date;
import conates.model.domain.Estoque;
import conates.model.service.IManterEstoque;
import conates.model.service.impl.ManterEstoque;
import conates.util.db.exception.PersistenciaException;
import conates.util.validacoes;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class RelatorioController implements Initializable {

    public static String l;
    @FXML
    private Button btnsobre;
    @FXML
    private Button btnajuda;
    @FXML
    private Button btnsair;
    @FXML
    private Button btnestoque;
    @FXML
    private Label lbluser;
    @FXML
    private Button btncadmedicamento;
    @FXML
    private Button btncontfornecimento;
    @FXML
    private Button btngerarelatorio;
    @FXML
    private Button btnconsultar;
    @FXML
    private Button btnpesquisar;
    @FXML
    private TextArea tfperiodode;
    @FXML
    private TextArea tfperiodoa;

    static List<Estoque> list;
    validacoes valida = new validacoes();
    List lista = new ArrayList();
    public static String botaoEscolhido;
    SimpleDateFormat dateForma = new SimpleDateFormat("dd-MM-yyyy");

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        valida.addTextLimiter(tfperiodode, 12);
        valida.addTextLimiter(tfperiodoa, 12);

        btnpesquisar.setDisable(false);
    }

   /* @FXML
    public void formatarData() {
        valida.formatar("##/##/####", tfperiodode, tfperiodode.getText().length(), 10);
       // valida.formatar("##/##/####", tfperiodoa, tfperiodoa.getText().length(), 10);
    }
*/
    // SAIR
    @FXML
    public void sair(ActionEvent event) {
        try {
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/br/cefetmg/conates/view/Login.fxml"));
            Scene novaCena = new Scene(root);
            window.setScene(novaCena);
        } catch (IOException ex) {
            Logger.getLogger(RelatorioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
// AJUDA

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
    // SOBRE

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
    public void buscar(ActionEvent event) throws PersistenciaException, ParseException {

        if ((this.tfperiodode.getText().isEmpty()) || (this.tfperiodoa.getText().isEmpty())) {
            JOptionPane.showMessageDialog(null, "Preencha a data de início e de término da consulta!", "Atenção", JOptionPane.ERROR_MESSAGE, null);
        } else if (!(valida.campoNumerico(this.tfperiodode.getText())) || (!valida.campoNumerico(this.tfperiodoa.getText()))) {
            JOptionPane.showMessageDialog(null, "A pesquisa só aceita números!", "Atenção", JOptionPane.ERROR_MESSAGE, null);
        } else {
            try {

                SimpleDateFormat formato = new SimpleDateFormat("yyyy-mm-dd");
                Date d = formato.parse(this.tfperiodode.getText());
                Date d1 = formato.parse(this.tfperiodoa.getText());

                IManterEstoque manterEstoque = new ManterEstoque();
                List<Estoque> estoque = manterEstoque.pesquisarPorIntervaloData(d, d1);
                if (estoque != null) {
                    //l = this.tfperiodode.getText();
                    list = estoque;
                    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    Parent root = FXMLLoader.load(getClass().getResource("/br/cefetmg/conates/view/RelatorioGerado.fxml"));
                    Scene novaCena = new Scene(root);
                    window.setScene(novaCena);
                } else {
                    JOptionPane.showMessageDialog(null, "O período não foi encontrado, verifique se as datas foram digitadas corretamente", "Atenção", JOptionPane.ERROR_MESSAGE, null);

                }
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);

            }

        }
    }

    /**
     * *** Barra de Menu *****
     */
    @FXML
    public void estoque(ActionEvent event) {
        try {
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/br/cefetmg/conates/view/Estoque.fxml"));
            Scene novaCena = new Scene(root);
            window.setScene(novaCena);
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void cadastrarMedicamento(ActionEvent event) {
        try {
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/br/cefetmg/conates/view/CadastroMedicamento.fxml"));
            Scene novaCena = new Scene(root);
            window.setScene(novaCena);
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void controleFornecimento(ActionEvent event) {
        try {
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/br/cefetmg/conates/view/ControleDeFornecimento.fxml"));
            Scene novaCena = new Scene(root);
            window.setScene(novaCena);
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void gerarRelatorio(ActionEvent event) {
        try {
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/br/cefetmg/conates/view/Relatorio.fxml"));
            Scene novaCena = new Scene(root);
            window.setScene(novaCena);
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * *** Fim da Barra de Menu *****
     */

}
