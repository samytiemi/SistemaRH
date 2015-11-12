/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import classes.ConnectionFactory;
import classes.Sistema;
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
public class SistemaDAO {
    private final String stmtInsert = "INSERT INTO sistema (sistema) VALUES (?)";
    private final String stmtSelectById = "SELECT * FROM sistema WHERE id = ?";
    private final String stmtSelectAll = "SELECT * FROM sistema";
    private final String stmtUpdade = "UPDATE sistema SET sistema = ? WHERE id = ?";
    private final String stmtDelete = "DELETE FROM sistema WHERE id = ?";
    
    public void insertSistema(Sistema sistema) {
        Connection con = null;
        PreparedStatement stmt = null;
        try{
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(stmtInsert,PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, sistema.getSistema());
            stmt.execute();
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            // Seta o ID do sistema
            long i = rs.getLong(1);
            sistema.setId(i);
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao inserir um sistema no banco de dados. Origem="+ex.getMessage());
        } finally{
            try{stmt.close();}catch(Exception ex){System.out.println("Erro ao fechar stmt. Ex="+ex.getMessage());};
            try{con.close();}catch(Exception ex){System.out.println("Erro ao fechar conexão. Ex="+ex.getMessage());};
        }
     }
    
    public List<Sistema> getListSistema() throws SQLException{
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        //PreparedStatement stmtLista = this.connection.prepareStatement("select * from contatos");
        try {
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(stmtSelectAll);
            rs = stmt.executeQuery();
            List<Sistema> sistemas = new ArrayList();
            while (rs.next()) {
                // criando o objeto Sistema
                Sistema sistema = new Sistema();
                sistema.setId(rs.getLong("id"));
                sistema.setSistema(rs.getString("sistema"));
                // adicionando o objeto à lista
                sistemas.add(sistema);
            }
            return sistemas;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally{
            try{rs.close();}catch(Exception ex){System.out.println("Erro ao fechar result set. Ex="+ex.getMessage());};
            try{stmt.close();}catch(Exception ex){System.out.println("Erro ao fechar stmt. Ex="+ex.getMessage());};
            try{con.close();}catch(Exception ex){System.out.println("Erro ao fechar conexão. Ex="+ex.getMessage());};
        }
    }
    
    public void updateSistema(Sistema sistema) throws SQLException{
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(stmtUpdade);
            stmt.setString(1, sistema.getSistema());
            stmt.setLong(2, sistema.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally{
            try{stmt.close();}catch(Exception ex){System.out.println("Erro ao fechar stmt. Ex="+ex.getMessage());};
            try{con.close();}catch(Exception ex){System.out.println("Erro ao fechar conexão. Ex="+ex.getMessage());};
        }

    }

    public void deleteSistema(Sistema sistema) throws SQLException {
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(stmtDelete);

            stmt.setLong(1, sistema.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally{
            try{stmt.close();}catch(Exception ex){System.out.println("Erro ao fechar stmt. Ex="+ex.getMessage());};
            try{con.close();}catch(Exception ex){System.out.println("Erro ao fechar conexão. Ex="+ex.getMessage());};
        }

    }
}
