/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package telas;

import classes.Sistema;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Bruno
 */
public class TabelaSistemas extends AbstractTableModel{
    
    private String[] colunas = new String[]{"id","Sistema"};
    private List<Sistema> lista = new ArrayList();
    
    public TabelaSistemas(List<Sistema> lista){
        this.lista = lista;
    }

    public TabelaSistemas(){
    }

    @Override
    public int getRowCount() {
        return this.lista.size();
    }

    @Override
    public int getColumnCount() {
        return this.colunas.length;
    }
    
    @Override
    public String getColumnName(int index) {
        return this.colunas[index];
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
        //if(column==0)
            //return false;
        //return true;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Sistema dep = lista.get(rowIndex);
        switch (columnIndex) {
            case 0: return dep.getId();
            case 1: return dep.getSistema();
            default : return null;
        }
    }
    
    @Override
    public void setValueAt(Object value, int row, int col) {
        Sistema dep = lista.get(row);
        switch (col) {
            case 0:
                dep.setId((Long) value); //if column 0 (code)
                break;
            case 1:
                dep.setSistema((String) value);
                break;
            default:
        }
        this.fireTableCellUpdated(row, col);
    }
    
    public boolean deleteSistema(Sistema dep) {
        int linha = this.lista.indexOf(dep);
        boolean result = this.lista.remove(dep);
        this.fireTableRowsDeleted(linha, linha);//update JTable
        return result;
    }

    public void insetSistema(Sistema dep) {
        this.lista.add(dep);
        //this.fireTableDataChanged();
        this.fireTableRowsInserted(lista.size() - 1, lista.size() - 1);//update JTable
    }
    
    public void setListSistema(List<Sistema> sistemas) {
        this.lista = sistemas;
        this.fireTableDataChanged();
        //this.fireTableRowsInserted(0,contatos.size()-1);//update JTable
    }

    public void clearTabela() {
        int indice = lista.size() - 1;
        if(indice<0)
            indice = 0;
        this.lista = new ArrayList();
        this.fireTableRowsDeleted(0,indice);//update JTable
    }

    public Sistema getSistema(int linha){
        return lista.get(linha);
    }
    
    public List<Sistema> getList(int[] indices){
        List<Sistema> lista = new ArrayList();
        for(int i = 0; i < indices.length; i++){
            lista.add(this.lista.get(indices[i]));
        }
        return lista;
    }
}
