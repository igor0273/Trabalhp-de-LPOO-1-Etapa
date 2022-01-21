/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.cc.lpoo.cv.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author Igor Rocha
 */
@Entity
@Table(name = "tb_fornecedor")
public class Fornecedor {
    
    @Id
     @SequenceGenerator(name = "seq_fornecedor", sequenceName = "seq_fornecedor_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_fornecedor", strategy = GenerationType.SEQUENCE)
    private Integer id;
    
    @Column
    private String cnpj;
    
    @Column
    private String ie;
    
    public Fornecedor(){
        
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
     * @return the cnpj
     */
    public String getCnpj() {
        return cnpj;
    }

    /**
     * @param cnpj the cnpj to set
     */
    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    /**
     * @return the ie
     */
    public String getIe() {
        return ie;
    }

    /**
     * @param ie the ie to set
     */
    public void setIe(String ie) {
        this.ie = ie;
    }
    
    
}
