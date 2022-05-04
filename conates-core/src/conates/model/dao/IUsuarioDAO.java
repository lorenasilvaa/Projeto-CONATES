package conates.model.dao;

import conates.model.domain.Usuario;
import conates.util.db.exception.PersistenciaException;

public interface IUsuarioDAO {
    
    Usuario consultarPorUsuarioSenha(String codUsuario,String tipoUsuario, String senha ) throws PersistenciaException;
}
