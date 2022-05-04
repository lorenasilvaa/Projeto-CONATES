
package conates.model.domain;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author BRUNA
 */
public class Estoque {
    private Long cod_estoque;
    private Long cod_lote_id;
    private Long cod_medic_id;
    private Long cod_tipo_id;
    private Date dat_validade;
    private Date dat_movimentacao;
    private double qtd_movimentada;

    public Long getCod_estoque() {
        return cod_estoque;
    }

    public void setCod_estoque(Long cod_estoque) {
        this.cod_estoque = cod_estoque;
    }

    public Long getCod_lote_id() {
        return cod_lote_id;
    }

    public void setCod_lote_id(Long cod_lote_id) {
        this.cod_lote_id = cod_lote_id;
    }

    public Long getCod_medic_id() {
        return cod_medic_id;
    }

    public void setCod_medic_id(Long cod_medic_id) {
        this.cod_medic_id = cod_medic_id;
    }

    public Long getCod_tipo_id() {
        return cod_tipo_id;
    }

    public void setCod_tipo_id(Long cod_tipo_id) {
        this.cod_tipo_id = cod_tipo_id;
    }

    public Date getDat_validade() {
        return dat_validade;
    }

    public void setDat_validade(Date dat_validade) {
        this.dat_validade = dat_validade;
    }

    public Date getDat_movimentacao() {
        return dat_movimentacao;
    }

    public void setDat_movimentacao(Date dat_movimentacao) {
        this.dat_movimentacao = dat_movimentacao;
    }

    public double getQtd_movimentada() {
        return qtd_movimentada;
    }

    public void setQtd_movimentada(double qtd_movimentada) {
        this.qtd_movimentada = qtd_movimentada;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.cod_estoque);
        hash = 29 * hash + Objects.hashCode(this.cod_lote_id);
        hash = 29 * hash + Objects.hashCode(this.cod_medic_id);
        hash = 29 * hash + Objects.hashCode(this.cod_tipo_id);
        hash = 29 * hash + Objects.hashCode(this.dat_validade);
        hash = 29 * hash + Objects.hashCode(this.dat_movimentacao);
        hash = 29 * hash + (int) (Double.doubleToLongBits(this.qtd_movimentada) ^ (Double.doubleToLongBits(this.qtd_movimentada) >>> 32));
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
        final Estoque other = (Estoque) obj;
        if (Double.doubleToLongBits(this.qtd_movimentada) != Double.doubleToLongBits(other.qtd_movimentada)) {
            return false;
        }
        if (!Objects.equals(this.cod_estoque, other.cod_estoque)) {
            return false;
        }
        if (!Objects.equals(this.cod_lote_id, other.cod_lote_id)) {
            return false;
        }
        if (!Objects.equals(this.cod_medic_id, other.cod_medic_id)) {
            return false;
        }
        if (!Objects.equals(this.cod_tipo_id, other.cod_tipo_id)) {
            return false;
        }
        if (!Objects.equals(this.dat_validade, other.dat_validade)) {
            return false;
        }
        if (!Objects.equals(this.dat_movimentacao, other.dat_movimentacao)) {
            return false;
        }
        return true;
    }

    

    
   }
