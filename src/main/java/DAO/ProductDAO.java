package DAO;

import form.Product;

import java.util.List;

public interface ProductDAO {

    public Product findById(Integer id);
    public Product insert(Product product);
    public Product update(Product product);
    public List<Product> listProducts();
    public List<Product> listProductsByCategoria(Integer categoria);
}
