package br.cefetmg.conates.controller;

import static br.cefetmg.conates.controller.ExibirConsultasController.botaoEscolhido;
import conates.model.domain.Consulta;
import conates.model.domain.Enfermeiro;
import conates.model.domain.Medicamento;
import conates.model.domain.Paciente;
import conates.model.domain.Receita;
import conates.model.domain.Sintomas;
import conates.model.domain.SintomasQueixa;
import conates.model.service.IManterConsulta;
import conates.model.service.IManterEnfermeiro;
import conates.model.service.IManterMedicamento;
import conates.model.service.IManterPaciente;
import conates.model.service.IManterReceita;
import conates.model.service.IManterSintomas;
import conates.model.service.IManterSintomasQueixa;
import conates.model.service.impl.ManterConsulta;
import conates.model.service.impl.ManterEnfermeiro;
import conates.model.service.impl.ManterMedicamento;
import conates.model.service.impl.ManterPaciente;
import conates.model.service.impl.ManterReceita;
import conates.model.service.impl.ManterSintomas;
import conates.model.service.impl.ManterSintomasQueixa;
import conates.util.FormatadorData;
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

public class PesquisarConsultaController implements Initializable {

    @FXML
    private Label lbnome;

    @FXML
    private Button btnsair;

    @FXML
    private Label lbluser;

    @FXML
    private TableView tbconsulta;

    @FXML
    private Button btnVoltar;

    @FXML
    private Button btnsobre;

    @FXML
    private Button btnajuda;

    List lista = new ArrayList();
    
    public static boolean verificaTela = true;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            IManterPaciente manterPaciente = new ManterPaciente();
            Paciente paciente = manterPaciente.pesquisarPorCod(Long.parseLong(ExibirConsultasController.l));
            this.lbnome.setText("Nenhum resultado da pesquisa  de : " + paciente.getNom_paciente());
        } catch (PersistenciaException ex) {
            Logger.getLogger(ExibirConsultasController.class.getName()).log(Level.SEVERE, null, ex);
        }
        init();

    }

    public void atualizarTabela() {

        try {
            IManterPaciente manterPaciente = new ManterPaciente();
            Paciente paciente = manterPaciente.pesquisarPorCod(Long.parseLong(ExibirConsultasController.l));
            //  ExibirConsultasController.Consultas c = new ExibirConsultasController.Consultas();

            IManterReceita manterReceita = new ManterReceita();

            List<Receita> listReceita = manterReceita.listarTodos();

            if (listReceita != null) {
                for (Receita receita : listReceita) {
                    if (receita != null) {

                        if (paciente.getCod_paciente().equals(receita.getPaciente().getCod_paciente())) {
                            //JOptionPane.showMessageDialog(null, receita.getPaciente().getCod_paciente()); 
                            this.lbnome.setText("Resultado da pesquisa  de : " + paciente.getNom_paciente());
                            Consultas c = new Consultas();
                            c.setPaciente(paciente.getNom_paciente());
                            final Button btneditar = new Button("Editar");

                            btneditar.setId(receita.getCod_receita().toString());
                            btneditar.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    //JOptionPane.showMessageDialog(null,btneditar.getId());
                                    chamaEditar(btneditar.getId());
                                    try {
                                        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                        Parent root = FXMLLoader.load(getClass().getResource("/br/cefetmg/conates/view/EditarConsultas.fxml"));
                                        Scene novaCena = new Scene(root);
                                        window.setScene(novaCena);
                                    } catch (IOException ex) {
                                        Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                            });
                            IManterSintomasQueixa manterSintoma = new ManterSintomasQueixa();
                            SintomasQueixa sintoma = manterSintoma.pesquisarPorCod(receita.getConsulta().getCod_consulta());
                            IManterSintomas s = new ManterSintomas();
                            Sintomas si = s.pesquisarPorCod(sintoma.getSintoma().getCod_queixa());
                            c.setSin(si.getDes_queixa());

                            IManterConsulta manterConsulta = new ManterConsulta();
                            Consulta consulta = manterConsulta.pesquisarPorCod(receita.getConsulta().getCod_consulta());
                            c.setData(consulta.getDat_consulta());
                            c.setHorario(FormatadorData.formatar(consulta.getDat_consulta(),"dd/MM/yyyy"));

                            IManterEnfermeiro manterEnfermeiro = new ManterEnfermeiro();
                            Enfermeiro enfermeiro = manterEnfermeiro.pesquisarPorCod(receita.getConsulta().getCod_enf_id());
                            c.setEnfermeiro(enfermeiro.getNom_enf());

                            IManterMedicamento manterMedicamento = new ManterMedicamento();
                            Medicamento medicamento = manterMedicamento.pesquisarPorCod(receita.getMedicamento().getCod_medic());

                            c.setMedicamento(medicamento.getNom_medic());
                            c.setQuantidade(receita.getQtd_fornecida());

                            c.setTxtDosagem(receita.getTxt_dosagem());

                            c.setBotaoEditar(btneditar);

                            lista.add(c);
                        }

                    }
                }
            }
            tbconsulta.setItems(FXCollections.observableArrayList(lista));
        } catch (PersistenciaException ex) {
            Logger.getLogger(ExibirConsultasController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void chamaEditar(String s) {
        botaoEscolhido = s;
    }
    @FXML
    public void sair(ActionEvent event) {
        try {
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/br/cefetmg/conates/view/Login.fxml"));
            Scene novaCena = new Scene(root);
            window.setScene(novaCena);
        } catch (IOException ex) {
            Logger.getLogger(PesquisarConsultaController.class.getName()).log(Level.SEVERE, null, ex);
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

    public void init() {

        TableColumn clPaciente;
        TableColumn clSintomas;
        TableColumn clData;
        TableColumn clHorario;
        TableColumn clEnfermeiro;
        TableColumn clMedicamento;
        TableColumn clQuantidade;
        TableColumn clTxtDosagem;
        TableColumn clEditar;

        clPaciente = new TableColumn<>("Paciente");
        clPaciente.setCellValueFactory(new PropertyValueFactory<>("Paciente"));
        clPaciente.setPrefWidth(120);

        clSintomas = new TableColumn<>("Sintomas");
        clSintomas.setCellValueFactory(new PropertyValueFactory<>("Sin"));
        clSintomas.setPrefWidth(120);

        clData = new TableColumn<>("Data");
        clData.setCellValueFactory(new PropertyValueFactory<>("Data"));
        clData.setPrefWidth(120);

        clHorario = new TableColumn<>("Horário");
        clHorario.setCellValueFactory(new PropertyValueFactory<>("Horario"));
        clHorario.setPrefWidth(120);

        clEnfermeiro = new TableColumn<>("Enfermeiro");
        clEnfermeiro.setCellValueFactory(new PropertyValueFactory<>("Enfermeiro"));
        clEnfermeiro.setPrefWidth(120);

        clMedicamento = new TableColumn<>("Medicamento");
        clMedicamento.setCellValueFactory(new PropertyValueFactory<>("Medicamento"));
        clMedicamento.setPrefWidth(120);

        clQuantidade = new TableColumn<>("Qtd");
        clQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        clQuantidade.setPrefWidth(120);

        clTxtDosagem = new TableColumn<>("Observação");
        clTxtDosagem.setCellValueFactory(new PropertyValueFactory<>("txtDosagem"));
        clTxtDosagem.setPrefWidth(120);

        clEditar = new TableColumn<>("Editar");
        clEditar.setCellValueFactory(new PropertyValueFactory<>("botaoEditar"));
        clEditar.setPrefWidth(120);

        tbconsulta.getColumns().addAll(clPaciente, clSintomas, clData, clHorario, clEnfermeiro, clMedicamento, clQuantidade, clTxtDosagem, clEditar);

        atualizarTabela();

    }

    public class Consultas {

        private String Paciente;
        private String Sin;
        private Date Data;
        private String Horario;
        private String Enfermeiro;
        private String Medicamento;
        private Double quantidade;
        private String txtDosagem;
        private Button botaoEditar;

        public Consultas() {
        }

        public Button getBotaoEditar() {
            return botaoEditar;
        }

        public void setBotaoEditar(Button botaoEditar) {
            this.botaoEditar = botaoEditar;
        }

        public String getPaciente() {
            return Paciente;
        }

        public void setPaciente(String Paciente) {
            this.Paciente = Paciente;
        }

        public String getSin() {
            return Sin;
        }

        public void setSin(String Sin) {
            this.Sin = Sin;
        }

        public Date getData() {
            return Data;
        }

        public void setData(Date Data) {
            this.Data = Data;
        }

        public String getHorario() {
            return Horario;
        }

        public void setHorario(String Horario) {
            this.Horario = Horario;
        }

        public String getEnfermeiro() {
            return Enfermeiro;
        }

        public void setEnfermeiro(String Enfermeiro) {
            this.Enfermeiro = Enfermeiro;
        }

        public String getMedicamento() {
            return Medicamento;
        }

        public void setMedicamento(String Medicamento) {
            this.Medicamento = Medicamento;
        }

        public Double getQuantidade() {
            return quantidade;
        }

        public void setQuantidade(Double quantidade) {
            this.quantidade = quantidade;
        }

        public String getTxtDosagem() {
            return txtDosagem;
        }

        public void setTxtDosagem(String txtDosagem) {
            this.txtDosagem = txtDosagem;
        }

    }

    @FXML
    public void voltarPesquisa(ActionEvent event) {
        try {
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/br/cefetmg/conates/view/ExibirConsultas.fxml"));
            Scene novaCena = new Scene(root);
            window.setScene(novaCena);
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
