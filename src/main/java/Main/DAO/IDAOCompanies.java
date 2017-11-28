package Main.DAO;

import Main.Entity.Companies;
import Main.Utils;

public interface IDAOCompanies extends IDAO <Companies,Long> {

    public void create(Companies companies);

    public void delete(Companies companies);

    public void update(Companies companies);

    public Companies read(Long id);

    public default boolean exists(Companies companies ){
        return Utils.getSession().get(Companies.class,new Long (companies.getId())) != null;
    }

}
