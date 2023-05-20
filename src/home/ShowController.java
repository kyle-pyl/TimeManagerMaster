package home;

import FileManager.SchedulBlock;
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
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ShowController implements Initializable {
    private double x, y;
    @FXML
    private Label NickName;
    @FXML
    private ImageView UserPictureOfHome;

    @FXML
    public TextField importance;
    SchedulBlock schedulblock = new SchedulBlock();
    @FXML
    private Button btnOverview;
    @FXML
    private TextArea textAreaOfAdd;
    @FXML
    private Button btnEdit;
    @FXML
    private TextField beginTime;
    @FXML
    private TextField endTime;
    @FXML
    private Button btnHistoryRecord;

    @FXML
    private Button btnFuturePlans;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //?????
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
        beginTime.setText(schedulblock.start.toString());
        endTime.setText(schedulblock.end.toString());
        importance.setText(schedulblock.importance.name());

        importance.setEditable(false);
        beginTime.setEditable(false);
        endTime.setEditable(false);
        textAreaOfAdd.setEditable(false);
        scheduleNameOfAdd.setEditable(false);
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

    public void cancelButtonAction (ActionEvent event) throws IOException, CloneNotSupportedException {
        //??????update???

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


    public void editBtnOfShowOnAction(ActionEvent Event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Edit.fxml"));
        Stage EditStage = new Stage();
        Scene scene = new Scene(root, 1050, 576);
        EditStage.setScene(scene);
        EditStage.initStyle(StageStyle.UNDECORATED);
        Stage stage = (Stage) btnSignout.getScene().getWindow();
        EditStage.setX(stage.getX());
        EditStage.setY(stage.getY());
        //drag it here
        root.setOnMousePressed(event1 -> {
            x = event1.getSceneX();
            y = event1.getSceneY();
        });
        root.setOnMouseDragged(event1 -> {

            EditStage.setX(event1.getScreenX() - x);
            EditStage.setY(event1.getScreenY() - y);

        });
        EditStage.show();
        stage.close();
    }


    public void ChangeImage(MouseEvent event)
    {
        // ???????FileChooser????
        FileChooser fileChooser = new FileChooser();
        // ???????
        fileChooser.setTitle("?????");
        Stage stage = (Stage) btnSignout.getScene().getWindow();
        // ?????????
        File file = fileChooser.showOpenDialog(stage);

        if (file != null) {
            // ??????
            Data.imgUrl = file.toURI().toString();
            Image image = new Image(Data.imgUrl);
            // ????UserPictureOfHome?????
            UserPictureOfHome.setImage(image);
        }
    }

}
