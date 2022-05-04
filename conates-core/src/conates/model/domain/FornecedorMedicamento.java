package conates.model.domain;

import java.util.Objects;

public class FornecedorMedicamento {

    private Long cod_fornecimento;
    private Long cod_medic_id;
    private Long cnpj_empresa_id;
        private int qtd_medicamento;
        private String txt_validacao;
        private String est_movimentacao;

    public Long getCod_fornecimento() {
        return cod_fornecimento;
    }

    public void setCod_fornecimento(Long cod_fornecimento) {
        this.cod_fornecimento = cod_fornecimento;
    }

    public Long getCod_medic_id() {
        return cod_medic_id;
    }

    public void setCod_medic_id(Long cod_medic_id) {
        this.cod_medic_id = cod_medic_id;
    }

    public Long getCnpj_empresa_id() {
        return cnpj_empresa_id;
    }

    public void setCnpj_empresa_id(Long cnpj_empresa_id) {
        this.cnpj_empresa_id = cnpj_empresa_id;
    }

    public int getQtd_medicamento() {
        return qtd_medicamento;
    }

    public void setQtd_medicamento(int qtd_medicamento) {
        this.qtd_medicamento = qtd_medicamento;
    }

    public String getTxt_validacao() {
        return txt_validacao;
    }

    public void setTxt_validacao(String txt_validacao) {
        this.txt_validacao = txt_validacao;
    }

    public String getEst_movimentacao() {
        return est_movimentacao;
    }

    public void setEst_movimentacao(String est_movimentacao) {
        this.est_movimentacao = est_movimentacao;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + Objects.hashCode(this.cod_fornecimento);
        hash = 47 * hash + Objects.hashCode(this.cod_medic_id);
        hash = 47 * hash + Objects.hashCode(this.cnpj_empresa_id);
        hash = 47 * hash + this.qtd_medicamento;
        hash = 47 * hash + Objects.hashCode(this.txt_validacao);
        hash = 47 * hash + Objects.hashCode(this.est_movimentacao);
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
        final FornecedorMedicamento other = (FornecedorMedicamento) obj;
        if (this.qtd_medicamento != other.qtd_medicamento) {
            return false;
        }
        if (!Objects.equals(this.txt_validacao, other.txt_validacao)) {
            return false;
        }
        if (!Objects.equals(this.est_movimentacao, other.est_movimentacao)) {
            return false;
        }
        if (!Objects.equals(this.cod_fornecimento, other.cod_fornecimento)) {
            return false;
        }
        if (!Objects.equals(this.cod_medic_id, other.cod_medic_id)) {
            return false;
        }
        if (!Objects.equals(this.cnpj_empresa_id, other.cnpj_empresa_id)) {
            return false;
        }
        return true;
    }
    
    

}
