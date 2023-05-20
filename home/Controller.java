package home;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    private double x,y;

    @FXML
    private Label NickName;

    @FXML
    private Label todayCount;
    @FXML
    private Label weekCount;
    @FXML
    private Label monthCount;
    @FXML
    private Label totCount;

    @FXML
    private VBox pnItems = null;
    @FXML
    private Button btnOverview;

    @FXML
    private Button btnEdit;

    @FXML
    private Button btnHistoryRecord;

    @FXML
    private Button btnFuturePlans;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnSignout;

    @FXML
    private Pane pnlFuturePlans;

    @FXML
    private Pane pnlHistoryRecord;

    @FXML
    private Pane pnlOverview;

    @FXML
    private Pane pnlEdit;

    @FXML
    private Pane pnlAdd;

    @FXML
    private ImageView UserPictureOfHome;

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
        int total_Schedule=Data.futureList.size();
        Node[] nodes = new Node[total_Schedule];
        for (int i = 0; i < nodes.length; i++) {
            try
            {
                Data.curIndex = i;
                String strName=Data.futureList.get(i).title;
                String strRemindTime=Data.futureList.get(i).end.toString();
                String strImportance=Data.futureList.get(i).importance.name();
                String strStatus=Data.futureList.get(i).status.name();
                String strSupplement=Data.futureList.get(i).text;

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

                nodes[i].setOnMouseEntered(event -> {
                    nodes[j].setStyle("-fx-background-color : #ee7044");
                });
                nodes[i].setOnMouseExited(event -> {
                    nodes[j].setStyle("-fx-background-color : #FFFFE0");
                });

                //将Item长条放到VBox里
                pnItems.getChildren().add(nodes[i]);


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        todayCount.setText(String.valueOf(Data.getTodayCount()));
        monthCount.setText(String.valueOf(Data.getMonthCount()));
        weekCount.setText(String.valueOf(Data.getWeekCount()));
        totCount.setText(String.valueOf(Data.futureList.size()));
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

    public void cancelButtonAction (ActionEvent event) throws IOException, CloneNotSupportedException {
        //此处应填update语句
        Data.update();
        Data.threadRunFlag=false;
        Stage stage = (Stage) btnSignout.getScene().getWindow();
        stage.close();
    }


    public void handle(MouseEvent event)
    {
        UserPictureOfHome.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            public void handle(MouseEvent event) {
                System.out.println("Image clicked");
            }
        });
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
            UserPictureOfHome.setImage(image);
        }
    }

}
