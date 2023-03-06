package toby.springframework.domain;

import java.sql.*;

public class UsersDao {

    public void add(Users users) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3307/toby_spring_framework", "root", "dkahffk11");
        PreparedStatement ps = c.prepareStatement("insert into users(id,name,password) values(?,?,?)");
        ps.setString(1, users.getId());
        ps.setString(2, users.getName());
        ps.setString(3, users.getPassword());

        ps.executeUpdate();

        ps.close();
        c.close();

    }

    public Users get(String id) throws ClassNotFoundException , SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3307/toby_spring_framework", "root", "dkahffk11");
        PreparedStatement ps = c.prepareStatement("select * from users where id = ?");
        ps.setString(1, id);

        ResultSet rs = ps.executeQuery();
        rs.next();
        Users users = new Users();
        users.setId(rs.getString("id"));
        users.setName(rs.getString("name"));
        users.setPassword(rs.getString("password"));

        rs.close();
        ps.close();
        c.close();

        return users;
    }
}
