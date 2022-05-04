package conates.model.domain;

import java.util.Objects;

public class Receita {

    private Long cod_receita;
    private Medicamento medicamento;
    private Consulta consulta;
    private Paciente paciente;
    private String txt_dosagem;
    private double qtd_fornecida;

    public Long getCod_receita() {
        return cod_receita;
    }

    public void setCod_receita(Long cod_receita) {
        this.cod_receita = cod_receita;
    }

    public Medicamento getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(Medicamento medicamento) {
        this.medicamento = medicamento;
    }

    public Consulta getConsulta() {
        return consulta;
    }

    public void setConsulta(Consulta consulta) {
        this.consulta = consulta;
    }

    public String getTxt_dosagem() {
        return txt_dosagem;
    }

    public void setTxt_dosagem(String txt_dosagem) {
        this.txt_dosagem = txt_dosagem;
    }

    public double getQtd_fornecida() {
        return qtd_fornecida;
    }

    public void setQtd_fornecida(double qtd_fornecida) {
        this.qtd_fornecida = qtd_fornecida;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + Objects.hashCode(this.cod_receita);
        hash = 73 * hash + Objects.hashCode(this.medicamento);
        hash = 73 * hash + Objects.hashCode(this.consulta);
        hash = 73 * hash + Objects.hashCode(this.paciente);
        hash = 73 * hash + Objects.hashCode(this.txt_dosagem);
        hash = 73 * hash + (int) (Double.doubleToLongBits(this.qtd_fornecida) ^ (Double.doubleToLongBits(this.qtd_fornecida) >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Receita other = (Receita) obj;
        if (Double.doubleToLongBits(this.qtd_fornecida) != Double.doubleToLongBits(other.qtd_fornecida)) {
            return false;
        }
        if (!Objects.equals(this.txt_dosagem, other.txt_dosagem)) {
            return false;
        }
        if (!Objects.equals(this.cod_receita, other.cod_receita)) {
            return false;
        }
        if (!Objects.equals(this.medicamento, other.medicamento)) {
            return false;
        }
        if (!Objects.equals(this.consulta, other.consulta)) {
            return false;
        }
        if (!Objects.equals(this.paciente, other.paciente)) {
            return false;
        }
        return true;
    }

    
}
