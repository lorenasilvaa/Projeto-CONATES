package conates.model.domain;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Date;
import java.util.Objects;

public class Medicamento {
    private Long cod_medic;
    private String nom_medic;
   private byte[] imagem;
    private Long cod_tipo_id;

    public Long getCod_medic() {
        return cod_medic;
    }

    public void setCod_medic(Long cod_medic) {
        this.cod_medic = cod_medic;
    }

    public String getNom_medic() {
        return nom_medic;
    }

    public void setNom_medic(String nom_medic) {
        this.nom_medic = nom_medic;
    }

    public byte[] getImagem() {
        return imagem;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
    }

   
    public Long getCod_tipo_id() {
        return cod_tipo_id;
    }

    public void setCod_tipo_id(Long cod_tipo_id) {
        this.cod_tipo_id = cod_tipo_id;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.cod_medic);
        hash = 29 * hash + Objects.hashCode(this.nom_medic);
        //hash = 29 * hash + Objects.hashCode(this.imagem);
        hash = 29 * hash + Objects.hashCode(this.cod_tipo_id);
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
        final Medicamento other = (Medicamento) obj;
        if (!Objects.equals(this.nom_medic, other.nom_medic)) {
            return false;
        }
        if (!Objects.equals(this.cod_medic, other.cod_medic)) {
            return false;
        }
      /*  if (!Objects.equals(this.imagem, other.imagem)) {
            return false;}
        */
        if (!Objects.equals(this.cod_tipo_id, other.cod_tipo_id)) {
            return false;
        }
        return true;
    }

    
  
}
