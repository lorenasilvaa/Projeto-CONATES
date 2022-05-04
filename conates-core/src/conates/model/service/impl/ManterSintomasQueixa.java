package conates.model.service.impl;

import conates.model.dao.ISintomasQueixaDAO;
import conates.model.dao.impl.SintomasQueixaDAO;
import conates.model.domain.SintomasQueixa;
import conates.model.service.IManterSintomasQueixa;
import conates.util.db.exception.NegocioException;
import conates.util.db.exception.PersistenciaException;
import java.util.List;

public class ManterSintomasQueixa implements IManterSintomasQueixa {

    @Override
    public Long cadastrar(SintomasQueixa sintomasqueixa) throws PersistenciaException, NegocioException {
        ISintomasQueixaDAO sintomasqueixaDAO = new SintomasQueixaDAO();

        Long result = sintomasqueixaDAO.adicionar(sintomasqueixa);
        sintomasqueixa.setCod_sintoma_queixa(result);

        return result;
    }

    @Override
    public boolean alterar(SintomasQueixa sintomasqueixa) throws PersistenciaException, NegocioException {
        ISintomasQueixaDAO sintomasqueixaDAO = new SintomasQueixaDAO();

        // Exceções
        boolean result = sintomasqueixaDAO.editar(sintomasqueixa);
        return result;
    }

    @Override
    public SintomasQueixa pesquisarPorCod(Long cod) throws PersistenciaException {
        ISintomasQueixaDAO sintomasqueixaDAO = new SintomasQueixaDAO();
        SintomasQueixa result = sintomasqueixaDAO.consultarPorCodigo(cod);

        return result;
    }

    @Override
    public List<SintomasQueixa> listarTodos() throws PersistenciaException {
        ISintomasQueixaDAO sintomasqueixaDAO = new SintomasQueixaDAO();
        List<SintomasQueixa> listSintomasQueixa = sintomasqueixaDAO.exibirTodos();
        return listSintomasQueixa;
    }
}
