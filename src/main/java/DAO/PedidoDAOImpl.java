package DAO;

import DB.DataBase;
import form.Pedido;
import form.PedidoProduct;
import form.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAOImpl implements PedidoDAO {

    private static Connection conexion;
    private static PreparedStatement sentencia;
    private static ResultSet resultado;
    private static int resultadoParaEnteros;
    public String query;
    public String query2;

    @Override
    public Pedido findPedidoByIdClient(Integer idClient) {
        query = "select id from pedido where id_client=?";
        Pedido pedido = null;
        List<PedidoProduct> pedidoProductList;
        try {
            conexion = DataBase.connection();
            sentencia = conexion.prepareStatement(query);
            sentencia.setInt(1,idClient);
            resultado = sentencia.executeQuery();

            while (resultado.next()){
                pedido = new Pedido(
                        resultado.getInt("id"),
                        resultado.getDouble("totalBalance"),
                        resultado.getDouble("tipBalance"),
                        resultado.getString("description")
                );
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return pedido;
    }

    @Override
    public Pedido insert(Pedido pedido) {
        query = "insert into pedido (totalBalance,tipBalance,description) values (?,?,?)";
        try{
            conexion = DataBase.connection();
            sentencia = conexion.prepareStatement(query);
            sentencia.setDouble(1,pedido.getTotalBalance());
            sentencia.setDouble(2,pedido.getTipBalance());
            sentencia.setString(3,pedido.getDescription());
            resultadoParaEnteros = sentencia.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if(resultadoParaEnteros>0){
            System.out.println("Pedido creado");
        }

        query = "select * from pedido ORDER BY created_at DESC LIMIT 1";
        Pedido pedidoResult = null;
        try{
            conexion = DataBase.connection();
            sentencia = conexion.prepareStatement(query);
            resultado = sentencia.executeQuery();

            while (resultado.next()){
                pedidoResult.setId(resultado.getInt("id"));
                pedidoResult.setTotalBalance(resultado.getDouble("totalBalance"));
                pedidoResult.setTipBalance(resultado.getDouble("tipBalance"));
                pedidoResult.setDescription(resultado.getString("description"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if(pedidoResult != null){
            System.out.println("Pedido con id: " + pedidoResult.getId());
            return  pedidoResult;
        }else {
            return null;
        }
    }

//    @Override
//    public Pedido insert(Pedido pedido) {
//
//        query2 = "select price from product where id = ?";
//        List<Double> balance = new ArrayList<>();
//        Double priceProduct = null;
//        Double balanceProduct = null;
//        Double totalBalancePedido = null;
//        try{
//            for(PedidoProduct pedidoProduct: pedido.getPedidoProducts()){
//                conexion = DataBase.connection();
//                sentencia = conexion.prepareStatement(query2);
//                sentencia.setInt(1, pedidoProduct.getId_product());
//                resultado = sentencia.executeQuery();
//
//                while (resultado.next()){
//                    priceProduct = resultado.getDouble("price");
//                }
//                if(priceProduct!=null){
//                    balanceProduct = priceProduct * pedidoProduct.getCantidadProduct();
//                    balance.add(balanceProduct);
//                }else {
//                    System.out.println("No se encontro el precio del producto");
//                }
//            }
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//        if(balance.size()>0){
//            for(Double x : balance){
//                totalBalancePedido+=x;
//            }
//            double tipBalance = totalBalancePedido * 0.10;
//
//            query = "insert into pedido(id_client,description,totalBalance,tipBalance)";
//            try{
//                conexion = DataBase.connection();
//                sentencia = conexion.prepareStatement(query);
//                sentencia.setInt(1,pedido.getId_cliente());
//                sentencia.setString(2,pedido.getDescription());
//                sentencia.setDouble(3,totalBalancePedido);
//                sentencia.setDouble(4,tipBalance);
//                resultadoParaEnteros = sentencia.executeUpdate();
//            } catch (SQLException throwables) {
//                throwables.printStackTrace();
//            }
//            if(resultadoParaEnteros >0){
//                System.out.println("Pedido ingresado: " + pedido.getDescription());
//            }
//        }
//        return pedido;
//    }

    @Override
    public Pedido update(Pedido pedido) {
        return null;
    }



    @Override
    public Pedido addProductToPedido(Pedido pedido, Product product, Integer cantidad) {
        PedidoProduct pedidoProduct = new PedidoProduct();
        pedidoProduct.setId_product(product.getId());
        pedidoProduct.setId_pedido(pedido.getId());
        pedidoProduct.setCantidadProduct(cantidad);
        pedido.setProductToPedido(pedidoProduct);

        query = "INSERT INTO pedido_product(id_pedido,id_product,cantidad) values (?,?,?)";
        try{
            conexion = DataBase.connection();
            sentencia = conexion.prepareStatement(query);
            sentencia.setInt(1,pedidoProduct.getId());
            sentencia.setInt(2,pedidoProduct.getId());
            sentencia.setInt(3,pedidoProduct.getCantidadProduct());
            resultadoParaEnteros = sentencia.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if(resultadoParaEnteros >0){
            System.out.println("Producto: " + product.getName() + "Agregado a pedido: " + pedido.getId());
        }




        return pedido;
    }
}
