package com.gui.startup;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class StartUpApplicationController
{

    @FXML private Pane PanelCreate;
    @FXML private Pane PanelOpen;
    @FXML OpenExistingEnvironmentController OpenPanelController;
    @FXML CreateNewEnvironmentController CreatePanelController;

    private Stage myStage;
    private NodeList environmentList;

    public void setUp(Element rootElement, Stage myStage)
    {
        environmentList = rootElement.getElementsByTagName("world");
        CreatePanelController.setUp(environmentList, myStage);
        OpenPanelController.setUp(environmentList, myStage);
        this.myStage = myStage;
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
    { Platform.exit(); }
}
