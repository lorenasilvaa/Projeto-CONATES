package conates.model.dao.impl;

import conates.model.dao.ILoteDAO;
import conates.model.domain.Lote;
import conates.util.db.JDBCConnectionManager;
import conates.util.db.exception.PersistenciaException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class LoteDAO implements ILoteDAO {

    @Override
    public Long adicionar(Lote lote) throws PersistenciaException {

        Long cod_lote = null;

        try {
            Connection connection = JDBCConnectionManager.getInstance().getConnection();

            String sql = "INSERT INTO loteFornecimento(cod_lote, dat_recebimento, cnpj_empresa_id) " + "VALUES(?, ?, ?) RETURNING cod_lote";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setLong(1, lote.getCod_lote());
            statement.setDate(2, new java.sql.Date(lote.getDat_recebimento().getTime()));
            statement.setLong(3, lote.getCnpj_empresa_id());

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                cod_lote = new Long(resultSet.getLong("cod_lote"));
                lote.setCod_lote(cod_lote);
            }
            connection.close();

            return cod_lote;
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e.getMessage(), e);
        }
    }
    @Override
    public boolean editar(Lote lote) throws PersistenciaException {
        try {
            Connection connection = JDBCConnectionManager.getInstance().getConnection();

            String sql = "UPDATE loteFornecimento "
                    + " SET dat_recebimento = ?,"
                    + "     cnpj_empresa_id = ?"
                    + " WHERE cod_lote = ?";

            PreparedStatement statement = connection.prepareStatement(sql);

            
            statement.setDate(1, new java.sql.Date(lote.getDat_recebimento().getTime()));
            statement.setLong(2, lote.getCnpj_empresa_id());
            statement.setLong(3, lote.getCod_lote());

            statement.execute();

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e.getMessage(), e);
        }
        return true;
    }

    @Override
    public List<Lote> exibirTodos() throws PersistenciaException {

        List<Lote> loteList = new ArrayList<Lote>();

        try {
            Connection connection = JDBCConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM loteFornecimento";

            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Lote lote = new Lote();
                lote.setCod_lote(rs.getLong("cod_lote"));
                lote.setDat_recebimento(rs.getDate("dat_recebimento"));
                lote.setCnpj_empresa_id(rs.getLong("cnpj_empresa_id"));

                loteList.add(lote);
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e.getMessage(), e);
        }
        return loteList;
    }

    @Override
    public Lote consultarPorCodigo(Long cod) throws PersistenciaException {
        try {
            Connection connection = JDBCConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM loteFornecimento WHERE cod_lote = ?";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, cod);
            ResultSet rs = pstmt.executeQuery();

            Lote lote = null;
            if (rs.next()) {
                lote = new Lote();
                lote.setCod_lote(rs.getLong("cod_lote"));
                lote.setDat_recebimento(rs.getDate("dat_recebimento"));
                lote.setCod_lote(rs.getLong("cnpj_empresa_id"));
            }

            rs.close();
            pstmt.close();
            connection.close();

            return lote;
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e.getMessage(), e);
        }
    }
}
