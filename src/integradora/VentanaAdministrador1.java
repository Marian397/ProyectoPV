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
import org.jdesktop.swingx.autocomplete.*;

/**
 *
 * @author Musica
 */
public class VentanaAdministrador1 extends javax.swing.JFrame {
 
    /**
     * Creates new form VentanaAdministrador
     */
     
    JButton boton1;
     DaoAlmacen1 almacenDao = new DaoAlmacen1();
     double suma=0.0;
     double suma2=0.0;
     DaoAlmacen1 almacen=new DaoAlmacen1();
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
    DefaultTableModel almacen1 = new DefaultTableModel(new String[]{"No", "Código", "Nombre", "Precio", "Precio Neto", "Marca", "Existencias"}, 0);
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
           
    public VentanaAdministrador1() {
        initComponents();
     numero.setVisible(false);
        this.setTitle("SIGC(SISTEMA DE INVENTARIO GRUPO COLORS)");
        lista();
        listaMarca();
        listaRubro();
        listaVenta();
        productos();
        tablaAlmacen();
        llenarCategorias();
        AutoCompleteDecorator.decorate(cmbProducto01);
        AutoCompleteDecorator.decorate(cmbProducto);
        List lista = dao.consultarProducto();
        AutoCompleteDecorator.decorate(txtCantidad1, lista, rootPaneCheckingEnabled);
        
        AutoCompleteDecorator.decorate(cmbAlmacen3, ObjectToStringConverter.DEFAULT_IMPLEMENTATION);
        tabla=new DefaultTableModel(dato, cabecera);
        jTable1.setModel(tabla); setLayout(null);
         List<Usuario>Usuario;
         List marca=unProductoDao.readMarca();
         tabla=new DefaultTableModel(dato, cabecera2);
        tbUsuarios.setModel(tabla);
        DaoUsuarios dao=new DaoUsuarios();
         Usuario=dao.ConsultaGeneral(); 
        for (int i = 0; i < Usuario.size(); i++) {
                Object datos[]={Usuario.get(i).getPersona().getNombre()
                +"",Usuario.get(i).getPersona().getaPaterno()+""
                ,Usuario.get(i).getUsuario()+"",Usuario.get(i).getContrasena()+""
                 ,Usuario.get(i).getTipoUsuario().getNombreTipo()+""};
                ((DefaultTableModel)tbUsuarios.getModel()).addRow(datos); 
        }
         tablaReportesActual();
          jButton4.setVisible(false);
          btnCancelar.setVisible(false);
    } 
      //**********************************************************************************************
    public void productos() {
        List lista = dao.consultarProducto();
        for (int i = 0; i < lista.size(); i++) {
            cmbAlmacen3.addItem(lista.get(i));
        }
    }
    
        //**********************************************************************************************
    public void tablaReportesActual() {

        List<Venta> ventas = reportes.consultarVentas(mes);

        for (int i = 0; i < ventas.size() - 1; i++) {
            Venta v = (Venta) ventas.get(i);
            String[] venta = {(i + 1) + "", v.getFolioVenta() + "", v.getFechaHoraVenta() + " ", v.getTotalVenta() + ""};
            tablaVentas.addRow(venta);
        }

        List<Compra> compras = reportes.consultarCompras(mes);

        for (int i = 0; i < compras.size() - 1; i++) {
            Compra c = (Compra) compras.get(i);
            String[] compra = {(i + 1) + "", c.getFolioCompra() + "", c.getFechaHoraCompra() + "", c.getTotalCompra() + ""};
            tablaCompras.addRow(compra);
        }

    }

    //**********************************************************************************************
    
     //**********************************************************************************************

    public void consultarVentasMes() {
        String mesResultado = reportes.especificas(modeloVentas.getSelectedItem().toString(), (fecha.get(Calendar.YEAR)) + "-");
        List<Venta> ventas = reportes.consultarVentas(mesResultado);
        DefaultTableModel nuevoVentas = new DefaultTableModel(new String[]{"No", "Folio", "Fecha", "Total"}, 0);
        tablitaVentas1.setModel(nuevoVentas);

        for (int i = 0; i < ventas.size(); i++) {
            Venta v = (Venta) ventas.get(i);
            String[] venta = {(i + 1) + "", v.getFolioVenta() + "", v.getFechaHoraVenta() + "", "$" + v.getTotalVenta() + ""};
            nuevoVentas.addRow(venta);
        }

    }

    //**********************************************************************************************
    public void consultarComprasMes() {
        String mesResultado = reportes.especificas(modeloCompras.getSelectedItem().toString(), (fecha.get(Calendar.YEAR)) + "-");
        List<Compra> compras = reportes.consultarCompras(mesResultado);
        DefaultTableModel nuevoCompras = new DefaultTableModel(new String[]{"No", "Folio", "Fecha", "Total"}, 0);
        tablitaCompras1.setModel(nuevoCompras);

        for (int i = 0; i < compras.size(); i++) {
            Compra c = (Compra) compras.get(i);
            String[] venta = {(i + 1) + "", c.getFolioCompra() + "", c.getFechaHoraCompra() + "", "$" + c.getTotalCompra() + ""};
            nuevoCompras.addRow(venta);
        }

    }

    //**********************************************************************************************
    public void registrar() {
        DaoUsuarios dao = new DaoUsuarios();
        Usuario bean = new Usuario();
        Persona p = new Persona();
        List<Usuario> persona;
        String nombre = txtNombre.getText();
        String apellidos = txtApellidos.getText();
        String usuario = txtUsuarios.getText();
        String contrasena = txtContrasena.getText();
        bean.setUsuario(usuario);
        bean.setContrasena(contrasena);
        p.setNombre(nombre);
        p.setaPaterno(apellidos);
        dao.altaPersona(p);
        dao.altaUsuario(bean, nombre);
        persona = dao.ConsultaGeneral();
        tbUsuarios.add((Component) persona);
    }

    //**********************************************************************************************
    public void eliminar() {
        int cell;

        if (jTable1.getRowCount() > 0) {
            cell = jTable1.getSelectedRow();
            tabla.removeRow(cell);
        }
    }

    //**********************************************************************************************
    public void registrarProducto() {
        Producto p = new Producto();
        String cantidad = txtCantidad.getText();
        String cmb = cmbProducto.getSelectedItem().toString();
        p = dao.consultarPrecio(cmb);
        Object datos[] = {cantidad, cmb, p.getPrecio() + "", p.getPrecioNeto(), boton1 = new JButton()};

        ((DefaultTableModel) jTable1.getModel()).addRow(datos);
    }

    //**********************************************************************************************
    public void totalVenta() throws SQLException {
        String mesResultado = reportes.especificas(modeloVentas.getSelectedItem().toString(), (fecha.get(Calendar.YEAR)) + "-");
        lblMesVenta1.setText("");
        double total = reportes.consultarTotalVenta(mesResultado);
        lblMesVenta1.setText("$" + total + "");
    }

    //**********************************************************************************************
    public void totalCompra() throws SQLException {
        String mesResultado = reportes.especificas(modeloCompras.getSelectedItem().toString(), (fecha.get(Calendar.YEAR)) + "-");
        lblMesCompra1.setText("");
        double total = reportes.consultarTotalCompra(mesResultado);
        lblMesCompra1.setText("$" + total + "");
    }
    //**********************************************************************************************

   
    public void tablaAlmacen() {
        List<Object[]> productos = almacenDao.consultarProductos();
        for (int i = 0; i < productos.size() - 1; i++) {
            Object[] beans = (Object[]) productos.get(i);
            String[] producto = {(i + 1) + "", ((Producto) beans[0]).getCodigo() + "", ((Producto) beans[0]).getNombreProducto(), "$" + ((Producto) beans[0]).getPrecio() + "", "$" + ((Producto) beans[0]).getPrecioNeto() + "", ((Marca) beans[1]).getNombreMarca(), ((Producto) beans[0]).getExistencias() + ""};
            almacen1.addRow(producto);
            
        }
    }
    
   
    //**********************************************************************************************
    
    public void lista(){
        List lista=dao.consultarProducto();
        for (int i = 0; i <lista.size(); i++) {
          cmbProducto.addItem(lista.get(i));  
        }
        }
     
    //**********************************************************************************************
    
     //**********************************************************************************************
    
    public void listaVenta(){
        List lista=dao.consultarProducto();
        for (int i = 0; i <lista.size(); i++) {
          cmbProducto01.addItem(lista.get(i)); 
          
        }
        }
       public void listaMarca(){
        List lista=unProductoDao.readMarca();
        for (int i = 0; i <lista.size(); i++) {
          MarcaCombo2.addItem(lista.get(i));  
        }
        }
        
    //**********************************************************************************************
          public void listaRubro(){
        List lista=unProductoDao.readCategoria();
        for (int i = 0; i <lista.size(); i++) {
          CategoriaCombo2.addItem(lista.get(i));  
        }
        }
           //**********************************************************************************************
    public void consultarProductosNombre() {
        String n = cmbAlmacen3.getSelectedItem().toString();
        

        List<Object[]> productos = almacenDao.consultarProductoNombre(n);
        DefaultTableModel nuevoAlmacen = new DefaultTableModel(new String[]{"No", "Código", "Nombre", "Precio", "Precio Neto", "Marca", "Existencias"}, 0);
        tablitaAlmacen.setModel(nuevoAlmacen);

        for (int i = 0; i < productos.size(); i++) {

            Object[] beans = (Object[]) productos.get(i);
            System.out.println(((Producto) beans[0]).getNombreProducto());
            String[] producto = {(i + 1) + "", ((Producto) beans[0]).getCodigo() + "", ((Producto) beans[0]).getNombreProducto(), "$" + ((Producto) beans[0]).getPrecio() + "", "$" + ((Producto) beans[0]).getPrecioNeto() + "", ((Marca) beans[1]).getNombreMarca(), ((Producto) beans[0]).getExistencias() + ""};
            nuevoAlmacen.addRow(producto);
        }
    }

//**********************************************************************************************
    public void consultarProductosCategoria() {
        String categoria = cmbCatego.getSelectedItem().toString();

        List<Object[]> productos = almacenDao.consultarProductoEspecifico(categoria);
        DefaultTableModel nuevoAlmacen = new DefaultTableModel(new String[]{"No", "Código", "Nombre", "Precio", "Precio Neto", "Marca", "Existencias"}, 0);
        tablitaAlmacen.setModel(nuevoAlmacen);

        for (int i = 0; i < productos.size(); i++) {

            Object[] beans = (Object[]) productos.get(i);
            System.err.println(((Producto) beans[0]).getNombreProducto());
            String[] producto = {(i + 1) + "", ((Producto) beans[0]).getCodigo() + "", ((Producto) beans[0]).getNombreProducto(), "$" + ((Producto) beans[0]).getPrecio() + "", "$" + ((Producto) beans[0]).getPrecioNeto() + "", ((Marca) beans[1]).getNombreMarca(), ((Producto) beans[0]).getExistencias() + ""};
            nuevoAlmacen.addRow(producto);
        }
    }

    //**********************************************************************************************
    public void llenarCategorias() {
        List<Categoria> categorias = almacenDao.consultarCategoria();
        for (int i = 0; i < categorias.size(); i++) {
            cmbCatego.addItem(categorias.get(i).getNombreRubro());
        }
    }

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
        jPanel16 = new javax.swing.JPanel();
        jPanel23 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel24 = new javax.swing.JPanel();
        cmbVenta1 = new javax.swing.JComboBox();
        btnConsultaVentas1 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        lblMesVenta1 = new javax.swing.JLabel();
        jPanel25 = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        tablitaVentas1 = new javax.swing.JTable();
        jPanel26 = new javax.swing.JPanel();
        jPanel27 = new javax.swing.JPanel();
        cmbCompras1 = new javax.swing.JComboBox();
        btnConsultaCompras1 = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        lblMesCompra1 = new javax.swing.JLabel();
        jPanel28 = new javax.swing.JPanel();
        jScrollPane11 = new javax.swing.JScrollPane();
        tablitaCompras1 = new javax.swing.JTable();
        jPanel14 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jButton5 = new javax.swing.JButton();
        idNombreAgregar2 = new javax.swing.JTextField();
        idCodigoAgregar2 = new javax.swing.JTextField();
        idPrecioAgregar2 = new javax.swing.JTextField();
        idPrecioNetoAgregar2 = new javax.swing.JTextField();
        MarcaCombo2 = new javax.swing.JComboBox();
        CategoriaCombo2 = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        idCantidaAgregar2 = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel36 = new javax.swing.JLabel();
        BuscarProducto = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        jLabel37 = new javax.swing.JLabel();
        NombreBusqueda = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        CodigoBusqueda = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        PrecioBusqueda = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        PNBusqueda = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        CantidadBusqueda = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        MarcaBusquedaComb = new javax.swing.JComboBox();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jPanel13 = new javax.swing.JPanel();
        jLabel43 = new javax.swing.JLabel();
        idMarca = new javax.swing.JTextField();
        jButton11 = new javax.swing.JButton();
        jPanel17 = new javax.swing.JPanel();
        jLabel44 = new javax.swing.JLabel();
        idCategoria = new javax.swing.JTextField();
        jButton12 = new javax.swing.JButton();
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
        jPanel11 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        cmbProducto = new javax.swing.JComboBox();
        txtCantidad = new javax.swing.JTextField();
        btnRegistrar = new javax.swing.JButton();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnEliminar = new javax.swing.JButton();
        txtFolio = new javax.swing.JTextField();
        jLabel47 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        btnGuardarCompra = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jPanel15 = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        jPanel22 = new javax.swing.JPanel();
        txtContrasena = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();
        txtUsuarios = new javax.swing.JTextField();
        txtApellidos = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        jScrollPane6 = new javax.swing.JScrollPane();
        tbUsuarios = new javax.swing.JTable();
        btnEliminar2 = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        jPanel36 = new javax.swing.JPanel();
        jPanel37 = new javax.swing.JPanel();
        cmbAlmacen3 = new javax.swing.JComboBox();
        btnProductos3 = new javax.swing.JButton();
        jLabel52 = new javax.swing.JLabel();
        jScrollPane12 = new javax.swing.JScrollPane();
        tablitaAlmacen = new javax.swing.JTable();
        jPanel38 = new javax.swing.JPanel();
        cmbCatego = new javax.swing.JComboBox();
        btnCategoria = new javax.swing.JButton();
        jLabel53 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jcombo = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        numero = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(204, 204, 204));

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        jTabbedPane2.setBackground(new java.awt.Color(204, 204, 204));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Listado de Ventas"));

        jPanel24.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        cmbVenta1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cmbVenta1.setModel(modeloVentas);
        cmbVenta1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbVenta1ActionPerformed(evt);
            }
        });

        btnConsultaVentas1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnConsultaVentas1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/search.png"))); // NOI18N
        btnConsultaVentas1.setText("Consultar");
        btnConsultaVentas1.setToolTipText("");
        btnConsultaVentas1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultaVentas1ActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Mes:");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel13.setText("Total Mensual:");

        lblMesVenta1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMesVenta1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cmbVenta1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnConsultaVentas1, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                    .addComponent(lblMesVenta1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(12, Short.MAX_VALUE))
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel24Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(cmbVenta1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnConsultaVentas1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(lblMesVenta1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        tablitaVentas1.setModel(tablaVentas);
        jScrollPane9.setViewportView(tablitaVentas1);

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 643, Short.MAX_VALUE))
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jPanel26.setBorder(javax.swing.BorderFactory.createTitledBorder("Listado de Compras"));

        jPanel27.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        cmbCompras1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cmbCompras1.setModel(modeloCompras);
        cmbCompras1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbCompras1ActionPerformed(evt);
            }
        });

        btnConsultaCompras1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnConsultaCompras1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/search.png"))); // NOI18N
        btnConsultaCompras1.setText("Consultar");
        btnConsultaCompras1.setToolTipText("");
        btnConsultaCompras1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultaCompras1ActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel16.setText("Mes:");

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel17.setText("Total Mensual:");

        lblMesCompra1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMesCompra1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17)
                    .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel27Layout.createSequentialGroup()
                        .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbCompras1, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnConsultaCompras1, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(lblMesCompra1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel27Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(cmbCompras1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnConsultaCompras1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17)
                    .addComponent(lblMesCompra1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        tablitaCompras1.setModel(tablaCompras);
        jScrollPane11.setViewportView(tablitaCompras1);

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 728, Short.MAX_VALUE)
            .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 433, Short.MAX_VALUE))
        );
        jPanel28Layout.setVerticalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel26Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel26Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(195, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Reportes", new javax.swing.ImageIcon(getClass().getResource("/imagenes/reportes.png")), jPanel16); // NOI18N

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Agregar Producto"));

        jButton5.setBackground(new java.awt.Color(153, 153, 153));
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/add.png"))); // NOI18N
        jButton5.setText("Agregar");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        idPrecioAgregar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idPrecioAgregar2ActionPerformed(evt);
            }
        });

        idPrecioNetoAgregar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idPrecioNetoAgregar2ActionPerformed(evt);
            }
        });

        MarcaCombo2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MarcaCombo2ActionPerformed(evt);
            }
        });

        jLabel4.setText("Nombre");

        jLabel14.setText("Codigo");

        jLabel15.setText("Precio $");

        jLabel32.setText("Precio Neto $");

        jLabel33.setText("Cantidad");

        jLabel34.setText("Marca");

        jLabel35.setText("Categoria");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(idNombreAgregar2, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(idCodigoAgregar2, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(97, 97, 97)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(74, 74, 74)
                        .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(idPrecioAgregar2, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(idPrecioNetoAgregar2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(30, 30, 30)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 69, Short.MAX_VALUE)
                        .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(idCantidaAgregar2, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(MarcaCombo2, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(45, 45, 45)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(CategoriaCombo2, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton5)
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel32)
                            .addComponent(jLabel34)
                            .addComponent(jLabel15)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel4))
                            .addComponent(jLabel33, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel35, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 3, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(idNombreAgregar2)
                            .addComponent(idCodigoAgregar2)
                            .addComponent(idPrecioAgregar2)
                            .addComponent(idPrecioNetoAgregar2)
                            .addComponent(idCantidaAgregar2)
                            .addComponent(MarcaCombo2)
                            .addComponent(CategoriaCombo2))))
                .addContainerGap())
        );

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder("Modificar / Eliminar Producto"));

        jLabel36.setText("Elegir Producto Nombre/ Codigo");

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/search.png"))); // NOI18N
        jButton7.setText("Buscar");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jLabel37.setText("Nombre");

        jLabel38.setText("Codigo");

        jLabel39.setText("Precio $");

        PrecioBusqueda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PrecioBusquedaActionPerformed(evt);
            }
        });

        jLabel40.setText("Precio Neto $");

        PNBusqueda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PNBusquedaActionPerformed(evt);
            }
        });

        jLabel41.setText("Cantidad");

        jLabel42.setText("Marca");

        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/modificar.png"))); // NOI18N
        jButton8.setText("Editar");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/eliminar.png"))); // NOI18N
        jButton9.setText("Eliminar");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Captura2.png"))); // NOI18N
        jButton10.setText("Guardar");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel36)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(BuscarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton7)))
                        .addContainerGap(914, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(NombreBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGap(41, 41, 41)
                                .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(37, 37, 37))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(CodigoBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addComponent(PrecioBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(PNBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                                .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28)))
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                                .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(63, 63, 63)
                                .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addComponent(CantidadBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(MarcaBusquedaComb, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGap(91, 91, 91)
                                .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGap(58, 58, 58)
                                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(53, 53, 53)
                                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(78, 78, 78))))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel36)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addComponent(BuscarProducto)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel37)
                            .addComponent(jLabel38)
                            .addComponent(jLabel39)
                            .addComponent(jLabel42)
                            .addComponent(jLabel41)
                            .addComponent(jLabel40))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(NombreBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CodigoBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(PrecioBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(PNBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CantidadBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(MarcaBusquedaComb, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(37, 37, 37)
                        .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27))))
        );

        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder("Agregar Marca"));

        jLabel43.setText("Marca");

        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/registrar.png"))); // NOI18N
        jButton11.setText("Registrar");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(idMarca)
                    .addComponent(jLabel43, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE))
                .addGap(29, 29, 29)
                .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel43)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton11, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                    .addComponent(idMarca))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jPanel17.setBorder(javax.swing.BorderFactory.createTitledBorder("Agregar Categoria"));

        jLabel44.setText("Categoria");

        jButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/registrar.png"))); // NOI18N
        jButton12.setText("Registrar");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(idCategoria)
                    .addComponent(jLabel44, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE))
                .addGap(29, 29, 29)
                .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel44)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addComponent(idCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 9, Short.MAX_VALUE))
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jButton12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(144, 144, 144)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64)
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(42, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Productos", new javax.swing.ImageIcon(getClass().getResource("/imagenes/prod.png")), jPanel14); // NOI18N
        jPanel14.getAccessibleContext().setAccessibleDescription("");

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

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Compras"));

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Registral Compra"));

        cmbProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbProductoActionPerformed(evt);
            }
        });

        btnRegistrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/registrar.png"))); // NOI18N
        btnRegistrar.setText("Registrar");
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });

        jLabel48.setText("Cantidad");

        jLabel49.setText("Producto");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cmbProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(87, 87, 87)
                .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(94, 94, 94)
                .addComponent(jLabel49)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel48)
                .addGap(398, 398, 398))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel48)
                    .addComponent(jLabel49))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnRegistrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(cmbProducto))
                .addContainerGap())
        );

        jLabel1.setText("Folio:");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Cantidad", "Codigo", "Precio", "Importe"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jScrollPane2.setViewportView(jScrollPane1);

        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/eliminar.png"))); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        jLabel47.setText("Total:");

        txtTotal.setEditable(false);

        btnGuardarCompra.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Captura2.png"))); // NOI18N
        btnGuardarCompra.setText("Guardar Compra");
        btnGuardarCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarCompraActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(104, 104, 104)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jScrollPane2)
                        .addGap(4, 4, 4))
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(73, 73, 73)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFolio, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(94, 94, 94))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(675, 675, 675)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel47)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnGuardarCompra)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtFolio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(201, 201, 201))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel47)
                    .addComponent(btnGuardarCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(154, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 442, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Compras", new javax.swing.ImageIcon(getClass().getResource("/imagenes/images97.png")), jPanel11); // NOI18N

        jPanel21.setBorder(javax.swing.BorderFactory.createTitledBorder("Usuarios"));

        jPanel22.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Captura2.png"))); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        jLabel9.setText("Nombre");

        jLabel10.setText("Apellidos");

        jLabel11.setText("Usuarios");

        jLabel12.setText("Contraseña");

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addGap(113, 113, 113)
                        .addComponent(jLabel9)
                        .addGap(168, 168, 168)
                        .addComponent(jLabel10))
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(77, 77, 77)
                        .addComponent(txtApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 72, Short.MAX_VALUE)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addComponent(txtUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48)
                        .addComponent(txtContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 72, Short.MAX_VALUE)
                        .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(jLabel11)
                        .addGap(123, 123, 123)
                        .addComponent(jLabel12)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel22Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15))
        );

        jButton6.setText("Cancelar");

        tbUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Nombre", "Apellidos", "Usuario", "Contraseña", "tipo"
            }
        ));
        jScrollPane6.setViewportView(tbUsuarios);

        jScrollPane5.setViewportView(jScrollPane6);

        btnEliminar2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/eliminar.png"))); // NOI18N
        btnEliminar2.setText("Eliminar");
        btnEliminar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminar2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(105, 105, 105))
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane5))
                .addGap(55, 55, 55)
                .addComponent(btnEliminar2, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(443, Short.MAX_VALUE))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnEliminar2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Usuarios", new javax.swing.ImageIcon(getClass().getResource("/imagenes/users.png")), jPanel15); // NOI18N

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jPanel36.setBorder(javax.swing.BorderFactory.createTitledBorder("Almacen"));
        jPanel36.setPreferredSize(new java.awt.Dimension(1000, 431));

        jPanel37.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        cmbAlmacen3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbAlmacen3ActionPerformed(evt);
            }
        });

        btnProductos3.setText("Buscar Producto");
        btnProductos3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProductos3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel37Layout = new javax.swing.GroupLayout(jPanel37);
        jPanel37.setLayout(jPanel37Layout);
        jPanel37Layout.setHorizontalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel37Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cmbAlmacen3, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(btnProductos3, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel37Layout.setVerticalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel37Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbAlmacen3, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(btnProductos3, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                .addContainerGap())
        );

        jLabel52.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel52.setText("Consulta Producto Especifico");

        tablitaAlmacen.setModel(almacen1);
        jScrollPane12.setViewportView(tablitaAlmacen);

        jPanel38.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        cmbCatego.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cmbCatego.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbCategoActionPerformed(evt);
            }
        });

        btnCategoria.setText("Consultar");
        btnCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCategoriaActionPerformed(evt);
            }
        });

        jLabel53.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel53.setText("Consulta por Categoria");

        javax.swing.GroupLayout jPanel38Layout = new javax.swing.GroupLayout(jPanel38);
        jPanel38.setLayout(jPanel38Layout);
        jPanel38Layout.setHorizontalGroup(
            jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel38Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel53)
                        .addComponent(cmbCatego, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnCategoria, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel38Layout.setVerticalGroup(
            jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel38Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel53)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cmbCatego, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel36Layout = new javax.swing.GroupLayout(jPanel36);
        jPanel36.setLayout(jPanel36Layout);
        jPanel36Layout.setHorizontalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel36Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel36Layout.createSequentialGroup()
                        .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane12)
                            .addGroup(jPanel36Layout.createSequentialGroup()
                                .addComponent(jPanel37, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 567, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel38, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8))
                    .addGroup(jPanel36Layout.createSequentialGroup()
                        .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel36Layout.setVerticalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel36Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel52)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel37, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel38, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44))
        );

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(1148, Short.MAX_VALUE))
            .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel12Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel36, javax.swing.GroupLayout.PREFERRED_SIZE, 1218, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(30, Short.MAX_VALUE)))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap(366, Short.MAX_VALUE)
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
            .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel12Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel36, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(24, Short.MAX_VALUE)))
        );

        jTabbedPane2.addTab("Almacenes", new javax.swing.ImageIcon(getClass().getResource("/imagenes/almacenes.png")), jPanel12); // NOI18N

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
//        DaoVentas dao=new DaoVentas();
        ventaDetalle detalle=new ventaDetalle();
        Producto p=new Producto();
        int cantidad=Integer.parseInt(txtCantidad1.getText());
        String cmb=cmbProducto01.getSelectedItem().toString();
        System.out.println(""+cmb);
        p =dao.consultarPrecio(cmb);
//      if(dao.ProductoCantidad(cmb)<cantidad){
//      JOptionPane.showMessageDialog(this,"exitencias de este  producto"+dao.ProductoCantidad(cmb));
//      
//      }else
        {
      
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
        //dao.InsertarVentaDetalle(detalle);
        //dao.ModificarExistencias(detalle,cmb);
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
         String total=txtTotal.getText();
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

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
        DaoUsuarios dao=new DaoUsuarios();
        Usuario bean=new Usuario();
        Persona p=new Persona();
        List<Usuario>Usuario;
        String nombre=txtNombre.getText();
        String apellidos=txtApellidos.getText();
        String usuario=txtUsuarios.getText();
        String contrasena=txtContrasena.getText();
        bean.setUsuario(usuario);
        bean.setContrasena(contrasena);
        p.setNombre(nombre);
        p.setaPaterno(apellidos);
        dao.altaPersona(p);
        dao.altaUsuario(bean,nombre);
   
         Usuario=dao.ConsultaGeneral(); 
        
        DefaultTableModel nuevoVentas = new DefaultTableModel(new String[]{"Nombre", "Apellidos", "Usuario", "Contraseña","Tipo"}, 0);
        tbUsuarios.setModel(nuevoVentas);
        
           for (int i = 0; i < Usuario.size(); i++) {
                Object datos[]={Usuario.get(i).getPersona().getNombre()
                +"",Usuario.get(i).getPersona().getaPaterno()+""
                ,Usuario.get(i).getUsuario()+"",Usuario.get(i).getContrasena()+""
                 ,Usuario.get(i).getTipoUsuario().getNombreTipo()+""};
                ((DefaultTableModel)tbUsuarios.getModel()).addRow(datos); 
        }
        txtNombre.setText("");
        txtApellidos.setText("");
        txtContrasena.setText("");
        txtUsuarios.setText("");
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnEliminar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminar2ActionPerformed
        // TODO add your handling code here:
        
        int cell;
        
        if(tbUsuarios.getRowCount()>0){
           cell=tbUsuarios.getSelectedRow();
           tabla.removeRow(cell);
        }
    }//GEN-LAST:event_btnEliminar2ActionPerformed

    private void jcomboMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jcomboMouseClicked
        // TODO add your handling code here:
     
    }//GEN-LAST:event_jcomboMouseClicked

    private void cmbCompras1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbCompras1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbCompras1ActionPerformed

    private void btnConsultaCompras1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultaCompras1ActionPerformed
        consultarComprasMes();
        try {
            totalCompra();
        } catch (SQLException ex) {
            Logger.getLogger(VentanaAdministrador1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnConsultaCompras1ActionPerformed

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

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:s
        int cell;
CompraDetalle detalle =new CompraDetalle();
int cantidad=Integer.parseInt(txtCantidad.getText());
  String codigo=jTable1.getValueAt(jTable1.getSelectedRow(),1).toString();
        if(jTable1.getRowCount()>0){
            cell=jTable1.getSelectedRow();
         double can=Double.parseDouble(jTable1.getValueAt(cell,3 )+"");
            tipo.removeRow(cell);
            totalCompra=totalCompra-can;
            txtTotal.setText(""+totalCompra);
        }
       detalle.setCantidad(cantidad);
        dao.EliminarExistencias(detalle,codigo);
                

    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed

       String valida[]={"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p"
               ,"q","r","s","t","u","v","w","x","y","z","ñ","!",""
               + "#","$","%","&","/","(",")",";",":","*","?","=","_","'","~",".","[","]","+","-","}","{","",};
        for (int i = 0; i < valida.length; i++) {
             if(txtCantidad.getText().equals(valida[i])){
        
       JOptionPane.showMessageDialog(this,"introdusca numero");
       }
        }
        try {
        if(txtCantidad.getText().equals("")){ 
            JOptionPane.showMessageDialog(this,"Ingresa la cantidad");
        }else{
        DaoCompras dao=new DaoCompras();
        CompraDetalle detalle=new CompraDetalle();
        Producto p=new Producto();
        int cantidad=Integer.parseInt(txtCantidad.getText());
        String cmb=cmbProducto.getSelectedItem().toString();
        p =dao.consultarPrecio(cmb);
        
        suma= cantidad*(double)p.getPrecio();
        Object datos[]={cantidad,cmb,p.getPrecio(),suma};
        Suma.add(suma);
        tipo=((DefaultTableModel)jTable1.getModel());
        tipo.addRow(datos);
       
        totalCompra=totalCompra+suma; 
        txtTotal.setText(totalCompra+"");
        detalle.setCantidad(cantidad);
       // detalle.setProducto(p.getIdProducto());
        detalle.setTotalCompraD(suma);
        dao.InsertarCompraDetalle(detalle);
        dao.ModificarExistencias(detalle,cmb);
      
}     
        } catch (Exception e) {
        }
      
       
    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void cmbProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbProductoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbProductoActionPerformed

    private void btnConsultaVentas1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultaVentas1ActionPerformed
        consultarVentasMes();
        try {
            totalVenta();
        } catch (SQLException ex) {
            Logger.getLogger(VentanaAdministrador1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnConsultaVentas1ActionPerformed

    private void cmbVenta1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbVenta1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbVenta1ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        try {
        if(idNombreAgregar2.getText().equals("")&&idCodigoAgregar2.getText().equals("")&&idPrecioAgregar2.getText().equals("")&&idPrecioNetoAgregar2.getText().equals("")&&idCantidaAgregar2.getText().equals("")){
                    JOptionPane.showMessageDialog(this,"Los campos estan  vacios");
            }else{
                
            String valida[]={"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p"
               ,"q","r","s","t","u","v","w","x","y","z","ñ","!",""
               + "#","$","%","&","/","(",")",";",":","*","?","=","_","'","~",".","[","]","+","-","}","{","",};
        for (int i = 0; i < valida.length; i++) {
             if(idCantidaAgregar2.getText().equals(valida[i])&&idPrecioAgregar2.getText().equals(valida[i])&&idPrecioNetoAgregar2.getText().equals(valida[i])){
        
       JOptionPane.showMessageDialog(this,"introdusca numero");
       }else{   
        }
            
        Producto producto = new Producto();
        producto.setNombreProducto(idNombreAgregar2.getText());
        double precio = Double.parseDouble(idPrecioAgregar2.getText());
        producto.setPrecio(precio);
        producto.setPrecioNeto(Double.parseDouble(idPrecioNetoAgregar2.getText()));
        int existencias = Integer.parseInt(idCantidaAgregar2.getText());
        producto.setExistencias(existencias);
        producto.setCodigo(idCodigoAgregar2.getText());
        int marca = MarcaCombo2.getSelectedIndex() + 1;
        int Rubro = CategoriaCombo2.getSelectedIndex() + 1;
        producto.setMarca(marca);
        producto.setRubro(Rubro);

        if (unProductoDao.insertProducto(producto)) {
            JOptionPane.showMessageDialog(this, "Producto Insertado Correctamente");
            
      

          cmbProducto01.addItem(idCodigoAgregar2.getText()); 
                   
        idNombreAgregar2.setText("");
        idPrecioAgregar2.setText("");
        idPrecioNetoAgregar2.setText("");
        idCantidaAgregar2.setText("");
        idCodigoAgregar2.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Error al Insertar Producto");
        }
}
        }
            
        } catch (Exception e) {
        }
           
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void idPrecioAgregar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idPrecioAgregar2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idPrecioAgregar2ActionPerformed

    private void idPrecioNetoAgregar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idPrecioNetoAgregar2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idPrecioNetoAgregar2ActionPerformed

    private void MarcaCombo2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MarcaCombo2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MarcaCombo2ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        String IDModificar ;
        Producto aux = new Producto();
        if (BuscarProducto.getText().equals("")) {
            JOptionPane.showMessageDialog(this,"ingresa la busqueda");
        }else
        if (unProductoDao.readIndividualProducto(BuscarProducto.getText()).getIdProducto() == 0) {
            JOptionPane.showMessageDialog(this, "El producto no existe");
            BuscarProducto.setText("");
        } else {
            aux = unProductoDao.readIndividualProducto(BuscarProducto.getText());
            NombreBusqueda.setText(aux.getNombreProducto());
            CodigoBusqueda.setText(aux.getCodigo());
            PrecioBusqueda.setText(aux.getPrecio().toString());
            PNBusqueda.setText(aux.getPrecioNeto()+"");
            CantidadBusqueda.setText((float)aux.getExistencias()+"");
            System.out.println("*************** "+aux.getExistencias());
            MarcaBusquedaComb.addItem(aux.getMarca());
            IDModificar = aux.getCodigo();
            NombreBusqueda.setEditable(false);
            CodigoBusqueda.setEditable(false);
            PrecioBusqueda.setEditable(false);
            PNBusqueda.setEditable(false);
            CantidadBusqueda.setEditable(false);
            MarcaBusquedaComb.setEditable(false);

            BuscarProducto.setText("");

        }

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton7ActionPerformed

    private void PrecioBusquedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PrecioBusquedaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PrecioBusquedaActionPerformed

    private void PNBusquedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PNBusquedaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PNBusquedaActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        NombreBusqueda.setEditable(true);
        CodigoBusqueda.setEditable(true);
        PrecioBusqueda.setEditable(true);
        PNBusqueda.setEditable(true);
        CantidadBusqueda.setEditable(true);
        MarcaBusquedaComb.setEditable(true);

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        if (unProductoDao.deleteProducto(IDModificar)) {
            JOptionPane.showMessageDialog(this, "Producto Eliminado Correctamente");
            NombreBusqueda.setText("");
            PrecioBusqueda.setText("");
            PNBusqueda.setText("");
            CantidadBusqueda.setText("");
            CodigoBusqueda.setText("");
            MarcaBusquedaComb.removeAllItems();

        } else {
            JOptionPane.showMessageDialog(this, "Error al Eliminar Producto");

        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        
        if (CodigoBusqueda.getText().equals("")&&NombreBusqueda.getText().equals("") && PrecioBusqueda.getText().equals("")&& PNBusqueda.getText().equals("")&&CantidadBusqueda.getText().equals("") ) {
            JOptionPane.showMessageDialog(this,"ingrese datos a modificar");
        }else{ 
        Producto producto = new Producto();
        producto.setNombreProducto(NombreBusqueda.getText());
        double precio = Double.parseDouble(PrecioBusqueda.getText());
        producto.setPrecio(precio);
        producto.setPrecioNeto(Double.parseDouble(PNBusqueda.getText()));
        float existencias = Float.parseFloat(CantidadBusqueda.getText());
        producto.setExistencias(existencias);
        producto.setCodigo(CodigoBusqueda.getText());
        int marca = MarcaBusquedaComb.getSelectedIndex() + 1;

        producto.setMarca(marca);
        producto.setCodigo(CodigoBusqueda.getText());

        if (unProductoDao.updateProducto(producto)) {
            JOptionPane.showMessageDialog(this, "Producto Modificado Correctamente");
            NombreBusqueda.setText("");
            PrecioBusqueda.setText("");
            PNBusqueda.setText("");
            CantidadBusqueda.setText("");
            CodigoBusqueda.setText("");
            MarcaBusquedaComb.removeAllItems();

        } else {
            JOptionPane.showMessageDialog(this, "Error al Modificar Producto");
        }        // TODO add your handling code here:
        }
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed

        if (idCategoria.getText().equals("")) {
            JOptionPane.showMessageDialog(this,"Ingresa la Categoria");
        }else {
        Categoria rubro = new Categoria();
        rubro.setNombreRubro(idCategoria.getText());
        if (unProductoDao.insertRubro(rubro)) {
            JOptionPane.showMessageDialog(this, "Categria Insertada Correctamente");
        } else {
            JOptionPane.showMessageDialog(this, "Error al Insertar Categoria");
        }}
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton12ActionPerformed

    private void btnGuardarCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarCompraActionPerformed
        // TODO add your handling code here:
         String folio=txtfolio.getText();
         String total=txtTotal.getText();
        if(folio.equals("")&&total.equals("")){
            JOptionPane.showMessageDialog(this,"El folio o el total se encuentra vacios");
        }else{
        Compra compra=new Compra();
        compra.setTotalCompra(totalCompra);
        compra.setFolioCompra(folio);
        dao.altaCompra(compra);
        dao.ModificarId();
        System.out.println(""+dao.ModificarId());
        
    }    
    }//GEN-LAST:event_btnGuardarCompraActionPerformed

    private void cmbAlmacen3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbAlmacen3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbAlmacen3ActionPerformed

    private void btnProductos3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProductos3ActionPerformed
        consultarProductosNombre();
    }//GEN-LAST:event_btnProductos3ActionPerformed

    private void cmbCategoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbCategoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbCategoActionPerformed

    private void btnCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCategoriaActionPerformed
        consultarProductosCategoria();
    }//GEN-LAST:event_btnCategoriaActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
         DefaultTableModel nuevocompra = new DefaultTableModel(new String[]{"Cantidad", "Codigo", "Precio", "Importe"}, 0);
        jTable1.setModel(nuevocompra);
        txtTotal.setText("0.0");
        txtFolio.setText("");
        txtCantidad.setText("");
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        Marca marca = new Marca();
        marca.setNombreMarca(idMarca.getText());
        if(idMarca.getText().equals("")) {
            JOptionPane.showMessageDialog(this,"Ingresar Marca");
        }else
        if (unProductoDao.insertMarca(marca)) {
            JOptionPane.showMessageDialog(this, "Marca Insertada Correctamente");
        } else {
            JOptionPane.showMessageDialog(this, "Error al Insertar Marca");
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton11ActionPerformed

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
            java.util.logging.Logger.getLogger(VentanaAdministrador1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaAdministrador1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaAdministrador1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaAdministrador1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaAdministrador1().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField BuscarProducto;
    private javax.swing.JTextField CantidadBusqueda;
    private javax.swing.JComboBox CategoriaCombo2;
    private javax.swing.JTextField CodigoBusqueda;
    private javax.swing.JComboBox MarcaBusquedaComb;
    private javax.swing.JComboBox MarcaCombo2;
    private javax.swing.JTextField NombreBusqueda;
    private javax.swing.JTextField PNBusqueda;
    private javax.swing.JTextField PrecioBusqueda;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnCategoria;
    private javax.swing.JButton btnConsultaCompras1;
    private javax.swing.JButton btnConsultaVentas1;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnEliminar1;
    private javax.swing.JButton btnEliminar2;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnGuardarCompra;
    private javax.swing.JButton btnProductos3;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JButton btnRegistrar1;
    private javax.swing.JComboBox cmbAlmacen3;
    private javax.swing.JComboBox cmbCatego;
    private javax.swing.JComboBox cmbCompras1;
    private javax.swing.JComboBox cmbProducto;
    private javax.swing.JComboBox cmbProducto01;
    private javax.swing.JComboBox cmbVenta1;
    private javax.swing.JTextField idCantidaAgregar2;
    private javax.swing.JTextField idCategoria;
    private javax.swing.JTextField idCodigoAgregar2;
    private javax.swing.JTextField idMarca;
    private javax.swing.JTextField idNombreAgregar2;
    private javax.swing.JTextField idPrecioAgregar2;
    private javax.swing.JTextField idPrecioNetoAgregar2;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel36;
    private javax.swing.JPanel jPanel37;
    private javax.swing.JPanel jPanel38;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    public static javax.swing.JComboBox jcombo;
    private javax.swing.JLabel lblMesCompra1;
    private javax.swing.JLabel lblMesVenta1;
    public static javax.swing.JTextField numero;
    private javax.swing.JTable tablitaAlmacen;
    private javax.swing.JTable tablitaCompras1;
    private javax.swing.JTable tablitaVentas1;
    private javax.swing.JTable tbUsuarios;
    private javax.swing.JTextField txtApellidos;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtCantidad1;
    private javax.swing.JTextField txtContrasena;
    private javax.swing.JTextField txtFolio;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtTotal;
    private javax.swing.JTextField txtUsuarios;
    private javax.swing.JTextField txtfolio;
    private javax.swing.JTextField txttotal2;
    // End of variables declaration//GEN-END:variables
}
