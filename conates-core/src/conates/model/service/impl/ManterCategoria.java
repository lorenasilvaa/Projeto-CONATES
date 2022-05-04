package conates.model.service.impl;

import conates.model.dao.ICategoriaDAO;
import conates.model.dao.impl.CategoriaDAO;
import conates.model.domain.Categoria;
import conates.model.service.IManterCategoria;
import conates.util.db.exception.PersistenciaException;
import java.util.List;

public class ManterCategoria implements IManterCategoria {

    @Override
    public List<Categoria> listarTodos() throws PersistenciaException {
        ICategoriaDAO categoria = new CategoriaDAO();
        List<Categoria> listCategoria = categoria.exibirTodos();
        return listCategoria;
    }

    @Override
    public Categoria pesquisarPorCod(Long cod) throws PersistenciaException {
        ICategoriaDAO categoriaDAO = new CategoriaDAO();
        Categoria result = categoriaDAO.consultarPorCodigo(cod);

        return result;
    }

    @Override
    public Categoria pesquisarPorNome(String nome) throws PersistenciaException {
        ICategoriaDAO categoriaDAO = new CategoriaDAO();
        Categoria result = categoriaDAO.consultarPorNome(nome);
        return result;
    }

}

