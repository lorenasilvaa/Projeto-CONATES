package conates.model.domain;

import java.util.Objects;

public class Enfermeiro {

    private Long cod_enf;
    private String nom_enf;

    public Long getCod_enf() {
        return cod_enf;
    }

    public void setCod_enf(Long cod_enf) {
        this.cod_enf = cod_enf;
    }

    public String getNom_enf() {
        return nom_enf;
    }

    public void setNom_enf(String nom_enf) {
        this.nom_enf = nom_enf;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.cod_enf);
        hash = 97 * hash + Objects.hashCode(this.nom_enf);
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
        final Enfermeiro other = (Enfermeiro) obj;
        if (!Objects.equals(this.nom_enf, other.nom_enf)) {
            return false;
        }
        if (!Objects.equals(this.cod_enf, other.cod_enf)) {
            return false;
        }
        return true;
    }

}
