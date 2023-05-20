package home;

import FileManager.scheduleStatus;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class ItemController {
    private double x,y;
    @FXML
    Label SupplementOf_Item;//补充

    @FXML
    Label ScheduleNameOf_Item;

    @FXML
    Label ImportanceLevelOf_Item;

    @FXML
    Button StatusButtonOf_Item;

    @FXML
    HBox itemC;

    @FXML
    Label RemindTimeOf_Item;

    int Id;

    @FXML
    void changeStatusOnAction(ActionEvent event) throws CloneNotSupportedException, IOException {
        if(StatusButtonOf_Item.getText().equals("Ongoing") || StatusButtonOf_Item.getText().equals("TimeOut"))
        {
            StatusButtonOf_Item.setText("Completed");
            //以下补充删除后内容的迭代
            if(Id < Data.futureList.size())
            {
                Data.futureList.get(Id).status= scheduleStatus.Completed;
            }
            else
            {
                Data.historyList.get(Id - Data.futureList.size()).status = scheduleStatus.Completed;
            }
            Data.update();


            //结束
        }
    }


    @FXML
    void goToSchedule(MouseEvent event)
    {
        itemC.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Parent root = null;
                Data.curIndex = Id;
                try {
                    root = FXMLLoader.load(getClass().getResource("show.fxml"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                Stage FutureStage = new Stage();
                Scene scene = new Scene(root, 1050, 576);
                FutureStage.setScene(scene);
                FutureStage.initStyle(StageStyle.UNDECORATED);
                Stage stage = (Stage) StatusButtonOf_Item.getScene().getWindow();
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

        });
    }



}
