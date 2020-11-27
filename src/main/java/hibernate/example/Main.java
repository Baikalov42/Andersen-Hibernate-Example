package hibernate.example;


import hibernate.example.dao.RoleDao;
import hibernate.example.dao.UserDao;
import hibernate.example.model.Role;
import hibernate.example.model.User;

public class Main {

    public static void main(String[] args) {
        RoleDao roleDao = new RoleDao();
        UserDao userDao = new UserDao();

        System.out.println("insert role Student");
        System.out.println(roleDao.insert(new Role("Student", "st from hg")));
        System.out.println("----------------------------");

        System.out.println("insert role Teacher");
        System.out.println(roleDao.insert(new Role("Teacher", "tch from hg")));
        System.out.println("----------------------------");

        System.out.println("insert user Germiona");
        System.out.println(userDao.insert(new User("Germiona", "Grainjer")));
        System.out.println("----------------------------");

        System.out.println("insert user Harry");
        System.out.println(userDao.insert(new User("Harry", "Potter")));
        System.out.println("----------------------------");

        System.out.println("get role by id = 2");
        System.out.println(roleDao.getById(2));
        System.out.println("----------------------------");

        System.out.println("get user by id = 2");
        System.out.println(userDao.getById(2));
        System.out.println("----------------------------");

        System.out.println("get all roles");
        System.out.println(roleDao.getAll());
        System.out.println("----------------------------");

        System.out.println("get all users");
        System.out.println(userDao.getAll());
        System.out.println("----------------------------");

        System.out.println("update Harry -> HARRYTOMREDDL");
        System.out.println(userDao.update(new User(2, "HARRYTOMREDDL", "Potter")));
        System.out.println("----------------------------");

        System.out.println("update Teacher -> TEACHER");
        System.out.println(roleDao.update(new Role("TEACHER", "tch from hg")));
        System.out.println("----------------------------");

        System.out.println("get all roles");
        System.out.println(roleDao.getAll());
        System.out.println("----------------------------");

        System.out.println("get all users");
        System.out.println(userDao.getAll());
        System.out.println("----------------------------");

        System.out.println("add germona -> student");
        userDao.addRoleToUser(1, 1);
        System.out.println("----------------------------");

        System.out.println("add Harry -> student");
        userDao.addRoleToUser(2, 1);
        System.out.println("----------------------------");

        System.out.println("get all roles by user germiona");
        System.out.println(roleDao.getRolesByUserId(1));
        System.out.println("----------------------------");

        System.out.println("get all users by role student");
        System.out.println(userDao.getUsersByRoleId(1));
        System.out.println("----------------------------");

        System.out.println("delete teacher");
        userDao.deleteById(2);
        System.out.println("----------------------------");

        System.out.println("get all users");
        System.out.println(userDao.getAll());
        System.out.println("----------------------------");
    }
}
