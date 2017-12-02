package Main.DAO.Interfaces;

import Main.Entity.Companies;

public interface ICompaniesDAO extends IDAO <Companies> {

    public void create(Companies companies);

    public void delete(Companies companies);

    public void update(Companies companies);

    public Companies read(Companies companies);

    public long exists(Companies companies );


}
