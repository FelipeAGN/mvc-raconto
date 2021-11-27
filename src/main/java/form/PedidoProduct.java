package form;

public class PedidoProduct {

    private Integer id;
    private Integer id_order;
    private Integer id_product;
    private Integer cantidadProduct;

    public PedidoProduct() { }

    public PedidoProduct(Integer id, Integer id_order, Integer id_product) {
        this.id = id;
        this.id_order = id_order;
        this.id_product = id_product;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId_order() {
        return id_order;
    }

    public void setId_order(Integer id_order) {
        this.id_order = id_order;
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
