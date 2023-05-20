package home;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;

public class RegisterController
{
    @FXML
    private TextField UsernameOfRegister;

    @FXML
    private TextField setPasswordOfRegister;

    @FXML
    private TextField confirmPasswordOfRegister;

    @FXML TextField NicknameOfRegister;

    @FXML Button registerButton;

    @FXML Button closeButton;

    @FXML Button BtnReturnToLogin;

    static Connection conn;
    static Statement sql;
    static ResultSet res;

    public static Connection getConnection() {
        try {
            //加载驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            //追踪异常并打印
            e.printStackTrace();
        }
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/todolist_new?useSSL=false&useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&serverTimezone=GMT%2B8&zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC", "root", "pyl20021124");
            System.out.println("数据库连接成功");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }



    public boolean Register(String name, String password1, String password2, String nickname)  throws SQLException {
        RegisterController.getConnection();
        if (!password1.equals(password2)) return false;
        sql = conn.createStatement();
        int h = 0;
        res = sql.executeQuery("select* from user_info where user_name = '"+name+"'");
        if(res.next())return false;
        while (true) {
            int s = 0;
            s = sql.executeUpdate("insert into user_info(user_name,user_password,user_nickname)" +
                    "values ('" + name + "','" + password1 + "','" + nickname + "')");
            //res.close();
            if (s == 1) {
                sql.close();
                conn.close();
                return true;
            }
        }
    }

    public void RegisterNewAcount(ActionEvent actionEvent) throws SQLException, IOException {
        String strUsername=UsernameOfRegister.getText();//???????Ψ???????
        String strSetPassword=setPasswordOfRegister.getText();//????
        String strConfirmPassword=confirmPasswordOfRegister.getText();//???????
        String strNickname=NicknameOfRegister.getText();//???

        //?ж???????????????????????Ψ?????????????????

        boolean regis = Register(strUsername,strSetPassword,strConfirmPassword,strNickname);



        //?ж????????????????????
        if(!Objects.equals(strSetPassword, strConfirmPassword))
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Wrong");
            alert.setHeaderText("Not match");
            alert.setContentText("The comfirming Password should be the same as Password!");
            alert.showAndWait();
            setPasswordOfRegister.setText("");//??????????????????????
            confirmPasswordOfRegister.setText("");
        }


        //?????????

        if( regis == true)
        {
            Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
            Stage CancelStage = new Stage();
            Scene scene = new Scene(root, 650, 400);
            CancelStage.setScene(scene);
            CancelStage.initStyle(StageStyle.UNDECORATED);
            CancelStage.show();
            Stage stage = (Stage) closeButton.getScene().getWindow();
            stage.close();
        }else
        {
            //失败了
        }



    }

    public void returnToLogin(ActionEvent actionEvent)throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        Stage CancelStage = new Stage();
        Scene scene = new Scene(root, 650, 400);
        CancelStage.setScene(scene);
        CancelStage.initStyle(StageStyle.UNDECORATED);
        CancelStage.show();
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    public void CloseAndSignOut(ActionEvent actionEvent) throws CloneNotSupportedException, IOException {
        Data.update();
        Data.threadRunFlag=false;
        Stage stage=(Stage)closeButton.getScene().getWindow();
        stage.close();
    }

}
