package DAO;

import form.Pedido;
import form.PedidoProduct;
import form.Product;

import java.util.List;

public interface PedidoDAO {

    public Pedido findPedidoByIdClient(Integer idClient);
    public Pedido insert(Pedido pedido);
    public Pedido update(Pedido pedido);

    // Agrega un producto al pedido y re calcula el balance del pedido en base a esta adicion.
    public Pedido addProductToPedido(Pedido pedido, Product product, Integer cantidad);
}
