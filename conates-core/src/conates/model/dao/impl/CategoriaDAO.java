package conates.model.dao.impl;

import conates.model.dao.ICategoriaDAO;
import conates.model.domain.Categoria;
import conates.util.db.JDBCConnectionManager;
import conates.util.db.exception.PersistenciaException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO implements ICategoriaDAO {

    @Override
    public List<Categoria> exibirTodos() throws PersistenciaException {

        List<Categoria> categoriaList = new ArrayList<Categoria>();

        try {
            Connection connection = JDBCConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM categoriaMedicamento";

            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Categoria categoria = new Categoria();
                categoria.setCod_tipo(rs.getLong("cod_tipo"));
                categoria.setNom_tipo(rs.getString("nom_tipo"));

                categoriaList.add(categoria);
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e.getMessage(), e);
        }
        return categoriaList;
    }

    @Override
    public Categoria consultarPorCodigo(Long cod) throws PersistenciaException {
        try {
            Connection connection = JDBCConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM categoriaMedicamento WHERE cod_tipo = ?";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, cod);
            ResultSet rs = pstmt.executeQuery();

            Categoria categoria = null;
            if (rs.next()) {
                categoria = new Categoria();
                categoria.setCod_tipo(rs.getLong("cod_tipo"));
                categoria.setNom_tipo(rs.getString("nom_tipo"));
            }

            rs.close();
            pstmt.close();
            connection.close();

            return categoria;
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e.getMessage(), e);
        }
    }

    @Override
    public Categoria consultarPorNome(String nome) throws PersistenciaException {
        try {
            Connection connection = JDBCConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM categoriaMedicamento WHERE nom_tipo = ?";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, nome);
            ResultSet rs = pstmt.executeQuery();

            Categoria categoria = null;
            if (rs.next()) {
                categoria = new Categoria();
                categoria.setCod_tipo(rs.getLong("cod_tipo"));
                categoria.setNom_tipo(rs.getString("nom_tipo"));
            }

            rs.close();
            pstmt.close();
            connection.close();

            return categoria;
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e.getMessage(), e);
        }
    }

}

