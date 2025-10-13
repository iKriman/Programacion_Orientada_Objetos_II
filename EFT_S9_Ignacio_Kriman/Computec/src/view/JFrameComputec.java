package view;

import command.CommandInvoker;
import command.CrearCliente;
import command.CrearEquipo;
import command.EliminarCliente;
import command.EliminarEquipo;
import command.ListarClientes;
import command.ListarEquipos;
import command.ListarVentas;
import modelo.Cliente;
import javax.swing.*;
import java.awt.*;
import modelo.Equipo;
import modelo.Laptop;
import modelo.Venta;
import service.Descuento;
import service.DescuentoBase;
import service.DescuentoPorcentaje;
import service.VentaService;
import modelo.Desktop;
import controller.ClienteController;
import controller.EquipoController;
import javax.swing.table.DefaultTableModel;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class JFrameComputec extends JFrame {

    private final CommandInvoker invoker = new CommandInvoker();
    private final ClienteController gestorCliente = new ClienteController();
    private final EquipoController gestorEquipo = new EquipoController();
    private final VentaService servicioVenta = new VentaService();

    private JTextArea areaLog;

    private JTable tablaClientes;
    private JTextField txtRut, txtNombre, txtDireccion, txtComuna, txtCorreo, txtTelefono;
    private JButton btnGuardarCliente, btnListarClientes, btnEliminarCliente;

    private JTable tablaEquipos;
    private JComboBox<String> cmbTipoEquipo;
    private JTextField txtModelo, txtCpu, txtDiscoDuroMB, txtRamGB, txtPrecio;
    private JTextField txtFuente, txtFactorForma;
    private JTextField txtPantallaPulgadas, txtPuertosUSB;
    private JCheckBox chkEsTouch;
    private JPanel specificPanel;
    private JButton btnGuardarEquipo, btnListarEquipos, btnEliminarEquipo;

    private JTable tablaReporteDetalle;
    private JTextField txtRutVenta, txtIdEquipoVenta, txtPrecioBaseVenta, txtPorcentajeDescuento;
    private JButton btnRegistrarVenta;

    private JComboBox<String> cmbFiltroReporte;
    private JTextArea areaReporteTotales;

    public JFrameComputec() {
        setTitle("Sistema de Gestión Computec");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initComponents();
        pack();
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        JTabbedPane tabbedPane = new JTabbedPane();

        tabbedPane.addTab("Clientes", crearPanelCliente());
        tabbedPane.addTab("Equipos", crearPanelEquipo());
        tabbedPane.addTab("Venta", crearPanelVenta());
        tabbedPane.addTab("Reportes", crearPanelReportes());

        areaLog = new JTextArea(8, 50);
        areaLog.setEditable(false);
        JScrollPane logScrollPane = new JScrollPane(areaLog);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(tabbedPane, BorderLayout.CENTER);
        getContentPane().add(new JLabel("Log de Eventos:"), BorderLayout.NORTH);
        getContentPane().add(logScrollPane, BorderLayout.SOUTH);
    }

    private JPanel crearPanelCliente() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createTitledBorder("Mantenedor de Clientes"));

        JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        txtRut = new JTextField();
        txtNombre = new JTextField();
        txtDireccion = new JTextField();
        txtComuna = new JTextField();
        txtCorreo = new JTextField();
        txtTelefono = new JTextField();
        formPanel.add(new JLabel("RUT:"));
        formPanel.add(txtRut);
        formPanel.add(new JLabel("Nombre:"));
        formPanel.add(txtNombre);
        formPanel.add(new JLabel("Dirección:"));
        formPanel.add(txtDireccion);
        formPanel.add(new JLabel("Comuna:"));
        formPanel.add(txtComuna);
        formPanel.add(new JLabel("Correo:"));
        formPanel.add(txtCorreo);
        formPanel.add(new JLabel("Teléfono:"));
        formPanel.add(txtTelefono);

        JPanel btnPanel = new JPanel(new FlowLayout());
        btnGuardarCliente = new JButton("GUARDAR CLIENTE");
        btnListarClientes = new JButton("LISTAR CLIENTES");
        btnEliminarCliente = new JButton("ELIMINAR POR RUT");
        btnPanel.add(btnGuardarCliente);
        btnPanel.add(btnListarClientes);
        btnPanel.add(btnEliminarCliente);

        tablaClientes = new JTable();

        btnGuardarCliente.addActionListener(e -> guardarCliente());
        btnListarClientes.addActionListener(e -> listarClientes());
        btnEliminarCliente.addActionListener(e -> eliminarCliente());

        panel.add(formPanel, BorderLayout.NORTH);
        panel.add(btnPanel, BorderLayout.CENTER);
        panel.add(new JScrollPane(tablaClientes), BorderLayout.SOUTH);

        return panel;
    }

    private JPanel crearPanelEquipo() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createTitledBorder("Mantenedor de Equipos"));

        JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        cmbTipoEquipo = new JComboBox<>(new String[]{"Desktop", "Laptop"});
        txtModelo = new JTextField();
        txtCpu = new JTextField();
        txtDiscoDuroMB = new JTextField();
        txtRamGB = new JTextField();
        txtPrecio = new JTextField();
        formPanel.add(new JLabel("Tipo:"));
        formPanel.add(cmbTipoEquipo);
        formPanel.add(new JLabel("Modelo:"));
        formPanel.add(txtModelo);
        formPanel.add(new JLabel("CPU:"));
        formPanel.add(txtCpu);
        formPanel.add(new JLabel("Disco MB:"));
        formPanel.add(txtDiscoDuroMB);
        formPanel.add(new JLabel("RAM GB:"));
        formPanel.add(txtRamGB);
        formPanel.add(new JLabel("Precio:"));
        formPanel.add(txtPrecio);

        specificPanel = new JPanel(new CardLayout());
        JPanel desktopPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        txtFuente = new JTextField();
        txtFactorForma = new JTextField();
        desktopPanel.add(new JLabel("Potencia Fuente:"));
        desktopPanel.add(txtFuente);
        desktopPanel.add(new JLabel("Factor Forma:"));
        desktopPanel.add(txtFactorForma);
        specificPanel.add(desktopPanel, "Desktop");

        JPanel laptopPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        txtPantallaPulgadas = new JTextField();
        txtPuertosUSB = new JTextField();
        chkEsTouch = new JCheckBox("Es Touch");
        laptopPanel.add(new JLabel("Pantalla:"));
        laptopPanel.add(txtPantallaPulgadas);
        laptopPanel.add(new JLabel("Puertos USB:"));
        laptopPanel.add(txtPuertosUSB);
        laptopPanel.add(new JLabel("Táctil:"));
        laptopPanel.add(chkEsTouch);
        specificPanel.add(laptopPanel, "Laptop");

        cmbTipoEquipo.addActionListener(e -> {
            CardLayout cl = (CardLayout) specificPanel.getLayout();
            cl.show(specificPanel, (String) cmbTipoEquipo.getSelectedItem());
        });

        JPanel btnPanel = new JPanel(new FlowLayout());
        btnGuardarEquipo = new JButton("GUARDAR EQUIPO");
        btnListarEquipos = new JButton("LISTAR EQUIPOS");
        btnEliminarEquipo = new JButton("ELIMINAR EQUIPO");
        btnPanel.add(btnGuardarEquipo);
        btnPanel.add(btnListarEquipos);
        btnPanel.add(btnEliminarEquipo);

        tablaEquipos = new JTable();

        btnGuardarEquipo.addActionListener(e -> guardarEquipo());
        btnListarEquipos.addActionListener(e -> listarEquipos());
        btnEliminarEquipo.addActionListener(e -> eliminarEquipo());

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(formPanel, BorderLayout.NORTH);
        mainPanel.add(specificPanel, BorderLayout.CENTER);
        mainPanel.add(btnPanel, BorderLayout.SOUTH);
        mainPanel.add(new JScrollPane(tablaEquipos), BorderLayout.EAST);

        panel.add(mainPanel, BorderLayout.CENTER);
        return panel;
    }

    private JPanel crearPanelVenta() {
        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
        panel.setBorder(BorderFactory.createTitledBorder("Registrar Venta"));

        txtRutVenta = new JTextField();
        txtIdEquipoVenta = new JTextField();
        txtPrecioBaseVenta = new JTextField();
        txtPorcentajeDescuento = new JTextField("0");

        panel.add(new JLabel("RUT Cliente:"));
        panel.add(txtRutVenta);
        panel.add(new JLabel("ID Equipo:"));
        panel.add(txtIdEquipoVenta);
        panel.add(new JLabel("Precio Base:"));
        panel.add(txtPrecioBaseVenta);
        panel.add(new JLabel("Descuento (0-1):"));
        panel.add(txtPorcentajeDescuento);

        btnRegistrarVenta = new JButton("REGISTRAR VENTA");
        panel.add(btnRegistrarVenta);
        btnRegistrarVenta.addActionListener(e -> registrarVenta());

        return panel;
    }

    private JPanel crearPanelReportes() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Reportes de Ventas"));

        JPanel filtroPanel = new JPanel(new FlowLayout());
        cmbFiltroReporte = new JComboBox<>(new String[]{"Todos", "Laptop", "Desktop"});
        JButton btnGenerar = new JButton("Generar Reporte");
        filtroPanel.add(new JLabel("Filtrar por Tipo:"));
        filtroPanel.add(cmbFiltroReporte);
        filtroPanel.add(btnGenerar);

        tablaReporteDetalle = new JTable();
        areaReporteTotales = new JTextArea(3, 50);
        areaReporteTotales.setEditable(false);

        btnGenerar.addActionListener(e -> generarReporteDetalladoAction());

        panel.add(filtroPanel, BorderLayout.NORTH);
        panel.add(new JScrollPane(tablaReporteDetalle), BorderLayout.CENTER);
        panel.add(areaReporteTotales, BorderLayout.SOUTH);

        return panel;
    }

    private void guardarCliente() {
        try {
            Cliente c = new Cliente(txtRut.getText(), txtNombre.getText(), txtDireccion.getText(), txtComuna.getText(), txtCorreo.getText(), txtTelefono.getText());
            CrearCliente comando = new CrearCliente(gestorCliente, c);
            invoker.run(comando);
            areaLog.append("Cliente guardado: " + c.getNombreCompleto() + "\n");
            listarClientes();
        } catch (Exception e) {
            areaLog.append("Error al guardar cliente\n");
        }
    }

    private void listarClientes() {
        try {
            ListarClientes comando = new ListarClientes(gestorCliente);
            invoker.run(comando);
            List<Cliente> clientes = comando.getResultado();
            String[] col = {"RUT", "Nombre", "Dirección", "Comuna", "Correo", "Teléfono"};
            Object[][] data = new Object[clientes.size()][6];
            for (int i = 0; i < clientes.size(); i++) {
                Cliente c = clientes.get(i);
                data[i][0] = c.getRut();
                data[i][1] = c.getNombreCompleto();
                data[i][2] = c.getDireccion();
                data[i][3] = c.getComuna();
                data[i][4] = c.getCorreo();
                data[i][5] = c.getTelefono();
            }
            tablaClientes.setModel(new DefaultTableModel(data, col));
        } catch (Exception e) {
            areaLog.append("Error al listar clientes\n");
        }
    }

    private void eliminarCliente() {
        String rut = JOptionPane.showInputDialog("Ingrese RUT a eliminar");
        if (rut == null || rut.isEmpty()) {
            return;
        }
        try {
            EliminarCliente comando = new EliminarCliente(gestorCliente, rut);
            invoker.run(comando);
            areaLog.append("Cliente eliminado: " + rut + "\n");
            listarClientes();
        } catch (Exception e) {
            areaLog.append("Error al eliminar cliente\n");
        }
    }

    private void guardarEquipo() {
        try {
            String tipo = (String) cmbTipoEquipo.getSelectedItem();
            String modelo = txtModelo.getText();
            String cpu = txtCpu.getText();
            int disco = Integer.parseInt(txtDiscoDuroMB.getText());
            int ram = Integer.parseInt(txtRamGB.getText());
            BigDecimal precio = new BigDecimal(txtPrecio.getText());
            Equipo e;
            if ("Desktop".equals(tipo)) {
                int fuente = Integer.parseInt(txtFuente.getText());
                String factor = txtFactorForma.getText();
                e = new Desktop(modelo, cpu, disco, ram, precio, fuente, factor);
            } else {
                BigDecimal pulgadas = new BigDecimal(txtPantallaPulgadas.getText());
                boolean esTouch = chkEsTouch.isSelected();
                int usb = Integer.parseInt(txtPuertosUSB.getText());
                e = new Laptop(modelo, cpu, disco, ram, precio, pulgadas, esTouch, usb);
            }
            CrearEquipo comando = new CrearEquipo(gestorEquipo, e);
            invoker.run(comando);
            areaLog.append("Equipo guardado: " + modelo + "\n");
            listarEquipos();
        } catch (Exception e) {
            areaLog.append("Error al guardar equipo\n");
        }
    }

    private void listarEquipos() {
        try {
            ListarEquipos comando = new ListarEquipos(gestorEquipo);
            invoker.run(comando);
            List<Equipo> equipos = comando.getResultado();
            if (equipos == null) {
                equipos = new ArrayList<>();
            }

            String[] col = {"ID", "Tipo", "Modelo", "CPU", "RAM", "Precio", "Extra"};
            Object[][] data = new Object[equipos.size()][7];

            for (int i = 0; i < equipos.size(); i++) {
                Equipo e = equipos.get(i);
                data[i][0] = e.getIdEquipo();
                data[i][1] = (e instanceof Desktop) ? "Desktop" : "Laptop";
                data[i][2] = e.getModelo();
                data[i][3] = e.getCpu();
                data[i][4] = e.getRamGB();
                data[i][5] = e.getPrecio();

                if (e instanceof Desktop d) {
                    data[i][6] = d.getFuente() + "W " + d.getFactorForma();
                } else if (e instanceof Laptop l) {
                    data[i][6] = l.getPantallaPulgadas() + " pulgadas " + (l.isEsTouch() ? "Touch" : "") + ", USB " + l.getPuertosUSB();
                } else {
                    data[i][6] = "";
                }
            }

            tablaEquipos.setModel(new DefaultTableModel(data, col));

        } catch (Exception e) {
            areaLog.append("Error al listar equipos: " + e.getMessage() + "\n");
        }
    }

    private void eliminarEquipo() {
        String idS = JOptionPane.showInputDialog("ID equipo a eliminar");
        if (idS == null || idS.isEmpty()) {
            return;
        }
        try {
            int id = Integer.parseInt(idS);
            EliminarEquipo comando = new EliminarEquipo(gestorEquipo, id);
            invoker.run(comando);
            areaLog.append("Equipo eliminado ID: " + id + "\n");
            listarEquipos();
        } catch (Exception e) {
            areaLog.append("Error al eliminar equipo\n");
        }
    }

    private void registrarVenta() {
        try {
            String rut = txtRutVenta.getText();
            int idEquipo = Integer.parseInt(txtIdEquipoVenta.getText());
            BigDecimal base = new BigDecimal(txtPrecioBaseVenta.getText());
            BigDecimal desc = new BigDecimal(txtPorcentajeDescuento.getText());

            Descuento estrategia = new DescuentoBase();
            if (desc.compareTo(BigDecimal.ZERO) > 0) {
                estrategia = new DescuentoPorcentaje(estrategia, desc);
            }

            BigDecimal precioFinal = estrategia.aplicar(base);

            String descuentoAplicado = (precioFinal.equals(base)) ? "0" : estrategia.toString();

            servicioVenta.registrarVenta(rut, idEquipo, base, precioFinal, descuentoAplicado);

            areaLog.append("Venta registrada: " + rut + " Final: " + precioFinal + "\n");
            limpiarCamposVenta();

        } catch (Exception e) {
            areaLog.append("Error al registrar venta: " + e.getMessage() + "\n");
        }
    }

    private void limpiarCamposVenta() {

        txtRutVenta.setText("");
        txtIdEquipoVenta.setText("");
        txtPrecioBaseVenta.setText("");

        txtPorcentajeDescuento.setText("0");

    }

    private void generarReporteDetalladoAction() {
        String filtro = (String) cmbFiltroReporte.getSelectedItem();
        try {

            ListarVentas comando = new ListarVentas(servicioVenta);
            invoker.run(comando);

            List<Venta> ventas = comando.getResultado();

            List<Venta> ventasFiltradas = ventas;

            String[] columnas = {"ID Venta", "ID Equipo", "RUT Cliente", "Descuento Aplicado", "Precio Final"};
            Object[][] data = new Object[ventasFiltradas.size()][5];

            BigDecimal totalRecaudado = BigDecimal.ZERO;
            int cantidadVentas = 0;

            for (int i = 0; i < ventasFiltradas.size(); i++) {
                Venta v = ventasFiltradas.get(i);

                data[i][0] = v.getId();
                data[i][1] = v.getIdEquipo();
                data[i][2] = v.getRutCliente();
                data[i][3] = v.getDescuentoAplicado();
                data[i][4] = v.getPrecioFinal();

                totalRecaudado = totalRecaudado.add(v.getPrecioFinal());
                cantidadVentas++;
            }

            tablaReporteDetalle.setModel(new javax.swing.table.DefaultTableModel(data, columnas));
            areaLog.append("Reporte de Ventas generado: " + cantidadVentas + " registros filtrados.\n");

            String resumen = String.format("Cantidad Total de Ventas: %d\n", cantidadVentas);
            resumen += String.format("Monto Total Recaudado: $%.2f", totalRecaudado);
            areaReporteTotales.setText(resumen);

        } catch (RuntimeException ex) {
            String detail = ex.getCause() != null ? ex.getCause().getMessage() : ex.getMessage();
            areaLog.append("Error al generar reporte: " + detail + "\n");
            areaReporteTotales.setText("ERROR DE CONEXIÓN o DATOS: " + detail);
        }
    }
}
