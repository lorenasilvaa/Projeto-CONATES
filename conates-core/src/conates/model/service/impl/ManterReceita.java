package conates.model.service.impl;

import conates.model.dao.IReceitaDAO;
import conates.model.dao.impl.ReceitaDAO;
import conates.model.domain.Receita;
import conates.model.service.IManterReceita;
import conates.util.db.exception.NegocioException;
import conates.util.db.exception.PersistenciaException;
import java.util.List;

public class ManterReceita implements IManterReceita {

    @Override
    public Long cadastrar(Receita receita) throws PersistenciaException, NegocioException {
        IReceitaDAO receitaDAO = new ReceitaDAO();

        Long result = receitaDAO.adicionar(receita);
        receita.setCod_receita(result);
        return result;
    }

    @Override
    public boolean alterar(Receita receita) throws PersistenciaException, NegocioException {
        IReceitaDAO receitaDAO = new ReceitaDAO();

        boolean result = receitaDAO.editar(receita);
        return result;
    }

    @Override
    public List<Receita> listarTodos() throws PersistenciaException {
        IReceitaDAO receitaDAO = new ReceitaDAO();
        List<Receita> listReceita = receitaDAO.exibirTodos();
        return listReceita;
    }

    @Override
    public Receita pesquisarPorCod(Long cod) throws PersistenciaException {
        IReceitaDAO receitaDAO = new ReceitaDAO();
        Receita result = receitaDAO.consultarPorCodigo(cod);
        return result;
    }
}
