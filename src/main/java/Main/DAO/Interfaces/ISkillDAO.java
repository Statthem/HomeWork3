package Main.DAO.Interfaces;


import Main.Entity.Skills;
import Main.Utils;

public interface ISkillDAO extends IDAO<Skills> {
    public void create(Skills skills);

    public void delete(Skills skills);

    public void update(Skills skills);

    public Skills read(Long id);

    public default boolean exists(Skills skills) {
        return Utils.getSession().get(Skills.class, new Long(skills.getId())) != null;
    }
}
