package br.cefetmg.conates.controller;

import br.cefetmg.conates.Conates;
import conates.model.domain.Estoque;
import conates.model.domain.Medicamento;
import conates.model.service.IManterEstoque;
import conates.model.service.IManterMedicamento;
import conates.model.service.impl.ManterEstoque;
import conates.model.service.impl.ManterMedicamento;
import conates.util.db.exception.PersistenciaException;
import conates.util.validacoes;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.embed.swing.SwingFXUtils;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

public class EstoqueController implements Initializable {

    @FXML
    private Button btnsobre;

    @FXML
    private Button btnajuda;

    @FXML
    private Button btnsair;

    @FXML
    private Label lbluser;

    @FXML
    private TableView tbestoque;

    @FXML
    private Button btnestoque;

    @FXML
    private Button btncadmedicamento;

    @FXML
    private Button btncontfornecimento;

    @FXML
    private Button btngerarelatorio;

    @FXML
    private Button btnapagar;

    validacoes valida = new validacoes();

    BufferedImage bufferedImage;

    ImageView myImageView;

    AtualizacaoEstoqueController atEstoque = new AtualizacaoEstoqueController();

    List lista = new ArrayList();
    public static String botaoEscolhido;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Conates.janelaAnterior = Conates.janela;
        Conates.janela = "Estoque.fxml";
        this.btnestoque.setDisable(true);

        init();
    }

    // SAIR
    @FXML
    public void sair(ActionEvent event) {
        try {
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/br/cefetmg/conates/view/Login.fxml"));
            Scene novaCena = new Scene(root);
            window.setScene(novaCena);
        } catch (IOException ex) {
            Logger.getLogger(EstoqueController.class.getName()).log(Level.SEVERE, null, ex);
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

    /**
     * *** Barra de Menu *****
     */
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

    public void atualizarTabela() {
        try {
            IManterEstoque manterEstoque = new ManterEstoque();

            List<Estoque> listEstoque = manterEstoque.listarTodos();
            if (listEstoque != null) {
                for (final Estoque estoque : listEstoque) {
                    if (estoque != null) {
                        Estoq c = new Estoq();

                        IManterMedicamento manterMedicamento = new ManterMedicamento();
                        Medicamento medicamento = manterMedicamento.pesquisarPorCod(estoque.getCod_medic_id());

                        c.setCodigo(medicamento.getCod_medic());
                        c.setMedicamento(medicamento.getNom_medic());
                        c.setQuantidade(estoque.getQtd_movimentada());

                        myImageView = new ImageView();
                        VBox vb = new VBox();
                        Image image = SwingFXUtils.toFXImage(createImageFromBytes(medicamento.getImagem()), null);
                        myImageView.setImage(image);
                        vb.getChildren().addAll(myImageView);

                        c.setImagem(vb);

                        lista.add(c);

                    }
                }
                tbestoque.setItems(FXCollections.observableArrayList(lista));

            }

        } catch (PersistenciaException ex) {
            Logger.getLogger(ExibirConsultasController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private BufferedImage createImageFromBytes(byte[] imageData) {
        ByteArrayInputStream bais = new ByteArrayInputStream(imageData);
        try {
            return ImageIO.read(bais);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void detalhes(ActionEvent event) {
        try {
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/br/cefetmg/conates/view/AtualizacaoEstoque.fxml"));
            Scene novaCena = new Scene(root);
            window.setScene(novaCena);

        } catch (IOException ex) {
            Logger.getLogger(LoginController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * *** Fim da Barra de Menu *****
     */
    public void init() {
        TableColumn clImagem;
        TableColumn clCodigo;
        TableColumn clMedicamento;
        TableColumn clQuantidade;
        TableColumn clRetirar;

        clImagem = new TableColumn<>("Medicamento");
        clImagem.setCellValueFactory(new PropertyValueFactory<>("imagem"));
        clImagem.setPrefWidth(200);

        clCodigo = new TableColumn<>("Código");
        clCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        clCodigo.setPrefWidth(200);

        clMedicamento = new TableColumn<>("Nome");
        clMedicamento.setCellValueFactory(new PropertyValueFactory<>("medicamento"));
        clMedicamento.setPrefWidth(200);

        clQuantidade = new TableColumn<>("Quantidade");
        clQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        clQuantidade.setPrefWidth(200);

        tbestoque.getColumns().addAll(clImagem, clMedicamento, clQuantidade);
        atualizarTabela();

    }

    public class Estoq {

        //private String imagem;
        private Long codigo;
        private String medicamento;
        private Double quantidade;
        private VBox imagem;
        private Button btRetirar;

        public Button getBtRetirar() {
            return btRetirar;
        }

        public void setBtRetirar(Button btRetirar) {
            this.btRetirar = btRetirar;
        }

        public VBox getImagem() {
            return imagem;
        }

        public void setImagem(VBox imagem) {
            this.imagem = imagem;
        }

        public Long getCodigo() {
            return codigo;
        }

        public void setCodigo(Long codigo) {
            this.codigo = codigo;
        }

        public String getMedicamento() {
            return medicamento;
        }

        public void setMedicamento(String medicamento) {
            this.medicamento = medicamento;
        }

        public Double getQuantidade() {
            return quantidade;
        }

        public void setQuantidade(Double quantidade) {
            this.quantidade = quantidade;
        }

    }
}
