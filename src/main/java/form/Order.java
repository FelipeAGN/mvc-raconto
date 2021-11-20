package form;

import java.util.Date;
import java.util.List;

public class Order {

    public Integer id;
    public Double totalBalance;
    public Double tipBalance;
    public List<Product> productList;
    public String description;

    // Those should be timestamps
    public Date creationDate;
    public Date paymentDate;

    public Order() { }
    public Order(Double totalBalance, Double tipBalance, List<Product> productList, Date creationDate, Date paymentDate, String description) {
        this.totalBalance = totalBalance;
        this.tipBalance = tipBalance;
        this.productList = productList;
        this.creationDate = creationDate;
        this.paymentDate = paymentDate;
        this.description= description;
    }
    public Order(Integer id, Double totalBalance, Double tipBalance, List<Product> productList, Date creationDate, Date paymentDate, String description) {
        this.id = id;
        this.totalBalance = totalBalance;
        this.tipBalance = tipBalance;
        this.productList = productList;
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
    public List<Product> getProductList() { return productList; }
    public void setProductList(List<Product> productList) { this.productList = productList; }
    public Date getCreationDate() { return creationDate; }
    public void setCreationDate(Date creationDate) { this.creationDate = creationDate; }
    public Date getPaymentDate() { return paymentDate; }
    public void setPaymentDate(Date paymentDate) { this.paymentDate = paymentDate; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
