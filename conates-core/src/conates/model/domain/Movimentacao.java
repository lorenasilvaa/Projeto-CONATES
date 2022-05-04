package conates.model.domain;

import java.util.Objects;

public class Movimentacao {

    private Long cod_tipo;
    private String des_tipo;

    public Long getCod_tipo() {
        return cod_tipo;
    }

    public void setCod_tipo(Long cod_tipo) {
        this.cod_tipo = cod_tipo;
    }

    public String getDes_tipo() {
        return des_tipo;
    }

    public void setDes_tipo(String des_tipo) {
        this.des_tipo = des_tipo;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + Objects.hashCode(this.cod_tipo);
        hash = 17 * hash + Objects.hashCode(this.des_tipo);
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
        final Movimentacao other = (Movimentacao) obj;
        if (!Objects.equals(this.des_tipo, other.des_tipo)) {
            return false;
        }
        if (!Objects.equals(this.cod_tipo, other.cod_tipo)) {
            return false;
        }
        return true;
    }

}
