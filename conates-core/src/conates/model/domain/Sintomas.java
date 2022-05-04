package conates.model.domain;

import java.util.Objects;

public class Sintomas {

    private Long cod_queixa;
    private String des_queixa;

    public Sintomas() {
    }

    public Long getCod_queixa() {
        return cod_queixa;
    }

    public void setCod_queixa(Long cod_queixa) {
        this.cod_queixa = cod_queixa;
    }

    public String getDes_queixa() {
        return des_queixa;
    }

    public void setDes_queixa(String des_queixa) {
        this.des_queixa = des_queixa;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.cod_queixa);
        hash = 17 * hash + Objects.hashCode(this.des_queixa);
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
        final Sintomas other = (Sintomas) obj;
        if (!Objects.equals(this.des_queixa, other.des_queixa)) {
            return false;
        }
        if (!Objects.equals(this.cod_queixa, other.cod_queixa)) {
            return false;
        }
        return true;
    }
    
    

}
