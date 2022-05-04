package conates.model.dao;

import conates.model.domain.Medicamento;
import conates.util.db.exception.PersistenciaException;
import java.util.List;

public interface IMedicamentoDAO{

    Long adicionar(Medicamento medicamento) throws PersistenciaException;

    boolean editar(Medicamento medicamento) throws PersistenciaException;

    Medicamento consultarPorCodigo(Long cod) throws PersistenciaException;

    List<Medicamento> exibirTodos() throws PersistenciaException;
    
    Medicamento consultarPorNome(String nome) throws PersistenciaException;

}
