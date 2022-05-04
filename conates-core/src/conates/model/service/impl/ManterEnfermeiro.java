package conates.model.service.impl;

import conates.model.dao.IEnfermeiroDAO;
import conates.model.dao.impl.EnfermeiroDAO;
import conates.model.domain.Enfermeiro;
import conates.model.service.IManterEnfermeiro;
import conates.util.db.exception.PersistenciaException;
import java.util.List;

public class ManterEnfermeiro implements IManterEnfermeiro {

    @Override
    public Enfermeiro pesquisarPorNome(String nome) throws PersistenciaException {
        IEnfermeiroDAO enfermeiroDAO = new EnfermeiroDAO();
        Enfermeiro result = enfermeiroDAO.consultarPorNome(nome);
        return result;
    }

    @Override
    public Enfermeiro pesquisarPorCod(Long cod) throws PersistenciaException {
        IEnfermeiroDAO enfermeiroDAO = new EnfermeiroDAO();
        Enfermeiro result = enfermeiroDAO.consultarPorCodigo(cod);

        return result;
    }

    @Override
    public List<Enfermeiro> listarTodos() throws PersistenciaException {
        IEnfermeiroDAO enfermeiroDAO = new EnfermeiroDAO();
        List<Enfermeiro> listEnfermeiro = enfermeiroDAO.exibirTodos();
        return listEnfermeiro;
    }
}
