package elclavofeliz.dal;

import elclavofeliz.et.Cliente;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAL {
    private final List<Cliente> clientes = new ArrayList<>();
    private int siguienteId = 1;

    public List<Cliente> listar() {
        return new ArrayList<>(clientes);
    }

    public Cliente buscarPorId(int id) {
        for (Cliente cliente : clientes) {
            if (cliente.getId() == id) {
                return cliente;
            }
        }
        return null;
    }

    public Cliente agregar(String nombre) {
        Cliente cliente = new Cliente(siguienteId++, nombre);
        clientes.add(cliente);
        return cliente;
    }

    public boolean actualizar(Cliente clienteActualizado) {
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getId() == clienteActualizado.getId()) {
                clientes.set(i, clienteActualizado);
                return true;
            }
        }
        return false;
    }

    public boolean eliminar(int id) {
        Cliente cliente = buscarPorId(id);
        if (cliente == null) {
            return false;
        }
        return clientes.remove(cliente);
    }
}
