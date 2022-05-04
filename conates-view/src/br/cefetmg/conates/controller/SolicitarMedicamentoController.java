package br.cefetmg.conates.controller;

import conates.util.validacoes;
import br.cefetmg.conates.Conates;
import conates.model.domain.Fornecedor;
import conates.model.domain.FornecedorMedicamento;
import conates.model.domain.Medicamento;
import conates.model.service.IManterFornecedor;
import conates.model.service.IManterFornecedorMedicamento;
import conates.model.service.IManterMedicamento;
import conates.model.service.impl.ManterFornecedor;
import conates.model.service.impl.ManterFornecedorMedicamento;
import conates.model.service.impl.ManterMedicamento;
import conates.util.db.exception.NegocioException;
import conates.util.db.exception.PersistenciaException;
import java.io.IOException;
import java.net.URL;
import java.util.List;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class SolicitarMedicamentoController implements Initializable {

    @FXML
    private Button btnsobre;
    @FXML
    private Button btnajuda;
    @FXML
    private Button btnsair;

    @FXML
    private Button btnvoltar;
    @FXML
    private Button btnenviar;
    @FXML
    private Button btnestoque;
    @FXML
    private Button btncadmedicamento;
    @FXML
    private Button btncontfornecimento;
    @FXML
    private Button btngerarelatorio;
    @FXML
    private TextField tfnommed;
    @FXML
    private TextField tfqtdmed;
    @FXML
    private MenuButton mbfornecedor;
    @FXML
    private Label lbluser;
    @FXML
    private MenuButton mbnomemedic;
    validacoes valida = new validacoes();

    LoginController login = new LoginController();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Conates.janelaAnterior = Conates.janela;
        Conates.janela = "SolicitarMedicamento.fxml";

        try {

            IManterMedicamento manterMedicamento = new ManterMedicamento();
            List<Medicamento> listmedicamentos = manterMedicamento.listarTodos();

            IManterFornecedor manterFornecedor = new ManterFornecedor();
            List<Fornecedor> listfornecedor = manterFornecedor.listarTodos();

            for (Medicamento medicamentos : listmedicamentos) {
                final MenuItem menuItem = new MenuItem(medicamentos.getNom_medic());
                menuItem.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {
                        mbnomemedic.setText(menuItem.getText());
                    }
                });
                mbnomemedic.getItems().addAll(menuItem);
            }

            for (Fornecedor fornecedor : listfornecedor) {
                final MenuItem menuItem = new MenuItem(fornecedor.getNom_empresa());
                menuItem.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {
                        mbfornecedor.setText(menuItem.getText());
                    }
                });
                mbfornecedor.getItems().addAll(menuItem);

            }
        } catch (PersistenciaException ex) {
            Logger.getLogger(SolicitarMedicamentoController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public boolean verificaCampoNumerico(String s) {
        //boolean teste = false;
        String nova = s;
        nova = nova.replace("/", "");
        nova = nova.replace("/", "");
        nova = nova.replace(":", "");

        return valida.campoNumerico(nova);
    }

    @FXML
    public void solicitarMedicamento(ActionEvent event) throws NegocioException, PersistenciaException {

        if (this.mbnomemedic.getText().equals("SELECIONE") || this.mbfornecedor.getText().equals("SELECIONE") || this.tfqtdmed.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios *", "Atenção", JOptionPane.ERROR_MESSAGE, null);
        } else if (!(verificaCampoNumerico(this.tfqtdmed.getText()))) {
            JOptionPane.showMessageDialog(null, "O campo Qtd. do medicamento apenas aceita números", "Atenção", JOptionPane.ERROR_MESSAGE, null);
        } else {
            try {
                // IManterMedicamento manterMedicamento = new ManterMedicamento();
                // Medicamento medicamento = manterMedicamento.pesquisarPorNome(this.mbnomemedic.getText());

                // IManterFornecedor manterFornecedor = new ManterFornecedor();
                //  Fornecedor fornecedor = manterFornecedor.pesquisarPorNome(this.mbfornecedor.getText());
                FornecedorMedicamento fornecedormedicamento = new FornecedorMedicamento();
                IManterMedicamento manterMedicamento = new ManterMedicamento();
                Medicamento medicamento = manterMedicamento.pesquisarPorNome(mbnomemedic.getText());

                fornecedormedicamento.setCod_medic_id(medicamento.getCod_medic());
                IManterFornecedor manterFornecedor = new ManterFornecedor();
                Fornecedor fornecedor = manterFornecedor.pesquisarPorNome(mbfornecedor.getText());
                fornecedormedicamento.setCnpj_empresa_id(fornecedor.getCnpj_empresa());
                fornecedormedicamento.setQtd_medicamento(Integer.parseInt(tfqtdmed.getText()));
                fornecedormedicamento.setTxt_validacao("");
                fornecedormedicamento.setEst_movimentacao("Solicitado");

                IManterFornecedorMedicamento manterFornecedorMedicamento = new ManterFornecedorMedicamento();
                Long fornecedormedicamentoId = manterFornecedorMedicamento.cadastrar(fornecedormedicamento);

                if (fornecedormedicamentoId != null) {
                    try {
                        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        Parent root = FXMLLoader.load(getClass().getResource("/br/cefetmg/conates/view/VisualizarSolicitacoes.fxml"));
                        Scene novaCena = new Scene(root);
                        window.setScene(novaCena);
                    } catch (IOException ex) {
                        Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Não foi possível gravar esse registro!", "Aviso", JOptionPane.ERROR_MESSAGE, null);
                }

            } catch (Exception e) {
                e.printStackTrace();

            }
        }
    }

    @FXML
    public void sair(ActionEvent event) {
        try {
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/br/cefetmg/conates/view/Login.fxml"));
            Scene novaCena = new Scene(root);
            window.setScene(novaCena);
        } catch (IOException ex) {
            Logger.getLogger(SolicitarMedicamentoController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    public void voltar(ActionEvent event) {
        try {
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/br/cefetmg/conates/view/ControleDeFornecimento.fxml"));
            Scene novaCena = new Scene(root);
            window.setScene(novaCena);
        } catch (IOException ex) {
            Logger.getLogger(SolicitarMedicamentoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //BARRA DE MENU 
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
            Parent root = FXMLLoader.load(getClass().getResource("/br/cefetmg/conates/view/ControleDeFornecimeto.fxml"));
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
            Parent root = FXMLLoader.load(getClass().getResource("/br/cefetmg/conates/view/RelatorioGerado.fxml"));
            Scene novaCena = new Scene(root);
            window.setScene(novaCena);
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
// BARRA FIM MENU

}
