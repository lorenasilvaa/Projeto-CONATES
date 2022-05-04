package conates.model.dao.impl;

import conates.model.dao.IMedicamentoDAO;
import conates.model.domain.Medicamento;
import conates.util.db.JDBCConnectionManager;
import conates.util.db.exception.PersistenciaException;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MedicamentoDAO implements IMedicamentoDAO {

    @Override
    public Long adicionar(Medicamento medicamento) throws PersistenciaException {

        Long cod_medic = null;

        try {
            Connection connection = JDBCConnectionManager.getInstance().getConnection();

            String sql = "INSERT INTO medicamento (cod_medic, nom_medic, cod_tipo_id,blb_foto) " + "VALUES(?, ?, ?,?) RETURNING cod_medic";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setLong(1, medicamento.getCod_medic());
            statement.setString(2, medicamento.getNom_medic());
            // statement.setB
            statement.setBytes(4, medicamento.getImagem());
            statement.setLong(3, medicamento.getCod_tipo_id());

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                cod_medic = new Long(resultSet.getLong("cod_medic"));
                medicamento.setCod_medic(cod_medic);
            }

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e.getMessage(), e);
        }

        return cod_medic;
    }

    @Override
    public boolean editar(Medicamento medicamento) throws PersistenciaException {

        try {
            Connection connection = JDBCConnectionManager.getInstance().getConnection();

            String sql = "UPDATE medicamento "
                    + " SET nom_medic = ?,"
                    + "     blb_foto = ?,"
                    + "     cod_tipo_id = ?"
                    + " WHERE cod_medic = ?";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, medicamento.getNom_medic());
            statement.setBytes(2, medicamento.getImagem());

            statement.setLong(3, medicamento.getCod_tipo_id());

            statement.setLong(4, medicamento.getCod_medic());
            statement.execute();

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e.getMessage(), e);
        }
        return true;
    }

    @Override
    public Medicamento consultarPorCodigo(Long cod) throws PersistenciaException {
        Medicamento medicamento = null;
        try {
            Connection connection = JDBCConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM medicamento WHERE cod_medic = ?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, cod);

            // * Conferir * \\
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                medicamento = new Medicamento();
                medicamento.setCod_medic(resultSet.getLong("cod_medic"));
                medicamento.setNom_medic(resultSet.getString("nom_medic"));
                medicamento.setImagem(resultSet.getBytes("blb_foto"));
                //         setBytesImg(resultSet.getObject("byteImg"). .toByteArray());

                medicamento.setCod_tipo_id(resultSet.getLong("cod_tipo_id"));

            }
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e.getMessage(), e);
        }
        return medicamento;
    }

    @Override
    public List<Medicamento> exibirTodos() throws PersistenciaException {

        List<Medicamento> medicamentoList = new ArrayList<Medicamento>();

        try {
            Connection connection = JDBCConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM medicamento";

            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Medicamento medicamento = new Medicamento();
                medicamento.setCod_medic(resultSet.getLong("cod_medic"));
                medicamento.setNom_medic(resultSet.getString("nom_medic"));

                InputStream input = resultSet.getBinaryStream("blb_foto");
                if (input != null) {
                    ByteArrayOutputStream output = new ByteArrayOutputStream();
                    // set read buffer size
                    byte[] rb = new byte[1024];
                    int ch = 0;
                    while ((ch = input.read(rb)) != -1) {
                        output.write(rb, 0, ch);
                    }
                    // transfer to byte buffer
                    byte[] b = output.toByteArray();
                    input.close();
                    output.close();
                    // onde o método setImagem espera um array de bytes
                    medicamento.setImagem(resultSet.getBytes("blb_foto"));
                }

                // medicamento.setValidade_medic(resultSet.getDate("validade_medic"));
                medicamento.setCod_tipo_id(resultSet.getLong("cod_tipo_id"));
                medicamentoList.add(medicamento);
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e.getMessage(), e);
        }
        return medicamentoList;
    }

    @Override
    public Medicamento consultarPorNome(String nome) throws PersistenciaException {
        try {
            Connection connection = JDBCConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM medicamento WHERE nom_medic = ?";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, nome);
            ResultSet rs = pstmt.executeQuery();

            Medicamento medicamento = null;
            if (rs.next()) {
                medicamento = new Medicamento();
                medicamento.setCod_medic(rs.getLong("cod_medic"));
                medicamento.setNom_medic(rs.getString("nom_medic"));
                medicamento.setCod_tipo_id(rs.getLong("cod_tipo_id"));
                medicamento.setImagem(rs.getBytes("blb_foto"));
            }

            rs.close();
            pstmt.close();
            connection.close();

            return medicamento;
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e.getMessage(), e);
        }
    }
}


/*package conates.model.dao.impl;

import conates.model.dao.IMedicamentoDAO;
import conates.model.domain.Medicamento;
import conates.util.db.JDBCConnectionManager;
import conates.util.db.exception.PersistenciaException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MedicamentoDAO implements IMedicamentoDAO {

    @Override
    public Long adicionar(Medicamento medicamento) throws PersistenciaException {

        Long cod_medic = null;

        try {
            Connection connection = JDBCConnectionManager.getInstance().getConnection();

            String sql = "INSERT INTO medicamento (cod_medic, nom_medic, cod_tipo_id) " + "VALUES(?, ?, ?) RETURNING cod_medic";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setLong(1, medicamento.getCod_medic());
            statement.setString(2, medicamento.getNom_medic());
            
            //statement.setBlob(3, medicamento.getBlb_foto());
            statement.setLong(3, medicamento.getCod_tipo_id());

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                cod_medic = new Long(resultSet.getLong("cod_medic"));
                medicamento.setCod_medic(cod_medic);
            }

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e.getMessage(), e);
        }

        return cod_medic;
    }

    @Override
    public boolean editar(Medicamento medicamento) throws PersistenciaException {

        try {
            Connection connection = JDBCConnectionManager.getInstance().getConnection();

            String sql = "UPDATE medicamento "
                    + " SET cod_medic = ?,"
                    + "     nom_medic = ?,"
                   // + "     blob_foto = ?,"
                    + "     cod_tipo_id = ?"
                    + " WHERE cod_medic = ?";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setLong(1, medicamento.getCod_medic());
            statement.setString(2, medicamento.getNom_medic());
            //statement.setBlob(3, medicamento.getBlb_foto());
            statement.setLong(3, medicamento.getCod_tipo_id());
            statement.execute();

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e.getMessage(), e);
        }
        return true;
    }

    @Override
    public Medicamento consultarPorCodigo(Long cod) throws PersistenciaException {
        Medicamento medicamento = null;
        try {
            Connection connection = JDBCConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM medicamento WHERE cod_medic = ?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, cod);

            // * Conferir * \\
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                medicamento = new Medicamento();
                medicamento.setCod_medic(resultSet.getLong("cod_medic"));
                medicamento.setNom_medic(resultSet.getString("nom_medic"));
                //medicamento.setBlb_foto(resultSet.getBlob("blb_foto"));
                medicamento.setCod_tipo_id(resultSet.getLong("cod_tipo_id"));

            }
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e.getMessage(), e);
        }
        return medicamento;
    }

    @Override
    public List<Medicamento> exibirTodos() throws PersistenciaException {

        List<Medicamento> medicamentoList = new ArrayList<Medicamento>();

        try {
            Connection connection = JDBCConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM medicamento";

            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Medicamento medicamento = new Medicamento();
                  medicamento.setCod_medic(resultSet.getLong("cod_medic"));
                medicamento.setNom_medic(resultSet.getString("nom_medic"));
//                medicamento.setBlb_foto(resultSet.getBlob("blb_foto"));
                medicamento.setCod_tipo_id(resultSet.getLong("cod_tipo_id"));
                medicamentoList.add(medicamento);
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e.getMessage(), e);
        }
        return medicamentoList;
    }

    @Override
    public Medicamento consultarPorNome(String nome) throws PersistenciaException {
        try {
            Connection connection = JDBCConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM medicamento WHERE nom_medic = ?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, nome);

            ResultSet resultSet = statement.executeQuery();

            Medicamento medicamento = null;
            if (resultSet.next()) {
                
                medicamento = new Medicamento();
                
                  medicamento.setCod_medic(resultSet.getLong("cod_medic"));
                medicamento.setNom_medic(resultSet.getString("nom_medic"));
              //  medicamento.setBlb_foto(resultSet.getBlob("blb_foto"));
                medicamento.setCod_tipo_id(resultSet.getLong("cod_tipo_id"));
            }

          //  resultSet.close();
           // statement.close();
            connection.close();

            return medicamento;
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e.getMessage(), e);
        }
    }
}
 */
 /*
package conates.model.dao.impl;

import conates.model.dao.IMedicamentoDAO;
import conates.model.domain.Medicamento;
import conates.util.db.JDBCConnectionManager;
import conates.util.db.exception.PersistenciaException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MedicamentoDAO implements IMedicamentoDAO {

    @Override
    public Long adicionar(Medicamento medicamento) throws PersistenciaException {

        Long cod_medic = null;

        try {
            Connection connection = JDBCConnectionManager.getInstance().getConnection();

            String sql = "INSERT INTO medicamento (cod_medic, nom_medic, qtd_disponivel, validade_medic, cod_tipo) " + "VALUES(?, ?, ?, ?, ?) RETURNING cod_medic";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setLong(1, medicamento.getCod_medic());
            statement.setString(2, medicamento.getNom_medic());
            statement.setInt(3, medicamento.getQtd_disponivel());
            statement.setDate(4, new java.sql.Date( medicamento.getValidade_medic().getTime()));
            statement.setLong(5, medicamento.getCod_tipo());

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                cod_medic = new Long(resultSet.getLong("cod_medic"));
                medicamento.setCod_medic(cod_medic);
            }

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e.getMessage(), e);
        }

        return cod_medic;
    }

    @Override
    public boolean editar(Medicamento medicamento) throws PersistenciaException {

        try {
            Connection connection = JDBCConnectionManager.getInstance().getConnection();

            String sql = "UPDATE medicamento "
                    + " SET cod_medic = ?,"
                    + "     nom_medic = ?,"
                    + "     qtd_disponivel = ?,"
                    + "     validade_medic = ?,"
                    + "     cod_tipo = ?"
                    + " WHERE cod_medic = ?";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setLong(1, medicamento.getCod_medic());
            statement.setString(2, medicamento.getNom_medic());
           // statement.setInt(3, medicamento.getQtd_disponivel());
            statement.setDate(4, new java.sql.Date( medicamento.getValidade_medic().getTime()));
            statement.setLong(5, medicamento.getCod_tipo());
            statement.execute();

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e.getMessage(), e);
        }
        return true;
    }

    @Override
    public Medicamento consultarPorCodigo(Long cod) throws PersistenciaException {
        Medicamento medicamento = null;
        try {
            Connection connection = JDBCConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM medicamento WHERE cod_medic = ?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, cod);

            // * Conferir * \\
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                medicamento = new Medicamento();
                medicamento.setCod_medic(resultSet.getLong("cod_medic"));
                medicamento.setNom_medic(resultSet.getString("nom_medic"));
                //medicamento.setQtd_disponivel(resultSet.getInt("qtd_disponivel"));
                //medicamento.setValidade_medic(resultSet.getDate("validade_medic"));
               // medicamento.setCod_tipo(resultSet.getLong("cod_tipo"));

            }
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e.getMessage(), e);
        }
        return medicamento;
    }

    @Override
    public List<Medicamento> exibirTodos() throws PersistenciaException {

        List<Medicamento> medicamentoList = new ArrayList<Medicamento>();

        try {
            Connection connection = JDBCConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM medicamento";

            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Medicamento medicamento = new Medicamento();
                medicamento.setCod_medic(resultSet.getLong("cod_medic"));
                medicamento.setNom_medic(resultSet.getString("nom_medic"));
                //medicamento.setQtd_disponivel(resultSet.getInt("qtd_disponivel"));
               // medicamento.setValidade_medic(resultSet.getDate("validade_medic"));
               // medicamento.setCod_tipo(resultSet.getLong("cod_tipo"));
                medicamentoList.add(medicamento);
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e.getMessage(), e);
        }
        return medicamentoList;
    }
    
    @Override
    public Medicamento consultarPorNome(String nome) throws PersistenciaException {
        try {
            Connection connection = JDBCConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM medicamento WHERE nom_medic = ?";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, nome);
            ResultSet rs = pstmt.executeQuery();

            Medicamento medicamento = null;
            if (rs.next()) {
                medicamento = new Medicamento();
                medicamento.setCod_medic(rs.getLong("cod_medic"));
                medicamento.setNom_medic(rs.getString("nom_medic"));
            }

            rs.close();
            pstmt.close();
            connection.close();

            return medicamento;
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e.getMessage(), e);
        }
    }
}


/*package conates.model.dao.impl;

import conates.model.dao.IMedicamentoDAO;
import conates.model.domain.Medicamento;
import conates.util.db.JDBCConnectionManager;
import conates.util.db.exception.PersistenciaException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MedicamentoDAO implements IMedicamentoDAO {

    @Override
    public Long adicionar(Medicamento medicamento) throws PersistenciaException {

        Long cod_medic = null;

        try {
            Connection connection = JDBCConnectionManager.getInstance().getConnection();

            String sql = "INSERT INTO medicamento (cod_medic, nom_medic, cod_tipo_id) " + "VALUES(?, ?, ?) RETURNING cod_medic";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setLong(1, medicamento.getCod_medic());
            statement.setString(2, medicamento.getNom_medic());
            
            //statement.setBlob(3, medicamento.getBlb_foto());
            statement.setLong(3, medicamento.getCod_tipo_id());

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                cod_medic = new Long(resultSet.getLong("cod_medic"));
                medicamento.setCod_medic(cod_medic);
            }

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e.getMessage(), e);
        }

        return cod_medic;
    }

    @Override
    public boolean editar(Medicamento medicamento) throws PersistenciaException {

        try {
            Connection connection = JDBCConnectionManager.getInstance().getConnection();

            String sql = "UPDATE medicamento "
                    + " SET cod_medic = ?,"
                    + "     nom_medic = ?,"
                   // + "     blob_foto = ?,"
                    + "     cod_tipo_id = ?"
                    + " WHERE cod_medic = ?";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setLong(1, medicamento.getCod_medic());
            statement.setString(2, medicamento.getNom_medic());
            //statement.setBlob(3, medicamento.getBlb_foto());
            statement.setLong(3, medicamento.getCod_tipo_id());
            statement.execute();

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e.getMessage(), e);
        }
        return true;
    }

    @Override
    public Medicamento consultarPorCodigo(Long cod) throws PersistenciaException {
        Medicamento medicamento = null;
        try {
            Connection connection = JDBCConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM medicamento WHERE cod_medic = ?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, cod);

            // * Conferir * \\
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                medicamento = new Medicamento();
                medicamento.setCod_medic(resultSet.getLong("cod_medic"));
                medicamento.setNom_medic(resultSet.getString("nom_medic"));
                //medicamento.setBlb_foto(resultSet.getBlob("blb_foto"));
                medicamento.setCod_tipo_id(resultSet.getLong("cod_tipo_id"));

            }
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e.getMessage(), e);
        }
        return medicamento;
    }

    @Override
    public List<Medicamento> exibirTodos() throws PersistenciaException {

        List<Medicamento> medicamentoList = new ArrayList<Medicamento>();

        try {
            Connection connection = JDBCConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM medicamento";

            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Medicamento medicamento = new Medicamento();
                  medicamento.setCod_medic(resultSet.getLong("cod_medic"));
                medicamento.setNom_medic(resultSet.getString("nom_medic"));
//                medicamento.setBlb_foto(resultSet.getBlob("blb_foto"));
                medicamento.setCod_tipo_id(resultSet.getLong("cod_tipo_id"));
                medicamentoList.add(medicamento);
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e.getMessage(), e);
        }
        return medicamentoList;
    }

    @Override
    public Medicamento consultarPorNome(String nome) throws PersistenciaException {
        try {
            Connection connection = JDBCConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM medicamento WHERE nom_medic = ?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, nome);

            ResultSet resultSet = statement.executeQuery();

            Medicamento medicamento = null;
            if (resultSet.next()) {
                
                medicamento = new Medicamento();
                
                  medicamento.setCod_medic(resultSet.getLong("cod_medic"));
                medicamento.setNom_medic(resultSet.getString("nom_medic"));
              //  medicamento.setBlb_foto(resultSet.getBlob("blb_foto"));
                medicamento.setCod_tipo_id(resultSet.getLong("cod_tipo_id"));
            }

          //  resultSet.close();
           // statement.close();
            connection.close();

            return medicamento;
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e.getMessage(), e);
        }
    }
}
 */
