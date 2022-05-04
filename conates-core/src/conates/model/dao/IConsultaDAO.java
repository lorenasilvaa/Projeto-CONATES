package conates.model.dao;

import conates.model.domain.Consulta;
import conates.util.db.exception.NegocioException;
import conates.util.db.exception.PersistenciaException;
import java.util.List;

public interface IConsultaDAO{

    Long adicionar(Consulta consulta) throws PersistenciaException, NegocioException;

    boolean editar(Consulta consulta) throws PersistenciaException, NegocioException;

    Consulta consultarPorCodigo(Long cod) throws PersistenciaException;

    List<Consulta> exibirTodos() throws PersistenciaException;

}
