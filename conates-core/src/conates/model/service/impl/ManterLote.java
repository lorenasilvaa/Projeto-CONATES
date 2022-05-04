package conates.model.service.impl;

import conates.model.dao.ILoteDAO;
import conates.model.dao.impl.LoteDAO;
import conates.model.domain.Lote;
import conates.model.service.IManterLote;
import conates.util.db.exception.NegocioException;
import conates.util.db.exception.PersistenciaException;
import java.util.List;

public class ManterLote implements IManterLote {

    @Override
    public Long cadastrar(Lote lote) throws PersistenciaException, NegocioException {
        ILoteDAO loteDAO = new LoteDAO();

        Long result = loteDAO.adicionar(lote);
        lote.setCod_lote(result);

        return result;
    }

    @Override
    public boolean alterar(Lote lote) throws PersistenciaException, NegocioException {
        ILoteDAO loteDAO = new LoteDAO();
        boolean result = loteDAO.editar(lote);
        return result;
    }

    @Override
    public Lote pesquisarPorCod(Long cod) throws PersistenciaException {
        ILoteDAO loteDAO = new LoteDAO();
        Lote result = loteDAO.consultarPorCodigo(cod);

        return result;
    }

    @Override
    public List<Lote> listarTodos() throws PersistenciaException {
        ILoteDAO loteDAO = new LoteDAO();
        List<Lote> listLote = loteDAO.exibirTodos();
        return listLote;
    }
}
