/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conates.model.dao.impl;

import conates.model.dao.IMovimentacaoDAO;
import conates.model.domain.Movimentacao;
import conates.util.db.JDBCConnectionManager;
import conates.util.db.exception.PersistenciaException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class MovimentacaoDAO implements IMovimentacaoDAO {

    @Override
    public List<Movimentacao> exibirTodos() throws PersistenciaException {

        List<Movimentacao> movimentacaoList = new ArrayList<Movimentacao>();

        try {
            Connection connection = JDBCConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM tipoMovimentacao";

            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Movimentacao movimentacao = new Movimentacao();
                movimentacao.setCod_tipo(rs.getLong("cod_tipo"));
                movimentacao.setDes_tipo(rs.getString("des_tipo"));

                movimentacaoList.add(movimentacao);
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e.getMessage(), e);
        }
        return movimentacaoList;
    }

    @Override
    public Movimentacao consultarPorCodigo(Long cod) throws PersistenciaException {
        try {
            Connection connection = JDBCConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM tipoMovimentacao WHERE cod_tipo = ?";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, cod);
            ResultSet rs = pstmt.executeQuery();

            Movimentacao movimentacao = null;
            if (rs.next()) {
                movimentacao = new Movimentacao();
                movimentacao.setCod_tipo(rs.getLong("cod_tipo"));
                movimentacao.setDes_tipo(rs.getString("des_tipo"));

            }

            rs.close();
            pstmt.close();
            connection.close();

            return movimentacao;
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e.getMessage(), e);
        }
    }

    @Override
    public Movimentacao consultarPorNome(String nome) throws PersistenciaException {
        try {
            Connection connection = JDBCConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM tipoMovimentacao WHERE des_tipo = ?";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, nome);
            ResultSet rs = pstmt.executeQuery();

            Movimentacao movimentacao = null;
            if (rs.next()) {

                movimentacao = new Movimentacao();
                movimentacao.setCod_tipo(rs.getLong("cod_tipo"));
                movimentacao.setDes_tipo(rs.getString("des_tipo"));

            }

            rs.close();
            pstmt.close();
            connection.close();

            return movimentacao;
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e.getMessage(), e);
        }
    }

}
