package Main.DAO.Interfaces;


import Main.Entity.Skills;

public interface ISkillDAO extends IDAO<Skills> {
    public void create(Skills skills);

    public void delete(Skills skills);

    public void update(Skills skills);

    public Skills read(Skills skills);

    public long exists(Skills skills);
}
