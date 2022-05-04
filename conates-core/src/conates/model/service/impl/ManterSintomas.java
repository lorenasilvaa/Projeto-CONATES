package conates.model.service.impl;

import conates.model.dao.ISintomasDAO;
import conates.model.dao.impl.SintomasDAO;
import conates.model.domain.Sintomas;
import conates.model.service.IManterSintomas;
import conates.util.db.exception.PersistenciaException;
import java.util.List;

public class ManterSintomas implements IManterSintomas {

    @Override
    public List<Sintomas> listarTodos() throws PersistenciaException {
        ISintomasDAO sintomas = new SintomasDAO();
        List<Sintomas> listSintomas = sintomas.exibirTodos();
        return listSintomas;
    }
    
    @Override
    public Sintomas pesquisarPorCod(Long cod) throws PersistenciaException {
        ISintomasDAO sintomasDAO = new SintomasDAO();
        Sintomas result = sintomasDAO.consultarPorCodigo(cod);

        return result;
    }
    
    @Override
    public Sintomas pesquisarPorNome(String nome) throws PersistenciaException {
        ISintomasDAO sintomasDAO = new SintomasDAO();
        Sintomas result = sintomasDAO.consultarPorNome(nome);
        return result;        
    }

    
}
