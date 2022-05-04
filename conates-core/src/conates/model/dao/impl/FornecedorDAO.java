package conates.model.dao.impl;

import conates.model.dao.IFornecedorDAO;
import conates.model.domain.Fornecedor;
import conates.util.db.JDBCConnectionManager;
import conates.util.db.exception.PersistenciaException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FornecedorDAO implements IFornecedorDAO {

    @Override
    public Fornecedor consultarPorCodigo(Long cod) throws PersistenciaException {
       
       try {
            Connection connection = JDBCConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM fornecedor WHERE cnpj_empresa = ?";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, cod);
            ResultSet rs = pstmt.executeQuery();

            Fornecedor fornecedor = null;
            if (rs.next()) {
                fornecedor = new Fornecedor();
                fornecedor.setCnpj_empresa(rs.getLong("cnpj_empresa"));
                fornecedor.setNom_fantasia(rs.getString("nom_fantasia"));
                fornecedor.setNom_empresa(rs.getString("nom_empresa"));
                fornecedor.setTel_empresa(rs.getString("tel_empresa"));
                fornecedor.setEmail_empresa(rs.getString("email_empresa"));
            }

            rs.close();
            pstmt.close();
            connection.close();

            return fornecedor;
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e.getMessage(), e);
        }
    }
    @Override
    public List<Fornecedor> exibirTodos() throws PersistenciaException {
         List<Fornecedor> fornecedorList = new ArrayList<Fornecedor>();

        try {
            Connection connection = JDBCConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM fornecedor";

            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Fornecedor fornecedor = new Fornecedor();
                fornecedor = new Fornecedor();
                fornecedor.setCnpj_empresa(rs.getLong("cnpj_empresa"));
                fornecedor.setNom_fantasia(rs.getString("nom_fantasia"));
                fornecedor.setNom_empresa(rs.getString("nom_empresa"));
                fornecedor.setTel_empresa(rs.getString("tel_empresa"));
                fornecedor.setEmail_empresa(rs.getString("email_empresa"));
            


                fornecedorList.add(fornecedor);
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e.getMessage(), e);
        }
        return fornecedorList;
    }

    @Override
    public Fornecedor consultarPorNome(String nome) throws PersistenciaException {
    try {
            Connection connection = JDBCConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM fornecedor WHERE nom_empresa = ?";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, nome);
            ResultSet rs = pstmt.executeQuery();

            Fornecedor fornecedor = null;
            if (rs.next()) {
                fornecedor = new Fornecedor();
                fornecedor.setCnpj_empresa(rs.getLong("cnpj_empresa"));
                fornecedor.setNom_fantasia(rs.getString("nom_fantasia"));
                fornecedor.setNom_empresa(rs.getString("nom_empresa"));
                fornecedor.setTel_empresa(rs.getString("tel_empresa"));
                fornecedor.setEmail_empresa(rs.getString("email_empresa"));
            }

            rs.close();
            pstmt.close();
            connection.close();

            return fornecedor;
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e.getMessage(), e);
        }
    }
    }