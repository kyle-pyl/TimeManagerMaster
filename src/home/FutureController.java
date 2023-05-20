package home;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FutureController  implements Initializable
{
    private double x, y;
    @FXML
    private Label NickName;
    @FXML
    private ImageView UserPictureOfHome;
    @FXML
    private VBox pnItems = null;
    @FXML
    private Button btnOverview;


    @FXML
    private Button btnHistoryRecord;

    @FXML
    private Button btnFuturePlans;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnSignout;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        try {
            Data.update();
            Data.init();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        int total_Schedule = Data.futureList.size();
        Node[] nodes = new Node[total_Schedule];
        //Node[] nodes = new Node[10];
        for (int i = 0; i < nodes.length; i++) {
            try {

                String strName=Data.futureList.get(i).title;
                String strRemindTime=Data.futureList.get(i).end.toString();
                String strImportance=Data.futureList.get(i).importance.name();
                String strStatus=Data.futureList.get(i).status.name();
                String strSupplement=Data.futureList.get(i).text;

                Data.curIndex = i;
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Item.fxml"));
                Parent root=loader.load();
                ItemController ct=loader.getController();

                //以下填写均需通过索引找到相应SchedulBlock填写
                ct.Id = Data.curIndex;
                Label NameOf_Item=ct.ScheduleNameOf_Item;
                NameOf_Item.setText(strName);//根据索引
                Label RemindTime=ct.RemindTimeOf_Item;
                RemindTime.setText(strRemindTime);
                Label importance=ct.ImportanceLevelOf_Item;
                importance.setText(strImportance);
                Label supplement=ct.SupplementOf_Item;
                supplement.setText(strSupplement);
                Button Status=ct.StatusButtonOf_Item;
                Status.setText(strStatus);

                nodes[i]=(Node)root;


                final int j = i;

                //give the items some effect

                nodes[i].setOnMouseEntered(event -> {
                    nodes[j].setStyle("-fx-background-color : #ee7044");
                });
                nodes[i].setOnMouseExited(event -> {
                    nodes[j].setStyle("-fx-background-color : #FFFFE0");
                });
                pnItems.getChildren().add(nodes[i]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Data.curIndex = -1;
        NickName.setText(Data.NickName);
        Image image = new Image(Data.imgUrl);
        UserPictureOfHome.setImage(image);
    }



    public void switchToAdd(ActionEvent event)throws IOException
    {
        Stage stage = (Stage) btnSignout.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("add.fxml"));
        Stage addStage = new Stage();
        Scene scene = new Scene(root, 1050, 576);
        addStage.setScene(scene);
        addStage.initStyle(StageStyle.UNDECORATED);
        addStage.setX(stage.getX());
        addStage.setY(stage.getY());
        //drag it here
        root.setOnMousePressed(event1 -> {
            x = event1.getSceneX();
            y = event1.getSceneY();
        });
        root.setOnMouseDragged(event1 -> {

            addStage.setX(event1.getScreenX() - x);
            addStage.setY(event1.getScreenY() - y);

        });
        addStage.show();

        stage.close();
    }

    public void switchToHistory(ActionEvent event)throws IOException
    {
        Stage stage = (Stage) btnSignout.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("History.fxml"));
        Stage HistoryStage = new Stage();
        Scene scene = new Scene(root, 1050, 576);
        HistoryStage.setScene(scene);
        HistoryStage.initStyle(StageStyle.UNDECORATED);
        HistoryStage.setX(stage.getX());
        HistoryStage.setY(stage.getY());
        //drag it here
        root.setOnMousePressed(event1 -> {
            x = event1.getSceneX();
            y = event1.getSceneY();
        });
        root.setOnMouseDragged(event1 -> {

            HistoryStage.setX(event1.getScreenX() - x);
            HistoryStage.setY(event1.getScreenY() - y);

        });
        HistoryStage.show();

        stage.close();
    }

    public void switchToFuture(ActionEvent event)throws IOException
    {
        Stage stage = (Stage) btnSignout.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("Future.fxml"));
        Stage FutureStage = new Stage();
        Scene scene = new Scene(root, 1050, 576);
        FutureStage.setScene(scene);
        FutureStage.initStyle(StageStyle.UNDECORATED);
        FutureStage.setX(stage.getX());
        FutureStage.setY(stage.getY());
        //drag it here
        root.setOnMousePressed(event1 -> {
            x = event1.getSceneX();
            y = event1.getSceneY();
        });
        root.setOnMouseDragged(event1 -> {

            FutureStage.setX(event1.getScreenX() - x);
            FutureStage.setY(event1.getScreenY() - y);

        });
        FutureStage.show();

        stage.close();
    }
    public void switchToOver(ActionEvent event)throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
        Stage OverStage = new Stage();
        Scene scene = new Scene(root, 1050, 576);
        OverStage.setScene(scene);
        OverStage.initStyle(StageStyle.UNDECORATED);
        Stage stage = (Stage) btnSignout.getScene().getWindow();
        OverStage.setX(stage.getX());
        OverStage.setY(stage.getY());
        //drag it here
        root.setOnMousePressed(event1 -> {
            x = event1.getSceneX();
            y = event1.getSceneY();
        });
        root.setOnMouseDragged(event1 -> {

            OverStage.setX(event1.getScreenX() - x);
            OverStage.setY(event1.getScreenY() - y);

        });
        OverStage.show();
        stage.close();
    }


    public void cancelButtonAction (ActionEvent event) throws IOException, CloneNotSupportedException {
        //此处应填update语句

        Data.update();
        Data.threadRunFlag=false;
        Stage stage = (Stage) btnSignout.getScene().getWindow();
        stage.close();
    }

    public void SwitchToLogin(MouseEvent event)throws IOException
    {
        if(!Data.isLogin)
        {
            Stage stage = (Stage) btnSignout.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
            Stage CancelStage = new Stage();
            Scene scene = new Scene(root, 650, 400);
            CancelStage.setScene(scene);
            CancelStage.initStyle(StageStyle.UNDECORATED);
            //drag it here
            root.setOnMousePressed(event1 -> {
                x = event1.getSceneX();
                y = event1.getSceneY();
            });
            root.setOnMouseDragged(event1 -> {

                CancelStage.setX(event1.getScreenX() - x);
                CancelStage.setY(event1.getScreenY() - y);

            });
            CancelStage.show();

            stage.close();
        }
        else {
            Stage stage = (Stage) btnSignout.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("UserInformation.fxml"));
            Stage CancelStage = new Stage();
            Scene scene = new Scene(root, 600, 400);
            CancelStage.setScene(scene);
            CancelStage.initStyle(StageStyle.UNDECORATED);
            //drag it here
            root.setOnMousePressed(event1 -> {
                x = event1.getSceneX();
                y = event1.getSceneY();
            });
            root.setOnMouseDragged(event1 -> {

                CancelStage.setX(event1.getScreenX() - x);
                CancelStage.setY(event1.getScreenY() - y);

            });
            CancelStage.show();

            stage.close();
        }
    }

    public void ChangeImage(MouseEvent event)
    {
        // 创建一个FileChooser对象
        FileChooser fileChooser = new FileChooser();
        // 设置标题
        fileChooser.setTitle("选择图片");
        Stage stage = (Stage) btnSignout.getScene().getWindow();
        // 显示打开对话框
        File file = fileChooser.showOpenDialog(stage);

        if (file != null) {
            // 加载图片
            Data.imgUrl = file.toURI().toString();
            Image image = new Image(Data.imgUrl);
            // 设置UserPictureOfHome的图像
            UserPictureOfHome.setImage(image);
        }
    }


}
