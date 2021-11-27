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
    public void addProductToPedido(Product product, int cantidad){
        PedidoProduct pedidoProduct = new PedidoProduct();

        pedidoProduct.setProduct(product);
        pedidoProduct.setCantidadProduct(cantidad);

        this.pedidoProducts.add(pedidoProduct);
    }

    public void removeProductToPedido(Product product)
    {
        for (PedidoProduct pedidoProduct: this.pedidoProducts)
        {
            Product aux = pedidoProduct.getProduct();

            if (aux.getId() == product.getId())
            {
                this.pedidoProducts.remove(pedidoProduct);
                break;
            }
        }

    }

    public Double getTotalBalance(){
        Double amount = 0.0;
        for(PedidoProduct pedidoProduct : this.pedidoProducts){
            amount+= pedidoProduct.getCantidadProduct() * pedidoProduct.getProduct().getPrice();
        }
        return amount;
    }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Product getProductByName(String name){
        List<PedidoProduct> list = this.pedidoProducts;
        for(PedidoProduct pedidoProduct: list){
            if(pedidoProduct.getProduct().getName().compareTo(name) == 0){
                return pedidoProduct.getProduct();
            }
        }
        return null;
    }
}
