package conates.model.service.impl;

import conates.model.dao.IFornecedorDAO;
import conates.model.dao.impl.FornecedorDAO;
import conates.model.domain.Fornecedor;
import conates.model.service.IManterFornecedor;
import conates.util.db.exception.PersistenciaException;
import java.util.List;

public class ManterFornecedor implements IManterFornecedor {

    @Override
    public Fornecedor pesquisarPorCod(Long cod) throws PersistenciaException {
        IFornecedorDAO fornecedorDAO = new FornecedorDAO();
        Fornecedor result = fornecedorDAO.consultarPorCodigo(cod);

        return result;
    }

    @Override
    public Fornecedor pesquisarPorNome(String nome) throws PersistenciaException {
        IFornecedorDAO enfermeiroDAO = new FornecedorDAO();
        Fornecedor result = enfermeiroDAO.consultarPorNome(nome);
        return result;

    }

    @Override
    public List<Fornecedor> listarTodos() throws PersistenciaException {
        IFornecedorDAO fornecedorDAO = new FornecedorDAO();
        List<Fornecedor> listFornecedor = fornecedorDAO.exibirTodos();
        return listFornecedor;
    }

}
