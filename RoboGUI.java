package view;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;

import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.control.Slider;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import javafx.scene.layout.VBox;

import javafx.scene.text.Text;
import javafx.stage.Stage;

public class RoboGUI extends Application implements RoboGUI_IF {
	
	Socket s;
	DataOutputStream out;
	DataInputStream in;
	private Button autoB, semiB, manB, vasen, oikea, ylos, alas, start, stop, exit;
	private Text auto, semi, man, nopeus;
	private BorderPane border = new BorderPane();
	private HBox hbox = new HBox();
	private HBox hboxAla = new HBox();
	private VBox vbox = new VBox();
	private BorderPane pane = new BorderPane();
	private GridPane grid = new GridPane();
	/**
	 * isPressed kertoo onko näppäin painettuna.
	 */
	private boolean isPressed = false;
	/**
	 * muuttujaa x kätetään viimeisimmän luetun komennon tallentamiseen.
	 */
	int x;
	/**
	 * Luodaan yhteys robotin kanssa
	 */
	public void connect() {

		try {
			s = new Socket("10.0.1.1", 1111);

			out = new DataOutputStream(s.getOutputStream());
			in = new DataInputStream(s.getInputStream());

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 *  luodaan sivujen otsikot
	 */
	private void createText() {
		auto = new Text();
		auto.setText("Automaattinen ohjaus");
		auto.setId("title");
		semi = new Text();
		semi.setText("Semiautomaattinen ohjaus");
		semi.setId("title");
		man = new Text();
		man.setText("Manuaalinen ohjaus");
		man.setId("title");

	}

	/**
	 * luodaan sivupaneelin nappulat ja tapahtumakäsittelijät
	 */
	private void createButtons() {

		autoB = new Button();
		autoB.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		autoB.setText("Autopilot");
		autoB.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// Näytä automaattisivu
				pane.getChildren().clear();
				hbox.getChildren().clear();
				hboxAla.getChildren().clear();
				pane.setId("autopilot");
				hbox.setAlignment(Pos.TOP_CENTER);
				hbox.getChildren().add(auto);
				hboxAla.setAlignment(Pos.BOTTOM_RIGHT);
				hboxAla.getChildren().add(start);
				hboxAla.getChildren().add(stop);
				// automapilotin käynnistys ja sammutus käsittelijät
				start.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					/**
					 * 
					 * @see javafx.event.EventHandler#handle(javafx.event.Event)
					 */
					public void handle(ActionEvent event) {
						try {
							if (x != 999) {
								out.writeInt(999);
								x = 999;

								out.flush();
							}

						} catch (Exception e) {
							e.printStackTrace();
						}

					}
				});
				stop.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {
						try {
							if (x != 1) {
								out.writeInt(1);
								x = 1;

								out.flush();
							}

						} catch (Exception e) {
							e.printStackTrace();
						}

					}
				});

			}
		});
		/**
		 *  Näytetään semiautomaatti
		 */
		semiB = new Button();
		semiB.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		semiB.setText("Semiauto");
		semiB.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				grid.getChildren().clear();
				pane.getChildren().clear();
				hbox.getChildren().clear();
				hboxAla.getChildren().clear();
				pane.setId("");
				hbox.setAlignment(Pos.TOP_CENTER);
				hboxAla.setAlignment(Pos.BOTTOM_RIGHT);
				hbox.getChildren().add(semi);
				hboxAla.getChildren().add(start);
				hboxAla.getChildren().add(stop);
				createSlider();
				start.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {
						try {
							if (x != 1) {
								out.writeInt(999);
								x = 999;
								out.flush();
							}
						} catch (Exception e) {
							e.printStackTrace();
						}

					}
				});
				// Semiautomaatin käynnistys ja sammutus käsittelijät
				stop.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {
						try {
							if (x != 1) {
								out.writeInt(1);
								x = 1;
								out.flush();
							}

						} catch (Exception e) {
							e.printStackTrace();
						}

					}
				});

			}
		});
		/**
		 *  Näytetään manuaalisivu
		 */
		manB = new Button();
		manB.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		manB.setText("Manual");
		manB.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// yläpalkin teksti
				grid.getChildren().clear();
				pane.getChildren().clear();
				hbox.getChildren().clear();
				hboxAla.getChildren().clear();
				pane.setId("");
				hbox.setAlignment(Pos.TOP_CENTER);
				hboxAla.setAlignment(Pos.BOTTOM_RIGHT);
				hbox.getChildren().add(man);
				hboxAla.getChildren().add(start);
				hboxAla.getChildren().add(stop);
				// näppäimet
				createNappaimet();
				// Manuaalin käynnistys ja sammutus käsittelijät
				start.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {

						try {
							if (x != 50) {
								out.writeInt(50);
								x = 50;
								out.flush();
							}

						} catch (Exception e) {
							e.printStackTrace();
						}

					}
				});
				stop.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {
						try {
							if (x != 1) {
								out.writeInt(1);
								x = 1;
								out.flush();
							}

						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});

			}
		});
		/**
		 * luiodaan robotin ohjelmanmsulkemispainike
		 */
		exit = new Button();
		exit.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		exit.setText("EXIT");
		exit.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				try {
					if (x != 666) {
						out.writeInt(666);
						x = 666;
						out.flush();
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	/**
	 * Luodaan käynnistys ja lopetus näppäimet
	 */
	private void createStartStop() {

		start = new Button();
		start.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		start.setId("start");
		stop = new Button();
		stop.setId("stop");
		stop.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
	}

	/**
	 *  luodaan nuolinäppäimet ja niiden tapahtumakäsittelijät hiirelle
	 */
	private void createNappaimet() {
		vasen = new Button();
		vasen.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		vasen.setId("left");
		oikea = new Button();
		oikea.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		oikea.setId("right");
		ylos = new Button();
		ylos.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		ylos.setId("up");
		alas = new Button();
		alas.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		alas.setId("down");

		// Vasemmalle painallus
		vasen.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// kääntyy vasemmalle
				try {
					out.writeInt(37);
					out.flush();

				} catch (Exception e) {
					e.printStackTrace();

				}

			}
		});

		// oikealle painallus
		oikea.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// kääntyy oikealla
				try {
					out.writeInt(39);
					out.flush();

				} catch (Exception e) {
					e.printStackTrace();

				}

			}

		});

		// ylospäin painallus
		ylos.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// ajaa eteenpain
				try {
					out.writeInt(38);
					out.flush();

				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});
		// Alas painallus
		alas.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// jarruttaa
				try {
					out.writeInt(40);
					out.flush();

				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});

		// käytetään hyväksi hiiren painalluksien tapahtumia ja tehdään samat
		// asiat nappaimiston painalluksilla sekä näytetään mitä näppäintä
		// painetaan
		grid.addEventHandler(KeyEvent.KEY_PRESSED, ev -> {
			vasen.setId("left");
			oikea.setId("right");
			ylos.setId("up");
			alas.setId("down");

			if (!isPressed) {
				if (ev.getCode() == KeyCode.UP) {
					ylos.setId("upg");
					ylos.fire();
					ev.consume();

				} else if (ev.getCode() == KeyCode.LEFT) {
					vasen.setId("leftg");
					vasen.fire();
					ev.consume();

				} else if (ev.getCode() == KeyCode.RIGHT) {
					oikea.setId("rightg");
					oikea.fire();
					ev.consume();

				} else if (ev.getCode() == KeyCode.DOWN) {
					alas.setId("downg");
					alas.fire();
					ev.consume();

				}

			}
		});

		grid.addEventHandler(KeyEvent.KEY_RELEASED, ev -> {
			try {
				if (isPressed && ev.getCode() == KeyCode.LEFT || ev.getCode() == KeyCode.RIGHT
						|| ev.getCode() == KeyCode.DOWN || ev.getCode() == KeyCode.UP)
					isPressed = false;
				vasen.setId("left");
				oikea.setId("right");
				ylos.setId("up");
				alas.setId("down");
				out.writeInt(-2);
				out.flush();

			} catch (Exception e) {
				e.printStackTrace();
			}

		});

		// lisätään näppäimet paneliin

		grid.setAlignment(Pos.CENTER);
		grid.setVgap(1);
		grid.setHgap(1);
		grid.add(ylos, 2, 2);
		grid.add(vasen, 1, 3);
		grid.add(oikea, 3, 3);
		grid.add(alas, 2, 3);
		pane.getChildren().add(grid);

	}

	/**
	 *  luodaan slider semisivulle
	 */
	private void createSlider() {

		Slider slider = new Slider(1, 6, 0.25);
		slider.setShowTickMarks(true);
		slider.setShowTickLabels(true);
		slider.setMajorTickUnit(0.25f);
		slider.setMinorTickCount(1);
		slider.setBlockIncrement(0.125f);
		slider.setSnapToTicks(true);
		slider.setOrientation(Orientation.VERTICAL);

		// käsitellään sliderin antamia arvoja ja lähetetään ne robotin
		// ohjelmalle
		slider.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			/**
			 * @see javafx.beans.value.ChangeListener#changed(javafx.beans.value.ObservableValue, java.lang.Object, java.lang.Object)
			 */
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

				try {
					if (x != newValue.intValue() * 100) {
						System.out.println(newValue.intValue() * 100);

						out.writeInt(newValue.intValue() * 100);
						x = newValue.intValue() * 100;
					}

				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});

		nopeus = new Text("Nopeus");
		nopeus.setId("nopeus");
		grid.setAlignment(Pos.CENTER);
		grid.add(nopeus, 2, 1);
		grid.add(slider, 2, 3);
		pane.getChildren().add(grid);

	}

	/**
	 * luodaan VBOX valikkonapeille
	 */
	private void createValikko() {

		vbox.setPadding(new Insets(10, 10, 10, 20));
		vbox.setSpacing(3);
		Text title = new Text("Mode");
		title.setId("title");
		autoB.setId("btn");
		semiB.setId("btn");
		manB.setId("btn");
		exit.setId("btn");
		vbox.getChildren().add(title);
		vbox.getChildren().add(autoB);
		vbox.getChildren().add(semiB);
		vbox.getChildren().add(manB);
		vbox.getChildren().add(exit);
	}

	/**
	 * luodaan Hboxit otsikoille ja start/stop-näppäimille
	 */
	private void createHbox() {
		hbox.setPadding(new Insets(12, 12, 12, 12));
		hboxAla.setPadding(new Insets(12, 12, 12, 12));
	}


	@Override
	/*
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	public void start(Stage primaryStage) throws Exception {
		try {
			// Start connection ja gui
			connect();
			primaryStage.setTitle("Viiva nenänpäässä");
			createButtons();
			createValikko();
			createHbox();
			createText();
			createStartStop();
			pane.setCenter(grid);
			border.setTop(hbox);
			border.setLeft(vbox);
			border.setCenter(pane);
			border.setBottom(hboxAla);
			border.setId("pane");

			Scene scene = new Scene(border, 640, 480);
			scene.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {

		launch(args);
	}

}