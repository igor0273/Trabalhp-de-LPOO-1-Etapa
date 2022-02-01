/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.cc.lpoo.cv.model.dao;

import br.edu.ifsul.cc.lpoo.cv.model.Cliente;
import br.edu.ifsul.cc.lpoo.cv.model.Pessoa;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

/**
 *
 * @author Igor Rocha
 */
public class PersistenciaJDBC implements InterfacePersistencia {

    private final String DRIVER = "org.postgresql.Driver";
    private final String USER = "postgres";
    private final String SENHA = "postgres";
    public static final String URL = "jdbc:postgresql://localhost:5432/trabalhoLPOO_CV";
    private Connection con = null;

    public PersistenciaJDBC() throws Exception {

        Class.forName(DRIVER);
        System.out.println("Tentando estabelecer conexao JDBC com :" + URL + " ...");

        this.con = (Connection) DriverManager.getConnection(URL, USER, SENHA);

    }

    @Override
    public Boolean conexaoAberta() {
        try {
            if (con != null) {
                return !con.isClosed();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public void fecharConexao() {
        try {
            this.con.close();
            System.out.println("Fechou conexao JDBC");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Object find(Class c, Object id) throws Exception {
        if (c == Cliente.class) {
            //tb_cliente
            PreparedStatement ps = this.con.prepareStatement("select id,data_ultima_visita,pessoa_id from tb_cliente where id = ?");
            ps.setInt(1, Integer.parseInt(id.toString()));

            ResultSet rs = ps.executeQuery();
             
            if (rs.next()) {

                Cliente cliente = new Cliente();
 //Elementos da consulta
                cliente.setId(rs.getInt("id"));
                //cliente.setData_ultima_visita(rs.getDate("data_ultima_visita"));
                cliente.setPessoa(rs.getInt("pessoa_id"));
                
                ps.close();

                return cliente;

            }
        } else if (c == Pessoa.class) {
            PreparedStatement ps = this.con.prepareStatement("select id,cpf,rg,nome,senha,numero_celular,email,data_cadastro,data_nascimento,cep,endereco,complemento from tb_pessoa where id = ?");
            ps.setInt(1, Integer.parseInt(id.toString()));

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Pessoa p = new Pessoa();
                p.setId(rs.getInt("id"));
                p.setCpf(rs.getString("cpf"));
                p.setRg(rs.getString("rg"));
                p.setNome(rs.getString("nome"));
                p.setSenha(rs.getString("senha"));
                p.setNumero_celular(rs.getString("numero_celular"));
                p.setEmail(rs.getString("email"));
                p.setCep(rs.getString("cep"));
                p.setEndereco(rs.getString("enderco"));
                p.setComplemento(rs.getString("complemento"));
                //falta datas
                ps.close();
                return p;
            }
        }

        return null;
    }

    @Override
    public void persist(Object o) throws Exception {

        if (o instanceof Cliente) {
            Pessoa p = (Pessoa) o;

            if (p.getId() == null) {

                PreparedStatement ps = this.con.prepareStatement("insert into tb_pessoa (id,cpf,rg,nome,senha,numero_celular,email,data_cadastro,data_nascimento,cep,endereco,complemento) values (nextval('seq_pessoa_id'),?,?,?,?,?,?,?,?,?,?,?) returning id");

                ps.setInt(1, p.getId());
                ps.setString(2, p.getCpf());
                ps.setString(3, p.getRg());
                ps.setString(4, p.getNome());
                ps.setString(5, p.getSenha());
                ps.setString(6, p.getNumero_celular());
                ps.setString(7, p.getEmail());
                //falta datas
                ps.setString(10, p.getCep());
                ps.setString(11, p.getEndereco());
                ps.setString(12, p.getComplemento());
                ps.execute();

            } else {
                PreparedStatement ps = this.con.prepareStatement("update tb_pessoa set "
                        + "cpf = ?, "
                        + "rg = ? ,"
                        + "nome = ? ,"
                        + "senha = ? ,"
                        + "numero_celular = ? ,"
                        + "email = ? ,"
                        + "data_cadastro = ? ,"
                        + "data_nascimento = ? ,"
                        + "cep = ? ,"
                        + "endereco = ? ,"
                        + "complemento = ?"
                        + "where id = ?");

                ps.setInt(1, p.getId());
                ps.setString(2, p.getCpf());
                ps.setString(3, p.getRg());
                ps.setString(4, p.getNome());
                ps.setString(5, p.getSenha());
                ps.setString(6, p.getNumero_celular());
                ps.setString(7, p.getEmail());
                //falta datas
                ps.setString(10, p.getCep());
                ps.setString(11, p.getEndereco());
                ps.setString(12, p.getComplemento());

                ps.execute();//executa o comando.
            }
        } else if (o instanceof Cliente) {
            Cliente c = (Cliente) o;

            if (c.getId() == null) {
                PreparedStatement ps = this.con.prepareStatement("insert into tb_cliente (id,data_ultima_visita,pessoa_id) values (nextval('seq_cliente_id'),?,?) returning id");

                ps.setInt(1, c.getId());
                ps.setDate(2, (Date) c.getData_ultima_visita());
                ps.setInt(3, c.getPessoa());
                ps.execute();

            } else {
                PreparedStatement ps = this.con.prepareStatement("update tb_cliente set "
                        + "data_ultima_visita = ?, "
                        + "pessoa_id = ? ,"
                        + "where id = ?");
                //Elementos de Tb_pessoa
                ps.setInt(3, c.getId());
                ps.setDate(1, (Date) c.getData_ultima_visita());
                ps.setInt(2, c.getPessoa());
                ps.execute();//executa o comando.
            }
        }
    }

    @Override
    public void remover(Object o) throws Exception {
        if (o instanceof Cliente) {

            Cliente c = (Cliente) o;

            PreparedStatement ps = this.con.prepareStatement("delete from tb_cliente where id = ?");
            ps.setInt(1, c.getId());
            ps.execute();
        } else if (o instanceof Pessoa) {
            Pessoa p = (Pessoa) o;
            PreparedStatement ps = this.con.prepareStatement("delete from tb_pessoa where id = ?");
            ps.setInt(1, p.getId());
            ps.execute();
        }
    }

}
