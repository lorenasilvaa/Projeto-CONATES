package conates.model.dao.impl;

import conates.model.dao.IFornecedorDAO;
import conates.model.dao.IFornecedorMedicamentoDAO;
import conates.model.dao.IMedicamentoDAO;
import conates.model.domain.Fornecedor;
import conates.model.domain.FornecedorMedicamento;
import conates.model.domain.Medicamento;
import conates.util.db.JDBCConnectionManager;
import conates.util.db.exception.PersistenciaException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FornecedorMedicamentoDAO implements IFornecedorMedicamentoDAO {

    @Override
    public Long adicionar(FornecedorMedicamento fornecedormedicamento) throws PersistenciaException {
        try {
            Connection connection = JDBCConnectionManager.getInstance().getConnection();

            String sql = "INSERT INTO fornecedorMedicamento(cod_medic_id, cnpj_empresa_id, qtd_medicamento, txt_validacao, est_movimentacao) VALUES(?,?,?,?,?) RETURNING cod_fornecimento";

            PreparedStatement pstmt = connection.prepareStatement(sql);
           // pstmt.setLong(1, fornecedormedicamento.getCod_fornecimento());
            pstmt.setLong(1, fornecedormedicamento.getCod_medic_id());
            pstmt.setLong(2, fornecedormedicamento.getCnpj_empresa_id());
            pstmt.setInt(3, fornecedormedicamento.getQtd_medicamento());
            pstmt.setString(4, fornecedormedicamento.getTxt_validacao());
            pstmt.setString(5, fornecedormedicamento.getEst_movimentacao());

            ResultSet rs = pstmt.executeQuery();

            Long cod_fornecimento = null;
            if (rs.next()) {
                cod_fornecimento = rs.getLong("cod_fornecimento");
                fornecedormedicamento.setCod_fornecimento(cod_fornecimento);
            }

            rs.close();
            pstmt.close();
            connection.close();

            return cod_fornecimento;
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e.getMessage(), e);
        }
    }

    @Override
    public boolean editar(FornecedorMedicamento fornecedormedicamento) throws PersistenciaException {
        try {

            Connection connection = JDBCConnectionManager.getInstance().getConnection();

            String sql = "UPDATE fornecedorMedicamento "
                    + "   SET cod_medic_id = ?, "
                    + "       cnpj_empresa_id = ? ,"
                    + "       qtd_medicamento = ?, "
                    + "       txt_validacao = ? ,"
                    + "       est_movimentacao = ? "
                    + " WHERE cod_fornecimento = ?;";

            PreparedStatement pstmt = connection.prepareStatement(sql);

            //pstmt.setLong(1, fornecedormedicamento.getCod_fornecimento());
            pstmt.setLong(1, fornecedormedicamento.getCod_medic_id());
            pstmt.setLong(2, fornecedormedicamento.getCnpj_empresa_id());
            pstmt.setInt(3, fornecedormedicamento.getQtd_medicamento());
            pstmt.setString(4, fornecedormedicamento.getTxt_validacao());
            pstmt.setString(5, fornecedormedicamento.getEst_movimentacao());
            pstmt.setLong(6, fornecedormedicamento.getCod_fornecimento());
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
    public List<FornecedorMedicamento> exibirTodos() throws PersistenciaException {

        try {
            Connection connection = JDBCConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM fornecedorMedicamento;";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            ArrayList<FornecedorMedicamento> listAll = null;
            IFornecedorDAO fornecedorDAO = new FornecedorDAO();
            IMedicamentoDAO medicamentoDAO = new MedicamentoDAO();

            if (rs.next()) {
                listAll = new ArrayList<>();
                do {
                    FornecedorMedicamento fornecedorMedicamento = new FornecedorMedicamento();

                    fornecedorMedicamento.setCod_fornecimento(rs.getLong("cod_fornecimento"));
                    fornecedorMedicamento.setCod_medic_id(rs.getLong("cod_medic_id"));
                    fornecedorMedicamento.setCnpj_empresa_id(rs.getLong("cnpj_empresa_id"));
                    fornecedorMedicamento.setQtd_medicamento(rs.getInt("qtd_medicamento"));
                    fornecedorMedicamento.setTxt_validacao(rs.getString("txt_validacao"));
                    fornecedorMedicamento.setEst_movimentacao(rs.getString("est_movimentacao"));

                    listAll.add(fornecedorMedicamento);
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
    public FornecedorMedicamento consultarPorCodigo(Long cod) throws PersistenciaException {
        try {
            Connection connection = JDBCConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM fornecedorMedicamento WHERE cod_fornecimento = ?";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, cod);
            ResultSet rs = pstmt.executeQuery();

            FornecedorMedicamento fornecedorMedicamento = null;
            IFornecedorDAO fornecedorDAO = new FornecedorDAO();
            IMedicamentoDAO medicamentoDAO = new MedicamentoDAO();

            if (rs.next()) {
                fornecedorMedicamento = new FornecedorMedicamento();
                fornecedorMedicamento.setCod_fornecimento(rs.getLong("cod_fornecimento"));
                fornecedorMedicamento.setCod_medic_id(rs.getLong("cod_medic_id"));
                fornecedorMedicamento.setCnpj_empresa_id(rs.getLong("cnpj_empresa_id"));

                fornecedorMedicamento.setQtd_medicamento(rs.getInt("qtd_medicamento"));
                fornecedorMedicamento.setTxt_validacao(rs.getString("txt_validacao"));
                fornecedorMedicamento.setEst_movimentacao(rs.getString("est_movimentacao"));
            }

            rs.close();
            pstmt.close();
            connection.close();

            return fornecedorMedicamento;
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e.getMessage(), e);
        }
    }
}
