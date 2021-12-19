package com.example.librarymanager;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class Upload {
    public static void start () {
        Stage updateStage = new Stage();
        Stage file_stage = new Stage();
        VBox vBox = new VBox();
        VBox vBox1 = new VBox();
        VBox vBox2 = new VBox();
        VBox vBox3 = new VBox();
        HBox hBox = new HBox();
        Scene scene = new Scene(vBox);
        Label book_lbl = new Label("Book Name:");
        Label author_lbl = new Label("Author Name:");
        Label path_lbl = new Label("Image Path:");
        TextField book_filed = new TextField();
        TextField author_filed = new TextField();
        TextField path_filed = new TextField();
        Button file_chooser = new Button("...");
        Button update_btn = new Button("Update");
        FileChooser fileChooser = new FileChooser();
        int selected_index = HelloApplication.listView.getSelectionModel().getSelectedIndex();

        updateStage.setHeight(300);
        updateStage.setWidth(300);
        updateStage.setResizable(false);

        book_filed.setMaxSize(200, 10);
        author_filed.setMaxSize(200, 10);
        path_filed.setMinSize(175, 10);

        book_filed.setText(HelloApplication.listView.getSelectionModel().getSelectedItem());
        author_filed.setText(HelloApplication.authors_list.get(selected_index));
        path_filed.setText(HelloApplication.book_cover.get(selected_index));

        update_btn.setTranslateY(20);
        
        update_btn.setOnAction(event -> {
            if (!"".equals(author_filed.getText()) || !"".equals(book_filed.getText()) || !"".equals(path_filed.getText())) {
                HelloApplication.list.set(selected_index, book_filed.getText());
                HelloApplication.authors_list.set(selected_index, author_filed.getText());
                HelloApplication.book_cover.set(selected_index, path_filed.getText());
                updateStage.close();
            }

        });

        EventHandler<ActionEvent> event = actionEvent -> {
            File file = fileChooser.showOpenDialog(file_stage);
            if (file != null) {
                path_filed.setText(file.getAbsolutePath());
            }
        };

        file_chooser.setOnAction(event);

        vBox.setSpacing(20);

        vBox1.getChildren().addAll(book_lbl, book_filed);
        vBox2.getChildren().addAll(author_lbl, author_filed);
        vBox3.getChildren().addAll(path_lbl, hBox);
        vBox.getChildren().addAll(vBox1, vBox2 , vBox3, update_btn);
        hBox.getChildren().addAll(path_filed, file_chooser);

        vBox.setAlignment(Pos.BASELINE_CENTER);
        vBox1.setAlignment(Pos.BASELINE_CENTER);
        vBox2.setAlignment(Pos.BASELINE_CENTER);
        vBox3.setAlignment(Pos.BASELINE_CENTER);
        hBox.setAlignment(Pos.BASELINE_CENTER);

        updateStage.setScene(scene);
        updateStage.show();
    }
}
