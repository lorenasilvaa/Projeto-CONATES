package conates.model.dao;

import conates.model.domain.FornecedorMedicamento;
import conates.util.db.exception.NegocioException;
import conates.util.db.exception.PersistenciaException;
import java.util.List;

public interface IFornecedorMedicamentoDAO {

    Long adicionar(FornecedorMedicamento fornecedorMedicamento) throws PersistenciaException, NegocioException;

    boolean editar(FornecedorMedicamento fornecedorMedicamento) throws PersistenciaException, NegocioException;

    FornecedorMedicamento consultarPorCodigo(Long cod) throws PersistenciaException;

    List<FornecedorMedicamento> exibirTodos() throws PersistenciaException;

}
