/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import classes.ConnectionFactory;
import classes.Departamento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Bruno
 */
public class DepartamentoDAO {
    private final String stmtInsert = "INSERT INTO departamento (departamento) VALUES (?)";
    private final String stmtSelectById = "SELECT * FROM departamento WHERE id = ?";
    private final String stmtSelectAll = "SELECT * FROM departamento";
    private String stmtUpdade = "UPDATE departamento SET departamento = ? WHERE id = ?";
    private String stmtDelete = "DELETE FROM departamento WHERE id = ?";
    
    public void insertDepartamento(Departamento departamento) {
        Connection con = null;
        PreparedStatement stmt = null;
        try{
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(stmtInsert,PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, departamento.getDepartamento());
            stmt.execute();
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            // Seta o ID do departamento
            long i = rs.getLong(1);
            departamento.setId(i);
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao inserir um autor no banco de dados. Origem="+ex.getMessage());
        } finally{
            try{stmt.close();}catch(Exception ex){System.out.println("Erro ao fechar stmt. Ex="+ex.getMessage());};
            try{con.close();}catch(Exception ex){System.out.println("Erro ao fechar conexão. Ex="+ex.getMessage());};
        }
     }
    
    public List<Departamento> getListDepartamento() throws SQLException{
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        //PreparedStatement stmtLista = this.connection.prepareStatement("select * from contatos");
        try {
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(stmtSelectAll);
            rs = stmt.executeQuery();
            List<Departamento> departamentos = new ArrayList();
            while (rs.next()) {
                // criando o objeto Contato
                Departamento departamento = new Departamento();
                departamento.setId(rs.getLong("id"));
                departamento.setDepartamento(rs.getString("departamento"));
                // adicionando o objeto à lista
                departamentos.add(departamento);
            }
            return departamentos;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally{
            try{rs.close();}catch(Exception ex){System.out.println("Erro ao fechar result set. Ex="+ex.getMessage());};
            try{stmt.close();}catch(Exception ex){System.out.println("Erro ao fechar stmt. Ex="+ex.getMessage());};
            try{con.close();}catch(Exception ex){System.out.println("Erro ao fechar conexão. Ex="+ex.getMessage());};
        }
    }
    
    public void updateDepartamento(Departamento departamento) throws SQLException{
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(stmtUpdade);
            stmt.setString(1, departamento.getDepartamento());
            stmt.setLong(2, departamento.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally{
            try{stmt.close();}catch(Exception ex){System.out.println("Erro ao fechar stmt. Ex="+ex.getMessage());};
            try{con.close();}catch(Exception ex){System.out.println("Erro ao fechar conexão. Ex="+ex.getMessage());};
        }

    }

    public void deleteDepartamento(Departamento departamento) throws SQLException {
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(stmtDelete);

            stmt.setLong(1, departamento.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally{
            try{stmt.close();}catch(Exception ex){System.out.println("Erro ao fechar stmt. Ex="+ex.getMessage());};
            try{con.close();}catch(Exception ex){System.out.println("Erro ao fechar conexão. Ex="+ex.getMessage());};
        }

    }
}
