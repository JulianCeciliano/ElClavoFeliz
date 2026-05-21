package elclavofeliz.bl;

import elclavofeliz.dal.PedidoDAL;
import elclavofeliz.et.Pedido;
import java.util.List;

public class PedidoBL {
    private final PedidoDAL pedidoDAL;
    private final ClienteBL clienteBL;

    public PedidoBL(PedidoDAL pedidoDAL, ClienteBL clienteBL) {
        this.pedidoDAL = pedidoDAL;
        this.clienteBL = clienteBL;
    }

    public List<Pedido> listar() {
        return pedidoDAL.listar();
    }

    public Pedido agregar(int clienteId, String productos) {
        if (!clienteExiste(clienteId) || productosInvalidos(productos)) {
            return null;
        }
        return pedidoDAL.agregar(clienteId, productos.trim());
    }

    public boolean actualizar(int id, int clienteId, String productos) {
        if (!clienteExiste(clienteId) || productosInvalidos(productos)) {
            return false;
        }
        return pedidoDAL.actualizar(new Pedido(id, clienteId, productos.trim()));
    }

    public boolean eliminar(int id) {
        return pedidoDAL.eliminar(id);
    }

    private boolean clienteExiste(int clienteId) {
        return clienteBL.buscarPorId(clienteId) != null;
    }

    private boolean productosInvalidos(String productos) {
        return productos == null || productos.trim().isEmpty();
    }
}
