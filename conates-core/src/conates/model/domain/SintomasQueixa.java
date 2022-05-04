package conates.model.domain;

import java.util.Objects;

public class SintomasQueixa {
    
    private Long cod_sintoma_queixa;
    private Consulta consulta;
    private Paciente pessoa;
    private Sintomas sintoma;
    private String txt_observacao;

    public Long getCod_sintoma_queixa() {
        return cod_sintoma_queixa;
    }

    public void setCod_sintoma_queixa(Long cod_sintoma_queixa) {
        this.cod_sintoma_queixa = cod_sintoma_queixa;
    }

    public Consulta getConsulta() {
        return consulta;
    }

    public void setConsulta(Consulta consulta) {
        this.consulta = consulta;
    }

    public Paciente getPessoa() {
        return pessoa;
    }

    public void setPessoa(Paciente pessoa) {
        this.pessoa = pessoa;
    }

    public Sintomas getSintoma() {
        return sintoma;
    }

    public void setSintoma(Sintomas sintoma) {
        this.sintoma = sintoma;
    }

    public String getTxt_observacao() {
        return txt_observacao;
    }

    public void setTxt_observacao(String txt_observacao) {
        this.txt_observacao = txt_observacao;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.cod_sintoma_queixa);
        hash = 97 * hash + Objects.hashCode(this.consulta);
        hash = 97 * hash + Objects.hashCode(this.pessoa);
        hash = 97 * hash + Objects.hashCode(this.sintoma);
        hash = 97 * hash + Objects.hashCode(this.txt_observacao);
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
        final SintomasQueixa other = (SintomasQueixa) obj;
        if (!Objects.equals(this.txt_observacao, other.txt_observacao)) {
            return false;
        }
        if (!Objects.equals(this.cod_sintoma_queixa, other.cod_sintoma_queixa)) {
            return false;
        }
        if (!Objects.equals(this.consulta, other.consulta)) {
            return false;
        }
        if (!Objects.equals(this.pessoa, other.pessoa)) {
            return false;
        }
        if (!Objects.equals(this.sintoma, other.sintoma)) {
            return false;
        }
        return true;
    }
}
