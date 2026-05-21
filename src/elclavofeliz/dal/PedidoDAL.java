package elclavofeliz.dal;

import elclavofeliz.et.Pedido;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAL {
    private final List<Pedido> pedidos = new ArrayList<>();
    private int siguienteId = 1;

    public List<Pedido> listar() {
        return new ArrayList<>(pedidos);
    }

    public Pedido buscarPorId(int id) {
        for (Pedido pedido : pedidos) {
            if (pedido.getId() == id) {
                return pedido;
            }
        }
        return null;
    }

    public List<Pedido> listarPorCliente(int clienteId) {
        List<Pedido> resultado = new ArrayList<>();
        for (Pedido pedido : pedidos) {
            if (pedido.getClienteId() == clienteId) {
                resultado.add(pedido);
            }
        }
        return resultado;
    }

    public Pedido agregar(int clienteId, String productos) {
        Pedido pedido = new Pedido(siguienteId++, clienteId, productos);
        pedidos.add(pedido);
        return pedido;
    }

    public boolean actualizar(Pedido pedidoActualizado) {
        for (int i = 0; i < pedidos.size(); i++) {
            if (pedidos.get(i).getId() == pedidoActualizado.getId()) {
                pedidos.set(i, pedidoActualizado);
                return true;
            }
        }
        return false;
    }

    public boolean eliminar(int id) {
        Pedido pedido = buscarPorId(id);
        if (pedido == null) {
            return false;
        }
        return pedidos.remove(pedido);
    }

    public void eliminarPorCliente(int clienteId) {
        pedidos.removeIf(p -> p.getClienteId() == clienteId);
    }
}
