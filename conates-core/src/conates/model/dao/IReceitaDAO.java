package conates.model.dao;

import conates.model.domain.Receita;
import conates.util.db.exception.NegocioException;
import conates.util.db.exception.PersistenciaException;
import java.util.List;

public interface IReceitaDAO {
    
    Long adicionar(Receita receita) throws PersistenciaException, NegocioException;

    boolean editar(Receita receita) throws PersistenciaException, NegocioException;

    Receita consultarPorCodigo(Long cod) throws PersistenciaException;

    List<Receita> exibirTodos() throws PersistenciaException;
    
}
