import java.util.Scanner;

public class Login {
    public static void main(String[] args) {
        //用户名及密码
        String username="root";
        String password="123456";

        //键盘输入
        for(int i=0;i<3;i++){
            Scanner scanner=new Scanner(System .in);
            System.out.println("请输入用户名");
            String s1=scanner.nextLine();
            System.out.println("请输入密码");
            String s2=scanner.nextLine();
            if (s1.equals(username)&&s2.equals(password)){
                System.out.println("登录成功");
                break;
            }else{
                System.out.println("用户名密码错误");
                break;
            }
        }
    }
}
