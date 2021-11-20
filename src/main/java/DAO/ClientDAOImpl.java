package DAO;

import DB.DataBase;
import form.Client;

import java.sql.*;
import java.util.List;

public class ClientDAOImpl implements ClientDAO{

    private static Connection conexion;
    private static PreparedStatement sentencia;
    private static ResultSet resultado;
    private static int resultadoParaEnteros;
    public String query;

    @Override
    public Client findById(Integer id) {
        query = "select * from client where id=?";
        Client client = null;
        try{
            conexion = DataBase.connection();
            sentencia = conexion.prepareStatement(query);
            sentencia.setInt(1,id);
            resultado = sentencia.executeQuery();

            while (resultado.next()){
                Client client1 = new Client(
                        resultado.getString("name"),
                        resultado.getString("lastName"),
                        resultado.getString("email")
                );
                client1.setId(id);
                client = client1;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return client;
    }

    @Override
    public Client insert(Client client) {

        query = "insert into client (name,lastName,email) values (?,?,?)";
        try{
            conexion = DataBase.connection();
            sentencia = conexion.prepareStatement(query);
            sentencia.setString(1,client.getName());
            sentencia.setString(2,client.getLastName());
            sentencia.setString(3,client.getEmail());
            resultadoParaEnteros = sentencia.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if(resultadoParaEnteros >0){
            System.out.println("Cliente creado: " + client.getName());
        }
        return client;
    }

    @Override
    public Client update(Client client) {

        return null;
    }

    @Override
    public List<Client> listClients() {
        return null;
    }
}
