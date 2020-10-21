package com.pathplanner.gui.startup;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.w3c.dom.NodeList;

public class StartUpApplicationController
{

    @FXML Pane PanelCreate;
    @FXML Pane PanelOpen;
    @FXML OpenExistingEnvironmentController OpenPanelController;
    @FXML CreateNewEnvironmentController CreatePanelController;


    private Stage myStage = null;
    private NodeList nList;

    public void initialize()
    {

    }

    public void setMyStage(Stage myStage)
    { this.myStage = myStage; }

    public void setNodeList(NodeList nList)
    {
        this.nList = nList;
        OpenPanelController.setNodeList(nList);
        CreatePanelController.setNodeList(nList);
    }

    public void pressCreateButtonAction(ActionEvent event) throws Exception
    {
        PanelOpen.setVisible(false);
        PanelCreate.setVisible(true);
        PanelCreate.setMinSize(PanelCreate.getMinWidth(), 150);
        PanelOpen.setMinSize(PanelCreate.getMinWidth(), 0);
        myStage.sizeToScene();
    }

    public void pressOpenButtonAction(ActionEvent event)
    {
        PanelCreate.setVisible(false);
        PanelOpen.setVisible(true);
        PanelOpen.setMinSize(PanelOpen.getMinWidth(), 150);
        PanelCreate.setMinSize(PanelCreate.getMinWidth(), 0);
        myStage.sizeToScene();
    }

    public void pressExitButtonAction(ActionEvent event)
    {
        Platform.exit();
    }
}
