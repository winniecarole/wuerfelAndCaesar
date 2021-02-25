package pis.hue1;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;



/**
 * @author winnie
 * @version java 15.0.1
 */
public class CodecGui extends Application {
    private Codec c = new Caesar();
    private Codec w = new Wuerfel("THM");
    private BorderPane root;//Hauptkomponente
    private boolean isCaesar;
    private Label lb2 = new Label("Loesungswort2:");
    private TextField lw2 = new TextField(); // der Textfield nimmt  mehr platz

    public static void main(String[] args) {

        launch(args);
    }

    /**
     *
     * @param stage windowsfenster der javafx
     * @throws Exception fehler
     */
    @Override
    public void start(Stage stage) throws Exception {
         root = new BorderPane();// Haupt_container

        root.setCenter(root1());// diese root ist in center der haupt_container
        Scene scene = new Scene(root, 1300,400); // Eigenschaft des fensters
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());//füght der css file hinzu
        stage.setTitle("CodecGui");// stage ist wie ein fenster
        stage.setScene(scene);
        stage.show();
    }

    /**
     *diese methode erzeugt  Erste Borderpane
     * @return der erste BorderPane
     */
    public BorderPane root1(){
        BorderPane root1 = new BorderPane();
        Label welcome = new Label("Welcome Zu Ein Klein Spiel Verschlüssung Caesar und Wuerfel");
        Button start  = new Button("Start");
        start.setPrefSize(100,50);
        root1.setCenter(welcome); root1.getCenter().setStyle(" -fx-background-color: red;");
        root1.setBottom(start);

        //Lamda-Ausdruecke
        //e ActionEvent

        start.setOnAction((e)->{
            root.setCenter(root2());

        });

        /*
        //anonyme klasse
        //EventHandler functionale Interface
        start.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                root.setCenter(root2());
            }
        });
         */

        return root1;
    }
    /**
     *diese methode erzeugt  zweite Borderpane
     * @return der zweite BorderPane
     */
    public BorderPane root2(){
        BorderPane root2 = new BorderPane();
        Label message = new Label("Message wunsch");
        RadioButton caesar = new RadioButton("caesar");
        RadioButton wuerfel = new RadioButton("wuerfel");
        ToggleGroup group = new ToggleGroup();
        caesar.setToggleGroup(group);
        wuerfel.setToggleGroup(group);

        Button ok = new Button("OK");
        ok.setDisable(true);
        VBox vb = new VBox( caesar, wuerfel); //allignement vertical
        vb.setSpacing(20);
       root2.setTop(message);
        root2.setCenter(vb);
        root2.setBottom(ok);

        group.selectedToggleProperty().addListener((e)->{
            if(group.getSelectedToggle() != null) {
                ok.setDisable(false);
                RadioButton button = (RadioButton)group.getSelectedToggle();

                if (button.getText().equalsIgnoreCase("caesar")) {
                    isCaesar = true;
                } else if (button.getText().equalsIgnoreCase("wuerfel")) {
                    isCaesar = false;
                }
            }
        });

        ok.setOnAction((e)->{
            if(isCaesar){
                lb2.setVisible(false);
                lw2.setVisible(false);
                root.setCenter(root3());
            }else{
                lb2.setVisible(true);
                lw2.setVisible(true);
                root.setCenter(root3());
            }
        });


        return root2;
    }

    /**
     *diese methode erzeugt dritte  Borderpane
     * @return die dritte BorderPane
     */
    public BorderPane root3(){
        BorderPane root3 = new BorderPane();

        Label lb1 = new Label("Loesungswort1:");
        TextField lw1 = new TextField();
        lw1.setPrefSize(200, 50);


        lw2.setPrefSize(200, 50);
        HBox hb= new HBox(lb1, lw1, lb2, lw2);

        TextArea klar = new TextArea("Klartext");
        TextArea geheim = new TextArea("Geheimtext");

        Button kodierung = new Button("Kodierung");
        Button dekodierung = new Button("deKodierung");
        Button restart = new Button("restart");
        restart.setPrefSize(200, 100);
        kodierung.setPrefSize(200, 100);
        dekodierung.setPrefSize(200, 100);
        VBox vb = new VBox(kodierung, dekodierung);
        vb.setSpacing(30);

        root3.setTop(hb);
        root3.setLeft(klar);
        root3.setRight(geheim);
        root3.setBottom(restart);
        root3.setCenter(vb);

        restart.setOnAction((e)->{
            lb2.setVisible(true);
            lw2.setVisible(true);
            root.setCenter(root1());
        });

        /*
         * diese methode set der Action unsere dekodierung
         */
        kodierung.setOnAction((e)->{
            if(isCaesar){
                c.setzeLosung(lw1.getText());
                geheim.setText(c.kodiere(klar.getText()));
            }else{
                w.setzeLosung(lw1.getText());
                geheim.setText(w.kodiere(klar.getText()));
                w.setzeLosung(lw2.getText());
                geheim.setText(w.kodiere(geheim.getText()));
            }
        });

        /*
         * diese methode set der Action unsere dekodierung
         */
        dekodierung.setOnAction((e)->{
            if(isCaesar){
                c.setzeLosung(lw1.getText());
                klar.setText(c.dekodiere(geheim.getText()));
            }else{
                w.setzeLosung(lw2.getText());
                klar.setText(w.dekodiere(geheim.getText()));
                w.setzeLosung(lw1.getText());
                klar.setText(w.dekodiere(klar.getText()));


            }
        });
        return root3;
    }
}
