/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.cc.lpoo.cv.model.dao;

import br.edu.ifsul.cc.lpoo.cv.model.Cliente;
import br.edu.ifsul.cc.lpoo.cv.model.Consulta;
import br.edu.ifsul.cc.lpoo.cv.model.Funcionario;
import br.edu.ifsul.cc.lpoo.cv.model.Pessoa;
import br.edu.ifsul.cc.lpoo.cv.model.Venda;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Date;

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

        try {
            Class.forName(DRIVER);
            System.out.println("Tentando estabelecer conexao JDBC com :" + URL + " ...");
            this.con = (Connection) DriverManager.getConnection(URL, USER, SENHA);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

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
            PreparedStatement ps = this.con.prepareStatement("select id,data_ultima_visita,cpf,rg from tb_cliente where cpf = ?");
            ps.setInt(1, Integer.parseInt(id.toString()));

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                Cliente cliente = new Cliente();
                cliente.setCpf(rs.getString("cpf"));

                Calendar dt1 = Calendar.getInstance();
                dt1.setTimeInMillis(rs.getDate("data_ultima_visita").getTime());
                cliente.setData_cadastro(dt1);

                cliente.setCpf(rs.getString("cpf"));
                cliente.setRg(rs.getString("rg"));
                ps.close();

                return cliente;

            }
        }

        return null;
    }

    @Override
    public void persist(Object o) throws Exception {

        if (o instanceof Cliente) {
            Pessoa p = (Pessoa) o;

            if (p.getCpf() == null) {

                PreparedStatement ps = this.con.prepareStatement("insert into tb_pessoa (cpf,rg,nome,senha,numero_celular,email,data_cadastro,data_nascimento,cep,endereco,complemento) values (nextval('seq_pessoa_id'),?,?,?,?,?,?,?,?,?,?,?) returning id");

                ps.setString(1, p.getCpf());
                ps.setString(2, p.getRg());
                ps.setString(3, p.getNome());
                ps.setString(4, p.getSenha());
                ps.setString(5, p.getNumero_celular());
                ps.setString(6, p.getEmail());

                Date dt1 = new Date();
                ps.setDate(7, (java.sql.Date) dt1);

                Date dt2 = new Date();
                ps.setDate(8, (java.sql.Date) dt2);

                ps.setString(9, p.getCep());
                ps.setString(10, p.getEndereco());
                ps.setString(11, p.getComplemento());
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

                ps.setString(1, p.getCpf());
                ps.setString(2, p.getRg());
                ps.setString(3, p.getNome());
                ps.setString(4, p.getSenha());
                ps.setString(5, p.getNumero_celular());
                ps.setString(6, p.getEmail());

                Date dt1 = new Date();
                ps.setDate(7, (java.sql.Date) dt1);

                Date dt2 = new Date();
                ps.setDate(8, (java.sql.Date) dt2);

                ps.setString(9, p.getCep());
                ps.setString(10, p.getEndereco());
                ps.setString(11, p.getComplemento());
                ps.execute();
            }
        } else if (o instanceof Cliente) {
            Cliente c = (Cliente) o;

            if (c.getCpf() == null) {
                PreparedStatement ps = this.con.prepareStatement("insert into tb_cliente (cpf,data_ultima_visita) values (nextval('seq_cliente_id'),?,?) returning id");

                ps.setString(1, c.getCpf());

                Date dt1 = new Date();

                ps.setDate(2, (java.sql.Date) (Date) dt1);
                ps.execute();

            } else {
                PreparedStatement ps = this.con.prepareStatement("update tb_cliente set "
                        + "data_ultima_visita = ?, "
                        + "where cpf = ?");
                //Elementos de Tb_pessoa
                ps.setString(2, c.getCpf());
                Date dt1 = new Date();

                ps.setDate(2, (java.sql.Date) (Date) dt1);

                ps.execute();//executa o comando.
            }
        }
    }

    @Override
    public void remover(Object o) throws Exception {
        if (o instanceof Cliente) {

            Cliente c = (Cliente) o;

            PreparedStatement ps = this.con.prepareStatement("delete from tb_cliente where cpf = ?");
            ps.setString(1, c.getCpf());
            ps.execute();
        } else if (o instanceof Pessoa) {
            Pessoa p = (Pessoa) o;
            PreparedStatement ps = this.con.prepareStatement("delete from tb_pessoa where cpf = ?");
            ps.setString(1, p.getCpf());
            ps.execute();
        }
    }

    @Override
    public List<Consulta> ListPerssistConsulta() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Venda> ListPerssistVenda() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Funcionario doLogin(String cpf, String senha) throws Exception {
        
        Funcionario func = null;
        PreparedStatement ps = this.con.prepareStatement("select f.cpf, f.senha from tb_funcionario f where f.cpf= ? and f.senha = ?");
        ps.setString(1, cpf);
        ps.setString(2, senha);
        
        ResultSet rs = ps.executeQuery();
        
        if(rs.next()){
            
            func = new Funcionario();
            func.setCpf(rs.getString("cpf"));
        }
        ps.close();
        return func;
    }

    @Override
    public List<Funcionario> getFuncionarios() throws Exception {
       List<Funcionario> listFuncionario = null;
       
       PreparedStatement ps = this.con.prepareStatement("select cpf, nome,cep,senha from tb_funcionario order by cpf asc");
       ResultSet rs = ps.executeQuery();
       
       listFuncionario = new ArrayList();
       
        while (rs.next()) {            
            
            Funcionario func = new Funcionario();
            func.setCpf(rs.getString("cpf"));
            func.setNome(rs.getString("nome"));
            func.setCep(rs.getString("cep"));
            func.setSenha(rs.getString("senha"));
            
            listFuncionario.add(func);
        }
        return listFuncionario;
//To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Cliente> getListCliente() throws Exception {
     
        List<Cliente> listCliente = null;
        
       PreparedStatement ps = this.con.prepareStatement("select cpf, nome,cep,senha from tb_funcionario order by cpf asc");
       ResultSet rs = ps.executeQuery();
       
       listCliente = new ArrayList();
       
       while(rs.next()){
            
           Cliente c = new Cliente();
           c.setCpf(rs.getString("cpf"));
           c.setNome(rs.getString("nome"));
           c.setCep(rs.getString("cep"));
           c.setSenha(rs.getString("senha"));
           
           listCliente.add(c);
       }
       
       return listCliente;
    }

}
