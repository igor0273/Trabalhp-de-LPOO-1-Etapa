/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.cc.lpoo.cv.model;

import java.util.Calendar;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Igor Rocha
 */
@Entity
@Table(name = "tb_cliente")
public class Cliente {
    
    @Id
     @SequenceGenerator(name = "seq_cliente", sequenceName = "seq_cliente_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_cliente", strategy = GenerationType.SEQUENCE)
    private Integer id;
    
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar data_ultima_visita;
    
    @OneToMany
    @JoinColumn(name = "pet_id")
    private List<Pet> pet;
    
    @OneToOne
    @JoinColumn(name = "pessoa_id")
    private Pessoa pessoa;
    
    @OneToMany
    @JoinColumn(name = "venda_id")
    private List<Venda> venda;
    
    public Cliente(){
        
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the data_ultima_visita
     */
    public Calendar getData_ultima_visita() {
        return data_ultima_visita;
    }

    /**
     * @param data_ultima_visita the data_ultima_visita to set
     */
    public void setData_ultima_visita(Calendar data_ultima_visita) {
        this.data_ultima_visita = data_ultima_visita;
    }

    /**
     * @return the pet
     */
    public List<Pet> getPet() {
        return pet;
    }

    /**
     * @param pet the pet to set
     */
    public void setPet(List<Pet> pet) {
        this.pet = pet;
    }
    
    
    
    
}
