/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package classes;

/**
 *
 * @author Bruno
 */
public class Sistema {
    private long id;
    private String sistema;
    
    public Sistema() {
        id = -1L;
    }

    public Sistema(long id, String sistema) {
        this.id = id;
        this.sistema = sistema;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSistema() {
        return sistema;
    }

    public void setSistema(String sistema) {
        this.sistema = sistema;
    }
}
