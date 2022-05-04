package conates.model.dao;

import conates.model.domain.Enfermeiro;
import conates.util.db.exception.PersistenciaException;
import java.util.List;

public interface IEnfermeiroDAO {
    Enfermeiro consultarPorCodigo(Long cod) throws PersistenciaException;
    List<Enfermeiro> exibirTodos() throws PersistenciaException;
    Enfermeiro consultarPorNome(String nome) throws PersistenciaException;
}
