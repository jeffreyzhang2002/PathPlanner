package com.gui.startup;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateNewEnvironmentController //implements Initializable
{
    private Pattern rowsColsPattern = Pattern.compile("^\\d*[1-9]\\d*$");
    private Pattern fileNamePattern = Pattern.compile("^[\\w\\-. ]+$");
    @FXML private ChoiceBox environmentTypeChoiceBox;
    @FXML private Label extensionLabel;
    @FXML private TextField rowsTextField, colsTextField, fileNameTextField;

    private int rows = 10, cols = 10;
    private String fileName = "default";

    private HashMap<String, Element> environmentTypeMap = new HashMap<>();
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
                String environmentTypeName = element.getAttribute("name");
                environmentTypeMap.put(environmentTypeName, element);
                environmentTypeChoiceBox.getItems().add(environmentTypeName);

                if(element.getAttribute("default").equals("true")) {
                    environmentTypeChoiceBox.setValue(environmentTypeName);
                }
            }
        }

        rowsTextField.setText(rows + "");
        colsTextField.setText(cols + "");
        fileNameTextField.setText(fileName + "");
    }


    public void environmentTypeChoiceBoxAction(ActionEvent event)
    { extensionLabel.setText(environmentTypeMap.get(environmentTypeChoiceBox.getValue()).getElementsByTagName("extension").item(0).getTextContent()); }

    public void colsTextFieldAction(ActionEvent event)
    {
        Matcher matcher = rowsColsPattern.matcher(colsTextField.getText());
        if(!matcher.find())
            colsTextField.setText(cols + "");
        else
            cols = Integer.parseInt(colsTextField.getText());
    }

    public void rowsTextFieldFieldAction(ActionEvent event)
    {
        Matcher matcher = rowsColsPattern.matcher(rowsTextField.getText());
        if(!matcher.find())
            rowsTextField.setText(rows + "");
        else
            rows = Integer.parseInt(rowsTextField.getText());
    }

    public void fileNameTextFieldAction (ActionEvent event)
    {
        Matcher matcher = fileNamePattern.matcher(fileNameTextField.getText());
        if(!matcher.find())
            fileNameTextField.setText(fileName + "");
        else
            fileName = fileNameTextField.getText();
    }

    public void enterButtonAction(ActionEvent event)
    {
        try {

        }
        catch (Exception E)
        {
            System.out.println("application Startup failture");
        }
    }
}
