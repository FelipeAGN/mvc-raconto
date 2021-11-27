package DAO;

import form.Pedido;
import form.PedidoProduct;

import java.util.List;

public interface PedidoDAO {

    public Pedido findPedidoByIdClient(Integer idClient);
    public Pedido insert(Pedido pedido);
    public Pedido update(Pedido pedido);
}
