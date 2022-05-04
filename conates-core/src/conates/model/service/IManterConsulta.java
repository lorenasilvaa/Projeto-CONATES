package conates.model.service;

import conates.model.domain.Consulta;
import conates.util.db.exception.NegocioException;
import conates.util.db.exception.PersistenciaException;
import java.util.List;

public interface IManterConsulta {

    public Long cadastrar(Consulta consulta) throws PersistenciaException, NegocioException;

    public boolean alterar(Consulta consulta) throws PersistenciaException, NegocioException;

    public List<Consulta> listarTodos() throws PersistenciaException;

    Consulta pesquisarPorCod(Long cod) throws PersistenciaException;
}
