package com.example.librarymanager;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class HelloApplication extends Application {

    public static ObservableList<String> list = FXCollections.observableArrayList(
            "The Da Vinci Code", "Harry Potter and the Deathly Hallows", "Harry Potter and the Philosopher's Stone", "Harry Potter and the Order of the Phoenix");

    public static ObservableList<String> authors_list = FXCollections.observableArrayList(
            "Brown, Dan", "Rowling, J.K.", "Rowling, J.K.", "Rowling, J.K.");
    public static ObservableList<String> book_cover = FXCollections.observableArrayList("img\\code.jpeg","img\\harry.jpg","img\\harry.jpg","img\\harry.jpg");

    public static ObservableList<String> getCover() {
        return book_cover;
    }
    public static ObservableList<String> getList() {
        return list;
    }
    public static ObservableList<String> getAuthors_list() {
        return authors_list;
    }

    public static ListView<String> listView = new ListView<>(list);
    public static BackgroundFill background_fill1 = new BackgroundFill(Color.rgb(189, 213, 255),
            CornerRadii.EMPTY, Insets.EMPTY);
    public static Background background1 = new Background(background_fill1);

    static class ListRows extends ListCell<String> {
        HBox hbox = new HBox();
        VBox vbox = new VBox();
        ImageView imageView = new ImageView();
        Label book_name = new Label();
        Label authors = new Label();

        public ListRows() throws FileNotFoundException {
            super();
            hbox.getChildren().addAll(vbox, imageView, book_name, authors);
            HBox.setHgrow(vbox, Priority.ALWAYS);
            imageView.setFitWidth(30);
            imageView.setFitHeight(50);
            authors.setStyle("-fx-padding: 14 100 0 100;");
        }

        @Override
        protected void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            if (empty || item == null) {
                setText(null);
                setGraphic(null);
                setBackground(background1);
                setStyle("-fx-font: 20 arial;");
            }

            if (item != null && !empty) {
                Image book_cover = null;
                try {
                    book_cover = new Image(new FileInputStream(HelloApplication.book_cover.get(getIndex())));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                imageView.setImage(book_cover);
                setText(item);
                setGraphic(hbox);
                authors.setText(authors_list.get(getIndex()));
            }
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        VBox vBox = new VBox();
        HBox hBox = new HBox();
        Scene scene = new Scene(vBox, 800, 700);
        Image logo = new Image(new FileInputStream("img\\vistula.png"));
        ImageView img = new ImageView(logo);

        TextField textField = new TextField(listView.getSelectionModel().getSelectedItem());
        Button delete_btn = new Button("Delete");
        Button clear_btn = new Button("Clear");
        Button update_btn = new Button("Update");
        Button add_btn = new Button("Add");
        Image icon = new Image(new FileInputStream("img\\icon.png"));
        BackgroundFill background_fill = new BackgroundFill(Color.rgb(53, 52, 75),
                CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(background_fill);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Library Manager");
        primaryStage.getIcons().add(icon);

        img.setFitHeight(180);
        img.setFitWidth(300);
        img.setTranslateX(50);
        img.setTranslateY(20);

        textField.setBackground(background1);

        listView.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> textField.setText(listView.getSelectionModel().getSelectedItem()));

        listView.setCellFactory(param -> {
            try {
                return new ListRows();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            return null;
        });
        listView.setMinHeight(400);

        vBox.setSpacing(40);
        hBox.setSpacing(40);
        hBox.setAlignment(Pos.BASELINE_RIGHT);
        HBox.setMargin(add_btn, new Insets(0, 100, 0, 100));
        vBox.setBackground(background);

        delete_btn.setOnAction(event -> {
            book_cover.remove(listView.getSelectionModel().getSelectedIndex());
            authors_list.remove(listView.getSelectionModel().getSelectedIndex());
            listView.getItems().remove(listView.getSelectionModel().getSelectedItem());
        });
        clear_btn.setOnAction(event -> listView.getItems().removeAll(list));
        add_btn.setOnAction(event -> Add.start());
        update_btn.setOnAction(event -> Update.start());

        clear_btn.setBackground(background1);
        add_btn.setBackground(background1);
        update_btn.setBackground(background1);
        delete_btn.setBackground(background1);

        hBox.getChildren().addAll(textField, delete_btn, clear_btn, update_btn, add_btn);
        vBox.getChildren().addAll(img, listView, hBox);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}