package conates.model.service.impl;

import conates.model.dao.IMedicamentoDAO;
import conates.model.dao.impl.MedicamentoDAO;
import conates.model.domain.Medicamento;
import conates.model.service.IManterMedicamento;
import conates.util.db.JDBCConnectionManager;
import conates.util.db.exception.NegocioException;
import conates.util.db.exception.PersistenciaException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ManterMedicamento implements IManterMedicamento {

    @Override
    public Long cadastrar(Medicamento medicamento) throws PersistenciaException, NegocioException {
        IMedicamentoDAO medicamentoDAO = new MedicamentoDAO();

        if ((medicamento.getCod_medic() == null)) {
            throw new NegocioException("Obrigatório informar o nome do curso.");
        }

        Long result = medicamentoDAO.adicionar(medicamento);
        medicamento.setCod_medic(result);

        return result;
    }

    @Override
    public boolean alterar(Medicamento medicamento) throws PersistenciaException, NegocioException {
        IMedicamentoDAO medicamentoDAO = new MedicamentoDAO();

        // Exceções
        boolean result = medicamentoDAO.editar(medicamento);
        return result;
    }

    @Override
    public Medicamento pesquisarPorCod(Long cod) throws PersistenciaException {
        IMedicamentoDAO medicamentoDAO = new MedicamentoDAO();
        Medicamento result = medicamentoDAO.consultarPorCodigo(cod);

        return result;
    }

    @Override
    public List<Medicamento> listarTodos() throws PersistenciaException {
        IMedicamentoDAO medicamentoDAO = new MedicamentoDAO();
        List<Medicamento> listMedicamento = medicamentoDAO.exibirTodos();
        return listMedicamento;
    }
    
    @Override
    public Medicamento pesquisarPorNome(String nome) throws PersistenciaException {
        IMedicamentoDAO medicamentoDAO = new MedicamentoDAO();
        Medicamento result = medicamentoDAO.consultarPorNome(nome);
        return result;        
    }

    @Override
    public void excluir(Medicamento medicamento) throws PersistenciaException, NegocioException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
