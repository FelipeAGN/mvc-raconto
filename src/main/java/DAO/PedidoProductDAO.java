package DAO;

import form.Client;

import java.util.List;

public interface PedidoProductDAO {

    public Client findById(Integer id);
    public Client insert(Client client);
    public Client update(Client client);
    public List<Client> listClients();
}
