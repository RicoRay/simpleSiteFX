/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import com.pixelduke.control.ParallaxPane;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker.State;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

/**
 *
 * @author carsint
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private AnchorPane anchorpane;

    @FXML
    private JFXHamburger hamburger;

    @FXML
    private JFXDrawer drawer;

    @FXML
    private VBox mainvbox;

    @FXML
    private ScrollPane scrollpane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            VBox box = FXMLLoader.load(getClass().getResource("/fxml/DrawerContent.fxml"));
            drawer.setSidePane(box);

            scrollpane.setContent(mainvbox);
            scrollpane.setHbarPolicy(ScrollBarPolicy.NEVER);
            scrollpane.setFitToWidth(true);

            ParallaxPane parallaxPane = new ParallaxPane();
            String urlImg = getClass().getResource("/images/DSC0185.JPG").toExternalForm();
            parallaxPane.setContent(new ImageView(urlImg));
            mainvbox.getChildren().add(parallaxPane);

            WebView myWebView = new WebView();
            WebEngine webEngine = myWebView.getEngine();
            URL urlHTML1 = getClass().getResource("/html/myHTML1.html");
            webEngine.load(urlHTML1.toString());
//            webEngine.getLoadWorker().stateProperty().addListener((ObservableValue<? extends State> observable, State oldValue, State newValue) -> {
//                if (newValue == State.SUCCEEDED) {
//
//                    mainvbox.getChildren().add(myWebView);
//
//                    ParallaxPane parallaxPane3 = new ParallaxPane();
//                    String urlImg3 = getClass().getResource("/images/DSC0188.JPG").toExternalForm();
//                    parallaxPane3.setContent(new ImageView(urlImg3));
//                    mainvbox.getChildren().add(parallaxPane3);
//                }
//            });

            mainvbox.getChildren().add(myWebView);

            ParallaxPane parallaxPane3 = new ParallaxPane();
            String urlImg3 = getClass().getResource("/images/DSC0188.JPG").toExternalForm();
            parallaxPane3.setContent(new ImageView(urlImg3));
            mainvbox.getChildren().add(parallaxPane3);

            HamburgerBackArrowBasicTransition burgerTask2 = new HamburgerBackArrowBasicTransition(hamburger);
            burgerTask2.setRate(-1);
            hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, e -> {
                burgerTask2.setRate(burgerTask2.getRate() * -1);
                burgerTask2.play();

                if (drawer.isClosed()) {
                    drawer.open();
                } else {
                    drawer.close();
                }
            });
        } catch (IOException ex) {
            System.out.println(ex.toString());
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
