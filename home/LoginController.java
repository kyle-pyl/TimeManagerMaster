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
        String username=EnterUsernameTextField.getText();//�û���
        String password=EnterPasswordFied.getText();//����
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
            //ʧ��
        }
    }

    public boolean user_Login(String name, String password)//��½�ɹ��Ļ�����true
    {
        boolean login_success = false;//�����Ƿ��½�ɹ��ı���
        //JDBC���еı�������
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet res=null;

        //���������֮ǰ����Ľṹ��userProfile��map�еõ���ID������
        //String user_id=name;//�û�ID
        //String user_password=password;//�û�����
        try {
            //1ע������
            Class.forName("com.mysql.cj.jdbc.Driver");
            //�ڶ�������ȡ���ӣ�JVM�����ݿ�֮���ͨ���򿪣�
            //"jdbc:mysql://localhost:3306/���ݿ�����","root","������ݿ�����"
            conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/todolist_new?useSSL=false&useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&serverTimezone=GMT%2B8&zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC","root","pyl20021124");
            //����������ȡԤ��������ݿ�������� ��ר��ִ��SQL���Ķ���
            //���û����ű��л�ȡID�����룬����ط���Ҫ�޸�һ�±������
            String sql="select * from user_info where user_name=? and user_password=?"; // ?Ϊռλ��
            ps=conn.prepareStatement(sql);
            ps.setString(1,name);//��loginName���ݸ��ϱߵ�ռλ��?�ĵط�,����ɹ��ͷ���1
            ps.setString(2,password);//����һ��ͬ���ɹ�����2
            //���Ĳ���ִ��SQL���
            res=ps.executeQuery();//executeQuery()�´�selectָ��
            //���岽: ��������
            if (res.next()){
                Data.NickName=res.getString("user_nickname");
                Data.userName=name;
                Data.password=password;
                return true; //����ѯ��������ݷ������¼�ɹ�
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //���������ͷ���Դ��ʹ������Դ֮��ر�.Java�����ݿ����ڽ��̼��ͨ�ţ�
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
