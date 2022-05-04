package br.cefetmg.conates.controller;

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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class ValidarRecebimentoController implements Initializable {

    @FXML
    private Button btnsobre;
    @FXML
    private Button btnajuda;
    @FXML
    private Button btnsair;
    @FXML
    private TableView tbvalidarrecebimento;
    @FXML
    private Button btnestoque;
    @FXML
    private Button btncadmedicamento;
    @FXML
    private Button btnvoltar;
    @FXML
    private Button btncontfornecimento;
    @FXML
    private Button btngerarelatorio;
    @FXML
    private Label lbluser;
    List lista = new ArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Conates.janelaAnterior = Conates.janela;
        Conates.janela = "ValidarRecebimento.fxml";
        init();
    }

    @FXML
    public void voltar(ActionEvent event) {
        try {
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/br/cefetmg/conates/view/ControleDeFornecimento.fxml"));
            Scene novaCena = new Scene(root);
            window.setScene(novaCena);
        } catch (IOException ex) {
            Logger.getLogger(ValidarRecebimentoController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ValidarRecebimentoController.class.getName()).log(Level.SEVERE, null, ex);
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

    public void atualizarTabela() {
        try {
            IManterFornecedorMedicamento manterFornecedorMedicamento = new ManterFornecedorMedicamento();

            List<FornecedorMedicamento> listFornecedorMedicamento = manterFornecedorMedicamento.listarTodos();
            if (listFornecedorMedicamento != null) {
                for (final FornecedorMedicamento fornecedor : listFornecedorMedicamento) {
                    if (fornecedor != null) {
                        if (fornecedor.getEst_movimentacao().equals("Solicitado")) {
                            recebimento r = new recebimento();
                            r.setCod_fornecimento(fornecedor.getCod_fornecimento());
                            IManterMedicamento manterMedicamento = new ManterMedicamento();
                            Medicamento medicamento = manterMedicamento.pesquisarPorCod(fornecedor.getCod_medic_id());
                            r.setNomeMedicamento(medicamento.getNom_medic());
                            r.setQuantidade(fornecedor.getQtd_medicamento());

                            IManterFornecedor manterFornecedor = new ManterFornecedor();
                            Fornecedor fornece = manterFornecedor.pesquisarPorCod(fornecedor.getCnpj_empresa_id());
                            r.setFornecedor(fornece.getNom_fantasia());
                            final Button btnRecebido = new Button("Recebido");

                            btnRecebido.setId(fornecedor.getCod_fornecimento().toString());
                            btnRecebido.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    try {
                                        //JOptionPane.showMessageDialog(null,btneditar.getId());
                                        chamaRecebido(btnRecebido.getId(),event);
                                    } catch (NegocioException ex) {
                                        Logger.getLogger(ValidarRecebimentoController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                   // try {
                                       // Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                       // Parent root = FXMLLoader.load(getClass().getResource("/br/cefetmg/conates/view/VisualizarSolicitacoes.fxml"));
                                       // Scene novaCena = new Scene(root);
                                       // window.setScene(novaCena);
                                    //} catch (IOException ex) {
                                    //    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                                   // }
                                }
                            });
                            r.setBotaoRecebido(btnRecebido);
                            lista.add(r);

                        }
                    }
                }
                tbvalidarrecebimento.setItems(FXCollections.observableArrayList(lista));

            }

        } catch (PersistenciaException ex) {
            Logger.getLogger(ExibirConsultasController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     public void chamaRecebido(String s,ActionEvent event) throws NegocioException {
         IManterFornecedorMedicamento manterFornecedorMedicamento = new ManterFornecedorMedicamento();
        try {
            FornecedorMedicamento fornece = manterFornecedorMedicamento.pesquisarPorCod(Long.parseLong(s));
            fornece.setEst_movimentacao("Recebido");
            boolean updated3 = manterFornecedorMedicamento.alterar(fornece);

                if ((updated3 = true)) {
                try {
                        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        Parent root = FXMLLoader.load(getClass().getResource("/br/cefetmg/conates/view/VisualizarSolicitacoes.fxml"));
                        Scene novaCena = new Scene(root);
                        window.setScene(novaCena);
                    } catch (IOException ex) {
                        Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Não foi possível alterar esse registro!", "Aviso", JOptionPane.ERROR_MESSAGE, null);
                }

            
        } catch (PersistenciaException ex) {
            Logger.getLogger(ValidarRecebimentoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

//Método que cria as colunas da tabela
    public void init() {

        TableColumn clCodSolicitacao;
        TableColumn clNomeMedic;
        TableColumn clQtd;
        TableColumn clFornecedor;
        TableColumn clRecebido;

        clCodSolicitacao = new TableColumn<>("Cód. Solicitação");
        clCodSolicitacao.setCellValueFactory(new PropertyValueFactory<>("cod_fornecimento"));
        clCodSolicitacao.setPrefWidth(140);

        clNomeMedic = new TableColumn<>("Nome do Medicamento");
        clNomeMedic.setCellValueFactory(new PropertyValueFactory<>("nomeMedicamento"));
        clNomeMedic.setPrefWidth(160);

        clQtd = new TableColumn<>("Qtd.");
        clQtd.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        clQtd.setPrefWidth(120);

        clFornecedor = new TableColumn<>("Fornecedor");
        clFornecedor.setCellValueFactory(new PropertyValueFactory<>("fornecedor"));
        clFornecedor.setPrefWidth(120);

        clRecebido = new TableColumn<>("");
        clRecebido.setCellValueFactory(new PropertyValueFactory<>("botaoRecebido"));
        clRecebido.setPrefWidth(120);

        tbvalidarrecebimento.getColumns().addAll(clCodSolicitacao, clNomeMedic, clQtd, clFornecedor, clRecebido);

        atualizarTabela();

    }

    public class recebimento {

        private Long cod_fornecimento;
        private String nomeMedicamento;
        private double quantidade;
        private String fornecedor;
        private Button botaoRecebido;

        public Button getBotaoRecebido() {
            return botaoRecebido;
        }

        public void setBotaoRecebido(Button botaoRecebido) {
            this.botaoRecebido = botaoRecebido;
        }

        public Long getCod_fornecimento() {
            return cod_fornecimento;
        }

        public void setCod_fornecimento(Long cod_fornecimento) {
            this.cod_fornecimento = cod_fornecimento;
        }

        public String getNomeMedicamento() {
            return nomeMedicamento;
        }

        public void setNomeMedicamento(String nomeMedicamento) {
            this.nomeMedicamento = nomeMedicamento;
        }

        public double getQuantidade() {
            return quantidade;
        }

        public void setQuantidade(double quantidade) {
            this.quantidade = quantidade;
        }

        public String getFornecedor() {
            return fornecedor;
        }

        public void setFornecedor(String fornecedor) {
            this.fornecedor = fornecedor;
        }

    }
// BARRA FIM MENU
}
