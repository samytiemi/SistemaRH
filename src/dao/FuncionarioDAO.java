/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

/**
 *
 * @author Bruno
 */

public class FuncionarioDAO {

    private final String stmtInserir = "INSERT INTO funcionario(nome, sobrenome, rg, cpf, telefone, cargo, nivel, departamento_id, tipo_id) "
            + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private final String stmtConsultar = "SELECT * FROM autor WHERE id = ?";
    private final String stmtListar = "SELECT * FROM autor";

   

}
