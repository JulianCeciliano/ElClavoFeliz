package elclavofeliz.et;

public class Pedido {
    private int id;
    private int clienteId;
    private String productos;

    public Pedido(int id, int clienteId, String productos) {
        this.id = id;
        this.clienteId = clienteId;
        this.productos = productos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public String getProductos() {
        return productos;
    }

    public void setProductos(String productos) {
        this.productos = productos;
    }
}
