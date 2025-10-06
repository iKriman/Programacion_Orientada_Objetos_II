package com.cine.magenta.gui;

import com.cine.magenta.utils.ConexionDB;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class MainFrame extends JFrame {
    // menu
    private JMenuBar barraMenu;
    private JMenu menuPeliculas;
    private JMenuItem itemAgregar, itemListar, itemBuscar, itemSalir;

    // formulario
    private JTextField txtId, txtTitulo, txtDirector, txtAnio, txtDuracion;
    private JComboBox<String> cbGenero;
    private JButton btnAgregar, btnModificar, btnEliminar, btnLimpiar;

    // tabla
    private JTable tabla;
    private DefaultTableModel modelo;

    public MainFrame() {
        configurarVentana();
        crearMenu();
        crearFormularioYTabla();
        probarConexion();
        cargarTabla(); 
    }

    private void configurarVentana() {
        setTitle("Sistema Cine Magenta - Gestión de Películas");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
    }

    private void crearMenu() {
        barraMenu = new JMenuBar();
        menuPeliculas = new JMenu("Películas");
        itemAgregar = new JMenuItem("Agregar Película");
        itemListar = new JMenuItem("Listar Películas");
        itemBuscar = new JMenuItem("Buscar Película");
        itemSalir = new JMenuItem("Salir");

        itemAgregar.addActionListener(e -> abrirAgregar());
        itemListar.addActionListener(e -> cargarTabla());
        itemBuscar.addActionListener(e -> abrirBuscar());
        itemSalir.addActionListener(e -> salir());

        menuPeliculas.add(itemAgregar);
        menuPeliculas.add(itemListar);
        menuPeliculas.add(itemBuscar);
        menuPeliculas.addSeparator();
        menuPeliculas.add(itemSalir);

        barraMenu.add(menuPeliculas);
        setJMenuBar(barraMenu);
    }

    private void crearFormularioYTabla() {
        // panel principal
        JPanel panelPrincipal = new JPanel(new BorderLayout());

        // panel formulario
        JPanel panelFormulario = new JPanel(new GridLayout(6, 2, 5, 5));
        panelFormulario.setBorder(BorderFactory.createTitledBorder("Formulario de Película"));

        txtId = new JTextField(); txtId.setEditable(false);
        txtTitulo = new JTextField();
        txtDirector = new JTextField();
        txtAnio = new JTextField();
        txtDuracion = new JTextField();
        cbGenero = new JComboBox<>(new String[]{"Drama","Acción","Comedia","Fantasía"});

        panelFormulario.add(new JLabel("ID:")); panelFormulario.add(txtId);
        panelFormulario.add(new JLabel("Título:")); panelFormulario.add(txtTitulo);
        panelFormulario.add(new JLabel("Director:")); panelFormulario.add(txtDirector);
        panelFormulario.add(new JLabel("Año:")); panelFormulario.add(txtAnio);
        panelFormulario.add(new JLabel("Duración:")); panelFormulario.add(txtDuracion);
        panelFormulario.add(new JLabel("Género:")); panelFormulario.add(cbGenero);

        // panel botones
        JPanel panelBotones = new JPanel(new FlowLayout());
        btnAgregar = new JButton("Agregar"); btnModificar = new JButton("Modificar");
        btnEliminar = new JButton("Eliminar"); btnLimpiar = new JButton("Limpiar");

        btnAgregar.addActionListener(e -> abrirAgregar());
        btnModificar.addActionListener(e -> modificarPelicula());
        btnEliminar.addActionListener(e -> eliminarPelicula());
        btnLimpiar.addActionListener(e -> limpiarCampos());

        panelBotones.add(btnAgregar); panelBotones.add(btnModificar);
        panelBotones.add(btnEliminar); panelBotones.add(btnLimpiar);

        
        JPanel panelArriba = new JPanel(new BorderLayout());
        panelArriba.add(panelFormulario, BorderLayout.NORTH);
        panelArriba.add(panelBotones, BorderLayout.SOUTH);

        // tabla
        modelo = new DefaultTableModel(new String[]{"ID","Título","Director","Año","Duración","Género"},0);
        tabla = new JTable(modelo);
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabla.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int fila = tabla.getSelectedRow();
                if(fila >= 0){
                    txtId.setText(modelo.getValueAt(fila,0).toString());
                    txtTitulo.setText(modelo.getValueAt(fila,1).toString());
                    txtDirector.setText(modelo.getValueAt(fila,2).toString());
                    txtAnio.setText(modelo.getValueAt(fila,3).toString());
                    txtDuracion.setText(modelo.getValueAt(fila,4).toString());
                    cbGenero.setSelectedItem(modelo.getValueAt(fila,5).toString());
                }
            }
        });
        JScrollPane scrollTabla = new JScrollPane(tabla);

       
        panelPrincipal.add(panelArriba, BorderLayout.NORTH);
        panelPrincipal.add(scrollTabla, BorderLayout.CENTER);

        add(panelPrincipal, BorderLayout.CENTER);
    }

    private void probarConexion(){
        if(ConexionDB.conectar()!=null){
            System.out.println("Conexión exitosa");
        } else {
            JOptionPane.showMessageDialog(this,"No se pudo conectar a la base de datos.","Error",JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cargarTabla() {
        modelo.setRowCount(0); // limpia filas existentes
        try{
            Connection conn = ConexionDB.conectar();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Cartelera");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                modelo.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("titulo"),
                    rs.getString("director"),
                    rs.getInt("anio"),
                    rs.getInt("duracion"),
                    rs.getString("genero")
                });
            }
            conn.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(this,"Error al cargar tabla: "+e.getMessage());
        }
    }

    private void limpiarCampos(){
        txtId.setText(""); txtTitulo.setText(""); txtDirector.setText("");
        txtAnio.setText(""); txtDuracion.setText(""); cbGenero.setSelectedIndex(0);
        tabla.clearSelection();
    }


    private void abrirAgregar(){
        JOptionPane.showMessageDialog(this,"Próximamente: Formulario para agregar películas");
    }

    private void abrirBuscar(){
        String titulo = JOptionPane.showInputDialog(this, "Ingrese el título de la película a buscar");
        if(titulo!=null && !titulo.trim().isEmpty()){
            try{
                Connection conn = ConexionDB.conectar();
                PreparedStatement ps = conn.prepareStatement("SELECT * FROM Cartelera WHERE titulo LIKE ?");
                ps.setString(1,"%"+titulo+"%");
                ResultSet rs = ps.executeQuery();
                if(rs.next()){
                    String info = "ID: "+rs.getInt("id")+
                            "\nTítulo: "+rs.getString("titulo")+
                            "\nDirector: "+rs.getString("director")+
                            "\nGénero: "+rs.getString("genero")+
                            "\nDuración: "+rs.getInt("duracion")+" min";
                    JOptionPane.showMessageDialog(this, info,"Película encontrada",JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this,"No se encontró la película.","Sin resultados",JOptionPane.WARNING_MESSAGE);
                }
                conn.close();
            }catch(Exception e){
                JOptionPane.showMessageDialog(this,"Error al buscar: "+e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // modificar
    private void modificarPelicula(){
        if(txtId.getText().isEmpty()){
            JOptionPane.showMessageDialog(this,"Seleccione una película primero.");
            return;
        }
        try{
            int id = Integer.parseInt(txtId.getText());
            String titulo = txtTitulo.getText().trim();
            String director = txtDirector.getText().trim();
            int anio = Integer.parseInt(txtAnio.getText().trim());
            int duracion = Integer.parseInt(txtDuracion.getText().trim());
            String genero = cbGenero.getSelectedItem().toString();

            if(titulo.isEmpty() || director.isEmpty()){
                JOptionPane.showMessageDialog(this,"Todos los campos son obligatorios.");
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(this,"¿Desea modificar esta película?","Confirmar",JOptionPane.YES_NO_OPTION);
            if(confirm==JOptionPane.YES_OPTION){
                Connection conn = ConexionDB.conectar();
                PreparedStatement ps = conn.prepareStatement(
                        "UPDATE Cartelera SET titulo=?, director=?, anio=?, duracion=?, genero=? WHERE id=?");
                ps.setString(1,titulo); ps.setString(2,director);
                ps.setInt(3,anio); ps.setInt(4,duracion); ps.setString(5,genero); ps.setInt(6,id);
                ps.executeUpdate(); conn.close();
                cargarTabla(); limpiarCampos();
                JOptionPane.showMessageDialog(this,"Película modificada.");
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(this,"Error: "+e.getMessage());
        }
    }

    // eliminar
    private void eliminarPelicula(){
        if(txtId.getText().isEmpty()){
            JOptionPane.showMessageDialog(this,"Seleccione una película primero.");
            return;
        }
        try{
            int id = Integer.parseInt(txtId.getText());
            int confirm = JOptionPane.showConfirmDialog(this,"¿Desea eliminar esta película?","Confirmar",JOptionPane.YES_NO_OPTION);
            if(confirm==JOptionPane.YES_OPTION){
                Connection conn = ConexionDB.conectar();
                PreparedStatement ps = conn.prepareStatement("DELETE FROM Cartelera WHERE id=?");
                ps.setInt(1,id);
                ps.executeUpdate();
                conn.close();
                cargarTabla(); limpiarCampos();
                JOptionPane.showMessageDialog(this,"Película eliminada.");
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(this,"Error: "+e.getMessage());
        }
    }

    private void salir(){
        int resp = JOptionPane.showConfirmDialog(this,"¿Está seguro que desea salir?","Confirmar",JOptionPane.YES_NO_OPTION);
        if(resp==JOptionPane.YES_OPTION) System.exit(0);
    }

    public static void main(String[] args){
        SwingUtilities.invokeLater(() -> new MainFrame().setVisible(true));
    }
}