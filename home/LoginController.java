package home;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class LoginController
{
    @FXML
    private Button RegisterBtnOfLogin;
    @FXML
    private Button CancelButton;

    @FXML
    private TextField EnterUsernameTextField;

    @FXML
    private TextField EnterPasswordFied;

    @FXML
    void login(ActionEvent event) throws IOException {
        String username=EnterUsernameTextField.getText();//用户名
        String password=EnterPasswordFied.getText();//密码
        if(user_Login(username, password))
        {
            Data.isLogin = true;
            Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
            Stage FutureStage = new Stage();
            Scene scene = new Scene(root, 1050, 576);
            FutureStage.setScene(scene);
            FutureStage.initStyle(StageStyle.UNDECORATED);
            FutureStage.show();
            Stage stage = (Stage) CancelButton.getScene().getWindow();
            stage.close();
        }
        else
        {
            //失败
        }
    }

    public boolean user_Login(String name, String password)//登陆成功的话返回true
    {
        boolean login_success = false;//定义是否登陆成功的变量
        //JDBC中有的变量名字
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet res=null;

        //单独定义从之前定义的结构体userProfile的map中得到的ID和密码
        //String user_id=name;//用户ID
        //String user_password=password;//用户密码
        try {
            //1注册驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //第二步：获取连接（JVM和数据库之间的通道打开）
            //"jdbc:mysql://localhost:3306/数据库名字","root","你的数据库密码"
            conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/todolist_new?useSSL=false&useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&serverTimezone=GMT%2B8&zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC","root","pyl20021124");
            //第三步：获取预编译的数据库操作对象 （专门执行SQL语句的对象）
            //从用户那张表中获取ID和密码，这个地方需要修改一下表的名字
            String sql="select * from user_info where user_name=? and user_password=?"; // ?为占位符
            ps=conn.prepareStatement(sql);
            ps.setString(1,name);//将loginName传递给上边的占位符?的地方,如果成功就返回1
            ps.setString(2,password);//和上一条同理，成功返回2
            //第四布：执行SQL语句
            res=ps.executeQuery();//executeQuery()下达select指令
            //第五步: 处理结果集
            if (res.next()){
                Data.NickName=res.getString("user_nickname");
                Data.userName=name;
                Data.password=password;
                return true; //若查询结果有数据返回则登录成功
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //第六步：释放资源（使用完资源之后关闭.Java和数据库属于进程间的通信）
            if(res!=null){
                try {
                    res.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(ps!=null){
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(conn!=null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return login_success;
    }

    public void cancelButtonAction(ActionEvent event) throws CloneNotSupportedException, IOException {
        Data.update();
        Data.threadRunFlag=false;
        Stage stage=(Stage)CancelButton.getScene().getWindow();
        stage.close();
    }

    public void returnToHome(ActionEvent event)throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
        Stage FutureStage = new Stage();
        Scene scene = new Scene(root, 1050, 576);
        FutureStage.setScene(scene);
        FutureStage.initStyle(StageStyle.UNDECORATED);
        FutureStage.show();
        Stage stage = (Stage) CancelButton.getScene().getWindow();
        stage.close();
    }


    public void SwitchToRegister()
    {
        try
        {
            Parent root = FXMLLoader.load(getClass().getResource("register.fxml"));
            Stage registerStage=new Stage();
            Scene scene = new Scene(root, 650, 500);
            registerStage.setTitle("TimeManager Master");
            registerStage.setScene(scene);
            registerStage.show();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            e.getCause();
        }
    }


}
