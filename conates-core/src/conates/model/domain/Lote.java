
package conates.model.domain;

import java.util.Date;
import java.util.Objects;

public class Lote {
    

    private Long cod_lote;
    private Date dat_recebimento;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.cod_lote);
        hash = 53 * hash + Objects.hashCode(this.dat_recebimento);
        hash = 53 * hash + Objects.hashCode(this.cnpj_empresa_id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Lote other = (Lote) obj;
        if (!Objects.equals(this.cod_lote, other.cod_lote)) {
            return false;
        }
        if (!Objects.equals(this.dat_recebimento, other.dat_recebimento)) {
            return false;
        }
        if (!Objects.equals(this.cnpj_empresa_id, other.cnpj_empresa_id)) {
            return false;
        }
        return true;
    }

    public Long getCod_lote() {
        return cod_lote;
    }

    public void setCod_lote(Long cod_lote) {
        this.cod_lote = cod_lote;
    }

    public Date getDat_recebimento() {
        return dat_recebimento;
    }

    public void setDat_recebimento(Date dat_recebimento) {
        this.dat_recebimento = dat_recebimento;
    }

    public Long getCnpj_empresa_id() {
        return cnpj_empresa_id;
    }

    public void setCnpj_empresa_id(Long cnpj_empresa_id) {
        this.cnpj_empresa_id = cnpj_empresa_id;
    }
    private Long cnpj_empresa_id;

}