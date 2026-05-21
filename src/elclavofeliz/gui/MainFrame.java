package elclavofeliz.gui;

import elclavofeliz.bl.ClienteBL;
import elclavofeliz.bl.PedidoBL;
import elclavofeliz.et.Cliente;
import elclavofeliz.et.Pedido;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class MainFrame extends JFrame {

    private final ClienteBL clienteBL;
    private final PedidoBL pedidoBL;

    private final DefaultTableModel clienteModel = new DefaultTableModel(new String[]{"ID", "Nombre"}, 0);
    private final DefaultTableModel pedidoModel = new DefaultTableModel(new String[]{"ID", "Cliente ID", "Productos"}, 0);

    private final JTextField txtAgregarClienteNombre = new JTextField();
    private final JTextField txtEditarClienteId = new JTextField();
    private final JTextField txtEditarClienteNombre = new JTextField();
    private final JTextField txtEliminarClienteId = new JTextField();

    private final JTextField txtAgregarPedidoClienteId = new JTextField();
    private final JTextField txtAgregarPedidoProductos = new JTextField();
    private final JTextField txtEditarPedidoId = new JTextField();
    private final JTextField txtEditarPedidoClienteId = new JTextField();
    private final JTextField txtEditarPedidoProductos = new JTextField();
    private final JTextField txtEliminarPedidoId = new JTextField();

    public MainFrame(ClienteBL clienteBL, PedidoBL pedidoBL) {
        this.clienteBL = clienteBL;
        this.pedidoBL = pedidoBL;

        setTitle("Ejemplo CRUD - Cliente y Pedido");
        setSize(900, 560);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTabbedPane tabsPrincipales = new JTabbedPane();
        tabsPrincipales.addTab("Cliente", construirTabCliente());
        tabsPrincipales.addTab("Pedido", construirTabPedido());

        add(tabsPrincipales);
        refrescarTablas();
    }

    private JPanel construirTabCliente() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JScrollPane(new JTable(clienteModel)), BorderLayout.CENTER);

        JTabbedPane subtabs = new JTabbedPane();
        subtabs.addTab("Agregar", construirSubtabAgregarCliente());
        subtabs.addTab("Editar", construirSubtabEditarCliente());
        subtabs.addTab("Eliminar", construirSubtabEliminarCliente());

        panel.add(subtabs, BorderLayout.SOUTH);
        return panel;
    }

    private JPanel construirTabPedido() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JScrollPane(new JTable(pedidoModel)), BorderLayout.CENTER);

        JTabbedPane subtabs = new JTabbedPane();
        subtabs.addTab("Agregar", construirSubtabAgregarPedido());
        subtabs.addTab("Editar", construirSubtabEditarPedido());
        subtabs.addTab("Eliminar", construirSubtabEliminarPedido());

        panel.add(subtabs, BorderLayout.SOUTH);
        return panel;
    }

    private JPanel construirSubtabAgregarCliente() {
        JPanel panel = new JPanel(new GridLayout(0, 2, 8, 8));
        panel.add(new JLabel("Nombre:"));
        panel.add(txtAgregarClienteNombre);

        JButton btn = new JButton("Agregar Cliente");
        btn.addActionListener(e -> agregarCliente());
        panel.add(btn);
        panel.add(new JLabel("ID se genera automatico"));
        return panel;
    }

    private JPanel construirSubtabEditarCliente() {
        JPanel panel = new JPanel(new GridLayout(0, 2, 8, 8));
        panel.add(new JLabel("ID:"));
        panel.add(txtEditarClienteId);
        panel.add(new JLabel("Nombre nuevo:"));
        panel.add(txtEditarClienteNombre);

        JButton btn = new JButton("Editar Cliente");
        btn.addActionListener(e -> actualizarCliente());
        panel.add(btn);
        panel.add(new JLabel(""));
        return panel;
    }

    private JPanel construirSubtabEliminarCliente() {
        JPanel panel = new JPanel(new GridLayout(0, 2, 8, 8));
        panel.add(new JLabel("ID:"));
        panel.add(txtEliminarClienteId);

        JButton btn = new JButton("Eliminar Cliente");
        btn.addActionListener(e -> eliminarCliente());
        panel.add(btn);
        panel.add(new JLabel("Elimina pedidos asociados"));
        return panel;
    }

    private JPanel construirSubtabAgregarPedido() {
        JPanel panel = new JPanel(new GridLayout(0, 2, 8, 8));
        panel.add(new JLabel("Cliente ID:"));
        panel.add(txtAgregarPedidoClienteId);
        panel.add(new JLabel("Productos:"));
        panel.add(txtAgregarPedidoProductos);

        JButton btn = new JButton("Agregar Pedido");
        btn.addActionListener(e -> agregarPedido());
        panel.add(btn);
        panel.add(new JLabel("ID se genera automatico"));
        return panel;
    }

    private JPanel construirSubtabEditarPedido() {
        JPanel panel = new JPanel(new GridLayout(0, 2, 8, 8));
        panel.add(new JLabel("Pedido ID:"));
        panel.add(txtEditarPedidoId);
        panel.add(new JLabel("Cliente ID:"));
        panel.add(txtEditarPedidoClienteId);
        panel.add(new JLabel("Productos:"));
        panel.add(txtEditarPedidoProductos);

        JButton btn = new JButton("Editar Pedido");
        btn.addActionListener(e -> actualizarPedido());
        panel.add(btn);
        panel.add(new JLabel(""));
        return panel;
    }

    private JPanel construirSubtabEliminarPedido() {
        JPanel panel = new JPanel(new GridLayout(0, 2, 8, 8));
        panel.add(new JLabel("ID:"));
        panel.add(txtEliminarPedidoId);

        JButton btn = new JButton("Eliminar Pedido");
        btn.addActionListener(e -> eliminarPedido());
        panel.add(btn);
        panel.add(new JLabel(""));
        return panel;
    }

    private void agregarCliente() {
        Cliente cliente = clienteBL.agregar(txtAgregarClienteNombre.getText());
        if (cliente == null) {
            mostrarError("No se pudo agregar cliente (nombre invalido).");
            return;
        }
        JOptionPane.showMessageDialog(this, "Cliente agregado con ID: " + cliente.getId());
        refrescarTablas();
    }

    private void agregarPedido() {
        try {
            int clienteId = Integer.parseInt(txtAgregarPedidoClienteId.getText().trim());
            Pedido pedido = pedidoBL.agregar(clienteId, txtAgregarPedidoProductos.getText());
            if (pedido == null) {
                mostrarError("No se pudo agregar pedido (cliente inexistente o productos vacios).");
                return;
            }
            JOptionPane.showMessageDialog(this, "Pedido agregado con ID: " + pedido.getId());
            refrescarTablas();
        } catch (NumberFormatException ex) {
            mostrarError("Cliente ID invalido.");
        }
    }

    private void actualizarCliente() {
        try {
            int id = Integer.parseInt(txtEditarClienteId.getText().trim());
            if (!clienteBL.actualizar(id, txtEditarClienteNombre.getText())) {
                mostrarError("No se pudo actualizar cliente.");
                return;
            }
            refrescarTablas();
        } catch (NumberFormatException ex) {
            mostrarError("ID de cliente invalido.");
        }
    }

    private void actualizarPedido() {
        try {
            int id = Integer.parseInt(txtEditarPedidoId.getText().trim());
            int clienteId = Integer.parseInt(txtEditarPedidoClienteId.getText().trim());
            if (!pedidoBL.actualizar(id, clienteId, txtEditarPedidoProductos.getText())) {
                mostrarError("No se pudo actualizar pedido.");
                return;
            }
            refrescarTablas();
        } catch (NumberFormatException ex) {
            mostrarError("ID de pedido o cliente invalido.");
        }
    }

    private void eliminarCliente() {
        try {
            int id = Integer.parseInt(txtEliminarClienteId.getText().trim());
            if (!clienteBL.eliminar(id)) {
                mostrarError("No se pudo eliminar cliente.");
                return;
            }
            refrescarTablas();
        } catch (NumberFormatException ex) {
            mostrarError("ID de cliente invalido.");
        }
    }

    private void eliminarPedido() {
        try {
            int id = Integer.parseInt(txtEliminarPedidoId.getText().trim());
            if (!pedidoBL.eliminar(id)) {
                mostrarError("No se pudo eliminar pedido.");
                return;
            }
            refrescarTablas();
        } catch (NumberFormatException ex) {
            mostrarError("ID de pedido invalido.");
        }
    }

    private void refrescarTablas() {
        clienteModel.setRowCount(0);
        List<Cliente> clientes = clienteBL.listar();
        for (Cliente cliente : clientes) {
            clienteModel.addRow(new Object[]{cliente.getId(), cliente.getNombre()});
        }

        pedidoModel.setRowCount(0);
        List<Pedido> pedidos = pedidoBL.listar();
        for (Pedido pedido : pedidos) {
            pedidoModel.addRow(new Object[]{pedido.getId(), pedido.getClienteId(), pedido.getProductos()});
        }
    }

    private void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
