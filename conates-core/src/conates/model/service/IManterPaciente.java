package conates.model.service;

import conates.model.domain.Paciente;
import conates.util.db.exception.PersistenciaException;

public interface IManterPaciente {
    Paciente pesquisarPorCod(Long cod) throws PersistenciaException;
}
