package form;

import java.util.ArrayList;
import java.util.List;

public class Pedido {

    public Integer id;
    public String description;
    public List<PedidoProduct> pedidoProducts;

    public Pedido()
    {
        List<PedidoProduct> list = new ArrayList<>();
        this.id = 1;
        this.description = "XD";
        this.pedidoProducts = list;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public List<PedidoProduct> getProductList() { return pedidoProducts; }
    public void setProductList(List<PedidoProduct> productList) { this.pedidoProducts = productList; }
    public void addProductToPedido(PedidoProduct pedidoProduct){ this.pedidoProducts.add(pedidoProduct); }
    public Double getTotalBalance(){
        Double amount = 0.0;
        for(PedidoProduct pedidoProduct : this.pedidoProducts){
            amount+= pedidoProduct.getCantidadProduct() * pedidoProduct.getProduct().getPrice();
        }
        return amount;
    }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
