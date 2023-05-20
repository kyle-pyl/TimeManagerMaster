package home;

import FileManager.SchedulBlock;
import FileManager.importanceLevel;
import FileManager.scheduleStatus;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
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

public class addController implements Initializable {
    private double x, y;

    @FXML
    private Label NickName;
    @FXML
    private ImageView UserPictureOfHome;
    SchedulBlock schedulblock = new SchedulBlock();
    @FXML
    private VBox pnItems = null;
    @FXML
    private Button btnOverview;
    @FXML
    private TextArea textAreaOfAdd;

    @FXML
    private Button addButtonOfAdd;
    @FXML
    private Button btnHistoryRecord;

    @FXML
    private Button btnFuturePlans;

    @FXML
    private Button btnAdd;
    @FXML
    private RadioButton low;
    @FXML
    private RadioButton high;
    @FXML
    private RadioButton middle;
    @FXML
    private Button btnSignout;
    @FXML
    private DatePicker beginTimeOfAdd;

    @FXML
    private DatePicker endTimeOfEnd;

    @FXML
    private ToggleGroup levelOfImportance;


    @FXML
    private TextField scheduleNameOfAdd;
    @FXML
    private TextField beginTime;
    @FXML
    private TextField endTime;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        NickName.setText(Data.NickName);
        Image image = new Image(Data.imgUrl);
        UserPictureOfHome.setImage(image);
    }

    public void switchToAdd(ActionEvent event)throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("add.fxml"));
        Stage addStage = new Stage();
        Scene scene = new Scene(root, 1050, 576);
        addStage.setScene(scene);
        addStage.initStyle(StageStyle.UNDECORATED);
        Stage stage = (Stage) btnSignout.getScene().getWindow();
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
    public void switchToHistory(ActionEvent event)throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("History.fxml"));
        Stage HistoryStage = new Stage();
        Scene scene = new Scene(root, 1050, 576);
        HistoryStage.setScene(scene);
        HistoryStage.initStyle(StageStyle.UNDECORATED);
        Stage stage = (Stage) btnSignout.getScene().getWindow();
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
        Parent root = FXMLLoader.load(getClass().getResource("Future.fxml"));
        Stage FutureStage = new Stage();
        Scene scene = new Scene(root, 1050, 576);
        FutureStage.setScene(scene);
        FutureStage.initStyle(StageStyle.UNDECORATED);
        Stage stage = (Stage) btnSignout.getScene().getWindow();
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
    public void cancelButtonAction (ActionEvent event) throws CloneNotSupportedException, IOException {
        Data.update();
        Data.threadRunFlag=false;
        Stage stage = (Stage) btnSignout.getScene().getWindow();
        stage.close();
    }
    @FXML
    void addBtnOfAddOnAction(ActionEvent event) throws CloneNotSupportedException, IOException { //将新事项塞入列表

        schedulblock.title=scheduleNameOfAdd.getText();
        schedulblock.establish = Data.getNow();

        //todo 时分秒未解决


        schedulblock.start.year = beginTimeOfAdd.getValue().getYear();
        schedulblock.start.month = beginTimeOfAdd.getValue().getMonthValue();
        schedulblock.start.day = beginTimeOfAdd.getValue().getDayOfMonth();
        schedulblock.start.hour = Integer.valueOf(beginTime.getText().split(":")[0]);
        schedulblock.start.minute = Integer.valueOf(beginTime.getText().split(":")[1]);
        schedulblock.start.second = Integer.valueOf(beginTime.getText().split(":")[2]);

        schedulblock.end.year = endTimeOfEnd.getValue().getYear();
        schedulblock.end.month = endTimeOfEnd.getValue().getMonthValue();
        schedulblock.end.day = endTimeOfEnd.getValue().getDayOfMonth();
        schedulblock.end.hour = Integer.valueOf(endTime.getText().split(":")[0]);
        schedulblock.end.minute = Integer.valueOf(endTime.getText().split(":")[1]);
        schedulblock.end.second = Integer.valueOf(endTime.getText().split(":")[2]);

        if (levelOfImportance.getSelectedToggle().equals(low)&&levelOfImportance.getSelectedToggle()!=null) {
            schedulblock.importance = importanceLevel.Low;
        }
        if (levelOfImportance.getSelectedToggle().equals(middle)&&levelOfImportance.getSelectedToggle()!=null) {
            schedulblock.importance = importanceLevel.Middle;
        }
        if (levelOfImportance.getSelectedToggle().equals(high)&&levelOfImportance.getSelectedToggle()!=null) {
            schedulblock.importance = importanceLevel.High;
        }

        if(schedulblock.start.compareTo(Data.getNow()) <= 0)
        {
            schedulblock.status = scheduleStatus.Ongoing;
        }
        else
        {
            schedulblock.status = scheduleStatus.NotStarted;
        }


        schedulblock.text=textAreaOfAdd.getText();
        Data.futureList.add(schedulblock);
        Data.update();
        Data.init();

    }

    public void SwitchToLogin(MouseEvent MouseEvent)throws IOException
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
