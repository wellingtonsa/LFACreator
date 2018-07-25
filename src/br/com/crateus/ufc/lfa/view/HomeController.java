package br.com.crateus.ufc.lfa.view;

import java.net.URL;
import java.util.ResourceBundle;

import br.com.crateus.ufc.lfa.model.State;
import br.com.crateus.ufc.lfa.model.TransitionDFA;
import br.com.crateus.ufc.lfa.repository.AutomatoRepository;
import br.com.crateus.ufc.lfa.util.FXMLResources;
import br.com.crateus.ufc.lfa.util.StateDraw;
import br.com.crateus.ufc.lfa.util.TransitionDraw;
import javafx.application.Application;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class HomeController extends Application implements Initializable {
	public static Stage myStage;

	@FXML
	Pane pDesktop;
	@FXML
	ToggleButton tgStates;
	@FXML
	ToggleButton tgTransition;

	AutomatoRepository ap;
	Circle cStart;
	Circle cEnd;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		HomeController.myStage = primaryStage;
		Pane pane = (Pane) FXMLLoader.load(FXMLResources.HOME);
		primaryStage.setScene(new Scene(pane));
		primaryStage.setResizable(false);
		primaryStage.initStyle(StageStyle.TRANSPARENT);
		primaryStage.show();

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ap = new AutomatoRepository();

		pDesktop.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if (event.getButton() == MouseButton.PRIMARY) {
					if (tgStates.isSelected()) {
						State s = new State();
						Circle c = new StateDraw(Color.BLACK, new SimpleDoubleProperty(event.getX()),
								new SimpleDoubleProperty(event.getY()));
						s.setName("Qn");
						pDesktop.getChildren().addAll(c);
						ap.addState(c, s);

					} else if (tgTransition.isSelected()) {
						cStart = ap.getStateCircle(event.getX(), event.getY());
					}

				}
			}

		});

		pDesktop.setOnMouseReleased(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if (event.getButton() == MouseButton.PRIMARY) {
					if (tgStates.isSelected()) {

					} else if (tgTransition.isSelected()) {

						cEnd = ap.getStateCircle(event.getX(), event.getY());
						TransitionDraw td = new TransitionDraw(cStart.centerXProperty(), cStart.centerYProperty(),
								cEnd.centerXProperty(), cEnd.centerYProperty());
						// TransitionDFA t = new TransitionDFA(origin, destination, condition);
						pDesktop.getChildren().addAll(td);
						// ap.addTransition(td, t);

					}

				}

			}
		});

	}

}
