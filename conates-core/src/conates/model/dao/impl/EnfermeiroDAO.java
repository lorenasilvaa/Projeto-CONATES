package conates.model.dao.impl;

import conates.model.dao.IEnfermeiroDAO;
import conates.model.domain.Enfermeiro;
import conates.util.db.JDBCConnectionManager;
import conates.util.db.exception.PersistenciaException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EnfermeiroDAO implements IEnfermeiroDAO {

    @Override
    public Enfermeiro consultarPorNome(String nome) throws PersistenciaException {
        try {
            Connection connection = JDBCConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM enfermeiro WHERE nom_enf = ?";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, nome);
            ResultSet rs = pstmt.executeQuery();

            Enfermeiro enfermeiro = null;
            if (rs.next()) {
                enfermeiro = new Enfermeiro();
                enfermeiro.setCod_enf(rs.getLong("cod_enf"));
                enfermeiro.setNom_enf(rs.getString("nom_enf"));
            }

            rs.close();
            pstmt.close();
            connection.close();

            return enfermeiro;
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e.getMessage(), e);
        }
    }

    @Override
    public List<Enfermeiro> exibirTodos() throws PersistenciaException {

        List<Enfermeiro> enfermeiroList = new ArrayList<Enfermeiro>();

        try {
            Connection connection = JDBCConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM enfermeiro";

            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Enfermeiro enfermeiro = new Enfermeiro();
                enfermeiro.setCod_enf(rs.getLong("cod_enf"));
                enfermeiro.setNom_enf(rs.getString("nom_enf"));

                enfermeiroList.add(enfermeiro);
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e.getMessage(), e);
        }
        return enfermeiroList;
    }

    @Override
    public Enfermeiro consultarPorCodigo(Long cod) throws PersistenciaException {
        try {
            Connection connection = JDBCConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM enfermeiro WHERE cod_enf = ?";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, cod);
            ResultSet rs = pstmt.executeQuery();

            Enfermeiro enfermeiro = null;
            if (rs.next()) {
                enfermeiro = new Enfermeiro();
                enfermeiro.setCod_enf(rs.getLong("cod_enf"));
                enfermeiro.setNom_enf(rs.getString("nom_enf"));
            }

            rs.close();
            pstmt.close();
            connection.close();

            return enfermeiro;
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e.getMessage(), e);
        }
    }
}
