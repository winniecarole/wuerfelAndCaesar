
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class test extends Application{

    @Override
    public void start(Stage stage) throws Exception {

        Text t=new Text("Hello javaFx");//create the text
        VBox vb =new VBox();//create the vbox
        vb.getChildren().add(t); // add the text to the vbox
        vb.setMinSize(330,250); //set the size of the vbox
        Scene scene=new Scene(vb); //create the scene
        /* set the properties of the stage*/
        stage.setX(100);
        stage.setY(200);
        stage.setMinHeight(300);
        stage.setMinWidth(400);
        stage.setScene(scene);// Add the scene to the scene
        stage.setTitle("First Javafx Example");
        stage.show();




    }

    public static void main(String[] args) {
        Application.launch(args);

    }
}