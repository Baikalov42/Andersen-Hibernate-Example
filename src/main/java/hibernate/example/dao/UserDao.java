package hibernate.example.dao;

import hibernate.example.connection.HibernateUtil;
import hibernate.example.exceptions.DaoException;
import hibernate.example.model.Role;
import hibernate.example.model.User;
import org.hibernate.Session;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class UserDao {

    public long insert(User user) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();

            return user.getId();

        } catch (Exception e) {
            throw new DaoException("inserting error, Obj = " + user, e);
        }
    }

    public User getById(long userId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            return session.get(User.class, userId);

        } catch (Exception e) {
            throw new DaoException("get by id error, id = " + userId, e);
        }
    }

    public List<User> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            return session.createQuery("" +
                    "select u " +
                    "from User u", User.class)
                    .getResultList();

        } catch (Exception e) {
            throw new DaoException("get all users error", e);
        }
    }

    public List<User> getUsersByRoleId(long roleId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            return session.createQuery("" +
                    "select u " +
                    "from User u " +
                    "join u.roles r " +
                    "where r.id =:id", User.class)
                    .setParameter("id", roleId)
                    .getResultList();
        }
    }

    public User update(User user) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();

            User result = (User) session.merge(user);
            session.getTransaction().commit();

            return result;
        } catch (Exception e) {
            throw new DaoException("update error, obj = " + user, e);
        }
    }

    public void addRoleToUser(long userId, long roleId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();

            User user = session.get(User.class, userId);
            Role role = session.get(Role.class, roleId);
            user.addRole(role);
            session.getTransaction().commit();

        } catch (Exception e) {
            throw new DaoException("add role to user error. user=" + userId + "role=" + roleId, e);
        }
    }

    public void removeRoleFromUser(long userId, long roleId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();

            User user = session.get(User.class, userId);
            Role role = session.get(Role.class, roleId);
            user.removeRole(role);
            session.getTransaction().commit();

        } catch (Exception e) {
            throw new DaoException("remove role from user error. user=" + userId + "role=" + roleId, e);
        }
    }

    public void deleteById(long userId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();

            User role = session.get(User.class, userId);
            session.remove(role);

            session.getTransaction().commit();
        } catch (Exception e) {
            throw new DaoException("delete error, user id = " + userId, e);
        }
    }
}
