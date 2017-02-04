/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Daos;

import Beans.Producto;
import Conexion.ConexionBD;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Beans.Usuario;
import Beans.TipoUsuario;
import Beans.Persona;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Musica
 */
public class DaoUsuarios {
    
  
    String ConsultaAlta = "SELECT Max(idpersona) FROM persona";
    String addPersona = "INSERT INTO persona (nombre,apellidos) VALUES (?,?);";
    String addUsuario = "INSERT INTO usuarios (usuario,contrasena,persona,tipoUsuario) VALUES (?,?,?,2);";
    String ConsultaGeneral="Select nombre,apellidos,usuario,contrasena,nombreTipo from persona join usuarios on idPersona=persona join tipoUsuario on idTipo=tipoUsuario";
    public int ConsultaID (){
        boolean existe=false;
        int res=0;
        try{
            Connection con = ConexionBD.getConnection();
            PreparedStatement ps = con.prepareStatement(ConsultaAlta);
            ResultSet rs = ps.executeQuery();
            rs.next();
            res=rs.getInt(1);
           
            ps.close();
            con.close();
        }catch(SQLException e){
            System.out.println("ERROR DE ID" + e);
        }
        return res;
    }
    
    public boolean altaPersona(Persona bean){
        boolean registroCorrecto = false;
       
        
        // conexion a la base de datos
        try {
             Connection conexion = ConexionBD.getConnection();
            // Preparar la consulta o sentencia SQL
              PreparedStatement ps ;
            ps = conexion.prepareStatement(addPersona);
            //Pasar los atributos del bean al PrepareStatement que contiene la sentencia SQL.
            ps.setString(1,bean.getNombre());
            ps.setString(2, bean.getaPaterno());
           
          
           
           
            registroCorrecto = ps.executeUpdate() == 1;
            
            ps.close();
            conexion.close();
        } catch (SQLException e) {
            System.err.println("ERROR INSERTAR PERSONA");
            e.printStackTrace();
        }
    return registroCorrecto;
    }
     public boolean altaUsuario(Usuario bean,String nombre){
        boolean registroCorrecto = false;
        int idpersona=ConsultaID();
       
        // conexion a la base de datos
        try {
             Connection conexion = ConexionBD.getConnection();
            // Preparar la consulta o sentencia SQL
              PreparedStatement ps ;
            ps = conexion.prepareStatement(addUsuario);
            //Pasar los atributos del bean al PrepareStatement que contiene la sentencia SQL.
            ps.setString(1,bean.getUsuario());
            ps.setString(2, bean.getContrasena());
            ps.setInt(3,idpersona);
           
          
           
           
            registroCorrecto = ps.executeUpdate() == 1;
            
            ps.close();
            conexion.close();
        } catch (SQLException e) {
            System.err.println("ERROR INSERTAR USUARIOS");
            e.printStackTrace();
        }
    return registroCorrecto;
    }
     public List<Usuario> ConsultaGeneral(){
      List<Usuario>lista= new ArrayList();
        

        try{
            Connection con = ConexionBD.getConnection();
            PreparedStatement ps = con.prepareStatement(ConsultaGeneral);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {   
              Usuario bean=new Usuario();
              Persona persona=new Persona();
              TipoUsuario tipo=new TipoUsuario();
              persona.setNombre(rs.getString(1));
              persona.setaPaterno(rs.getString(2));
              tipo.setNombreTipo(rs.getString(5));
              bean.setUsuario(rs.getString(3));
              bean.setContrasena(rs.getString(4));
              bean.setPersona(persona);
              bean.setTipoUsuario(tipo);
              
              lista.add(bean);
                
            }
         
           
            ps.close();
            con.close();
        }catch(SQLException e){
            System.out.println("ERROR DE ID" + e);
        }
        return lista;
    }
    public static void main(String[] args) {
        DaoUsuarios usuario=new DaoUsuarios();
        System.out.println("respuesta:----"+usuario.ConsultaGeneral());
    }
    
}


   
