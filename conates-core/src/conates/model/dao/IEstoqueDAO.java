package conates.model.dao;

import conates.model.domain.Estoque;
import conates.util.db.exception.NegocioException;
import conates.util.db.exception.PersistenciaException;
import java.util.Date;
import java.util.List;

public interface IEstoqueDAO {

    Long adicionar(Estoque estoque) throws PersistenciaException, NegocioException;

    boolean editar(Estoque estoque) throws PersistenciaException;

    Estoque consultarPorCodigo(Long cod) throws PersistenciaException;

    List<Estoque> exibirTodos() throws PersistenciaException;
    
    List<Estoque> consultarPorIntervaloData(Date dat1, Date dat2) throws PersistenciaException;

}
