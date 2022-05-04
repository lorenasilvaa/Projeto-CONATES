package conates.model.dao;

import conates.model.domain.Sintomas;
import conates.util.db.exception.PersistenciaException;
import java.util.List;

public interface ISintomasDAO{

    List<Sintomas> exibirTodos() throws PersistenciaException;
    Sintomas consultarPorCodigo(Long cod) throws PersistenciaException;
    Sintomas consultarPorNome(String nome) throws PersistenciaException;
}
