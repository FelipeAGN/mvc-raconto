package DAO;

import DB.DataBase;
import form.Pedido;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PedidoDAOImpl implements PedidoDAO {

    private static Connection conexion;
    private static PreparedStatement sentencia;
    private static ResultSet resultado;
    private static int resultadoParaEnteros;
    public String query;
    public String query2;

//    @Override
//    public Pedido findPedidoById(Integer id) {
//        query = "select * from pedido where id=?";
//        Pedido pedido1 = null;
//        try{
//            conexion = DataBase.connection();
//            sentencia = conexion.prepareStatement(query);
//            sentencia.setInt(1, id);
//            resultado = sentencia.executeQuery();
//
//            while (resultado.next()){
//                Pedido pedido = new Pedido(
//
//                );
//                pedido1 = pedido;
//            }
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//        if (pedido1 != null){ return pedido1; }
//        return null;
//    }

    @Override
    public Pedido insert(Pedido pedido) {
        query = "insert into pedido(desciption) values(?)";
        try{
            conexion = DataBase.connection();
            sentencia = conexion.prepareStatement(query);
            sentencia.setString(1,pedido.getDescription());
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
//    public Pedido update(Pedido pedido) {
//        return null;
//    }
//
//
//    // Para la carta: Setea una cantidad
//    @Override
//    public Pedido addProductToPedido(Pedido pedido, Product product, Integer cantidad) {
//        PedidoProduct pedidoProduct = new PedidoProduct();
//        pedidoProduct.setId_product(product.getId());
//        pedidoProduct.setId_pedido(pedido.getId());
//        pedidoProduct.setCantidadProduct(cantidad);
//        pedido.addProductToPedido(pedidoProduct);
//
//        query = "INSERT INTO pedido_product(id_pedido,id_product,cantidad) values (?,?,?)";
//        try{
//            conexion = DataBase.connection();
//            sentencia = conexion.prepareStatement(query);
//            sentencia.setInt(1,pedidoProduct.getId());
//            sentencia.setInt(2,pedidoProduct.getId());
//            sentencia.setInt(3,pedidoProduct.getCantidadProduct());
//            resultadoParaEnteros = sentencia.executeUpdate();
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//        if(resultadoParaEnteros >0){
//            System.out.println("Producto: " + product.getName() + "Agregado a pedido: " + pedido.getId());
//        }
//
//        List<PedidoProduct> list = pedido.getProductList();
//        Double amount = 0.0;
//
//        return pedido;
//    }

}
