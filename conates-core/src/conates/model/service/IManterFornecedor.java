package conates.model.service;

import conates.model.domain.Fornecedor;
import conates.util.db.exception.PersistenciaException;
import java.util.List;

public interface IManterFornecedor {

    Fornecedor pesquisarPorCod(Long cod) throws PersistenciaException;

    public Fornecedor pesquisarPorNome(String nome) throws PersistenciaException;

    public List<Fornecedor> listarTodos() throws PersistenciaException;

}
