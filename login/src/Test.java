import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("请输入用户名");
        String username=sc.nextLine();
        System.out.println("请输入密码");
        String password=sc.nextLine();
        //调用方法，login是非静态地址，创建对象
        boolean flag = new Test().login(username,password);
        //判断结果，输出不同语句
        if(flag){
            System.out.println("登录成功");
        }else{
            System.out.println("用户名或密码错误");
        }
    }
    private boolean login(String username, String password) {
        if (username == null || password == null) {
            return false; //uwername和password都不为空
        }
        //连接数据库判断是否登录成功
        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        try {
            //获取数据库
//            Connection conn=JDBCUTILS.getCinnection();
        conn = JDBCUTILS.getCinnection();
        //定义sql
            String sql ="select * from user where name=? and password=?";
        //获取执行sql对象
//            Preparedstatement pstmt=conn.Preparestatement(sql);
            pstmt =conn.prepareStatement(sql);
            //给？赋值
            pstmt.setString(1,username);//第一个？
            pstmt.setString(2,password);//第二个？
            //执行查询，不需要传递参数
            rs=pstmt.executeQuery();
            //判断结果里有没有数据
            /*if (rs.next()){//如果有下一行，则返回return
                return true;
            }else{
            return false;
            }
             */
            return rs.next();//如果有下一行，则返回true
        } catch (Exception e) {
            e.printStackTrace();
        }finally {//释放资源，把try里的conn,stmt声明在外面等于null
            JDBCUTILS.close(rs,pstmt,conn);
        }
        return false;
    }
}
