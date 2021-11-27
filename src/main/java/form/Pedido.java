package form;

import java.util.Date;
import java.util.List;

public class Pedido {

    public Integer id;
    public Integer id_cliente;
    public Double totalBalance;
    public Double tipBalance;
    public List<PedidoProduct> pedidoProducts;
    public String description;

    // Those should be timestamps
    public Date creationDate;
    public Date paymentDate;

    public Pedido() { }

    public Pedido(Integer id, Double totalBalance, Double tipBalance, String description) {
        this.id = id;
        this.totalBalance = totalBalance;
        this.tipBalance = tipBalance;
        this.description = description;
    }

    public Pedido(Double totalBalance, Double tipBalance, String description) {
        this.totalBalance = totalBalance;
        this.tipBalance = tipBalance;
        this.description = description;
    }

    public Pedido(Double totalBalance, Double tipBalance, List<PedidoProduct> productList, Date creationDate, Date paymentDate, String description) {
        this.totalBalance = totalBalance;
        this.tipBalance = tipBalance;
        this.pedidoProducts = productList;
        this.creationDate = creationDate;
        this.paymentDate = paymentDate;
        this.description= description;
    }
    public Pedido(Integer id, Double totalBalance, Double tipBalance, List<PedidoProduct> productList, Date creationDate, Date paymentDate, String description) {
        this.id = id;
        this.totalBalance = totalBalance;
        this.tipBalance = tipBalance;
        this.pedidoProducts = productList;
        this.creationDate = creationDate;
        this.paymentDate = paymentDate;
        this.description= description;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Double getTotalBalance() { return totalBalance; }
    public void setTotalBalance(Double totalBalance) { this.totalBalance = totalBalance; }
    public Double getTipBalance() { return tipBalance; }
    public void setTipBalance(Double tipBalance) { this.tipBalance = tipBalance; }
    public List<PedidoProduct> getProductList() { return pedidoProducts; }
    public void setProductList(List<PedidoProduct> productList) { this.pedidoProducts = productList; }
    public Date getCreationDate() { return creationDate; }
    public void setCreationDate(Date creationDate) { this.creationDate = creationDate; }
    public Date getPaymentDate() { return paymentDate; }
    public void setPaymentDate(Date paymentDate) { this.paymentDate = paymentDate; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Integer getId_cliente() { return id_cliente; }
    public void setId_cliente(Integer id_cliente) { this.id_cliente = id_cliente; }
    public void setProductToPedido(PedidoProduct pedidoProduct){ this.pedidoProducts.add(pedidoProduct); }
}
