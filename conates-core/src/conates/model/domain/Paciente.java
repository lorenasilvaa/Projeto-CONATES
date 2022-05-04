package conates.model.domain;

import java.util.Objects;

public class Paciente {
    
    private Long cod_paciente;
    private String nom_paciente;
    private String idt_servidor_aluno;

    public Long getCod_paciente() {
        return cod_paciente;
    }

    public void setCod_paciente(Long cod_paciente) {
        this.cod_paciente = cod_paciente;
    }

    public String getNom_paciente() {
        return nom_paciente;
    }

    public void setNom_paciente(String nom_paciente) {
        this.nom_paciente = nom_paciente;
    }

    public String getIdt_servidor_aluno() {
        return idt_servidor_aluno;
    }

    public void setIdt_servidor_aluno(String idt_servidor_aluno) {
        this.idt_servidor_aluno = idt_servidor_aluno;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.cod_paciente);
        hash = 97 * hash + Objects.hashCode(this.nom_paciente);
        hash = 97 * hash + Objects.hashCode(this.idt_servidor_aluno);
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
        final Paciente other = (Paciente) obj;
        if (!Objects.equals(this.nom_paciente, other.nom_paciente)) {
            return false;
        }
        if (!Objects.equals(this.idt_servidor_aluno, other.idt_servidor_aluno)) {
            return false;
        }
        if (!Objects.equals(this.cod_paciente, other.cod_paciente)) {
            return false;
        }
        return true;
    }
}
