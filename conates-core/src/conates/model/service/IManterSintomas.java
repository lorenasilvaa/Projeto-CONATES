package conates.model.service;

import conates.model.domain.Sintomas;
import conates.util.db.exception.PersistenciaException;
import java.util.List;

public interface IManterSintomas {
    public List<Sintomas> listarTodos() throws PersistenciaException;
    Sintomas pesquisarPorCod(Long cod) throws PersistenciaException;
    public Sintomas pesquisarPorNome(String nome) throws PersistenciaException;
}
