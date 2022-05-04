package conates.model.service;

import conates.model.domain.Usuario;
import conates.util.db.exception.NegocioException;
import conates.util.db.exception.PersistenciaException;

public interface IManterUsuario {

    public Usuario getUserLogin(String codUsuario,String tipoUsuario, String senha ) throws PersistenciaException, NegocioException;
}
