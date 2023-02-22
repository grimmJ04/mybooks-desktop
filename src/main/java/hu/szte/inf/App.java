package hu.szte.inf;

import hu.szte.inf.repositories.BookMemoryRepository;
import hu.szte.inf.utils.db.DbInitializer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    private static Stage stage;

    public static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        stage.sizeToScene();
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        new BookMemoryRepository().saveAll(DbInitializer.getDefaultBooks());

        App.stage = stage;
        Scene scene = new Scene(loadFXML("main"));
        stage.setScene(scene);
        stage.show();
    }

}