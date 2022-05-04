package conates.model.service;

import conates.model.domain.Lote;
import conates.util.db.exception.NegocioException;
import conates.util.db.exception.PersistenciaException;
import java.util.List;

public interface IManterLote {

    public Long cadastrar(Lote lote) throws PersistenciaException, NegocioException;

    public boolean alterar(Lote lote) throws PersistenciaException, NegocioException;

    Lote pesquisarPorCod(Long cod) throws PersistenciaException;

    public List<Lote> listarTodos() throws PersistenciaException;
}
