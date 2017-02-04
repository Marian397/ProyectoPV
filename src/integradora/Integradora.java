/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package integradora;

import Daos.DaoCompras;
import Daos.ProductoDao;
import java.sql.SQLException;

/**
 *
 * @author Musica
 */
public class Integradora {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        // TODO code application logic here
        
//        Login login =new Login();
//        login.setLocationRelativeTo(null);
//        login.setVisible(true);

 
        ProductoDao productodao = new ProductoDao();
        
        ;
        
        for (int i = 0; i < productodao.readAllProducts().size() ; i++) {
            System.out.println("hola");
        }
      
      
        
                
    }
    
}
