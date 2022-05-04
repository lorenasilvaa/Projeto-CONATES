package conates.model.service;

import conates.model.domain.Estoque;
import conates.util.db.exception.NegocioException;
import conates.util.db.exception.PersistenciaException;
import java.util.Date;
import java.util.List;

public interface IManterEstoque {

    public Long cadastrar(Estoque estoque) throws PersistenciaException, NegocioException;

    public boolean alterar(Estoque estoque) throws PersistenciaException, NegocioException;

    public List<Estoque> listarTodos() throws PersistenciaException;

    Estoque pesquisarPorCod(Long cod) throws PersistenciaException;
    
    List<Estoque> pesquisarPorIntervaloData(Date dat1, Date dat2) throws PersistenciaException;

}
