package hibernate.example.dao;

import hibernate.example.connection.HibernateUtil;
import hibernate.example.exceptions.DaoException;
import hibernate.example.model.Role;
import org.hibernate.Session;

import java.util.List;

public class RoleDao {

    public long insert(Role role) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            session.beginTransaction();
            session.save(role);
            session.getTransaction().commit();

            return role.getId();

        } catch (Exception e) {
            throw new DaoException("inserting error, Obj = " + role, e);
        }
    }

    public Role getById(long roleId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            return session.get(Role.class, roleId);

        } catch (Exception e) {
            throw new DaoException("get by id error, id = " + roleId, e);
        }
    }

    public List<Role> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            return session.createQuery("" +
                    "select r " +
                    "from Role r", Role.class)
                    .getResultList();

        } catch (Exception e) {
            throw new DaoException("get all roles error", e);
        }
    }

    public List<Role> getRolesByUserId(long userId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            return session.createQuery("" +
                    "select r " +
                    "from Role r " +
                    "join r.users u " +
                    "where u.id =:id", Role.class)
                    .setParameter("id", userId)
                    .getResultList();
        }
    }

    public Role update(Role role) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();

            Role result = (Role) session.merge(role);
            session.getTransaction().commit();

            return result;
        } catch (Exception e) {
            throw new DaoException("update error, obj = " + role, e);
        }
    }

    public void deleteById(long roleId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();

            Role role = session.get(Role.class, roleId);
            session.remove(role);

            session.getTransaction().commit();
        } catch (Exception e) {
            throw new DaoException("delete error, role id = " + roleId, e);
        }
    }
}
