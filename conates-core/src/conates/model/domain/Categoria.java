package conates.model.domain;

import java.util.Objects;

public class Categoria {

    private Long cod_tipo;
    private String nom_tipo;

    public Long getCod_tipo() {
        return cod_tipo;
    }

    public void setCod_tipo(Long cod_tipo) {
        this.cod_tipo = cod_tipo;
    }

    public String getNom_tipo() {
        return nom_tipo;
    }

    public void setNom_tipo(String nom_tipo) {
        this.nom_tipo = nom_tipo;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.cod_tipo);
        hash = 29 * hash + Objects.hashCode(this.nom_tipo);
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
        final Categoria other = (Categoria) obj;
        if (!Objects.equals(this.nom_tipo, other.nom_tipo)) {
            return false;
        }
        if (!Objects.equals(this.cod_tipo, other.cod_tipo)) {
            return false;
        }
        return true;
    }

    
    
    
}
