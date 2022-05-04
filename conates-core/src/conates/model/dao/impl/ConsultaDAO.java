package conates.model.dao.impl;

import conates.model.dao.IConsultaDAO;
import conates.model.domain.Consulta;
import conates.util.db.JDBCConnectionManager;
import conates.util.db.exception.PersistenciaException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ConsultaDAO implements IConsultaDAO {

    @Override
    public Long adicionar(Consulta consulta) throws PersistenciaException {

        Long cod_consulta = null;

        try {
            Connection connection = JDBCConnectionManager.getInstance().getConnection();

            String sql = "INSERT INTO consulta (cod_paciente_id, dat_consulta, hr_consulta, cod_enf_id) " + "VALUES(?, ?, ?, ?) RETURNING cod_consulta";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setLong(1, consulta.getCod_paciente_id());
            statement.setDate(2, new java.sql.Date(consulta.getDat_consulta().getTime()));
            statement.setString(3, consulta.getHor_consulta());
            statement.setLong(4, consulta.getCod_enf_id());

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                cod_consulta = new Long(resultSet.getLong("cod_consulta"));
                consulta.setCod_consulta(cod_consulta);
            }
            connection.close();

            return cod_consulta;
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e.getMessage(), e);
        }
    }

    @Override
    public boolean editar(Consulta consulta) throws PersistenciaException {
        try {
            Connection connection = JDBCConnectionManager.getInstance().getConnection();

            String sql = "UPDATE consulta "
                    + " SET cod_paciente_id = ?,"
                    + "     dat_consulta = ?,"
                    + "     hr_consulta = ?,"
                    + "     cod_enf_id = ?"
                    + " WHERE cod_consulta = ?";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setLong(1, consulta.getCod_paciente_id());
            statement.setDate(2, new java.sql.Date(consulta.getDat_consulta().getTime()));
            statement.setString(3, consulta.getHor_consulta());
            statement.setLong(4, consulta.getCod_enf_id());
            statement.setLong(5, consulta.getCod_consulta());

            statement.execute();

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e.getMessage(), e);
        }
        return true;
    }

    @Override
    public Consulta consultarPorCodigo(Long cod) throws PersistenciaException {
        try {
            Connection connection = JDBCConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM consulta WHERE cod_consulta = ?";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, cod);
            ResultSet rs = pstmt.executeQuery();

            Consulta consulta = null;
            if (rs.next()) {
                consulta = new Consulta();
                consulta.setCod_consulta(rs.getLong("cod_consulta"));
                consulta.setCod_paciente_id(rs.getLong("cod_paciente_id"));
                consulta.setDat_consulta(rs.getDate("dat_consulta"));
                consulta.setHor_consulta(rs.getString("hr_consulta"));
                consulta.setCod_enf_id(rs.getLong("cod_enf_id"));
            }

            rs.close();
            pstmt.close();
            connection.close();

            return consulta;
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e.getMessage(), e);
        }
    }

    @Override
    public List<Consulta> exibirTodos() throws PersistenciaException {

        List<Consulta> consultaList = new ArrayList<Consulta>();

        try {
            Connection connection = JDBCConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM consulta";

            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Consulta consulta = new Consulta();
                consulta.setCod_consulta(resultSet.getLong("cod_consulta"));
                consulta.setCod_paciente_id(resultSet.getLong("cod_paciente_id"));
                consulta.setDat_consulta(resultSet.getDate("dat_consulta"));
                consulta.setHor_consulta(resultSet.getString("hr_consulta"));
                consulta.setCod_enf_id(resultSet.getLong("cod_enf_id"));

                consultaList.add(consulta);
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e.getMessage(), e);
        }
        return consultaList;
    }

}
