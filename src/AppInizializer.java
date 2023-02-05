import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class AppInizializer extends Application {
    private double x, y;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(
                getClass().getResource("com/bookiebook/pos/view/LoadingScreen.fxml")
        );
        primaryStage.setScene(new Scene(root));
        primaryStage.initStyle(StageStyle.UNDECORATED);

        //we gonna drag the frame
        root.setOnMousePressed(event -> {
            x = event.getSceneX();
            y = event.getSceneY();
        });

        root.setOnMouseDragged(event -> {
            primaryStage.setX(event.getScreenX() - x);
            primaryStage.setY(event.getScreenY() - y);
        });
        primaryStage.show();
        primaryStage.setResizable(false);
        primaryStage.centerOnScreen();
        primaryStage.setTitle("BookieBook");
        Image icon = new Image(getClass().getResourceAsStream("/com/bookiebook/pos/assets/02.png"));
        primaryStage.getIcons().add( icon);

    }
}
