package com.gui.startup;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OpenExistingEnvironmentController
{
    @FXML private TextField fileNameTextField;
    private ArrayList<FileChooser.ExtensionFilter> filterList = new ArrayList<>();
    private Pattern fileNamePattern = Pattern.compile("^[\\w\\-. ]+$");
    private String fileName = "";
    private File selectedFile = null;

    private NodeList nList;
    private Stage myStage;

    public void setUp(NodeList nList, Stage myStage)
    {
        this.nList = nList;
        this.myStage = myStage;
        for(int i = 0; i < nList.getLength(); i++)
        {
            Node currentNode = nList.item(i);
            if(currentNode.getNodeType() == Node.ELEMENT_NODE)
            {
                Element element = (Element) currentNode;

                String name = element.getAttribute("name");
                String ext = element.getElementsByTagName("extension").item(0).getTextContent();
                filterList.add(new FileChooser.ExtensionFilter(name, ext));
            }
        }
    }

    public void fileExplorerButtonAction (ActionEvent event)
    {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open PathPlanner File");
        if(filterList != null)
            fileChooser.getExtensionFilters().addAll(filterList);
        fileChooser.setInitialDirectory(new File("C:\\Users\\Jeffr\\IdeaProjects\\PathPlanner"));

        File file = fileChooser.showOpenDialog(new Stage());
        if(file != null)
        {
            fileNameTextField.setText(file.getName());
            fileName = file.getName();
            selectedFile = file;
        }
    }

    public void submitButtonAction (ActionEvent event)
    {
        System.out.println("button Pressed");
    }

    public void fileNameTextFieldAction (ActionEvent event) {
        Matcher matcher = fileNamePattern.matcher(fileNameTextField.getText());
        if (!matcher.find())
            fileNameTextField.setText(fileName + "");
        else {
            fileName = fileNameTextField.getText();
            selectedFile = new File(fileName);
            System.out.println(selectedFile);
            if(selectedFile == null)
                fileNameTextField.setText("");
        }
    }
}
