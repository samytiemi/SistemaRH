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

/**
 *
 * @author Bruno
 */
public class DepartamentoDAO {
    private final String stmtInserir = "INSERT INTO departamento (departamento) VALUES (?)";
    private final String stmtConsultar = "SELECT * FROM autor WHERE id = ?";
    private final String stmtListar = "SELECT * FROM autor";
    
    public void inserirDepartamento(Departamento departamento) {
        Connection con = null;
        PreparedStatement stmt = null;
        try{
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(stmtInserir,PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, departamento.getDepartamento());
            stmt.executeUpdate();
            departamento.setId(lerIdDepartamento(stmt));
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao inserir um autor no banco de dados. Origem="+ex.getMessage());
        } finally{
            try{stmt.close();}catch(Exception ex){System.out.println("Erro ao fechar stmt. Ex="+ex.getMessage());};
            try{con.close();}catch(Exception ex){System.out.println("Erro ao fechar conexão. Ex="+ex.getMessage());};
        }
     }
    
    private int lerIdDepartamento(PreparedStatement stmt) throws SQLException {
        ResultSet rs = stmt.getGeneratedKeys();
        rs.next();
        return rs.getInt(1);
    }
}
