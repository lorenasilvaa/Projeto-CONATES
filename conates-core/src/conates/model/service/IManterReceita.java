package conates.model.service;

import conates.model.domain.Receita;
import conates.util.db.exception.NegocioException;
import conates.util.db.exception.PersistenciaException;
import java.util.List;

public interface IManterReceita {

    Receita pesquisarPorCod(Long cod) throws PersistenciaException;

    public Long cadastrar(Receita receita) throws PersistenciaException, NegocioException;

    public boolean alterar(Receita receita) throws PersistenciaException, NegocioException;

    public List<Receita> listarTodos() throws PersistenciaException;

}
