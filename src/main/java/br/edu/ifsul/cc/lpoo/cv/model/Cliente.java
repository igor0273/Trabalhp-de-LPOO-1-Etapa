/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.cc.lpoo.cv.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Igor Rocha
 */
@Entity
@Table(name = "tb_cliente")
@DiscriminatorValue("C")
public class Cliente extends Pessoa implements Serializable{
    
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar data_ultima_visita;
    

    
    @ManyToOne
    @JoinColumn(name = "venda_id")
    private Venda venda;
    
    public Cliente(){
        
    }

    public Calendar getData_ultima_visita() {
        return data_ultima_visita;
    }

    public void setData_ultima_visita(Calendar data_ultima_visita) {
        this.data_ultima_visita = data_ultima_visita;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

  
    
    
}
