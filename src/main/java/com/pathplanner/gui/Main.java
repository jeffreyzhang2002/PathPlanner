package com.pathplanner.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class Main extends Application
{

    private static final String startupScene = "/startup/StartUpApplicationPanel.fxml";
    private static final String config = "";
    File mainScene = new File("");

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document document = dBuilder.parse(new File(config));
        document.getDocumentElement().normalize();
        NodeList nList = document.getElementsByTagName("environment");


        FXMLLoader loader = new FXMLLoader(getClass().getResource(startupScene));
        Parent root = loader.load();
    }
}
