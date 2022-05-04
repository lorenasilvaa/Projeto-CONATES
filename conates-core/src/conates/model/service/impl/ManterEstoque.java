package conates.model.service.impl;

import conates.model.dao.IEstoqueDAO;
import conates.model.dao.impl.EstoqueDAO;
import conates.model.domain.Estoque;
import conates.model.service.IManterEstoque;
import conates.util.db.JDBCConnectionManager;
import conates.util.db.exception.NegocioException;
import conates.util.db.exception.PersistenciaException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ManterEstoque implements IManterEstoque {

    @Override
    public Long cadastrar(Estoque estoque) throws PersistenciaException, NegocioException {
        IEstoqueDAO estoqueDAO = new EstoqueDAO();

        Long result = estoqueDAO.adicionar(estoque);
        estoque.setCod_estoque(result);

        return result;
    }

    @Override
    public boolean alterar(Estoque estoque) throws PersistenciaException, NegocioException {
        IEstoqueDAO estoqueDAO = new EstoqueDAO();

        // Exceções
        boolean result = estoqueDAO.editar(estoque);
        return result;
    }

    @Override
    public Estoque pesquisarPorCod(Long cod) throws PersistenciaException {
        IEstoqueDAO estoqueDAO = new EstoqueDAO();
        Estoque result = estoqueDAO.consultarPorCodigo(cod);

        return result;
    }

    @Override
    public List<Estoque> listarTodos() throws PersistenciaException {
        IEstoqueDAO estoqueDAO = new EstoqueDAO();
        List<Estoque> listEstoque = estoqueDAO.exibirTodos();
        return listEstoque;
    }

    @Override
    public List<Estoque> pesquisarPorIntervaloData(Date dat1, Date dat2) throws PersistenciaException {
        IEstoqueDAO estoqueDAO = new EstoqueDAO();
        List<Estoque> listEstoque = estoqueDAO.consultarPorIntervaloData(dat1, dat2);

        return listEstoque;
    }

}
