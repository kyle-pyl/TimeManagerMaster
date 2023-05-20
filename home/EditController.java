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
import java.time.LocalDate;
import java.util.ResourceBundle;

public class EditController implements Initializable
{
    private double x, y;
    @FXML
    private ImageView UserPictureOfHome;
    @FXML
    private Label NickName;
    SchedulBlock schedulblock;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnSignout;

    @FXML
    private TextField scheduleNameOfAdd;
    @FXML
    private TextField beginTime;
    @FXML
    private TextField endTime;
    @FXML
    private DatePicker beginTimeOfAdd;

    @FXML
    private DatePicker endTimeOfEnd;

    @FXML
    private ToggleGroup levelOfImportance;
    @FXML
    private RadioButton low;
    @FXML
    private RadioButton high;
    @FXML
    private RadioButton middle;
    @FXML
    private TextArea textAreaOfAdd;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //��ʼ��
        if(Data.curIndex < Data.futureList.size() && Data.curIndex >= 0)
        {
            schedulblock = Data.futureList.get(Data.curIndex);
        }
        else if(Data.curIndex >= Data.futureList.size())
        {
            schedulblock = Data.historyList.get(Data.curIndex - Data.futureList.size());
        }

        scheduleNameOfAdd.setText(schedulblock.title);
        textAreaOfAdd.setWrapText(true);
        textAreaOfAdd.setText(schedulblock.text);
        beginTimeOfAdd.setValue(LocalDate.of(schedulblock.start.year,schedulblock.start.month,schedulblock.start.day));
        endTimeOfEnd.setValue(LocalDate.of(schedulblock.end.year,schedulblock.end.month,schedulblock.end.day));
        beginTime.setText(schedulblock.start.toString().split("-")[1]);
        endTime.setText(schedulblock.end.toString().split("-")[1]);
        switch (schedulblock.importance)
        {
            case High:levelOfImportance.selectToggle(high);high.setSelected(true);break;
            case Low:levelOfImportance.selectToggle(low);low.setSelected(true);break;
            case Middle:levelOfImportance.selectToggle(middle);middle.setSelected(true);break;
        }
        NickName.setText(Data.NickName);
        Image image = new Image(Data.imgUrl);
        UserPictureOfHome.setImage(image);
    }
    public void switchToAdd(ActionEvent event)throws IOException
    {
        Stage stage = (Stage) btnAdd.getScene().getWindow();
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

    public void switchToOver(ActionEvent event)throws IOException
    {
        Stage stage = (Stage) btnAdd.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
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
    public void switchToHistory(ActionEvent event)throws IOException
    {
        Stage stage = (Stage) btnAdd.getScene().getWindow();
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
        Stage stage = (Stage) btnAdd.getScene().getWindow();
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

    public void cancelButtonAction (ActionEvent event) throws CloneNotSupportedException, IOException {
        Data.update();
        Data.threadRunFlag=false;
        Stage stage = (Stage) btnSignout.getScene().getWindow();
        stage.close();
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

    public void deleteButtonOfEditOnAction(ActionEvent actionEvent) throws CloneNotSupportedException, IOException {
        if(Data.curIndex < Data.futureList.size() && Data.curIndex >= 0)
        {
            Data.futureList.remove(Data.curIndex);
        }
        else if(Data.curIndex >= Data.futureList.size())
        {
            Data.historyList.remove(Data.curIndex - Data.futureList.size());
        }
        Data.update();
        Data.init();
        Data.curIndex = -1;
    }

    public void changeScheduleOnAction(ActionEvent actionEvent) throws CloneNotSupportedException, IOException {
        if(Data.curIndex < Data.futureList.size() && Data.curIndex >= 0)
        {
            schedulblock = Data.futureList.get(Data.curIndex);
        }
        else if(Data.curIndex >= Data.futureList.size())
        {
            schedulblock = Data.historyList.get(Data.curIndex - Data.futureList.size());
        }
        schedulblock.title=scheduleNameOfAdd.getText();
        schedulblock.establish = Data.getNow();

        //todo ʱ����δ���


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
        if (levelOfImportance.getSelectedToggle().equals(levelOfImportance.getToggles().get(0))&&levelOfImportance.getSelectedToggle()!=null) {
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
        Data.update();
        Data.init();
        Data.curIndex = -1;
    }

    public void ChangeImage(MouseEvent event)
    {
        // ����һ��FileChooser����
        FileChooser fileChooser = new FileChooser();
        // ���ñ���
        fileChooser.setTitle("ѡ��ͼƬ");
        Stage stage = (Stage) btnSignout.getScene().getWindow();
        // ��ʾ�򿪶Ի���
        File file = fileChooser.showOpenDialog(stage);

        if (file != null) {
            // ����ͼƬ
            Data.imgUrl = file.toURI().toString();
            Image image = new Image(Data.imgUrl);

            // ����UserPictureOfHome��ͼ��
            UserPictureOfHome.setImage(image);
        }
    }


}
