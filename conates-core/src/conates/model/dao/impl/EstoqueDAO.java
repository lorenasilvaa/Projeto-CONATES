package conates.model.dao.impl;

import conates.model.dao.IEstoqueDAO;
import conates.model.domain.Estoque;
import conates.util.db.JDBCConnectionManager;
import conates.util.db.exception.PersistenciaException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EstoqueDAO implements IEstoqueDAO {

    @Override
    public Long adicionar(Estoque estoque) throws PersistenciaException {

        Long cod_estoque = null;

        try {
            Connection connection = JDBCConnectionManager.getInstance().getConnection();

            String sql = "INSERT INTO estoque (cod_lote_id,cod_medic_id, cod_tipo_id,dat_validade,dat_movimentacao,qtd_movimentada) " + "VALUES(?,?, ?, ?, ?, ?) RETURNING cod_estoque";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setLong(1, estoque.getCod_lote_id());
            statement.setLong(2, estoque.getCod_medic_id());
            statement.setLong(3, estoque.getCod_tipo_id());
            statement.setDate(4, new java.sql.Date(estoque.getDat_validade().getTime()));
            statement.setDate(5, new java.sql.Date(estoque.getDat_movimentacao().getTime()));
            statement.setDouble(6, estoque.getQtd_movimentada());

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                cod_estoque = new Long(resultSet.getLong("cod_estoque"));
                estoque.setCod_estoque(cod_estoque);
            }
            connection.close();

            return cod_estoque;
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e.getMessage(), e);
        }
    }

    @Override
    public boolean editar(Estoque estoque) throws PersistenciaException {

        try {
            Connection connection = JDBCConnectionManager.getInstance().getConnection();

            String sql = "UPDATE estoque "
                    + " SET cod_lote_id = ?,"
                    + "     cod_medic_id = ?,"
                    + "     cod_tipo_id = ?,"
                    + "     dat_validade = ?,"
                    + "     dat_movimentacao = ?,"
                    + "     qtd_movimentada = ?"
                    + " WHERE cod_estoque = ?";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setLong(1, estoque.getCod_lote_id());
            statement.setLong(2, estoque.getCod_medic_id());
            statement.setLong(3, estoque.getCod_tipo_id());
            statement.setDate(4, new java.sql.Date(estoque.getDat_validade().getTime()));
            statement.setDate(5, new java.sql.Date(estoque.getDat_movimentacao().getTime()));
            statement.setDouble(6, estoque.getQtd_movimentada());
            statement.execute();

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e.getMessage(), e);
        }
        return true;
    }

    @Override
    public Estoque consultarPorCodigo(Long cod) throws PersistenciaException {
        Estoque estoque = null;
        try {
            Connection connection = JDBCConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM estoque WHERE cod_estoque = ?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, cod);

            // * Conferir * \\
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                estoque = new Estoque();
                estoque.setCod_estoque(resultSet.getLong("cod_estoque"));
                estoque.setCod_lote_id(resultSet.getLong("cod_lote_id"));
                estoque.setCod_medic_id(resultSet.getLong("cod_medic_id"));
                estoque.setCod_tipo_id(resultSet.getLong("cod_tipo_id"));
                estoque.setDat_validade(resultSet.getDate("dat_validade"));
                estoque.setDat_movimentacao(resultSet.getDate("dat_movimentacao"));
                estoque.setQtd_movimentada(resultSet.getDouble("qtd_movimentada"));

            }

            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e.getMessage(), e);
        }
        return estoque;
    }

    @Override
    public List<Estoque> exibirTodos() throws PersistenciaException {

        List<Estoque> estoqueList = new ArrayList<Estoque>();

        try {
            Connection connection = JDBCConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM estoque";

            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Estoque estoque = new Estoque();
                estoque.setCod_estoque(resultSet.getLong("cod_estoque"));
                estoque.setCod_lote_id(resultSet.getLong("cod_lote_id"));
                estoque.setCod_medic_id(resultSet.getLong("cod_medic_id"));
                estoque.setCod_tipo_id(resultSet.getLong("cod_tipo_id"));
                estoque.setDat_validade(resultSet.getDate("dat_validade"));
                estoque.setDat_movimentacao(resultSet.getDate("dat_movimentacao"));
                estoque.setQtd_movimentada(resultSet.getDouble("qtd_movimentada"));
                estoqueList.add(estoque);
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e.getMessage(), e);
        }
        return estoqueList;
    }

    @Override
    public List<Estoque> consultarPorIntervaloData(java.util.Date dat1, java.util.Date dat2) throws PersistenciaException {
        Estoque estoque = null;
        List<Estoque> estoqueList = new ArrayList<Estoque>();
        try {
            Connection connection = JDBCConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM estoque WHERE dat_movimentacao BETWEEN ? AND ?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setDate(1, new java.sql.Date(dat1.getTime()));
            statement.setDate(2, new java.sql.Date(dat2.getTime()));

            // * Conferir * \\
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                estoque = new Estoque();
                estoque.setCod_estoque(resultSet.getLong("cod_estoque"));
                estoque.setCod_lote_id(resultSet.getLong("cod_lote_id"));
                estoque.setCod_medic_id(resultSet.getLong("cod_medic_id"));
                estoque.setCod_tipo_id(resultSet.getLong("cod_tipo_id"));
                estoque.setDat_validade(resultSet.getDate("dat_validade"));
                estoque.setDat_movimentacao(resultSet.getDate("dat_movimentacao"));
                estoque.setQtd_movimentada(resultSet.getDouble("qtd_movimentada"));
                estoqueList.add(estoque);

            }

            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e.getMessage(), e);
        }
        return estoqueList;
    }

}
