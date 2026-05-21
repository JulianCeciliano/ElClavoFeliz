package elclavofeliz;

import elclavofeliz.bl.ClienteBL;
import elclavofeliz.bl.PedidoBL;
import elclavofeliz.dal.ClienteDAL;
import elclavofeliz.dal.PedidoDAL;
import elclavofeliz.gui.MainFrame;
import javax.swing.SwingUtilities;

public class ElClavoFeliz {

    public static void main(String[] args) {
        ClienteDAL clienteDAL = new ClienteDAL();
        PedidoDAL pedidoDAL = new PedidoDAL();

        ClienteBL clienteBL = new ClienteBL(clienteDAL, pedidoDAL);
        PedidoBL pedidoBL = new PedidoBL(pedidoDAL, clienteBL);

        SwingUtilities.invokeLater(() -> new MainFrame(clienteBL, pedidoBL).setVisible(true));
    }
}
