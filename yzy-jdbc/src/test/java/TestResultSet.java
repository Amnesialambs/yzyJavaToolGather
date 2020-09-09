import java.sql.*;

/**
 *  写这个类的目的是，测试 resultSet 的 next() 方法，每次调用是不是还要链接数据库，执行sql。我现在描述的也不是很清楚
 */
public class TestResultSet {


    public static void main(String[] args) {

        try {
            //1. 注册驱动 ps: com.mysql.jdbc.Driver 在版本 8.0.16 已被弃用。改用  com.mysql.cj.jdbc.Driver
            Class.forName("com.mysql.jdbc.Driver");
            //2. 建立数据库连接
            Connection connection = DriverManager.getConnection("jdbc:mysql://192.168.3.122/test","root","root");
            //3. 创建预编译
            String querySql = "select * from tm_user";
            PreparedStatement prepareStatement = connection.prepareStatement(querySql);
            ResultSet resultSet = prepareStatement.executeQuery();

            while (resultSet.next()){
                System.out.println(resultSet.getString(1));
            }
            String sql = "INSERT INTO test.tm_user(name, age, sex) VALUES ('张三', ?, '男') ";
            PreparedStatement prepareStatement1 = connection.prepareStatement(sql);
            for (int i=0;i<10000000;i++){
                prepareStatement1.setInt(1,i);
                prepareStatement1.addBatch();
            }
            prepareStatement1.executeBatch();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

}
