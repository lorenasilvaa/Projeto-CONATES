package conates.model.dao.impl;

import conates.model.dao.IConsultaDAO;
import conates.model.dao.IPacienteDAO;
import conates.model.dao.ISintomasDAO;
import conates.model.dao.ISintomasQueixaDAO;
import conates.model.domain.Consulta;
import conates.model.domain.Paciente;
import conates.model.domain.Sintomas;
import conates.model.domain.SintomasQueixa;
import conates.util.db.JDBCConnectionManager;
import conates.util.db.exception.PersistenciaException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SintomasQueixaDAO implements ISintomasQueixaDAO {

    @Override
    public Long adicionar(SintomasQueixa sintomasqueixa) throws PersistenciaException {
        try {
            Connection connection = JDBCConnectionManager.getInstance().getConnection();

            String sql = "INSERT INTO sintomasQueixa(cod_consulta_id, cod_paciente_id, cod_queixa_id, descricao) VALUES(?,?,?,?) RETURNING cod_sintoma_queixa";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, sintomasqueixa.getConsulta().getCod_consulta());
            pstmt.setLong(2, sintomasqueixa.getPessoa().getCod_paciente());
            pstmt.setLong(3, sintomasqueixa.getSintoma().getCod_queixa());
            pstmt.setString(4, sintomasqueixa.getTxt_observacao());

            ResultSet rs = pstmt.executeQuery();

            Long cod_sintoma_queixa = null;
            if (rs.next()) {
                cod_sintoma_queixa = rs.getLong("cod_sintoma_queixa");
                sintomasqueixa.setCod_sintoma_queixa(cod_sintoma_queixa);
            }

            rs.close();
            pstmt.close();
            connection.close();

            return cod_sintoma_queixa;
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e.getMessage(), e);
        }
    }

    @Override
    public boolean editar(SintomasQueixa sintomasqueixa) throws PersistenciaException {
        try {

            Connection connection = JDBCConnectionManager.getInstance().getConnection();

            String sql = "UPDATE sintomasQueixa"
                    + "   SET cod_consulta_id = ?, "
                    + "       cod_paciente_id = ?, "
                    + "       cod_queixa_id = ?, "
                    + "       descricao = ? "
                    + " WHERE cod_sintoma_queixa = ?;";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, sintomasqueixa.getConsulta().getCod_consulta());
            pstmt.setLong(2, sintomasqueixa.getPessoa().getCod_paciente());
            pstmt.setLong(3, sintomasqueixa.getSintoma().getCod_queixa());
            pstmt.setString(4, sintomasqueixa.getTxt_observacao());
            pstmt.setLong(5, sintomasqueixa.getCod_sintoma_queixa());
            pstmt.executeUpdate();

            pstmt.close();
            connection.close();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e.getMessage(), e);
        }
    }

    @Override
    public List<SintomasQueixa> exibirTodos() throws PersistenciaException {

        try {
            Connection connection = JDBCConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM sintomasQueixa;";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            ArrayList<SintomasQueixa> listAll = null;
            IConsultaDAO consultaDAO = new ConsultaDAO();
            ISintomasDAO sintomasDAO = new SintomasDAO();
            IPacienteDAO pacienteDAO = new PacienteDAO();
            if (rs.next()) {
                listAll = new ArrayList<>();
                do {
                    SintomasQueixa sintomasqueixa = new SintomasQueixa();
                    sintomasqueixa.setCod_sintoma_queixa(rs.getLong("cod_sintoma_queixa"));

                    Consulta consulta = consultaDAO.consultarPorCodigo(rs.getLong("cod_consulta_id"));
                    sintomasqueixa.setConsulta(consulta);

                    Paciente paciente = pacienteDAO.consultarPorCodigo(rs.getLong("cod_paciente_id"));
                    sintomasqueixa.setPessoa(paciente);

                    Sintomas sintomas = sintomasDAO.consultarPorCodigo(rs.getLong("cod_queixa_id"));
                    sintomasqueixa.setSintoma(sintomas);

                    sintomasqueixa.setTxt_observacao(rs.getString("descricao"));

                    listAll.add(sintomasqueixa);
                } while (rs.next());
            }

            rs.close();
            pstmt.close();
            connection.close();

            return listAll;
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e.getMessage(), e);
        }
    }

    @Override
    public SintomasQueixa consultarPorCodigo(Long cod) throws PersistenciaException {
        try {
            Connection connection = JDBCConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM sintomasQueixa WHERE cod_sintoma_queixa = ?";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, cod);
            ResultSet rs = pstmt.executeQuery();

            SintomasQueixa sintomasqueixa = null;
            IConsultaDAO consultaDAO = new ConsultaDAO();
            ISintomasDAO sintomasDAO = new SintomasDAO();
            IPacienteDAO pacienteDAO = new PacienteDAO();
            if (rs.next()) {
                sintomasqueixa = new SintomasQueixa();
                sintomasqueixa.setCod_sintoma_queixa(rs.getLong("cod_sintoma_queixa"));

                Consulta consulta = consultaDAO.consultarPorCodigo(rs.getLong("cod_consulta_id"));
                sintomasqueixa.setConsulta(consulta);

                Paciente paciente = pacienteDAO.consultarPorCodigo(rs.getLong("cod_paciente_id"));
                sintomasqueixa.setPessoa(paciente);

                Sintomas sintomas = sintomasDAO.consultarPorCodigo(rs.getLong("cod_queixa_id"));
                sintomasqueixa.setSintoma(sintomas);

                sintomasqueixa.setTxt_observacao(rs.getString("descricao"));

            }

            rs.close();
            pstmt.close();
            connection.close();

            return sintomasqueixa;
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e.getMessage(), e);
        }
    }
}
