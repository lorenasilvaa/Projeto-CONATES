package conates.model.service.impl;

import conates.model.dao.IUsuarioDAO;
import conates.model.dao.impl.UsuarioDAO;
import conates.model.domain.Usuario;
import conates.model.service.IManterUsuario;
import conates.util.db.exception.NegocioException;
import conates.util.db.exception.PersistenciaException;

public class ManterUsuario implements IManterUsuario {

    private IUsuarioDAO usuarioDAO;
    
    public ManterUsuario() {
        usuarioDAO = new UsuarioDAO();
    }
 

    @Override
    public Usuario getUserLogin(String codUsuario,String tipoUsuario, String senha ) throws PersistenciaException, NegocioException {
        Usuario result = usuarioDAO.consultarPorUsuarioSenha(codUsuario,tipoUsuario, senha );
        return result;               
    }
}
