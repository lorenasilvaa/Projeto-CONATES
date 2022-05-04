package conates.model.service;

import conates.model.domain.Categoria;
import conates.util.db.exception.PersistenciaException;
import java.util.List;

public interface IManterCategoria {
    public List<Categoria> listarTodos() throws PersistenciaException;
    Categoria pesquisarPorCod(Long cod) throws PersistenciaException;
    public Categoria pesquisarPorNome(String nome) throws PersistenciaException;
}
