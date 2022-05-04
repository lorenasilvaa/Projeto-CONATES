package conates.model.service;

import conates.model.domain.Movimentacao;
import conates.util.db.exception.PersistenciaException;
import java.util.List;

public interface IManterMovimentacao {

    public List<Movimentacao> listarTodos() throws PersistenciaException;

    Movimentacao pesquisarPorCod(Long cod) throws PersistenciaException;

    public Movimentacao pesquisarPorNome(String nome) throws PersistenciaException;
}
