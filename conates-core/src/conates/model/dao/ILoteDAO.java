package conates.model.dao;

import conates.model.domain.Lote;
import conates.util.db.exception.NegocioException;
import conates.util.db.exception.PersistenciaException;
import java.util.List;

/**
 *
 * @author BRUNA
 */
public interface ILoteDAO {

    Long adicionar(Lote lote) throws PersistenciaException, NegocioException;

    boolean editar(Lote lote) throws PersistenciaException, NegocioException;

    Lote consultarPorCodigo(Long cod) throws PersistenciaException;

    List<Lote> exibirTodos() throws PersistenciaException;
}
