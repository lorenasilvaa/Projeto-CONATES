package conates.model.dao;

import conates.model.domain.SintomasQueixa;
import conates.util.db.exception.NegocioException;
import conates.util.db.exception.PersistenciaException;
import java.util.List;

public interface ISintomasQueixaDAO {
    
    Long adicionar(SintomasQueixa sintomasqueixa) throws PersistenciaException, NegocioException;

    boolean editar(SintomasQueixa sintomasqueixa) throws PersistenciaException, NegocioException;

    SintomasQueixa consultarPorCodigo(Long cod) throws PersistenciaException;

    List<SintomasQueixa> exibirTodos() throws PersistenciaException;
}
