/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

/**
 *
 * @author Samara
 */
public class Funcionario extends Pessoa{
    
    private String cargo, departamento, senha;
    private int id, nivel;
    private double salario;

    public Funcionario(String cargo, int nivel, String departamento, String senha, String nome, String sobrenome, String rg, String cpf, String telefone) {
        super(nome, sobrenome, rg, cpf, telefone);
        this.cargo = cargo;
        this.nivel = nivel;
        this.departamento = departamento;
        this.senha = senha;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    public int getId() {
        return id;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }
}
