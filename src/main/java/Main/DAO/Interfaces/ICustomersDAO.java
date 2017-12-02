package Main.DAO.Interfaces;

import Main.Entity.Customers;

public interface ICustomersDAO extends IDAO <Customers>{
    public void create(Customers customers);

    public void delete(Customers customers);

    public void update(Customers customers);

    public Customers read(Customers customers);

    public long exists(Customers customers );
}
