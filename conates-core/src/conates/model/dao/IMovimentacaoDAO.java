package conates.model.dao;

import conates.model.domain.Movimentacao;
import conates.util.db.exception.PersistenciaException;
import java.util.List;

public interface IMovimentacaoDAO {

    List<Movimentacao> exibirTodos() throws PersistenciaException;

    Movimentacao consultarPorCodigo(Long cod) throws PersistenciaException;

    Movimentacao consultarPorNome(String nome) throws PersistenciaException;
}
