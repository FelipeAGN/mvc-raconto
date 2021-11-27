package DAO;

import DB.DataBase;
import form.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOImpl implements ProductDAO{

    private static Connection conexion;
    private static PreparedStatement sentencia;
    private static ResultSet resultado;
    private static int resultadoParaEnteros;
    public String query;

    @Override
    public Product findById(Integer id) {
        query = "select * from product where id=?";
        Product productReturn = null;
        try{
            conexion = DataBase.connection();
            sentencia = conexion.prepareStatement(query);
            sentencia.setInt(1,id);
            resultado = sentencia.executeQuery();

            while (resultado.next()){
                Product product = new Product(
                        resultado.getString("name"),
                        resultado.getDouble("price"),
                        resultado.getString("description")
                );
                product.setId(id);
                productReturn = product;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return productReturn;
    }

    @Override
    public Product insert(Product product) {
        query = "insert into product (name,price,description) values (?,?,?)";
        try{
            conexion = DataBase.connection();
            sentencia = conexion.prepareStatement(query);
            sentencia.setString(1,product.getName());
            sentencia.setDouble(2,product.getPrice());
            sentencia.setString(3,product.getDescription());
            resultadoParaEnteros = sentencia.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if(resultadoParaEnteros >0){
            System.out.println("Producto creado: " + product.getName());
        }
        return product;
    }

    @Override
    public Product update(Product product) {
        return null;
    }

    @Override
    public List<Product> listProducts() {
        List<Product> list = new ArrayList<>();
        query = "select * from product";
        try {
            conexion = DataBase.connection();
            sentencia = conexion.prepareStatement(query);
            resultado = sentencia.executeQuery();

            while (resultado.next()){
                Product product = new Product(
                        resultado.getInt("id"),
                        resultado.getString("name"),
                        resultado.getDouble("price"),
                        resultado.getInt("category"),
                        resultado.getString("description"),
                        resultado.getString("path")
                );
                list.add(product);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Product> listProductsByCategoria(Integer categoria) {
        List<Product> list = new ArrayList<>();
        query = "select * from product WHERE category = ?";
        try {
            conexion = DataBase.connection();
            sentencia = conexion.prepareStatement(query);
            sentencia.setInt(1,categoria);
            resultado = sentencia.executeQuery();

            while (resultado.next()){
                Product product = new Product(
                        resultado.getInt("id"),
                        resultado.getString("name"),
                        resultado.getDouble("price"),
                        resultado.getInt("category"),
                        resultado.getString("description"),
                        resultado.getString("path")
                );
                list.add(product);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if(!list.isEmpty()){ return list; }
        return  null;
    }
}
