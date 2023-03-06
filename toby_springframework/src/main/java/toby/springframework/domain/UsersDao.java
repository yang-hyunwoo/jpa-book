package toby.springframework.domain;

import java.sql.*;

public class UsersDao {

    /*
        Class.forName()은 클래스로더 라는 녀석을 통해서
        해당 데이터베이스 드라이버를 로드할 뿐
        데이터베이스와의 연결에 관해서는 아무런 동작도 하지 않는다고 합니다.

     */

    /*
     JDBC 4.0 이상에서는 Class.forName을 통해 jdbc.Driver를 호출하지 않아도 자동으로 드라이버를 초기화 한다.
     */

    public void add(Users users) throws ClassNotFoundException, SQLException {
//        Class.forName("com.mysql.jdbc.Driver");
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
//        Class.forName("com.mysql.jdbc.Driver");
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
