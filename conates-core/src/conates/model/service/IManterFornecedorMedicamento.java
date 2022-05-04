package conates.model.service;

import conates.model.domain.FornecedorMedicamento;
import conates.util.db.exception.NegocioException;
import conates.util.db.exception.PersistenciaException;
import java.util.List;

public interface IManterFornecedorMedicamento {

    FornecedorMedicamento pesquisarPorCod(Long cod) throws PersistenciaException;

    public Long cadastrar(FornecedorMedicamento fornecedormedicamento) throws PersistenciaException, NegocioException;

    public boolean alterar(FornecedorMedicamento fornecedormedicamento) throws PersistenciaException, NegocioException;

    public List<FornecedorMedicamento> listarTodos() throws PersistenciaException;

}