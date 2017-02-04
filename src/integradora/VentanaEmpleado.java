/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package integradora;

import Beans.Compra;
import Beans.CompraDetalle;
import Beans.Marca;
import Daos.DaoCompras;
import Beans.Producto;
import Beans.Usuario;
import Beans.Persona;
import Beans.Categoria;
import Beans.Venta;
import Beans.ventaDetalle;
import Daos.DaoAlmacen;
import Daos.DaoAlmacen1;
import Daos.DaoReportes;
import Daos.DaoUsuarios;
import Daos.DaoVentas;
import Daos.ProductoDao;
import java.awt.Button;
import java.awt.Component;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;


/**
 *
 * @author Musica
 */
public class VentanaEmpleado extends javax.swing.JFrame {
 
    /**
     * Creates new form VentanaAdministrador
     */
     
    JButton boton1;
     DaoAlmacen1 almacenDao = new DaoAlmacen1();
     double suma=0.0;
     double suma2=0.0;
     
     List Suma =new ArrayList();
      List Suma2 =new ArrayList();
    DefaultTableModel tabla;
    DaoCompras dao=new DaoCompras();
    DaoVentas da02 = new DaoVentas();
    Button btn=new Button();
     String dato[][]={};
     Double cant[];
     Double cant2[];
    ProductoDao unProductoDao = new ProductoDao();
    int IDModificar = 0;
   
    Double totalCompra=0.0;
     Double totalVenta=0.0;
    String[] meses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
    DaoReportes reportes = new DaoReportes();
    DefaultTableModel almacen = new DefaultTableModel(new String[]{"No", "Código", "Nombre", "Precio", "Precio Neto", "Marca", "Existencias"}, 0);
    DefaultTableModel tablaVentas = new DefaultTableModel(new String[]{"No", "Folio", "Fecha", "Total"}, 0);
    DefaultTableModel tablaCompras = new DefaultTableModel(new String[]{"No", "Folio", "Fecha", "Total"}, 0);
    DefaultComboBoxModel modeloVentas = new DefaultComboBoxModel(meses);
    DefaultComboBoxModel modeloCompras = new DefaultComboBoxModel(meses);
      DefaultTableModel tipo= new DefaultTableModel();
    DefaultTableModel tipo2= new DefaultTableModel();
    String datoR[][] = {};

    String cabeceraR[] = {"Cantidad", "Codigo", "Precio", "Importe", "eliminar"};
    String cabeceraV[] = {"Cantidad", "Codigo", "Precio", "Importe", "eliminar"};
    Calendar fecha = Calendar.getInstance();
    String mes = (fecha.get(Calendar.YEAR)) + "-0" + ((fecha.get(Calendar.MONTH)) + 1);

    //**********************************************************************************************
    
     String cabecera[]={"Cantidad","Codigo","Precio","Importe"};
     String cabecera2[]={"Nombre","Apellidos","Usuario","Contraseña","Tipo"};
           
    public VentanaEmpleado() {
        initComponents();
     numero.setVisible(false);
        this.setTitle("SIGC(SISTEMA DE INVENTARIO GRUPO COLORS)");
        AutoCompleteDecorator.decorate(cmbProducto01);

        tabla=new DefaultTableModel(dato, cabecera);
        jTable2.setModel(tabla); setLayout(null);
         List<Usuario>Usuario;
         List marca=unProductoDao.readMarca();
         tabla=new DefaultTableModel(dato, cabecera2);
         listaVenta();
         jButton4.setVisible(false);
    } 
      //**********************************************************************************************

        //**********************************************************************************************
  
    //**********************************************************************************************
    
     //**********************************************************************************************

    

    //**********************************************************************************************
    
    //**********************************************************************************************
   
    //**********************************************************************************************



    //**********************************************************************************************

    //**********************************************************************************************

    //**********************************************************************************************

   
    public void tablaAlmacen() {
        List<Object[]> productos = almacenDao.consultarProductos();
        for (int i = 0; i < productos.size() - 1; i++) {
            Object[] beans = (Object[]) productos.get(i);
            String[] producto = {(i + 1) + "", ((Producto) beans[0]).getCodigo() + "", ((Producto) beans[0]).getNombreProducto(), "$" + ((Producto) beans[0]).getPrecio() + "", "$" + ((Producto) beans[0]).getPrecioNeto() + "", ((Marca) beans[1]).getNombreMarca(), ((Producto) beans[0]).getExistencias() + ""};
            almacen.addRow(producto);
        }
    }
    
   
    //**********************************************************************************************
    
    //**********************************************************************************************
    
     //**********************************************************************************************
    
    public void listaVenta(){
        List lista=dao.consultarProducto();
        for (int i = 0; i <lista.size(); i++) {
          cmbProducto01.addItem(lista.get(i)); 
          
        }
        }
    
    //**********************************************************************************************
      //**********************************************************************************************
   
//**********************************************************************************************
   
    //**********************************************************************************************

    //**********************************************************************************************
   
    
            
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel9 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        cmbProducto01 = new javax.swing.JComboBox();
        txtCantidad1 = new javax.swing.JTextField();
        btnRegistrar1 = new javax.swing.JButton();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txttotal2 = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        btnEliminar1 = new javax.swing.JButton();
        txtfolio = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jcombo = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        numero = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(204, 204, 204));

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        jTabbedPane2.setBackground(new java.awt.Color(204, 204, 204));

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Ventas"));

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder("Registrar Venta"));

        cmbProducto01.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbProducto01ActionPerformed(evt);
            }
        });

        btnRegistrar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/add.png"))); // NOI18N
        btnRegistrar1.setText("Agregar");
        btnRegistrar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrar1ActionPerformed(evt);
            }
        });

        jLabel45.setText("Cantidad");

        jLabel46.setText("Producto");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(cmbProducto01, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtCantidad1, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(100, 100, 100)
                .addComponent(btnRegistrar1, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGap(124, 124, 124)
                .addComponent(jLabel46)
                .addGap(313, 313, 313)
                .addComponent(jLabel45)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel46, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel45))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(txtCantidad1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(cmbProducto01, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnRegistrar1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
        );

        jLabel5.setText("Folio:");

        jLabel6.setText("Total:");

        txttotal2.setEnabled(false);

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Captura2.png"))); // NOI18N
        jButton3.setText("Guardar Venta");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Cancelar");

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cantidad", "Codigo", "Nombre", "Precio", "Importe"
            }
        ));
        jScrollPane4.setViewportView(jTable2);

        jScrollPane3.setViewportView(jScrollPane4);

        btnEliminar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/eliminar.png"))); // NOI18N
        btnEliminar1.setText("Eliminar");
        btnEliminar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminar1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtfolio, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(67, 67, 67))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txttotal2, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3))
                        .addGap(258, 258, 258))))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane3))
                .addGap(27, 27, 27)
                .addComponent(btnEliminar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(160, 160, 160))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtfolio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminar1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txttotal2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(114, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Ventas", new javax.swing.ImageIcon(getClass().getResource("/imagenes/ventas.png")), jPanel9); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1263, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jLabel3.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 51, 51));
        jLabel3.setText("Sistema Inventario Grupo Colors");

        jcombo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jcomboMouseClicked(evt);
            }
        });
        jcombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcomboActionPerformed(evt);
            }
        });

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/colors2.PNG"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addComponent(numero, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jLabel2)
                .addGap(150, 150, 150)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 575, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jcombo, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(72, 72, 72))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1272, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jcombo, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel7)
                                .addComponent(jLabel3))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(numero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(8, 8, 8)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmbProducto01ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbProducto01ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbProducto01ActionPerformed

    private void btnRegistrar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrar1ActionPerformed
        // TODO add your handling code here:
        
         String valida[]={"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p"
               ,"q","r","s","t","u","v","w","x","y","z","ñ","!",""
               + "#","$","%","&","/","(",")",";",":","*","?","=","_","'","~",".","[","]","+","-","}","{","",};
        for (int i = 0; i < valida.length; i++) {
             if(txtCantidad1.getText().equals(valida[i])){
        
       JOptionPane.showMessageDialog(this,"introdusca numero");
       }
        }
        try {
        if(txtCantidad1.getText().equals("")){ 
            JOptionPane.showMessageDialog(this,"Ingresa la cantidad");
        }else{
        DaoVentas dao=new DaoVentas();
        ventaDetalle detalle=new ventaDetalle();
        Producto p=new Producto();
        int cantidad=Integer.parseInt(txtCantidad1.getText());
        String cmb=cmbProducto01.getSelectedItem().toString();
        System.out.println(""+cmb);
        p =dao.consultarPrecio(cmb);
      if(dao.ProductoCantidad(cmb)<cantidad){
      JOptionPane.showMessageDialog(this,"exitencias de este  producto"+dao.ProductoCantidad(cmb));
      
      }else{
      
        suma2= cantidad*(double)p.getPrecio();
        Object datos[]={cantidad,cmb,p.getNombreProducto(),p.getPrecio(),suma2};
        Suma2.add(suma2);
        tipo2=((DefaultTableModel)jTable2.getModel());
        tipo2.addRow(datos);
        totalVenta=totalVenta+suma2; 
        txttotal2.setText(totalVenta+"");
        detalle.setCantidad(cantidad);
       // detalle.setProducto(p.getIdProducto());
        detalle.setTotalVentaD(totalVenta);
        dao.InsertarVentaDetalle(detalle);
        dao.ModificarExistencias(detalle,cmb);
      }
}     
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnRegistrar1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        DaoVentas dao=new DaoVentas();
        Usuario usuario=new Usuario();
         String folio=txtfolio.getText();
         String total=txttotal2.getText();
        if(folio.equals("")&&total.equals("")){
            JOptionPane.showMessageDialog(this,"El folio o el total se encuentra vacios");
        }else{
            
        Venta venta=new Venta();
        venta.setTotalVenta(totalVenta);
        venta.setFolioVenta(folio);
        venta.setUsuario(Integer.parseInt(numero.getText()));
        dao.altaVenta(venta);
        dao.ModificarId();
        System.out.println(""+dao.ModificarId());
        
    }    
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void btnEliminar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminar1ActionPerformed
        // TODO add your handling code here:
     ventaDetalle detalle =new ventaDetalle();
        int cantidad=Integer.parseInt(txtCantidad1.getText());
        int cell;
        String codigo=jTable2.getValueAt(jTable2.getSelectedRow(),1).toString();
        if(jTable2.getRowCount()>0){
            cell=jTable2.getSelectedRow();
         double can=Double.parseDouble(jTable2.getValueAt(cell,3 )+"");
            tipo2.removeRow(cell);
            totalVenta=totalVenta+can;
           
            txttotal2.setText(""+totalVenta);
        }
        detalle.setCantidad(cantidad);
         da02.EliminarExistencias(detalle, codigo);
          
    }//GEN-LAST:event_btnEliminar1ActionPerformed

    private void jcomboMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jcomboMouseClicked
        // TODO add your handling code here:
     
    }//GEN-LAST:event_jcomboMouseClicked

    private void jcomboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcomboActionPerformed
        // TODO add your handling code here:
        int opc =jcombo.getSelectedIndex();
        Login l=new Login();
          if(opc==1){
         int i = JOptionPane.showConfirmDialog(this,"Realmente quieres salir","confirmar Salida",JOptionPane.YES_NO_OPTION);
       
              if(i==0){
            l.setVisible(true);
            this.setVisible(false);
        }
            }else{
            jcombo.setSelectedIndex(0);
        }
  
         
    }//GEN-LAST:event_jcomboActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VentanaEmpleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaEmpleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaEmpleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaEmpleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaEmpleado().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEliminar1;
    private javax.swing.JButton btnRegistrar1;
    private javax.swing.JComboBox cmbProducto01;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTable jTable2;
    public static javax.swing.JComboBox jcombo;
    public static javax.swing.JTextField numero;
    private javax.swing.JTextField txtCantidad1;
    private javax.swing.JTextField txtfolio;
    private javax.swing.JTextField txttotal2;
    // End of variables declaration//GEN-END:variables
}
