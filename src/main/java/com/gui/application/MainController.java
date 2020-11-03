package com.gui.application;

import com.gui.render.world.environment.PlaneRender;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.FileInputStream;
import java.util.HashMap;

public class MainController
{
    private Element rootElement;

    @FXML private LeftController LeftPanelController;
    @FXML private BorderPane centerDisplay;
    @FXML private ToolBar toolbar;
    @FXML private Label mouseLabel;
    private HashMap<Button, Class> map = new HashMap<>();

    public void setUp(Element rootElement)
    {
        this.rootElement = rootElement;
        LeftPanelController.setUp(rootElement);

        configureToolBar(rootElement);

        PlaneRender p = new PlaneRender(400,400);
        Pane r = new Pane();

        EventHandler<MouseEvent> mouseDraggedEvent = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                mouseLabel.setText("Mouse: (" + event.getX() + "," + event.getY() + ")");
            }
        };

        EventHandler<MouseEvent> mouseExitedEvent = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                mouseLabel.setText("Mouse: (X,Y)");
            }
        };

        r.setOnMouseMoved(mouseDraggedEvent);
        r.setOnMouseExited(mouseExitedEvent);

        //p.render(r);
        BorderPane.setAlignment(r, Pos.CENTER);
        centerDisplay.setCenter(r);
    }

    public void configureToolBar(Element element)
    {
        NodeList nodes = rootElement.getElementsByTagName("actors").item(0).getChildNodes();
        for(int i = 0; i<nodes.getLength(); i++) {
            if (nodes.item(i).getNodeType() == Node.ELEMENT_NODE) {
                Button b = new Button();

                try {
                    String s = (((Element) nodes.item(i)).getElementsByTagName("classPath").item(0).getTextContent()).trim();
                    System.out.println(s);
                    Class c = Class.forName(s);
                    map.put(b, c);
                } catch (ClassNotFoundException e) {
                    System.out.println("class not found");
                }

                EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        Class c = map.get(event.getSource());
                        System.out.println(c.getName());
                    }
                };

                b.setOnAction(event);

                try {
                    String s = (((Element) nodes.item(i)).getElementsByTagName("icon").item(0).getTextContent()).trim();
                    Image img = new Image(new FileInputStream(s));
                    ImageView imgView = new ImageView(img);
                    imgView.setPreserveRatio(true);
                    imgView.setFitHeight(25);
                    b.setGraphic(imgView);
                } catch (Exception e) {
                    b.setText(((Element) nodes.item(i)).getAttribute("name"));
                }
                Tooltip tooltip = new Tooltip(((Element) nodes.item(i)).getAttribute("name"));
                b.setTooltip(tooltip);
                toolbar.getItems().add(b);
            }
        }
    }
}
