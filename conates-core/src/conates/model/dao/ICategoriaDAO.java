package conates.model.dao;

import conates.model.domain.Categoria;
import conates.util.db.exception.PersistenciaException;
import java.util.List;

public interface ICategoriaDAO {

    List<Categoria> exibirTodos() throws PersistenciaException;

    Categoria consultarPorCodigo(Long cod) throws PersistenciaException;

    Categoria consultarPorNome(String nome) throws PersistenciaException;
}
