package com.gui.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class Main extends Application
{
    private static final String config = "src/main/configuration/config.xml";

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File(config));
        doc.normalizeDocument();

        //initStage
        primaryStage.setTitle("pathPlanner");
        primaryStage.getIcons().add(new Image("assets/pathPlannerIcon.png"));
        //primaryStage.setResizable(false);

        //load startup
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/MainApplication.fxml"));
        Parent root = loader.load();
        ((MainController) loader.getController()).setUp((Element)doc.getDocumentElement().getElementsByTagName("environment").item(0));

        primaryStage.setScene(new Scene(root));

        primaryStage.show();
        //load main
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
