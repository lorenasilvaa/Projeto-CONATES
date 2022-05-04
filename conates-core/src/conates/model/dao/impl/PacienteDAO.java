package conates.model.dao.impl;

import conates.model.dao.IPacienteDAO;
import conates.model.domain.Paciente;
import conates.util.db.JDBCConnectionManager;
import conates.util.db.exception.PersistenciaException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PacienteDAO implements IPacienteDAO {

    @Override
    public Paciente consultarPorCodigo(Long cod) throws PersistenciaException {
        try {
            Connection connection = JDBCConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM paciente WHERE cod_paciente = ?";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, cod);
            ResultSet rs = pstmt.executeQuery();

            Paciente paciente = null;
            if (rs.next()) {
                paciente = new Paciente();
                paciente.setCod_paciente(rs.getLong("cod_paciente"));
                paciente.setNom_paciente(rs.getString("nom_paciente"));
                paciente.setIdt_servidor_aluno(rs.getString("idt_servidor_aluno"));
            }

            rs.close();
            pstmt.close();
            connection.close();

            return paciente;
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e.getMessage(), e);
        }
    }
}
