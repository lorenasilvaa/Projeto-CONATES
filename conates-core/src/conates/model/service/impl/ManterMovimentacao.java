package conates.model.service.impl;

import conates.model.dao.IMovimentacaoDAO;
import conates.model.dao.impl.MovimentacaoDAO;
import conates.model.domain.Movimentacao;
import conates.model.service.IManterMovimentacao;
import conates.util.db.exception.PersistenciaException;
import java.util.List;

public class ManterMovimentacao implements IManterMovimentacao {

    @Override
    public List<Movimentacao> listarTodos() throws PersistenciaException {
        IMovimentacaoDAO movimentacao = new MovimentacaoDAO();
        List<Movimentacao> listMovimentacao = movimentacao.exibirTodos();
        return listMovimentacao;
    }

    @Override
    public Movimentacao pesquisarPorCod(Long cod) throws PersistenciaException {
        IMovimentacaoDAO movimentacaoDAO = new MovimentacaoDAO();
        Movimentacao result = movimentacaoDAO.consultarPorCodigo(cod);

        return result;
    }

    @Override
    public Movimentacao pesquisarPorNome(String nome) throws PersistenciaException {
        IMovimentacaoDAO movimentacaoDAO = new MovimentacaoDAO();
        Movimentacao result = movimentacaoDAO.consultarPorNome(nome);
        return result;
    }

}
