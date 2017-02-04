/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Daos;

import Conexion.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Beans.Usuario;

/**
 *
 * @author Musica
 */
public class DaoLogin {

    public Usuario Login(Usuario usuario) {
        Usuario user = new Usuario();
        try {
            String query = "SELECT * FROM usuario WHERE usuario= ? and contrasena= ? and estatus = 1";

            Connection con = ConexionBD.getConnection();
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, usuario.getUsuario());
            ps.setString(2, usuario.getContrasena());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user.setIdUsuario(rs.getInt("idUsuario"));
                user.setUsuario(rs.getString("usuario"));
                user.setContrasena(rs.getString("contrasena"));
                user.setNombre(rs.getString("nombre"));
                user.setTipoUsuario(rs.getInt("tipoUsuario"));
                user.setEstatus(rs.getInt("estatus"));
            }else{
                user = null; 
            }

            rs.close();
            ps.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("ERROR al intentar iniciar sesión " + e);
        }
        return user;
    }
}
