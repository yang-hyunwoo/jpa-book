package toby.springframework;

import toby.springframework.domain.Users;
import toby.springframework.domain.UsersDao;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException , SQLException {
        UsersDao usersDao = new UsersDao();
        Users users = new Users();

        users.setId("whiteship");
        users.setName("양현우");
        users.setPassword("yang");

        usersDao.add(users);

        System.out.println(users.getId() + "등록 성공");

        Users user2 = usersDao.get(users.getId());
        System.out.println(user2.getName());
        System.out.println(user2.getPassword());

        System.out.println(user2.getId() + "조회 성공");
    }
}