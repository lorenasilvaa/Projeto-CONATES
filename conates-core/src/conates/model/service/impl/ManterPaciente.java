package conates.model.service.impl;

import conates.model.dao.IPacienteDAO;
import conates.model.dao.impl.PacienteDAO;
import conates.model.domain.Paciente;
import conates.model.service.IManterPaciente;
import conates.util.db.exception.PersistenciaException;

public class ManterPaciente implements IManterPaciente {

    @Override
    public Paciente pesquisarPorCod(Long cod) throws PersistenciaException {
        IPacienteDAO pacienteDAO = new PacienteDAO();
        Paciente result = pacienteDAO.consultarPorCodigo(cod);

        return result;
    }
}
