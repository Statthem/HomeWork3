package Main.DAO.Classes;

import Main.DAO.Interfaces.ICustomersDAO;
import Main.Entity.Customers;

public class CustomersDAO<Customer> implements ICustomersDAO {
    @Override
    public void create(Customers customers) {

    }

    @Override
    public void delete(Customers customers) {

    }

    @Override
    public void update(Customers customers) {

    }

    @Override
    public Customers read(Customers entity) {
        return null;
    }

    @Override
    public Customers read(java.lang.Long id) {
        return null;
    }

    @Override
    public boolean exists(Customers customers) {
        return false;
    }
}
