package form;

public class PedidoProduct {

    private Product product;
    private Integer cantidadProduct;

    public PedidoProduct() { }

    public Integer getCantidadProduct() { return cantidadProduct; }
    public void setCantidadProduct(Integer cantidadProduct) { this.cantidadProduct = cantidadProduct; }
    public void addCantidad() { this.cantidadProduct++; }
    public void lessCantidad() { this.cantidadProduct--; }
    public Product getProduct() { return product; }
    public void setProduct(Product product) { this.product = product; }
}
