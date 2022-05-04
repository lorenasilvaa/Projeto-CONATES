package conates.model.dao.impl;

import conates.model.dao.IConsultaDAO;
import conates.model.dao.IMedicamentoDAO;
import conates.model.dao.IPacienteDAO;
import conates.model.dao.IReceitaDAO;
import conates.model.domain.Consulta;
import conates.model.domain.Medicamento;
import conates.model.domain.Paciente;
import conates.model.domain.Receita;
import conates.util.db.JDBCConnectionManager;
import conates.util.db.exception.PersistenciaException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ReceitaDAO implements IReceitaDAO {

    @Override
    public Long adicionar(Receita receita) throws PersistenciaException {
        try {
            Connection connection = JDBCConnectionManager.getInstance().getConnection();

            String sql = "INSERT INTO receita (cod_cons_id, cod_pac_id, cod_medic_id, txt_dosagem, qtd_fornecida) VALUES(?,?,?,?,?) RETURNING cod_receita";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, receita.getConsulta().getCod_consulta());
            pstmt.setLong(2, receita.getPaciente().getCod_paciente());
            pstmt.setLong(3, receita.getMedicamento().getCod_medic());
            pstmt.setString(4, receita.getTxt_dosagem());
            pstmt.setDouble(5, receita.getQtd_fornecida());

            ResultSet rs = pstmt.executeQuery();

            Long cod_receita = null;
            if (rs.next()) {
                cod_receita = rs.getLong("cod_receita");
                receita.setCod_receita(cod_receita);
            }

            rs.close();
            pstmt.close();
            connection.close();

            return cod_receita;
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e.getMessage(), e);
        }
    }

    @Override
    public boolean editar(Receita receita) throws PersistenciaException {
        try {

            Connection connection = JDBCConnectionManager.getInstance().getConnection();

            String sql = "UPDATE receita "
                    + "   SET cod_cons_id = ?, "
                    + "       cod_pac_id = ?, "
                    + "       cod_medic_id = ?, "
                    + "       txt_dosagem = ?, "
                    + "       qtd_fornecida = ? "
                    + " WHERE cod_receita = ?;";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, receita.getConsulta().getCod_consulta());
            pstmt.setLong(2, receita.getPaciente().getCod_paciente());
            pstmt.setLong(3, receita.getMedicamento().getCod_medic());
            pstmt.setString(4, receita.getTxt_dosagem());
            pstmt.setDouble(5, receita.getQtd_fornecida());
            pstmt.setLong(6, receita.getCod_receita());
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
    public List<Receita> exibirTodos() throws PersistenciaException {

        try {
            Connection connection = JDBCConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM receita;";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            ArrayList<Receita> listAll = null;
            IConsultaDAO consultaDAO = new ConsultaDAO();
            IMedicamentoDAO medicamentoDAO = new MedicamentoDAO();
            IPacienteDAO pacienteDAO = new PacienteDAO();
            if (rs.next()) {
                listAll = new ArrayList<>();
                do {
                    Receita receita = new Receita();
                    receita.setCod_receita(rs.getLong("cod_receita"));

                    Consulta consulta = consultaDAO.consultarPorCodigo(rs.getLong("cod_cons_id"));
                    receita.setConsulta(consulta);

                    Paciente paciente = pacienteDAO.consultarPorCodigo(rs.getLong("cod_pac_id"));
                    receita.setPaciente(paciente);

                    Medicamento medicamento = medicamentoDAO.consultarPorCodigo(rs.getLong("cod_medic_id"));
                    receita.setMedicamento(medicamento);

                    receita.setTxt_dosagem(rs.getString("txt_dosagem"));
                    receita.setQtd_fornecida(rs.getDouble("qtd_fornecida"));

                    listAll.add(receita);
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
    public Receita consultarPorCodigo(Long cod) throws PersistenciaException {
        try {
            Connection connection = JDBCConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM receita WHERE cod_receita = ?";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, cod);
            ResultSet rs = pstmt.executeQuery();

            Receita receita = null;
            IConsultaDAO consultaDAO = new ConsultaDAO();
            IMedicamentoDAO medicamentoDAO = new MedicamentoDAO();
            IPacienteDAO pacienteDAO = new PacienteDAO();
            if (rs.next()) {
                receita = new Receita();
                receita.setCod_receita(rs.getLong("cod_receita"));
                Consulta consulta = consultaDAO.consultarPorCodigo(rs.getLong("cod_cons_id"));
                receita.setConsulta(consulta);

                Paciente paciente = pacienteDAO.consultarPorCodigo(rs.getLong("cod_pac_id"));
                receita.setPaciente(paciente);

                Medicamento medicamento = medicamentoDAO.consultarPorCodigo(rs.getLong("cod_medic_id"));
                receita.setMedicamento(medicamento);

                receita.setTxt_dosagem(rs.getString("txt_dosagem"));
                receita.setQtd_fornecida(rs.getDouble("qtd_fornecida"));
            }

            rs.close();
            pstmt.close();
            connection.close();

            return receita;
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e.getMessage(), e);
        }
    }
}
