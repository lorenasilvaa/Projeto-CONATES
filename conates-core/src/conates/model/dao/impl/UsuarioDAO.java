package conates.model.dao.impl;

import conates.model.dao.IUsuarioDAO;
import conates.model.domain.Usuario;
import conates.util.db.JDBCConnectionManager;
import conates.util.db.exception.PersistenciaException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class UsuarioDAO implements IUsuarioDAO {
    @Override
    public Usuario consultarPorUsuarioSenha(String codUsuario,String tipoUsuario, String senha ) throws PersistenciaException{
        try {
           Connection connection = JDBCConnectionManager.getInstance().getConnection();

            String sql = "SELECT * FROM usuario WHERE cod_usuario = ? AND tipo_usuario = ? AND senha = md5(?) ";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, codUsuario);
            pstmt.setString(2, tipoUsuario);
            pstmt.setString(3, senha);
            ResultSet rs = pstmt.executeQuery();

            Usuario usuario = null;
            if (rs.next()) {
                usuario = new Usuario();
                usuario.setCod_usuario(rs.getString("cod_usuario"));
                usuario.setTipo_usuario(rs.getString("tipo_usuario"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setNome(rs.getString("nome"));
            }

            rs.close();
            pstmt.close();
            connection.close();

            return usuario;
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaException(e.getMessage(), e);
        }
    }
}
