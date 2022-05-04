package conates.model.service.impl;


import conates.model.dao.IFornecedorMedicamentoDAO;
import conates.model.dao.impl.FornecedorMedicamentoDAO;
import conates.model.domain.FornecedorMedicamento;
import conates.model.service.IManterFornecedorMedicamento;
import conates.util.db.exception.NegocioException;
import conates.util.db.exception.PersistenciaException;
import java.util.List;

public class ManterFornecedorMedicamento implements IManterFornecedorMedicamento {

    @Override
    public Long cadastrar(FornecedorMedicamento fornecedormedicamento) throws PersistenciaException, NegocioException {
        IFornecedorMedicamentoDAO fornecedormedicamentoDAO = new FornecedorMedicamentoDAO();

        Long result = fornecedormedicamentoDAO.adicionar(fornecedormedicamento);
        fornecedormedicamento.setCod_fornecimento(result);
        return result;
    }

    @Override
    public boolean alterar(FornecedorMedicamento fornecedormedicamento) throws PersistenciaException, NegocioException {
        IFornecedorMedicamentoDAO fornecedormedicamentoDAO = new FornecedorMedicamentoDAO();

        boolean result = fornecedormedicamentoDAO.editar(fornecedormedicamento);
        return result;
    }

    @Override
    public List<FornecedorMedicamento> listarTodos() throws PersistenciaException {
        IFornecedorMedicamentoDAO fornecedormedicamentoDAO = new FornecedorMedicamentoDAO();
        List<FornecedorMedicamento> listFornecedorMedicamento = fornecedormedicamentoDAO.exibirTodos();
        return listFornecedorMedicamento;
    }

    @Override
    public FornecedorMedicamento pesquisarPorCod(Long cod) throws PersistenciaException {
        IFornecedorMedicamentoDAO fornecedormedicamentoDAO = new FornecedorMedicamentoDAO();
        FornecedorMedicamento result = fornecedormedicamentoDAO.consultarPorCodigo(cod);
        return result;
    }
}