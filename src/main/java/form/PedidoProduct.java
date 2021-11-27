package form;

public class PedidoProduct {

    private Integer id;
    private Integer id_pedido;
    private Integer id_product;
    private Integer cantidadProduct;

    public PedidoProduct() { }

    public PedidoProduct(Integer id, Integer id_order, Integer id_product) {
        this.id = id;
        this.id_pedido = id_order;
        this.id_product = id_product;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(Integer id_order) {
        this.id_pedido = id_order;
    }

    public Integer getId_product() {
        return id_product;
    }

    public void setId_product(Integer id_product) {
        this.id_product = id_product;
    }

    public Integer getCantidadProduct() { return cantidadProduct; }

    public void setCantidadProduct(Integer cantidadProduct) { this.cantidadProduct = cantidadProduct; }
}
