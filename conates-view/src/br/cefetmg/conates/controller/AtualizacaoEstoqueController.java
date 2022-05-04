package br.cefetmg.conates.controller;

import br.cefetmg.conates.Conates;

import conates.model.domain.Estoque;
import conates.model.domain.Fornecedor;
import conates.model.domain.Lote;
import conates.model.domain.Medicamento;
import conates.model.domain.Movimentacao;
import conates.model.service.IManterEstoque;
import conates.model.service.IManterFornecedor;
import conates.model.service.IManterFornecedorMedicamento;
import conates.model.service.IManterLote;
import conates.model.service.IManterMedicamento;
import conates.model.service.IManterMovimentacao;
import conates.model.service.impl.ManterEstoque;
import conates.model.service.impl.ManterFornecedor;
import conates.model.service.impl.ManterFornecedorMedicamento;
import conates.model.service.impl.ManterLote;
import conates.model.service.impl.ManterMedicamento;
import conates.model.service.impl.ManterMovimentacao;
import conates.util.FormatadorData;
import conates.util.db.exception.PersistenciaException;
import conates.util.validacoes;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
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

public class AtualizacaoEstoqueController implements Initializable {

    @FXML
    private Button btnsobre;

    @FXML
    private Button btnajuda;

    @FXML
    private Label lbluser;

    @FXML
    private TableView tbatestoque;

    @FXML
    private Button btnsair;

    @FXML
    private Button btnestoque;

    @FXML
    private Button btncadmedicamento;

    @FXML
    private Button btncontfornecimento;

    @FXML
    private Button btngerarelatorio;

    validacoes valida = new validacoes();

//    EstoqueController Estoque = new EstoqueController();
    List lista = new ArrayList();

    public void initialize(URL url, ResourceBundle rb) {
        Conates.janelaAnterior = Conates.janela;
        Conates.janela = "AtualizacaoEstoque.fxml";
        init();
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
    public void sair(ActionEvent event) {
        try {
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/br/cefetmg/conates/view/Login.fxml"));
            Scene novaCena = new Scene(root);
            window.setScene(novaCena);
        } catch (IOException ex) {
            Logger.getLogger(AtualizacaoEstoqueController.class.getName()).log(Level.SEVERE, null, ex);
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
            Parent root = FXMLLoader.load(getClass().getResource("/br/cefetmg/conates/view/ControleDeFornecimento.fxml"));
            Scene novaCena = new Scene(root);
            window.setScene(novaCena);
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

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
     * *** Fim da Barra de Menu ****
     */
    public void atualizarTabela() {
        // StringBuilder hora = new StringBuilder();
        try {
            IManterEstoque manterEstoque = new ManterEstoque();

            List<Estoque> listEstoque = manterEstoque.listarTodos();
            if (listEstoque != null) {
                for (final Estoque estoque : listEstoque) {
                    if (estoque != null) {
                        atEstoque c = new atEstoque();

                        IManterMedicamento manterMedicamento = new ManterMedicamento();
                        Medicamento medicamento = manterMedicamento.pesquisarPorCod(estoque.getCod_medic_id());

                        IManterLote manterLote = new ManterLote();
                        Lote lote = manterLote.pesquisarPorCod(estoque.getCod_lote_id());

                        IManterMovimentacao manterMov = new ManterMovimentacao();
                        Movimentacao mov = manterMov.pesquisarPorCod(estoque.getCod_tipo_id());

                        c.setCodigo(medicamento.getCod_medic().toString());
                        c.setNome(medicamento.getNom_medic());
                        c.setValidade(FormatadorData.formatar(estoque.getDat_validade(), "dd/MM/yyyy"));
                        c.setLote(lote.getCod_lote().toString());

                        c.setMovimentacao(mov.getDes_tipo());
                        
                        c.setQtdMovimentada(estoque.getQtd_movimentada());

                        lista.add(c);

                    }
                }
                tbatestoque.setItems(FXCollections.observableArrayList(lista));

            }

        } catch (PersistenciaException ex) {
            Logger.getLogger(ExibirConsultasController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void init() {

        TableColumn clCodigo;
        TableColumn clNome;
        TableColumn clValidade;
        TableColumn clLote;
        TableColumn clMovimentacao;
        TableColumn clQtdMovimentada;

        clCodigo = new TableColumn<>("Código");
        clCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        clCodigo.setPrefWidth(120);

        clNome = new TableColumn<>("Nome");
        clNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        clNome.setPrefWidth(120);
        
        clValidade = new TableColumn<>("Validade");
        clValidade.setCellValueFactory(new PropertyValueFactory<>("validade"));
        clValidade.setPrefWidth(120);

        clLote = new TableColumn<>("Lote");
        clLote.setCellValueFactory(new PropertyValueFactory<>("lote"));
        clLote.setPrefWidth(120);

        clMovimentacao = new TableColumn<>("Movimentação");
        clMovimentacao.setCellValueFactory(new PropertyValueFactory<>("movimentacao"));
        clMovimentacao.setPrefWidth(150);
        
        clQtdMovimentada = new TableColumn<>("Qtd Movimentada");
        clQtdMovimentada.setCellValueFactory(new PropertyValueFactory<>("qtdMovimentada"));
        clQtdMovimentada.setPrefWidth(120);

        tbatestoque.getColumns().addAll(clCodigo, clNome, clValidade, clLote, clMovimentacao,clQtdMovimentada);
        atualizarTabela();
    }

    public class atEstoque {

        private String codigo;
        private String nome;
        private String validade;
        private String lote;
        private Double qtdMovimentada;
        private String movimentacao;

        public Double getQtdMovimentada() {
            return qtdMovimentada;
        }

        public void setQtdMovimentada(Double qtdMovimentada) {
            this.qtdMovimentada = qtdMovimentada;
        }

        public String getCodigo() {
            return codigo;
        }

        public void setCodigo(String codigo) {
            this.codigo = codigo;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public String getValidade() {
            return validade;
        }

        public void setValidade(String validade) {
            this.validade = validade;
        }

        public String getLote() {
            return lote;
        }

        public void setLote(String lote) {
            this.lote = lote;
        }

        public String getMovimentacao() {
            return movimentacao;
        }

        public void setMovimentacao(String movimentacao) {
            this.movimentacao = movimentacao;
        }

    }

}
