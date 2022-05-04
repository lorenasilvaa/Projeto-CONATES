package conates.model.dao.impl;

import conates.model.dao.ISintomasDAO;
import conates.model.domain.Sintomas;
import conates.util.db.JDBCConnectionManager;
import conates.util.db.exception.NegocioException;
import conates.util.db.exception.PersistenciaException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SintomasDAO implements ISintomasDAO {

    @Override
    public List<Sintomas> exibirTodos() throws PersistenciaException {

        List<Sintomas> sintomaList = new ArrayList<Sintomas>();

        try {
            Connection connection = JDBCConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM sintomas";

            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Sintomas sintoma = new Sintomas();
                sintoma.setCod_queixa(rs.getLong("cod_queixa"));
                sintoma.setDes_queixa(rs.getString("des_queixa"));

                sintomaList.add(sintoma);
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e.getMessage(), e);
        }
        return sintomaList;
    }

    @Override
    public Sintomas consultarPorCodigo(Long cod) throws PersistenciaException {
        try {
            Connection connection = JDBCConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM sintomas WHERE cod_queixa = ?";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, cod);
            ResultSet rs = pstmt.executeQuery();

            Sintomas sintomas = null;
            if (rs.next()) {
                sintomas = new Sintomas();
                sintomas.setCod_queixa(rs.getLong("cod_queixa"));
                sintomas.setDes_queixa(rs.getString("des_queixa"));
            }

            rs.close();
            pstmt.close();
            connection.close();

            return sintomas;
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e.getMessage(), e);
        }
    }

    @Override
    public Sintomas consultarPorNome(String nome) throws PersistenciaException {
        try {
            Connection connection = JDBCConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM sintomas WHERE des_queixa = ?";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, nome);
            ResultSet rs = pstmt.executeQuery();

            Sintomas sintoma = null;
            if (rs.next()) {
                sintoma = new Sintomas();
                sintoma.setCod_queixa(rs.getLong("cod_queixa"));
                sintoma.setDes_queixa(rs.getString("des_queixa"));
            }

            rs.close();
            pstmt.close();
            connection.close();

            return sintoma;
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e.getMessage(), e);
        }
    }

}
