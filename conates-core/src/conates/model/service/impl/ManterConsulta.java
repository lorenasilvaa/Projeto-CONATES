package conates.model.service.impl;

import conates.model.dao.IConsultaDAO;
import conates.model.dao.impl.ConsultaDAO;
import conates.model.domain.Consulta;
import conates.model.service.IManterConsulta;
import conates.util.db.JDBCConnectionManager;
import conates.util.db.exception.NegocioException;
import conates.util.db.exception.PersistenciaException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ManterConsulta implements IManterConsulta {

    @Override
    public Long cadastrar(Consulta consulta) throws PersistenciaException, NegocioException {
        IConsultaDAO consultaDAO = new ConsultaDAO();

        if ((consulta.getCod_paciente_id() == null)) {
            throw new NegocioException("Obrigatório informar o código do paciente.");
        }
        if ((consulta.getDat_consulta().equals(""))) {
            throw new NegocioException("Obrigatório informar a data da consulta.");
        }
        if (consulta.getHor_consulta().equals("")) {
            throw new NegocioException("Obrigatório informar o horário da consulta.");
        }
        if ((consulta.getCod_enf_id() == null)) {
            throw new NegocioException("Obrigatório informar o código do enfermeiro.");
        }

        Long result = consultaDAO.adicionar(consulta);
        consulta.setCod_consulta(result);

        return result;
    }

    @Override
    public boolean alterar(Consulta consulta) throws PersistenciaException, NegocioException {
        IConsultaDAO consultaDAO = new ConsultaDAO();
        if ((consulta.getCod_paciente_id() == null)) {
            throw new NegocioException("Obrigatório informar o código do paciente.");
        }
        if ((consulta.getDat_consulta() == null)) {
            throw new NegocioException("Obrigatório informar a data da consulta.");
        }
        if (consulta.getHor_consulta().equals("")) {
            throw new NegocioException("Obrigatório informar o horário da consulta.");
        }
        if ((consulta.getCod_enf_id() == null)) {
            throw new NegocioException("Obrigatório informar o código do enfermeiro.");
        }
        boolean result = consultaDAO.editar(consulta);
        return result;
    }

    @Override
    public Consulta pesquisarPorCod(Long cod) throws PersistenciaException {
        IConsultaDAO cursoDAO = new ConsultaDAO();
        Consulta result = cursoDAO.consultarPorCodigo(cod);
        return result;
    }

    @Override
    public List<Consulta> listarTodos() throws PersistenciaException {
        IConsultaDAO consultaDAO = new ConsultaDAO();
        List<Consulta> listConsulta = consultaDAO.exibirTodos();
        return listConsulta;
    }

    /*public static void main(String[] args) throws ClassNotFoundException, SQLException {
        System.out.println("a");
         Connection connection = JDBCConnectionManager.getInstance().getConnection();
        System.out.println("a");
        Date x = new Date(22 / 02 / 2012);

        Consulta a = new Consulta();
        a.setCod_enf_id(3l);
        a.setCod_paciente_id(201411130256l);
        a.setDat_consulta(x);
        a.setHor_consulta("12:30");
        

        IManterConsulta GradeHorario = new ManterConsulta();

        try {
            GradeHorario.cadastrar(a);
        } catch (PersistenciaException ex) {
            Logger.getLogger(Consulta.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NegocioException ex) {
            Logger.getLogger(ManterConsulta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/

}
