package conates.model.domain;

import java.util.Date;
import java.util.Objects;

public class Consulta {

    private Long cod_consulta;
    private Long cod_paciente_id;
    private Date dat_consulta;
    private String hor_consulta;
    private Long cod_enf_id;

    public Consulta() {
    }

    public Long getCod_consulta() {
        return cod_consulta;
    }

    public void setCod_consulta(Long cod_consulta) {
        this.cod_consulta = cod_consulta;
    }

    public Long getCod_paciente_id() {
        return cod_paciente_id;
    }

    public void setCod_paciente_id(Long cod_paciente_id) {
        this.cod_paciente_id = cod_paciente_id;
    }

    public Date getDat_consulta() {
        return dat_consulta;
    }

    public void setDat_consulta(Date dat_consulta) {
        this.dat_consulta = dat_consulta;
    }

    public String getHor_consulta() {
        return hor_consulta;
    }

    public void setHor_consulta(String hor_consulta) {
        this.hor_consulta = hor_consulta;
    }

    public Long getCod_enf_id() {
        return cod_enf_id;
    }

    public void setCod_enf_id(Long cod_enf_id) {
        this.cod_enf_id = cod_enf_id;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 13 * hash + Objects.hashCode(this.cod_consulta);
        hash = 13 * hash + Objects.hashCode(this.cod_paciente_id);
        hash = 13 * hash + Objects.hashCode(this.dat_consulta);
        hash = 13 * hash + Objects.hashCode(this.hor_consulta);
        hash = 13 * hash + Objects.hashCode(this.cod_enf_id);
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
        final Consulta other = (Consulta) obj;
        if (!Objects.equals(this.hor_consulta, other.hor_consulta)) {
            return false;
        }
        if (!Objects.equals(this.cod_consulta, other.cod_consulta)) {
            return false;
        }
        if (!Objects.equals(this.cod_paciente_id, other.cod_paciente_id)) {
            return false;
        }
        if (!Objects.equals(this.dat_consulta, other.dat_consulta)) {
            return false;
        }
        if (!Objects.equals(this.cod_enf_id, other.cod_enf_id)) {
            return false;
        }
        return true;
    }

}
