package conates.model.dao;

import conates.model.domain.Paciente;
import conates.util.db.exception.PersistenciaException;

public interface IPacienteDAO {
    Paciente consultarPorCodigo(Long cod) throws PersistenciaException;
}
