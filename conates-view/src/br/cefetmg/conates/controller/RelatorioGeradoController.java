package br.cefetmg.conates.controller;

import br.cefetmg.conates.Conates;
import conates.model.domain.Estoque;
import conates.model.domain.Medicamento;
import conates.model.domain.Movimentacao;
import conates.model.service.IManterEstoque;
import conates.model.service.IManterMedicamento;
import conates.model.service.IManterMovimentacao;
import conates.model.service.impl.ManterEstoque;
import conates.model.service.impl.ManterMedicamento;
import conates.model.service.impl.ManterMovimentacao;
import conates.util.db.exception.PersistenciaException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
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

public class RelatorioGeradoController implements Initializable {

    @FXML
    private Button btnsobre;
    @FXML
    private Button btnajuda;
    @FXML
    private Button btnsair;
    @FXML
    private Button btnestoque;
    @FXML
    private Button btncadmedicamento;
    @FXML
    private Button btnatumedicamento;
    @FXML
    private Button btncontfornecimento;
    @FXML
    private Button btngerarelatorio;
    @FXML
    private Button btnvoltar;
    @FXML
    private TableView tbnomedic;

    @FXML
    private Label lbluser;
    List lista = new ArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Conates.janelaAnterior = Conates.janela;
        Conates.janela = "RelatorioGerado.fxml";
        this.btngerarelatorio.setDisable(true);
        init();
    }

    @FXML
    public void sair(ActionEvent event) {
        try {
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/br/cefetmg/conates/view/Login.fxml"));
            Scene novaCena = new Scene(root);
            window.setScene(novaCena);
        } catch (IOException ex) {
            Logger.getLogger(RelatorioGeradoController.class.getName()).log(Level.SEVERE, null, ex);
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
            Parent root = FXMLLoader.load(getClass().getResource("/br/cefetmg/conates/view/Relatorio.fxml"));
            Scene novaCena = new Scene(root);
            window.setScene(novaCena);
        } catch (IOException ex) {
            Logger.getLogger(RelatorioGeradoController.class.getName()).log(Level.SEVERE, null, ex);
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


    public void atualizarTabela() {

        try {
            IManterEstoque manterEstoque = new ManterEstoque();

            List<Estoque> listEstoque = RelatorioController.list;
            if (listEstoque != null) {
                for (final Estoque estoque : listEstoque) {
                    if (estoque != null) {
                        relatorio c = new relatorio();
                        IManterMedicamento manterMedicamento = new ManterMedicamento();
                        Medicamento medicamento = manterMedicamento.pesquisarPorCod(estoque.getCod_medic_id());
                        c.setNomeMedic(medicamento.getNom_medic());

                        IManterMovimentacao manterMovimentacao = new ManterMovimentacao();
                        Movimentacao movimentacao = manterMovimentacao.pesquisarPorCod(estoque.getCod_tipo_id());

                        c.setMovimentacao(movimentacao.getDes_tipo());

                        c.setData(estoque.getDat_movimentacao());

                        lista.add(c);

                    }
                }
                tbnomedic.setItems(FXCollections.observableArrayList(lista));

            }

        } catch (PersistenciaException ex) {
            Logger.getLogger(ExibirConsultasController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Método que cria as colunas da tabela
    public void init() {

        TableColumn clNome;
        TableColumn clMovimentacao;
        TableColumn clData;

        clNome = new TableColumn<>("Nome Medicamento");
        clNome.setCellValueFactory(new PropertyValueFactory<>("nomeMedic"));
        clNome.setPrefWidth(160);

        clMovimentacao = new TableColumn<>("Movimentação");
        clMovimentacao.setCellValueFactory(new PropertyValueFactory<>("movimentacao"));
        clMovimentacao.setPrefWidth(160);

        clData = new TableColumn<>("Data");
        clData.setCellValueFactory(new PropertyValueFactory<>("Data"));
        clData.setPrefWidth(160);

        tbnomedic.getColumns().addAll(clNome, clMovimentacao, clData);

        atualizarTabela();

    }
// BARRA FIM MENU

    public class relatorio {

        private String nomeMedic;
        private String movimentacao;
        private Date data;

        public String getNomeMedic() {
            return nomeMedic;
        }

        public void setNomeMedic(String nomeMedic) {
            this.nomeMedic = nomeMedic;
        }

        public String getMovimentacao() {
            return movimentacao;
        }

        public void setMovimentacao(String movimentacao) {
            this.movimentacao = movimentacao;
        }

        public Date getData() {
            return data;
        }

        public void setData(Date data) {
            this.data = data;
        }

    }
}
