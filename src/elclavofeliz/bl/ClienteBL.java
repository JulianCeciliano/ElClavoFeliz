package elclavofeliz.bl;

import elclavofeliz.dal.ClienteDAL;
import elclavofeliz.dal.PedidoDAL;
import elclavofeliz.et.Cliente;
import java.util.List;

public class ClienteBL {
    //Hola amigo esto es un comentario
    private final ClienteDAL clienteDAL;
    private final PedidoDAL pedidoDAL;

    public ClienteBL(ClienteDAL clienteDAL, PedidoDAL pedidoDAL) {
        this.clienteDAL = clienteDAL;
        this.pedidoDAL = pedidoDAL;
    }

    public List<Cliente> listar() {
        return clienteDAL.listar();
    }

    public Cliente agregar(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            return null;
        }
        return clienteDAL.agregar(nombre.trim());
    }

    public boolean actualizar(int id, String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            return false;
        }
        return clienteDAL.actualizar(new Cliente(id, nombre.trim()));
    }

    public boolean eliminar(int id) {
        boolean eliminado = clienteDAL.eliminar(id);
        if (eliminado) {
            pedidoDAL.eliminarPorCliente(id);
        }
        return eliminado;
    }

    public Cliente buscarPorId(int id) {
        return clienteDAL.buscarPorId(id);
    }
}
