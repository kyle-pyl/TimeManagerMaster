package home;

import FileManager.SchedulBlock;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class UserInformationController implements Initializable
{
    @FXML
    private Button btnReturnToHome;

    @FXML
    private Button btnUpload;

    @FXML
    private Button btnDownload;

    @FXML Button btnChangeInfo;

    @FXML
    private TextField UsernameOfUserInfo;

    @FXML
    private TextField nicknameOfUserInfo;

    @FXML
    private TextField PasswordOfUserInfo;

    public void changeInfo(ActionEvent event) throws SQLException {
        String strNickname=nicknameOfUserInfo.getText();//昵称
        String strPassword=PasswordOfUserInfo.getText();//密码
        MyJDBC myJDBC = new MyJDBC();
        MyJDBC.getConnection();
        myJDBC.changinfo(strNickname,strPassword);
        //传入更改数据库内容
        Data.NickName = strNickname;
        Data.password = strPassword;
    }

    public void uploadSchedule(ActionEvent event) throws SQLException {
        //数据库同学填写
        MyJDBC myJDBC = new MyJDBC();
        userProfile userprofile = new userProfile();
        userprofile.setUser_account(Data.userName);
        userprofile.setUser_id(Data.NickName);
        userprofile.setUser_password(Data.password);
        List<SchedulBlock> list=new ArrayList<>();
        list.addAll(Data.historyList);
        list.addAll(Data.futureList);
        MyJDBC.getConnection();
        myJDBC.updataDataBase(list,userprofile);
    }

    public void downloadSchedule(ActionEvent event) throws SQLException {
        //数据库同学填写
        MyJDBC myJDBC = new MyJDBC();
        userProfile userprofile = new userProfile();
        userprofile.setUser_account(Data.userName);
        userprofile.setUser_id(Data.NickName);
        userprofile.setUser_password(Data.password);
        List<SchedulBlock> list=new ArrayList<>();
        list.addAll(Data.historyList);
        list.addAll(Data.futureList);
        MyJDBC.getConnection();
        MyJDBC.updateLocal(list,userprofile);
        Data.futureList.clear();
        Data.historyList.clear();
        for(SchedulBlock sch:list)
        {
            if(sch.end.compareTo(Data.getNow()) < 0)
            {
                Data.historyList.add(sch);
            }
            else
            {
                Data.futureList.add(sch);
            }
        }
        System.out.println();
    }

    public void returnToHome(ActionEvent event)throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
        Stage FutureStage = new Stage();
        Scene scene = new Scene(root, 1050, 576);
        FutureStage.setScene(scene);
        FutureStage.initStyle(StageStyle.UNDECORATED);
        FutureStage.show();
        Stage stage = (Stage) btnReturnToHome.getScene().getWindow();
        stage.close();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        UsernameOfUserInfo.setText(Data.userName);
        nicknameOfUserInfo.setText(Data.NickName);
        PasswordOfUserInfo.setText(Data.password);
        UsernameOfUserInfo.setEditable(false);
    }
}
