package conates.model.service;

import conates.model.domain.SintomasQueixa;
import conates.util.db.exception.NegocioException;
import conates.util.db.exception.PersistenciaException;
import java.util.List;

public interface IManterSintomasQueixa {

    public Long cadastrar(SintomasQueixa sintomasqueixa) throws PersistenciaException, NegocioException;

    public boolean alterar(SintomasQueixa sintomasqueixa) throws PersistenciaException, NegocioException;

    public List<SintomasQueixa> listarTodos() throws PersistenciaException;

    SintomasQueixa pesquisarPorCod(Long cod) throws PersistenciaException;
}
