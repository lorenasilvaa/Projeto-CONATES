package conates.model.service;

import conates.model.domain.Medicamento;
import conates.util.db.exception.NegocioException;
import conates.util.db.exception.PersistenciaException;
import java.util.List;

public interface IManterMedicamento {

    public Long cadastrar(Medicamento medicamento) throws PersistenciaException, NegocioException;

    public boolean alterar(Medicamento medicamento) throws PersistenciaException, NegocioException;
    
    public void excluir(Medicamento medicamento) throws PersistenciaException, NegocioException;

    public List<Medicamento> listarTodos() throws PersistenciaException;

    Medicamento pesquisarPorCod(Long cod) throws PersistenciaException;
    
    public Medicamento pesquisarPorNome(String nome) throws PersistenciaException;
}
