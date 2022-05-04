package conates.model.service;

import conates.model.domain.Enfermeiro;
import conates.util.db.exception.PersistenciaException;
import java.util.List;

public interface IManterEnfermeiro {

    Enfermeiro pesquisarPorCod(Long cod) throws PersistenciaException;
    public Enfermeiro pesquisarPorNome(String nome) throws PersistenciaException;
    public List<Enfermeiro> listarTodos() throws PersistenciaException;
}
