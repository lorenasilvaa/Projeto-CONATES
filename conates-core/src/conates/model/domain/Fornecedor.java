package conates.model.domain;

import java.util.Objects;

public class Fornecedor {

    private Long cnpj_empresa;
    private String nom_fantasia;
    private String nom_empresa;
    private String tel_empresa;
    private String email_empresa;

    public Long getCnpj_empresa() {
        return cnpj_empresa;
    }

    public void setCnpj_empresa(Long cnpj_empresa) {
        this.cnpj_empresa = cnpj_empresa;
    }

    public String getNom_fantasia() {
        return nom_fantasia;
    }

    public void setNom_fantasia(String nom_fantasia) {
        this.nom_fantasia = nom_fantasia;
    }

    public String getNom_empresa() {
        return nom_empresa;
    }

    public void setNom_empresa(String nom_empresa) {
        this.nom_empresa = nom_empresa;
    }

    public String getTel_empresa() {
        return tel_empresa;
    }

    public void setTel_empresa(String tel_empresa) {
        this.tel_empresa = tel_empresa;
    }

    

    public String getEmail_empresa() {
        return email_empresa;
    }

    public void setEmail_empresa(String email_empresa) {
        this.email_empresa = email_empresa;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 73 * hash + Objects.hashCode(this.cnpj_empresa);
        hash = 73 * hash + Objects.hashCode(this.nom_fantasia);
        hash = 73 * hash + Objects.hashCode(this.nom_empresa);
        hash = 73 * hash + Objects.hashCode(this.tel_empresa);
        hash = 73 * hash + Objects.hashCode(this.email_empresa);
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
        final Fornecedor other = (Fornecedor) obj;
        if (!Objects.equals(this.nom_fantasia, other.nom_fantasia)) {
            return false;
        }
        if (!Objects.equals(this.nom_empresa, other.nom_empresa)) {
            return false;
        }
        if (!Objects.equals(this.tel_empresa, other.tel_empresa)) {
            return false;
        }
        if (!Objects.equals(this.email_empresa, other.email_empresa)) {
            return false;
        }
        if (!Objects.equals(this.cnpj_empresa, other.cnpj_empresa)) {
            return false;
        }
        return true;
    }

    
}
