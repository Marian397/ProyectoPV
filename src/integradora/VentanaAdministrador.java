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
import Beans.Precio;
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
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import static java.awt.image.ImageObserver.WIDTH;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;
import utilerias.Mayusculas;
//import org.jdesktop.swingx.autocomplete.*;

/**
 *
 * @author Musica
 */
public final class VentanaAdministrador extends javax.swing.JFrame {

    /**
     * Creates new form VentanaAdministrador
     */
    JButton boton1;
    DaoAlmacen1 almacenDao = new DaoAlmacen1();
    double suma = 0.0;
    double suma2 = 0.0;
    DaoAlmacen1 almacen = new DaoAlmacen1();
    List Suma = new ArrayList();
    List Suma2 = new ArrayList();
    DefaultTableModel tabla;
    DaoCompras dao = new DaoCompras();
    DaoVentas da02 = new DaoVentas();
    Button btn = new Button();
    String dato[][] = {};
    Double cant[];
    Double cant2[];
    ProductoDao productoDao = new ProductoDao();
    int IDModificar = 0;
    Double totalCompra = 0.0;
    Double totalVenta = 0.0;
    String[] meses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
    DaoReportes reportes = new DaoReportes();
    DefaultTableModel model = new DefaultTableModel(new String[]{"No", "Descripcion", "Precio", "Cantidad"}, 0);
    DefaultTableModel tablaVentas = new DefaultTableModel(new String[]{"Cantidad", "Producto", "Precio", "Importe"}, 0);
    DefaultTableModel tablaCompras = new DefaultTableModel(new String[]{"No", "Folio", "Fecha", "Total"}, 0);
    public DefaultTableModel existProductModel = new DefaultTableModel(new String[]{"No", "Producto", "Precio Compra", "Precio Venta", "Precio Mayoreo", "Precio Medio Mayoreo", "Existencias"}, 0);
    DefaultTableModel modeloVenta = new DefaultTableModel(new String[]{"No", "Cantidad", "Producto", "Precio", "Importe"}, 0);
    DefaultComboBoxModel modeloVentas = new DefaultComboBoxModel(meses);
    DefaultComboBoxModel modeloCompras = new DefaultComboBoxModel(meses);
    DefaultTableModel tipo = new DefaultTableModel();
    DefaultTableModel tipo2 = new DefaultTableModel();
    String datoR[][] = {};
    String cabeceraR[] = {"Cantidad", "Codigo", "Precio", "Importe", "eliminar"};
    String cabeceraV[] = {"Cantidad", "Codigo", "Precio", "Importe", "eliminar"};
    Calendar fecha = Calendar.getInstance();
    String mes = (fecha.get(Calendar.YEAR)) + "-0" + ((fecha.get(Calendar.MONTH)) + 1);
    //**********************************************************************************************
    String cabecera[] = {"Cantidad", "Codigo", "Precio", "Importe"};
    String cabecera2[] = {"Nombre", "Apellidos", "Usuario", "Contraseña", "Tipo"};

    public VentanaAdministrador(DefaultTableModel existProductModel,int producto,float cantidad) {



    }

    public VentanaAdministrador() {

        initComponents();
        nombreProductoTxt.setDocument(new Mayusculas());
        modificarTabla();
        consultarProductosRegistrados(existProductModel);
        tabla_productos.editingStopped(null);
        tabla_productos.setEnabled(false);


        // this.setTitle("SIGC(SISTEMA DE INVENTARIO GRUPO COLORS)");
//        lista();
//        productos();
//        // AutoCompleteDecorator.decorate(cmbProducto01);
//        // AutoCompleteDecorator.decorate(cmbProducto);
//        List lista = dao.consultarProducto();
        // AutoCompleteDecorator.decorate(txtCantidad1, lista, rootPaneCheckingEnabled);

        //  AutoCompleteDecorator.decorate(cmbAlmacen3, ObjectToStringConverter.DEFAULT_IMPLEMENTATION);
        // tabla = new DefaultTableModel(dato, cabecera);
        //productos2.setModel(tabla);
//        setLayout(null);
//        List<Usuario> Usuario;
//        List marca = productoDao.readMarca();
//        tabla = new DefaultTableModel(dato, cabecera2);
//        tbUsuarios.setModel(tabla);
//        DaoUsuarios dao = new DaoUsuarios();
//        Usuario = dao.ConsultaGeneral();
//        for (int i = 0; i < Usuario.size(); i++) {
//            Object datos[] = {Usuario.get(i).getPersona().getNombre()
//                + "", Usuario.get(i).getPersona().getaPaterno() + "", Usuario.get(i).getUsuario() + "", Usuario.get(i).getContrasena() + "", Usuario.get(i).getTipoUsuario().getNombreTipo() + ""};
//            ((DefaultTableModel) tbUsuarios.getModel()).addRow(datos);
//        }
//        tablaReportesActual();
//        jButton4.setVisible(false);
//        btnCancelar.setVisible(false);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.setResizable(false);

    }

    public VentanaAdministrador(int producto) {
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

        if (productos2.getRowCount() > 0) {
            cell = productos2.getSelectedRow();
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

        ((DefaultTableModel) productos2.getModel()).addRow(datos);
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

    public void lista() {
        List lista = dao.consultarProducto();
        for (int i = 0; i < lista.size(); i++) {
            cmbProducto.addItem(lista.get(i));
        }
    }

    //**********************************************************************************************
    //**********************************************************************************************
    public void listaVenta() {
        List lista = dao.consultarProducto();
        for (int i = 0; i < lista.size(); i++) {
            cmbProducto01.addItem(lista.get(i));

        }
    }

    public void listaMarca() {
        List lista = productoDao.readMarca();
        for (int i = 0; i < lista.size(); i++) {
            //  MarcaCombo2.addItem(lista.get(i));  
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
    String numberFormat[] = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "ñ", "!"
        + "#", "$", "%", "&", "/", "(", ")", ";", ":", "*", "?", "=", "_", "'", "~", "[", "]", "+", "-", "}", "{"};
    //**********************************************************************************************

    //$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$(SEMILLAS)$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
    //LISTA DE CATEGORIAS
//    public void consultarCategorias() {
//        List<Categoria> categorias = productoDao.readCategoria();
//        for (int i = 0; i < categorias.size(); i++) {
//            Categoria categoria = categorias.get(i);
//        }
//    }
    //**********************************************************************************************
    //  TABLA DE PRODUCTOS REGISTRADOS
    public void consultarProductosRegistrados(DefaultTableModel existProductModel) {

        //LIMPIAR LA TABLA, REMOVIENDO SUS FILAS
        while (existProductModel.getRowCount() > 0) {
            existProductModel.removeRow(0);
        }

        //CONSULTAR LOS PRODUCTOS REGISTRADOS Y AGREGARLOS AL MODELO
        List<Object[]> productos = productoDao.readAllProducts();


        for (int i = 0; i < productos.size(); i++) {
            Object[] producto_precios = (Object[]) productos.get(i);
            String[] producto = {(i + 1) + "", ((Producto) producto_precios[0]).getNombreProducto(), "$ " + ((Precio) producto_precios[1]).getPrecioCompra(), "$ " + ((Precio) producto_precios[1]).getPrecioUnidad(), "$ " + ((Precio) producto_precios[1]).getMayoreo(), "$ " + ((Precio) producto_precios[1]).getMedioMayoreo(), ((Producto) producto_precios[0]).getCantidad() + ""};
            existProductModel.addRow(producto);
            String[] listaProducto = {(i + 1) + "", ((Producto) producto_precios[0]).getNombreProducto()};
        }
    }
    
    
        //  TABLA DE PRODUCTOS REGISTRADOS
    public void agregarProductosVenta(DefaultTableModel existProductModel) {

        //CONSULTAR LOS PRODUCTOS REGISTRADOS Y AGREGARLOS AL MODELO
        List<Object[]> productos = productoDao.readAllProducts();


        for (int i = 0; i < productos.size(); i++) {
            Object[] producto_precios = (Object[]) productos.get(i);
            String[] producto = {(i + 1) + "", ((Producto) producto_precios[0]).getNombreProducto(), "$ " + ((Precio) producto_precios[1]).getPrecioCompra(), "$ " + ((Precio) producto_precios[1]).getPrecioUnidad(), "$ " + ((Precio) producto_precios[1]).getMayoreo(), "$ " + ((Precio) producto_precios[1]).getMedioMayoreo(), ((Producto) producto_precios[0]).getCantidad() + ""};
            existProductModel.addRow(producto);
            String[] listaProducto = {(i + 1) + "", ((Producto) producto_precios[0]).getNombreProducto()};
        }
    }

    //**********************************************************************************************
    private void filter(String producto) {
        TableRowSorter<DefaultTableModel> tablerow = new TableRowSorter<DefaultTableModel>(existProductModel);
        tabla_productos.setRowSorter(tablerow);
        tablerow.setRowFilter(RowFilter.regexFilter(producto));

    }

    public void modificarTabla() {
        TableColumnModel columnModel = tabla_productos.getColumnModel();

        //AJUSTAR EL TAMAÑO DE LAS COLUMNAS DE LA TABLA
        columnModel.getColumn(0).setPreferredWidth(50);
        columnModel.getColumn(1).setPreferredWidth(300);
        columnModel.getColumn(2).setPreferredWidth(80);
        columnModel.getColumn(3).setPreferredWidth(80);
        columnModel.getColumn(4).setPreferredWidth(80);
        columnModel.getColumn(5).setPreferredWidth(100);
        columnModel.getColumn(6).setPreferredWidth(80);

    }

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
        jPanel9 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        cmbProducto01 = new javax.swing.JComboBox<Producto>();
        txtCantidad1 = new javax.swing.JTextField();
        btnRegistrar1 = new javax.swing.JButton();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txttotal2 = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        btnEliminar1 = new javax.swing.JButton();
        txtfolio = new javax.swing.JTextField();
        jPanel11 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        eliminarProductoBtn1 = new javax.swing.JButton();
        jPanel13 = new javax.swing.JPanel();
        registrarPreciosPeso = new javax.swing.JButton();
        jPanel19 = new javax.swing.JPanel();
        agregarProductoBtn1 = new javax.swing.JButton();
        jPanel20 = new javax.swing.JPanel();
        precioCuartoTxt3 = new javax.swing.JTextField();
        precioCuartoTxt2 = new javax.swing.JTextField();
        precioCuartoTxt1 = new javax.swing.JTextField();
        precioCuartoTxt = new javax.swing.JTextField();
        jLabel51 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jPanel29 = new javax.swing.JPanel();
        eliminarProductoBtn2 = new javax.swing.JButton();
        jScrollPane8 = new javax.swing.JScrollPane();
        tabla_venta = new javax.swing.JTable();
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
        jPanel14 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        nombreProductoTxt = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        mudificarProductoBtn = new javax.swing.JButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        tabla_productos = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        eliminarProductoBtn = new javax.swing.JButton();
        jPanel17 = new javax.swing.JPanel();
        agregarProductoBtn = new javax.swing.JButton();
        medidaCmbx = new javax.swing.JComboBox();
        jLabel50 = new javax.swing.JLabel();
        jcombo = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("", new javax.swing.ImageIcon(getClass().getResource("/imagenes/reportes.png")), jPanel16); // NOI18N

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
                        .addGap(153, 153, 153)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3))
                        .addGap(258, 258, 258))))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(btnEliminar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(160, 160, 160))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(txttotal2, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtfolio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txttotal2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addComponent(btnEliminar1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 491, Short.MAX_VALUE)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("", new javax.swing.ImageIcon(getClass().getResource("/imagenes/ventas.png")), jPanel9); // NOI18N

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Quitar producto"));

        eliminarProductoBtn1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/eliminar.png"))); // NOI18N
        eliminarProductoBtn1.setText("Quitar producto");
        eliminarProductoBtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarProductoBtn1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addComponent(eliminarProductoBtn1, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addComponent(eliminarProductoBtn1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );

        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder("Registrar venta"));

        registrarPreciosPeso.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/registrar.png"))); // NOI18N
        registrarPreciosPeso.setText("Registrar venta");
        registrarPreciosPeso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registrarPreciosPesoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(registrarPreciosPeso, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(registrarPreciosPeso, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jPanel19.setBorder(javax.swing.BorderFactory.createTitledBorder("Buscar productos"));

        agregarProductoBtn1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/add.png"))); // NOI18N
        agregarProductoBtn1.setText("Buscar producto");
        agregarProductoBtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarProductoBtn1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(agregarProductoBtn1)
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(agregarProductoBtn1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel20.setBorder(javax.swing.BorderFactory.createTitledBorder("Costo"));

        precioCuartoTxt3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        precioCuartoTxt2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        precioCuartoTxt1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        precioCuartoTxt.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel51.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel51.setText("Total");

        jLabel57.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel57.setText("$");

        jLabel54.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel54.setText("Descuento");

        jLabel58.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel58.setText("$");

        jLabel59.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel59.setText("$");

        jLabel55.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel55.setText("Cambio");

        jLabel60.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel60.setText("$");

        jLabel56.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel56.setText("Paga con");

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel60, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addComponent(precioCuartoTxt3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel59, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel56))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addComponent(precioCuartoTxt2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel55))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addComponent(precioCuartoTxt1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                        .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel54))
                .addGap(10, 10, 10)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel51)
                    .addComponent(precioCuartoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel51)
                    .addComponent(jLabel54)
                    .addComponent(jLabel55)
                    .addComponent(jLabel56))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(precioCuartoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel57)
                    .addComponent(precioCuartoTxt1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel58)
                    .addComponent(precioCuartoTxt2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(precioCuartoTxt3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel60)
                    .addComponent(jLabel59))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel29.setBorder(javax.swing.BorderFactory.createTitledBorder("Cancelar venta"));

        eliminarProductoBtn2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/eliminar.png"))); // NOI18N
        eliminarProductoBtn2.setText("Cancelar venta");
        eliminarProductoBtn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarProductoBtn2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
        jPanel29.setLayout(jPanel29Layout);
        jPanel29Layout.setHorizontalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel29Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addComponent(eliminarProductoBtn2, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(eliminarProductoBtn2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        tabla_venta.setModel(modeloVenta);
        tabla_venta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla_ventaMouseClicked(evt);
            }
        });
        tabla_venta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tabla_ventaKeyPressed(evt);
            }
        });
        jScrollPane8.setViewportView(tabla_venta);

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 823, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34))
        );

        jTabbedPane2.addTab("Ventas", new javax.swing.ImageIcon(getClass().getResource("/imagenes/images97.png")), jPanel11); // NOI18N

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
                .addGap(69, 69, 69)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(168, 168, 168)
                        .addComponent(jLabel10))
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
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
                .addGap(18, 18, 18)
                .addComponent(btnEliminar2, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(480, Short.MAX_VALUE))
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        tablitaAlmacen.setModel(existProductModel);
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
                            .addComponent(jPanel37, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 759, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 274, Short.MAX_VALUE)
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
                    .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(85, 85, 85))
        );

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(1128, Short.MAX_VALUE))
            .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel12Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel36, javax.swing.GroupLayout.PREFERRED_SIZE, 1218, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap(353, Short.MAX_VALUE)
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
            .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel12Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel36, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jTabbedPane2.addTab("Almacenes", new javax.swing.ImageIcon(getClass().getResource("/imagenes/almacenes.png")), jPanel12); // NOI18N

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Buscar producto"));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Descripción del producto");

        nombreProductoTxt.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        nombreProductoTxt.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        nombreProductoTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nombreProductoTxtActionPerformed(evt);
            }
        });
        nombreProductoTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nombreProductoTxtKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                nombreProductoTxtKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 12, Short.MAX_VALUE)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(200, 200, 200))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(nombreProductoTxt)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nombreProductoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 20, Short.MAX_VALUE))
        );

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder("Modificar Producto"));
        jPanel10.setToolTipText("Registrar Categoría");

        mudificarProductoBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/modificar.png"))); // NOI18N
        mudificarProductoBtn.setText("Modificar");
        mudificarProductoBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mudificarProductoBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addComponent(mudificarProductoBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(mudificarProductoBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabla_productos.setModel(existProductModel);
        tabla_productos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla_productosMouseClicked(evt);
            }
        });
        tabla_productos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tabla_productosKeyPressed(evt);
            }
        });
        jScrollPane7.setViewportView(tabla_productos);

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Eliminar Producto"));

        eliminarProductoBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/eliminar.png"))); // NOI18N
        eliminarProductoBtn.setText("Eliminar");
        eliminarProductoBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarProductoBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(eliminarProductoBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(eliminarProductoBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jPanel17.setBorder(javax.swing.BorderFactory.createTitledBorder("Registrar Producto"));

        agregarProductoBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/add.png"))); // NOI18N
        agregarProductoBtn.setText("Agregar");
        agregarProductoBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarProductoBtnActionPerformed(evt);
            }
        });

        medidaCmbx.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "PIEZA", "KILO" }));
        medidaCmbx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                medidaCmbxActionPerformed(evt);
            }
        });

        jLabel50.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel50.setText("Medida");

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addComponent(agregarProductoBtn)
                .addGap(18, 18, 18)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(medidaCmbx, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(agregarProductoBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(medidaCmbx, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19))
        );

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane7)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel14Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(54, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(47, Short.MAX_VALUE))
        );

        jPanel10.getAccessibleContext().setAccessibleName("Registrar Categoría");

        jTabbedPane2.addTab("Productos", new javax.swing.ImageIcon(getClass().getResource("/imagenes/prod.png")), jPanel14); // NOI18N
        jPanel14.getAccessibleContext().setAccessibleDescription("");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 507, Short.MAX_VALUE)
        );

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
                .addGap(117, 117, 117)
                .addComponent(jLabel2)
                .addGap(150, 150, 150)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 495, Short.MAX_VALUE)
                .addComponent(jcombo, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(85, 85, 85))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7)
                            .addComponent(jcombo, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel2)))
                .addGap(5, 5, 5)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jcomboMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jcomboMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jcomboMouseClicked

    private void jcomboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcomboActionPerformed
        // TODO add your handling code here:
        int opc = jcombo.getSelectedIndex();
        Login l = new Login();
        if (opc == 1) {
            int i = JOptionPane.showConfirmDialog(this, "Realmente quieres salir", "confirmar Salida", JOptionPane.YES_NO_OPTION);

            if (i == 0) {
                l.setVisible(true);
                this.setVisible(false);
            }
        } else {
            jcombo.setSelectedIndex(0);
        }


    }//GEN-LAST:event_jcomboActionPerformed

    private void btnEliminar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminar2ActionPerformed
        // TODO add your handling code here:

        int cell;

        if (tbUsuarios.getRowCount() > 0) {
            cell = tbUsuarios.getSelectedRow();
            tabla.removeRow(cell);
        }
    }//GEN-LAST:event_btnEliminar2ActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
        DaoUsuarios dao = new DaoUsuarios();
        Usuario bean = new Usuario();
        Persona p = new Persona();
        List<Usuario> Usuario;
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

        Usuario = dao.ConsultaGeneral();

        DefaultTableModel nuevoVentas = new DefaultTableModel(new String[]{"Nombre", "Apellidos", "Usuario", "Contraseña", "Tipo"}, 0);
        tbUsuarios.setModel(nuevoVentas);

        for (int i = 0; i < Usuario.size(); i++) {
            Object datos[] = {Usuario.get(i).getPersona().getNombre()
                + "", Usuario.get(i).getPersona().getaPaterno() + "", Usuario.get(i).getUsuario() + "", Usuario.get(i).getContrasena() + "", Usuario.get(i).getTipoUsuario().getNombreTipo() + ""};
            ((DefaultTableModel) tbUsuarios.getModel()).addRow(datos);
        }
        txtNombre.setText("");
        txtApellidos.setText("");
        txtContrasena.setText("");
        txtUsuarios.setText("");
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnConsultaVentas1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultaVentas1ActionPerformed
        consultarVentasMes();
        try {
            totalVenta();
        } catch (SQLException ex) {
            Logger.getLogger(VentanaAdministrador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnConsultaVentas1ActionPerformed

    private void cmbVenta1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbVenta1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbVenta1ActionPerformed

    private void btnConsultaCompras1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultaCompras1ActionPerformed
        consultarComprasMes();
        try {
            totalCompra();
        } catch (SQLException ex) {
            Logger.getLogger(VentanaAdministrador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnConsultaCompras1ActionPerformed

    private void cmbCompras1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbCompras1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbCompras1ActionPerformed

    private void btnEliminar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminar1ActionPerformed
        // TODO add your handling code here:
        ventaDetalle detalle = new ventaDetalle();
        int cantidad = Integer.parseInt(txtCantidad1.getText());
        int cell;
        String codigo = jTable2.getValueAt(jTable2.getSelectedRow(), 1).toString();
        if (jTable2.getRowCount() > 0) {
            cell = jTable2.getSelectedRow();
            double can = Double.parseDouble(jTable2.getValueAt(cell, 3) + "");
            tipo2.removeRow(cell);
            totalVenta = totalVenta + can;

            txttotal2.setText("" + totalVenta);
        }
        detalle.setCantidad(cantidad);
        da02.EliminarExistencias(detalle, codigo);
    }//GEN-LAST:event_btnEliminar1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        DaoVentas dao = new DaoVentas();
        Usuario usuario = new Usuario();
        String folio = txtfolio.getText();
        String total = txtTotal.getText();
        if (folio.equals("") && total.equals("")) {
            JOptionPane.showMessageDialog(this, "El folio o el total se encuentra vacios");
        } else {

            Venta venta = new Venta();
            venta.setTotalVenta(totalVenta);
            venta.setFolioVenta(folio);
            venta.setUsuario(Integer.parseInt(numero.getText()));
            dao.altaVenta(venta);
            dao.ModificarId();
            System.out.println("" + dao.ModificarId());

        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void btnRegistrar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrar1ActionPerformed
        // TODO add your handling code here:

        String valida[] = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "ñ", "!", ""
            + "#", "$", "%", "&", "/", "(", ")", ";", ":", "*", "?", "=", "_", "'", "~", ".", "[", "]", "+", "-", "}", "{", "",};
        for (int i = 0; i < valida.length; i++) {
            if (txtCantidad1.getText().equals(valida[i])) {

                JOptionPane.showMessageDialog(this, "introdusca numero");
            }
        }
        try {
            if (txtCantidad1.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Ingresa la cantidad");
            } else {
                //        DaoVentas dao=new DaoVentas();
                ventaDetalle detalle = new ventaDetalle();
                Producto p = new Producto();
                int cantidad = Integer.parseInt(txtCantidad1.getText());
                String cmb = cmbProducto01.getSelectedItem().toString();
                System.out.println("" + cmb);
                p = dao.consultarPrecio(cmb);
                //      if(dao.ProductoCantidad(cmb)<cantidad){
                //      JOptionPane.showMessageDialog(this,"exitencias de este  producto"+dao.ProductoCantidad(cmb));
                //
                //      }else
                {

                    suma2 = cantidad * (double) p.getPrecio();
                    Object datos[] = {cantidad, cmb, p.getNombreProducto(), p.getPrecio(), suma2};
                    Suma2.add(suma2);
                    tipo2 = ((DefaultTableModel) jTable2.getModel());
                    tipo2.addRow(datos);
                    totalVenta = totalVenta + suma2;
                    txttotal2.setText(totalVenta + "");
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

    private void cmbProducto01ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbProducto01ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbProducto01ActionPerformed

    private void medidaCmbxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_medidaCmbxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_medidaCmbxActionPerformed

    private void nombreProductoTxtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nombreProductoTxtKeyReleased
        filter(nombreProductoTxt.getText().toUpperCase());
    }//GEN-LAST:event_nombreProductoTxtKeyReleased

    private void nombreProductoTxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nombreProductoTxtKeyPressed
    }//GEN-LAST:event_nombreProductoTxtKeyPressed

    private void nombreProductoTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombreProductoTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nombreProductoTxtActionPerformed

    private void agregarProductoBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarProductoBtnActionPerformed
        int medida = medidaCmbx.getSelectedIndex();
        Object[] producto_precios = null;
        //PIEZA
        if (medida == 0) {
            VentanaProductoPiezaEditar ventanaPiezaEditar = new VentanaProductoPiezaEditar(null);
            ventanaPiezaEditar.setVisible(true);
            //KILO
        } else if (medida == 1) {
            VentanaProductoPesoEditar ventanaPesoEditar = new VentanaProductoPesoEditar(null);
            ventanaPesoEditar.setVisible(true);
        }

    }//GEN-LAST:event_agregarProductoBtnActionPerformed

    private void mudificarProductoBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mudificarProductoBtnActionPerformed
        int[] seleccionados = tabla_productos.getSelectedRows();

        //SELECCIONAR UN PRODUCTO A LA VEZ
        if (seleccionados.length <= 0) {
            JOptionPane.showMessageDialog(this, "Seleccionar al menos un producto");
        } else {
            if (seleccionados.length > 1) {
                JOptionPane.showMessageDialog(this, "Seleccionar un producto a la vez");
            } else {
                //OBTENER EL NUMERO PRODUCTO EN LA TABLA, SELECCIONADO LA COLUMNA DE CONTEO   
                int fila = Integer.parseInt(tabla_productos.getValueAt(seleccionados[0], 0).toString());
                //OBTENER PRODUCTO DE LA LISTA MEDINATE LA POSICIÓN EN LA TABLA
                Object[] producto_precio = productoDao.readAllProducts().get(fila - 1);
                //PRODUCTOS PIEZA
                if (((Producto) producto_precio[0]).getMedida() == 1) {
                    VentanaProductoPiezaEditar vppe = new VentanaProductoPiezaEditar(producto_precio);
                    vppe.setVisible(true);
                    //PRODUCTOS PESO
                } else if (((Producto) producto_precio[0]).getMedida() == 2) {
                    VentanaProductoPesoEditar vpeso = new VentanaProductoPesoEditar(producto_precio);
                    vpeso.setVisible(true);
                }
            }
        }
    }//GEN-LAST:event_mudificarProductoBtnActionPerformed

    private void eliminarProductoBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarProductoBtnActionPerformed
        int[] seleccionados = tabla_productos.getSelectedRows();

        //SELECCIONAR UN PRODUCTO A LA VEZ
        if (seleccionados.length <= 0) {
            JOptionPane.showMessageDialog(this, "Seleccionar al menos un producto");
        } else {
            if (seleccionados.length > 1) {
                JOptionPane.showMessageDialog(this, "Seleccionar un producto a la vez");
            } else {
                //OBTENER EL NUMERO PRODUCTO EN LA TABLA, SELECCIONADO LA COLUMNA DE CONTEO   
                int fila = Integer.parseInt(tabla_productos.getValueAt(seleccionados[0], 0).toString());
                Object[] producto_precio = productoDao.readAllProducts().get(fila - 1);
                int idProducto = ((Producto) producto_precio[0]).getIdProducto();

                int respuesta = JOptionPane.showConfirmDialog(this, "¿Deseas eliminar este producto?", "Eliminar producto", JOptionPane.YES_NO_OPTION);
                if (respuesta == 0) {
                    if (productoDao.deleteProducto(idProducto)) {
                        JOptionPane.showMessageDialog(this, "El producto fue eliminado correctamente");
                        //SE OBTIENE EL MODELO YA CREADO EN LA VISTA ADMINISTRADOR 
                        DefaultTableModel model = (DefaultTableModel) VentanaAdministrador.tabla_productos.getModel();

                        //SE ENVIA ESTE MISMO MODELO COMO PARAMETRO EN EL CONSTRUCTOR, PARA QUE SEA ACTUALIZADO
                        VentanaAdministrador va = new VentanaAdministrador(model);

                    } else {
                        JOptionPane.showMessageDialog(this, "No fue posible eliminar el prosucto");
                    }
                }
            }
        }
    }//GEN-LAST:event_eliminarProductoBtnActionPerformed

    private void tabla_productosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabla_productosKeyPressed
    }//GEN-LAST:event_tabla_productosKeyPressed

    private void tabla_productosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_productosMouseClicked
    }//GEN-LAST:event_tabla_productosMouseClicked

    private void btnCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCategoriaActionPerformed
        consultarProductosCategoria();
    }//GEN-LAST:event_btnCategoriaActionPerformed

    private void cmbCategoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbCategoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbCategoActionPerformed

    private void btnProductos3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProductos3ActionPerformed
        consultarProductosNombre();
    }//GEN-LAST:event_btnProductos3ActionPerformed

    private void cmbAlmacen3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbAlmacen3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbAlmacen3ActionPerformed

    private void tabla_ventaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_ventaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tabla_ventaMouseClicked

    private void tabla_ventaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabla_ventaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tabla_ventaKeyPressed

    private void registrarPreciosPesoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registrarPreciosPesoActionPerformed
    }//GEN-LAST:event_registrarPreciosPesoActionPerformed

    private void eliminarProductoBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarProductoBtn1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_eliminarProductoBtn1ActionPerformed

    private void eliminarProductoBtn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarProductoBtn2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_eliminarProductoBtn2ActionPerformed

    private void agregarProductoBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarProductoBtn1ActionPerformed
        VentanaTablaProductos vtp = new VentanaTablaProductos();
        vtp.setVisible(true);// TODO add your handling code here:
    }//GEN-LAST:event_agregarProductoBtn1ActionPerformed

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
            java.util.logging.Logger.getLogger(VentanaAdministrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaAdministrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaAdministrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaAdministrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaAdministrador().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton agregarProductoBtn;
    private javax.swing.JButton agregarProductoBtn1;
    private javax.swing.JButton btnCategoria;
    private javax.swing.JButton btnConsultaCompras1;
    private javax.swing.JButton btnConsultaVentas1;
    private javax.swing.JButton btnEliminar1;
    private javax.swing.JButton btnEliminar2;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnProductos3;
    private javax.swing.JButton btnRegistrar1;
    private javax.swing.JComboBox cmbAlmacen3;
    private javax.swing.JComboBox cmbCatego;
    private javax.swing.JComboBox cmbCompras1;
    private javax.swing.JComboBox cmbProducto01;
    private javax.swing.JComboBox cmbVenta1;
    private javax.swing.JButton eliminarProductoBtn;
    private javax.swing.JButton eliminarProductoBtn1;
    private javax.swing.JButton eliminarProductoBtn2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
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
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel36;
    private javax.swing.JPanel jPanel37;
    private javax.swing.JPanel jPanel38;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane2;
    public static javax.swing.JComboBox jcombo;
    private javax.swing.JLabel lblMesCompra1;
    private javax.swing.JLabel lblMesVenta1;
    private javax.swing.JComboBox medidaCmbx;
    private javax.swing.JButton mudificarProductoBtn;
    private javax.swing.JTextField nombreProductoTxt;
    private javax.swing.JTextField precioCuartoTxt;
    private javax.swing.JTextField precioCuartoTxt1;
    private javax.swing.JTextField precioCuartoTxt2;
    private javax.swing.JTextField precioCuartoTxt3;
    private javax.swing.JButton registrarPreciosPeso;
    public static javax.swing.JTable tabla_productos;
    public static javax.swing.JTable tabla_venta;
    private javax.swing.JTable tablitaAlmacen;
    private javax.swing.JTable tablitaCompras1;
    private javax.swing.JTable tablitaVentas1;
    private javax.swing.JTable tbUsuarios;
    private javax.swing.JTextField txtApellidos;
    private javax.swing.JTextField txtCantidad1;
    private javax.swing.JTextField txtContrasena;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtUsuarios;
    private javax.swing.JTextField txtfolio;
    private javax.swing.JTextField txttotal2;
    // End of variables declaration//GEN-END:variables
}
