import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.ConnectException;
import java.net.URL;
import java.nio.file.Path;
import java.sql.*;
import java.util.Properties;


//连接数据库用户登录
public class JDBCUTILS {
    private static String url;//局部变量提供给到成员的位置，声明3个变量
    private static String user;
    private static String password;
    private static String driver;//把配置文件的driver读进来，变成通用的，声明成员变量driver

    //文件的读取，只需要读一次即可拿到这些值，使用静态代码块，静态代码块随着类的加载，只执行一次
    static { //读取文件资源，获取值
        try {
            //创建properties集合类
            Properties pro=new Properties();
            //classLoader类加载器，获取src路径下的文件
            ClassLoader classLoader=JDBCUTILS.class.getClassLoader();
            URL res=classLoader.getResource("jdbc.properties"); //定义文件的绝对路径
            String path=res.getPath();//获取字符串路径
            //System.out.println(path);
            //加载文件
//            pro.load(new FileReader("src/jdbc.properties"));src/jdbc.properties":src下面的jdbc.properties
//            pro.load(new FileReader("D:\\java file\\demo\\src\\com\\demo3\\src\\jdbc.properties"));绝对路径
            pro.load(new FileReader(path));

            //获取数据，赋值
            url =pro.getProperty("url");//url要和配置文件jdbc.properties的名字一样
            System.out.println(url);
            user=pro.getProperty("user");
            password=pro.getProperty("password");
            driver = pro.getProperty("driver");
            //注册驱动
            Class.forName(driver);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //抽取一个方法释放资源
    public static void close(Statement stmt, Connection conn) {
        if (stmt!=null){
            try {
                stmt.close();//先释放小的再释放大的
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    //抽取一个方法获取连接对象
    public static Connection getCinnection()throws SQLException {//获取连接，返回return连接对象

       // return null; 占位，不报错
        return DriverManager.getConnection(url,user,password);//局部变量变成成员变量，静态代码块给成员变量
    }

        //抽取一个方法释放资源，重载的形式
    public static void close(ResultSet rs,Statement stmt,Connection conn){
        if (rs!=null){
            try {
                rs.close();//先释放小的再释放大的
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (stmt!=null){
            try {
                stmt.close();//先释放小的再释放大的
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (conn!=null){
            try {
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

}
