package br.cefetmg.conates.controller;

import br.cefetmg.conates.Conates;
import conates.model.domain.Medicamento;

import conates.model.service.IManterMedicamento;
import conates.model.service.IManterCategoria;
import conates.model.service.IManterFornecedor;
import conates.model.service.impl.ManterMedicamento;
import conates.model.service.impl.ManterCategoria;
import conates.model.service.impl.ManterFornecedor;
import conates.model.domain.Categoria;
import conates.model.domain.Estoque;
import conates.model.domain.Fornecedor;
import conates.model.domain.Lote;
import conates.model.domain.Movimentacao;
import conates.model.service.IManterEstoque;
import conates.model.service.IManterLote;
import conates.model.service.IManterMovimentacao;
import conates.model.service.impl.ManterEstoque;
import conates.model.service.impl.ManterLote;
import conates.model.service.impl.ManterMovimentacao;
import conates.util.db.exception.PersistenciaException;
import conates.util.validacoes;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class CadastroMedicamentoController implements Initializable {

    @FXML
    private Button btnestoque;

    @FXML
    private Button btncadamedicamento;

    @FXML
    private Label lbluser;

    @FXML
    private Button btnconfornecimento;

    @FXML
    private Button btngerarelatorio;

    @FXML
    private Button btnsobre;

    @FXML
    private Button btnajuda;

    @FXML
    private Button btncadastra;

    @FXML
    private Button btnsair;

    @FXML
    private TextField tfnomemedi;

    @FXML
    private TextField tfcodmed;

    @FXML
    private TextField tfcodlote;

    @FXML
    private MenuButton mbfornecedor;

    @FXML
    private Button btnarquivo;

    @FXML
    private MenuButton mbcategoria;

    @FXML
    private TextField tfqtd;

    @FXML
    private TextArea tavalidade;

    @FXML
    private TextArea tadat;

    String caminho;

    @FXML
    private VBox vbimagem;
    validacoes valida = new validacoes();
    ImageView myImageView;
    validacoes validar = new validacoes();
    BufferedImage bufferedImage;
    LoginController login = new LoginController();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.btncadamedicamento.setDisable(true);
        validar.addTextLimiter(tavalidade, 10);

        Conates.janelaAnterior = Conates.janela;
        Conates.janela = "CadastroMedicamento.fxml";

        try {
            IManterCategoria manterCategoria = new ManterCategoria();
            List<Categoria> listCategoria = manterCategoria.listarTodos();

            IManterFornecedor manterFornecedor = new ManterFornecedor();
            List<Fornecedor> listFornecedor = manterFornecedor.listarTodos();

            for (Categoria categoria : listCategoria) {
                final MenuItem menuItem = new MenuItem(categoria.getNom_tipo());
                menuItem.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {
                        mbcategoria.setText(menuItem.getText());
                    }
                });
                mbcategoria.getItems().addAll(menuItem);
            }
            for (Fornecedor fornecedor : listFornecedor) {
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
            Logger.getLogger(CadastroMedicamentoController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
            Logger.getLogger(CadastroMedicamentoController.class.getName()).log(Level.SEVERE, null, ex);
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
    File file;
    EventHandler<ActionEvent> btnLoadEventListener = new EventHandler<ActionEvent>() {

        @Override
        public void handle(ActionEvent t) {
            FileChooser fileChooser = new FileChooser();

            //Set extension filter
            FileChooser.ExtensionFilter extFilterJPG
                    = new FileChooser.ExtensionFilter("JPG files (*.JPG)", "*.JPG");
            FileChooser.ExtensionFilter extFilterjpg
                    = new FileChooser.ExtensionFilter("jpg files (*.jpg)", "*.jpg");
            FileChooser.ExtensionFilter extFilterPNG
                    = new FileChooser.ExtensionFilter("PNG files (*.PNG)", "*.PNG");
            FileChooser.ExtensionFilter extFilterpng
                    = new FileChooser.ExtensionFilter("png files (*.png)", "*.png");
            fileChooser.getExtensionFilters()
                    .addAll(extFilterJPG, extFilterjpg, extFilterPNG, extFilterpng);

            file = fileChooser.showOpenDialog(null);

            try {
                bufferedImage = ImageIO.read(file);
                Image image = SwingFXUtils.toFXImage(bufferedImage, null);

                myImageView.setImage(image);
            } catch (IOException ex) {
                Logger.getLogger(CadastroMedicamentoController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    };

    @FXML
    public void imagem(ActionEvent event) {
        myImageView = new ImageView();
        btnarquivo.setOnAction(btnLoadEventListener);
        vbimagem.getChildren().addAll(myImageView);
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
    @FXML
    public void formatarData() {
        validar.formatar("##/##/####", tavalidade, tavalidade.getText().length(), 10);
        validar.formatar("##/##/####", tadat, tadat.getText().length(), 10);
    }

    public byte[] imageToByte(String image) throws IOException {
        InputStream is = null;
        byte[] buffer = null;
        is = new FileInputStream(image);
        buffer = new byte[is.available()];
        is.read(buffer);
        is.close();
        return buffer;
    }

    public boolean verificaCampo(String s) {
        //boolean teste = false;
        String nova = s;

        return valida.campoNumerico(nova);
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
    public void cadastrarMedicamentos(ActionEvent event) {
        if (this.tfnomemedi.getText().isEmpty()
                || this.tfcodmed.getText().isEmpty()
                || this.tfcodlote.getText().isEmpty()
                || this.mbfornecedor.getText().equals("SELECIONE")
                || this.mbcategoria.getText().equals("SELECIONE")
                || this.tfqtd.getText().isEmpty()
                || this.tavalidade.getText().isEmpty()
                || this.tadat.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios *", "Atenção", JOptionPane.ERROR_MESSAGE, null);
        } else if (!(verificaCampoNumerico(this.tavalidade.getText()))
                || !(verificaCampoNumerico(this.tadat.getText()))
                || !(verificaCampo(this.tfqtd.getText()))
                || !(verificaCampo(this.tfcodmed.getText()))
                || !(verificaCampo(this.tfcodlote.getText()))) {
            JOptionPane.showMessageDialog(null, "Os campos data, validade, quantidade, código do medicamento e lote apenas aceitam números", "Atenção", JOptionPane.ERROR_MESSAGE, null);
        } else if (!(valida.verificaData(this.tavalidade.getText())) || !(valida.verificaData(this.tadat.getText()))) {
        JOptionPane.showMessageDialog(null, "Data inválida", "Atenção", JOptionPane.ERROR_MESSAGE, null);
        } else {
            try {

                /* -- Cadastrar o nome e o código do medicamento na tabela de medicamento -- */
                Medicamento medicamento = new Medicamento();
                medicamento.setCod_medic(Long.parseLong(tfcodmed.getText()));
                medicamento.setNom_medic(tfnomemedi.getText());
                // BufferedImage image = SwingFXUtils.fromFXImage(myImageView, null);
                medicamento.setImagem(imageToByte(file.toPath().toString()));

                /* -- Cadastrar a categoria (cod_tipo_id) na tabela de medicamento -- */
                IManterCategoria manterCategoria = new ManterCategoria();
                Categoria categoria = manterCategoria.pesquisarPorNome(mbcategoria.getText());
                medicamento.setCod_tipo_id(categoria.getCod_tipo());

                /* -- Cadastrar lote(cod_lote), data (dat_recebimento) na tabela Lote -- */
                SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                Date data = formato.parse(this.tadat.getText());

                Lote lote = new Lote();
                lote.setCod_lote(Long.parseLong(tfcodlote.getText()));
                lote.setDat_recebimento(data);

                /* -- Cadastrar fornecedor na tabela LoteFornecimento -- */
                IManterFornecedor manterFornecedor = new ManterFornecedor();
                Fornecedor fornecedor = manterFornecedor.pesquisarPorNome(mbfornecedor.getText());
                lote.setCnpj_empresa_id(fornecedor.getCnpj_empresa());

                /* -- Cadastrar os dados no estoque -- */
                Estoque estoque = new Estoque();
                estoque.setCod_medic_id(Long.parseLong(tfcodmed.getText()));
                // estoque. .setCod_lote_id(Long.parseLong(mbfornecedor.getText()));
                //medicamento.setBytesImg();

                /* -- Cadastrar o cod_tipo da movimentação -- */
                IManterMovimentacao manterMovimentacao = new ManterMovimentacao();
                Movimentacao movimentacao = manterMovimentacao.pesquisarPorCod(1L);
                estoque.setCod_tipo_id(movimentacao.getCod_tipo());

                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

                Date d = new Date();
                sdf.format(d);
                estoque.setDat_movimentacao(d);

                estoque.setQtd_movimentada(Double.parseDouble(tfqtd.getText()));
                Date data1 = formato.parse(this.tavalidade.getText());
                estoque.setDat_validade(data1);
                estoque.setCod_lote_id(Long.parseLong(tfcodlote.getText()));

                IManterLote manterLote = new ManterLote();
                Long LoteId = manterLote.cadastrar(lote);

                IManterMedicamento manterMedicamento = new ManterMedicamento();
                Long MedicamentoId = manterMedicamento.cadastrar(medicamento);

                IManterEstoque manterEstoque = new ManterEstoque();
                Long EstoqueId = manterEstoque.cadastrar(estoque);

                if (LoteId != null && MedicamentoId != null && EstoqueId != null) {
                    try {
                        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        Parent root = FXMLLoader.load(getClass().getResource("/br/cefetmg/conates/view/Estoque.fxml"));
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
}
