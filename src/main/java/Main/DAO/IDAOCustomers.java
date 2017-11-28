package Main.DAO;

import Main.Entity.Customers;
import Main.Utils;

public interface IDAOCustomers extends IDAO <Customers,Long>{
    public void create(Customers customers);

    public void delete(Customers customers);

    public void update(Customers customers);

    public Customers read(Long id);

    public default boolean exists(Customers customers ){
        return Utils.getSession().get(Customers.class,new Long (customers.getId())) != null;
    }
}
