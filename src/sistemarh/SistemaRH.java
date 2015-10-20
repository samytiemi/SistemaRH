/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemarh;

import classes.Departamento;
import dao.DepartamentoDAO;
import java.util.Scanner;

/**
 *
 * @author Samara
 */
public class SistemaRH {
    
    private DepartamentoDAO departamentoDAO;

    public SistemaRH() {
        departamentoDAO = new DepartamentoDAO();
    }
    
    
    
    public static void main(String[] args) {
        
        SistemaRH srh = new SistemaRH();
        srh.incluirDepartamento();
    }
    
    public void incluirDepartamento(){
        System.out.print("Digite o nome do departamento:");
        Scanner sc = new Scanner(System.in,"ISO-8859-1");
        String nome = sc.nextLine();
        Departamento departamento = new Departamento(nome);
        departamentoDAO.inserirDepartamento(departamento);
    }
}
