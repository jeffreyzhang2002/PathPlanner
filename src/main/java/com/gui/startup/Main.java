package com.gui.startup;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.w3c.dom.Document;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class Main extends Application
{
    private static final String startupScene = "/startup/StartUpApplicationPanel.fxml";
    private static final String config = "src/main/configuration/config.xml";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        //load config doc.
        Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File(config));
        doc.normalizeDocument();

        //initStage
                primaryStage.setTitle(doc.getDocumentElement().getAttribute("name"));
                primaryStage.getIcons().add(new Image("assets/pathPlannerIcon.png"));
                primaryStage.setResizable(false);

                //load startup
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/startup/StartUpApplicationPanel.fxml"));
                System.out.println(getClass().getResource("/startup/StartUpApplicationPanel.fxml"));
                Parent root = loader.load();

                //dependency injection
                ((StartUpApplicationController) loader.getController()).setUp(doc.getDocumentElement(), primaryStage);


                primaryStage.setScene(new Scene(root));

                primaryStage.show();
                //load main



    }
}
