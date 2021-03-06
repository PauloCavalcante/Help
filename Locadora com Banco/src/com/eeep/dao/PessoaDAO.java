/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eeep.dao;

import com.eeep.model.Filme;
import com.eeep.model.Pessoa;
import com.eeep.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Junior
 */
public class PessoaDAO {
    public void cadastrarPessoa(Pessoa pessoa){
        String sql = "insert into pessoa (cpf_pessoa, nome_pessoa)"
                + " values (?,?)";
        Connection conn = null;
        PreparedStatement pstm = null;
        try {
            conn = ConnectionFactory.getConnection();
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, pessoa.getCpf());
            pstm.setString(2, pessoa.getNome());
            pstm.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstm != null) {
                    pstm.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    public Pessoa buscarPorCpf(String cpf){
        String sql = "select * from pessoa where cpf_pessoa=?";
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rset = null;
        Pessoa pessoa = null;
        try{
            conn = ConnectionFactory.getConnection();
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, cpf);
            rset = pstm.executeQuery();
            while(rset.next()){
                pessoa = new Pessoa();
                pessoa.setCpf(rset.getString("cpf_pessoa"));
                pessoa.setNome(rset.getString("nome_pessoa"));
                
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            try{
                if(rset!=null){
                    rset.close();
                }
                if(pstm!=null){
                    pstm.close();
                }
                if(conn!=null){
                    conn.close();
                }
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return pessoa;
    }
    
}
