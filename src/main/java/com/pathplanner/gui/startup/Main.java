package com.pathplanner.gui.startup;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class Main extends Application
{
    public void start(Stage primaryStage) throws Exception
    {
        System.out.println(getClass().getResource("/startup/StartUpApplicationPanel.fxml"));

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/startup/StartUpApplicationPanel.fxml"));
        Parent root = loader.load();

        File inputFile = new File("src/main/configuration/environmentTypes.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        System.out.println(inputFile);
        Document document = dBuilder.parse(inputFile);
        document.getDocumentElement().normalize();
        NodeList nList = document.getElementsByTagName("environment");

        StartUpApplicationController controller = (StartUpApplicationController) loader.getController();
        controller.setMyStage(primaryStage);
        controller.setNodeList(nList);

        primaryStage.setTitle("PathPlanner");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);

        primaryStage.getIcons().add(new Image("assets/pathPlannerIcon.png"));

        primaryStage.show();

    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
