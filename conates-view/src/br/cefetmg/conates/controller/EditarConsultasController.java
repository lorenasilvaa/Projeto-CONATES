package br.cefetmg.conates.controller;

import br.cefetmg.conates.Conates;
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
import conates.util.validacoes;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class EditarConsultasController implements Initializable {

    @FXML
    private Button btnsobre;

    @FXML
    private Button btnajuda;

    @FXML
    public Label lbluser;

    @FXML
    private Button btnlimpar;

    @FXML
    private Button btnsair;

    @FXML
    private TextArea tadosagem;

    @FXML
    private TextField tfcodpaciente;

    @FXML
    private TextArea tadata;

    @FXML
    private MenuButton mbenfermeiro;

    @FXML
    private MenuButton mbmedicamento;

    @FXML
    private TextArea tfqtdreceitada;

    @FXML
    private TextArea tahorario;

    @FXML
    private MenuButton mbselecione;

    @FXML
    private Button btnsairConsulta;

    @FXML
    private Button btnconcluirEdicao;

    validacoes valida = new validacoes();
    Long cod;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Conates.janelaAnterior = Conates.janela;
        Conates.janela = "EditarConsultas.fxml";
        valida.addTextLimiter(tadata, 10);
        valida.addTextLimiter(tahorario, 5);
        this.tfcodpaciente.setDisable(true);
        iniciaMenus();
        try {
            cod = Long.parseLong(ExibirConsultasController.botaoEscolhido);

            IManterReceita manterReceita = new ManterReceita();
            Receita receita = manterReceita.pesquisarPorCod(cod);

            if (receita != null) {

                IManterPaciente manterPaciente = new ManterPaciente();
                Paciente paciente = manterPaciente.pesquisarPorCod(receita.getPaciente().getCod_paciente());
                this.tfcodpaciente.setText(paciente.getCod_paciente().toString());

                IManterSintomasQueixa manterSintoma = new ManterSintomasQueixa();
                SintomasQueixa sintoma = manterSintoma.pesquisarPorCod(receita.getConsulta().getCod_consulta());
                IManterSintomas s = new ManterSintomas();
                Sintomas si = s.pesquisarPorCod(sintoma.getSintoma().getCod_queixa());
                this.mbselecione.setText(si.getDes_queixa());

                IManterConsulta manterConsulta = new ManterConsulta();
                Consulta consulta = manterConsulta.pesquisarPorCod(receita.getConsulta().getCod_consulta());
                this.tadata.setText(FormatadorData.formatar(consulta.getDat_consulta(), "dd/MM/yyyy"));
                this.tahorario.setText(consulta.getHor_consulta().toString());

                IManterEnfermeiro manterEnfermeiro = new ManterEnfermeiro();
                Enfermeiro enfermeiro = manterEnfermeiro.pesquisarPorCod(receita.getConsulta().getCod_enf_id());
                this.mbenfermeiro.setText(enfermeiro.getNom_enf());

                IManterMedicamento manterMedicamento = new ManterMedicamento();
                Medicamento medicamento = manterMedicamento.pesquisarPorCod(receita.getMedicamento().getCod_medic());

                this.mbmedicamento.setText(medicamento.getNom_medic());
                
                this.tfqtdreceitada.setText(String.valueOf(receita.getQtd_fornecida()));

                this.tadosagem.setText(receita.getTxt_dosagem());

            }
        } catch (PersistenciaException ex) {
            Logger.getLogger(ExibirConsultasController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void iniciaMenus() {
        try {
            IManterSintomas manterSintomas = new ManterSintomas();
            List<Sintomas> listSintomas = manterSintomas.listarTodos();

            IManterMedicamento manterMedicamento = new ManterMedicamento();
            List<Medicamento> listmedicamentos = manterMedicamento.listarTodos();

            IManterEnfermeiro manterEnfermeiro = new ManterEnfermeiro();
            List<Enfermeiro> listenfermeiro = manterEnfermeiro.listarTodos();

            for (Sintomas sintomas : listSintomas) {
                final MenuItem menuItem = new MenuItem(sintomas.getDes_queixa());
                menuItem.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {
                        mbselecione.setText(menuItem.getText());
                    }
                });
                this.mbselecione.getItems().addAll(menuItem);
            }

            for (Medicamento medicamentos : listmedicamentos) {
                final MenuItem menuItem = new MenuItem(medicamentos.getNom_medic());
                menuItem.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {
                        mbmedicamento.setText(menuItem.getText());
                    }
                });
                this.mbmedicamento.getItems().addAll(menuItem);
            }

            for (Enfermeiro enfermeiro : listenfermeiro) {
                final MenuItem menuItem = new MenuItem(enfermeiro.getNom_enf());
                menuItem.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {
                        mbenfermeiro.setText(menuItem.getText());
                    }
                });
                this.mbenfermeiro.getItems().addAll(menuItem);
            }

        } catch (PersistenciaException ex) {
            Logger.getLogger(CadastroConsultasController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void formatarData() {
        valida.formatar("##/##/####", tadata, tadata.getText().length(), 10);
    }

    @FXML
    public void formatarHorario() {
        valida.formatar("##:##", tahorario, tahorario.getText().length(), 5);
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
    public void concluirEdicao(ActionEvent event) {
        
        if (this.tfqtdreceitada.getText().isEmpty() || this.mbmedicamento.getText().equals("SELECIONE") || this.tadata.getText().isEmpty() || this.tahorario.getText().isEmpty() || this.mbselecione.getText().equals("SELECIONE") || this.mbenfermeiro.getText().equals("SELECIONE")) {

            JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios *", "Atenção", JOptionPane.ERROR_MESSAGE, null);

        } else if (!(verificaCampoNumerico(this.tadata.getText())) || !(verificaCampoNumerico(this.tahorario.getText())) || !(verificaCampo(this.tfqtdreceitada.getText()))) {
            JOptionPane.showMessageDialog(null, "Os campos data, hora, quantidade e código do paciente apenas aceitam números", "Atenção", JOptionPane.ERROR_MESSAGE, null);
        } else if (!(valida.verificaData(this.tadata.getText()))) {
            JOptionPane.showMessageDialog(null, "Data inválida!!", "Atenção", JOptionPane.ERROR_MESSAGE, null);

        } else if (!(valida.verificaHora(this.tahorario.getText()))) {
            JOptionPane.showMessageDialog(null, "Hora inválida!!", "Atenção", JOptionPane.ERROR_MESSAGE, null);
        } else {
            try {

                IManterEnfermeiro manterEnfermeiro = new ManterEnfermeiro();
                Enfermeiro enfermeiro = manterEnfermeiro.pesquisarPorNome(this.mbenfermeiro.getText());

                IManterPaciente manterPaciente = new ManterPaciente();
                Paciente paciente = manterPaciente.pesquisarPorCod(Long.parseLong(this.tfcodpaciente.getText()));

                IManterMedicamento manterMedicamento = new ManterMedicamento();
                Medicamento medicamento = manterMedicamento.pesquisarPorNome(this.mbmedicamento.getText());

                IManterSintomas manterSintomas = new ManterSintomas();
                Sintomas sintomas = manterSintomas.pesquisarPorNome(this.mbselecione.getText());

                IManterReceita manterReceita = new ManterReceita();
                Receita receita = manterReceita.pesquisarPorCod(cod);

                Consulta consultas = new Consulta();

                SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                Date data = formato.parse(this.tadata.getText());

                consultas.setCod_consulta(receita.getConsulta().getCod_consulta());
                consultas.setCod_paciente_id(paciente.getCod_paciente());
                consultas.setCod_enf_id(enfermeiro.getCod_enf());
                consultas.setDat_consulta(data);
                consultas.setHor_consulta(this.tahorario.getText());

                IManterConsulta manterConsulta = new ManterConsulta();
                boolean updated1 = manterConsulta.alterar(consultas);
                //--------

                SintomasQueixa sintomasqueixa = new SintomasQueixa();
                if (this.tadosagem.getText().isEmpty()) {
                    this.tadosagem.setText("");
                }
                IManterSintomasQueixa manterSintomasQueixa = new ManterSintomasQueixa();
                sintomasqueixa = manterSintomasQueixa.pesquisarPorCod(consultas.getCod_consulta());

                sintomasqueixa.setCod_sintoma_queixa(sintomasqueixa.getCod_sintoma_queixa());
                sintomasqueixa.setConsulta(consultas);
                sintomasqueixa.setPessoa(paciente);
                sintomasqueixa.setSintoma(sintomas);

                //--------
                receita = new Receita();
                if (this.tfqtdreceitada.getText().isEmpty()) {
                    this.tfqtdreceitada.setText("0");
                }
                receita.setCod_receita(cod);
                receita.setConsulta(consultas);
                receita.setMedicamento(medicamento);
                receita.setPaciente(paciente);
                receita.setQtd_fornecida(Double.parseDouble(this.tfqtdreceitada.getText()));
                receita.setTxt_dosagem(this.tadosagem.getText());

                boolean updated2 = manterSintomasQueixa.alterar(sintomasqueixa);

                boolean updated3 = manterReceita.alterar(receita);

                if ((updated1 = true) && (updated2 = true) && (updated3 = true)) {
                    try {
                        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        Parent root = FXMLLoader.load(getClass().getResource("/br/cefetmg/conates/view/ExibirConsultas.fxml"));
                        Scene novaCena = new Scene(root);
                        window.setScene(novaCena);
                    } catch (IOException ex) {
                        Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Não foi possível alterar esse registro!", "Aviso", JOptionPane.ERROR_MESSAGE, null);
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
            Logger.getLogger(EditarConsultasController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void sairEdicao(ActionEvent event) {
        try {
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/br/cefetmg/conates/view/ExibirConsultas.fxml"));
            Scene novaCena = new Scene(root);
            window.setScene(novaCena);
        } catch (IOException ex) {
            Logger.getLogger(CadastroConsultasController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void limparTela(ActionEvent event) {
        tadata.setText("");
        mbenfermeiro.setText("SELECIONE");
        mbmedicamento.setText("SELECIONE");
        tfqtdreceitada.setText("");
        tahorario.setText("");
        tadosagem.setText("");
        mbselecione.setText("SELECIONE");
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

}
