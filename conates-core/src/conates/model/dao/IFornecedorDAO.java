package conates.model.dao;

import conates.model.domain.Fornecedor;
import conates.util.db.exception.PersistenciaException;
import java.util.List;

public interface IFornecedorDAO {
    Fornecedor consultarPorCodigo(Long cod) throws PersistenciaException;
    List<Fornecedor> exibirTodos() throws PersistenciaException;
    Fornecedor consultarPorNome(String nome) throws PersistenciaException;
}