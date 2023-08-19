package net.toiviainen.pong.scene;

import static java.util.Objects.requireNonNull;
import static net.toiviainen.pong.PongApplication.BIG_FONT;
import static net.toiviainen.pong.PongApplication.RESOLUTION_HEIGHT;
import static net.toiviainen.pong.PongApplication.RESOLUTION_WIDTH;
import static net.toiviainen.pong.PongApplication.SMALL_FONT;

import javafx.collections.ObservableList;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import net.toiviainen.pong.PongApplication;
import java.io.FileWriter;
import java.io.IOException;
// adding textfield
import javafx.scene.control.TextField;

/**
 * <p>
 * The welcoming scene for the Pong game.
 * </p>
 * <p>
 * This scene is the introduction scene which will be shown to users when they
 * start the application. Scene contains the topic of the game, instructions
 * about how to move the paddles with the keyboard and a text which contains an
 * instructions about how to start the game.
 * </p>
 */
public class WelcomeScene extends AbstractScene {

	private final Text topicText;
	private final Text leftControlsTopicText;
	private final Text leftControlsText;
	private final Text rightControlsTopicText;
	private final Text rightControlsText;
	private final Text proceedInstructionsText;

	/* The below variables are declared by Jay for creating the boxes
    to accept the names of the two players */
	private Text firstPlayerName;
	private TextField player1Name;
	private Text secondPlayerName;
	private TextField player2Name;


	public WelcomeScene(PongApplication application) throws NullPointerException {
		super(new Group(), RESOLUTION_WIDTH, RESOLUTION_HEIGHT);

		requireNonNull(application, "The application cannot be null!");

		topicText = new Text("JavaFX Pong");
		topicText.setTextOrigin(VPos.CENTER);
		topicText.setFont(BIG_FONT);
		topicText.setLayoutX((RESOLUTION_WIDTH - topicText.prefWidth(-1)) / 2);
		topicText.setLayoutY(RESOLUTION_HEIGHT / 6);
		topicText.setFill(Color.WHITE);

		leftControlsTopicText = new Text("Controls for the left player:");
		leftControlsTopicText.setTextOrigin(VPos.CENTER);
		leftControlsTopicText.setFont(SMALL_FONT);
		leftControlsTopicText.setLayoutX((RESOLUTION_WIDTH - leftControlsTopicText.prefWidth(-1)) / 2);
		leftControlsTopicText.setLayoutY(topicText.getLayoutY() + 100);
		leftControlsTopicText.setFill(Color.WHITE);

		leftControlsText = new Text("W and S");
		leftControlsText.setTextOrigin(VPos.CENTER);
		leftControlsText.setFont(SMALL_FONT);
		leftControlsText.setLayoutX((RESOLUTION_WIDTH - leftControlsText.prefWidth(-1)) / 2);
		leftControlsText.setLayoutY(leftControlsTopicText.getLayoutY() + 40);
		leftControlsText.setFill(Color.WHITE);

		rightControlsTopicText = new Text("Controls for the right player:");
		rightControlsTopicText.setTextOrigin(VPos.CENTER);
		rightControlsTopicText.setFont(SMALL_FONT);
		rightControlsTopicText.setLayoutX((RESOLUTION_WIDTH - rightControlsTopicText.prefWidth(-1)) / 2);
		rightControlsTopicText.setLayoutY(leftControlsText.getLayoutY() + 60);
		rightControlsTopicText.setFill(Color.WHITE);

		rightControlsText = new Text("UP-ARROW and DOWN-ARROW");
		rightControlsText.setTextOrigin(VPos.CENTER);
		rightControlsText.setFont(SMALL_FONT);
		rightControlsText.setLayoutX((RESOLUTION_WIDTH - rightControlsText.prefWidth(-1)) / 2);
		rightControlsText.setLayoutY(rightControlsTopicText.getLayoutY() + 40);
		rightControlsText.setFill(Color.WHITE);

		proceedInstructionsText = new Text("Press [ENTER] to start the match");
		proceedInstructionsText.setTextOrigin(VPos.CENTER);
		proceedInstructionsText.setFont(SMALL_FONT);
		proceedInstructionsText.setLayoutX((RESOLUTION_WIDTH - proceedInstructionsText.prefWidth(-1)) / 2);
		proceedInstructionsText.setLayoutY(rightControlsTopicText.getLayoutY() + 235);
		proceedInstructionsText.setFill(Color.WHITE);

        /* The below code is written by Jay for showing the tabs
		to accept the names of the two players on the welcome scene
		 */
		firstPlayerName = new Text("Player 1 Name: ");
		firstPlayerName.setTextOrigin(VPos.CENTER);
		firstPlayerName.setFont(SMALL_FONT);
		firstPlayerName.setLayoutX((RESOLUTION_WIDTH - proceedInstructionsText.prefWidth(-1)) / 2);
		firstPlayerName.setLayoutY(rightControlsTopicText.getLayoutY() + 110);
		firstPlayerName.setFill(Color.WHITE);

		player1Name = new TextField();
		player1Name.setLayoutX((RESOLUTION_WIDTH - player1Name.prefWidth(-1)) / 2);
		player1Name.setLayoutY(player1Name.getLayoutY() + 395);


		secondPlayerName = new Text("Player 2 Name: ");
		secondPlayerName.setTextOrigin(VPos.CENTER);
		secondPlayerName.setFont(SMALL_FONT);
		secondPlayerName.setLayoutX((RESOLUTION_WIDTH - proceedInstructionsText.prefWidth(-1)) / 2);
		secondPlayerName.setLayoutY(rightControlsTopicText.getLayoutY() + 155);
		secondPlayerName.setFill(Color.WHITE);

		player2Name = new TextField();
		player2Name.setLayoutX((RESOLUTION_WIDTH - player2Name.prefWidth(-1)) / 2);
		player2Name.setLayoutY(player2Name.getLayoutY() + 440);


		Parent root = getRoot();
		if (!(root instanceof Group)) {
			throw new AssertionError("The scene root is not a Group instance!");
		}

		Group rootGroup = (Group) root;
		ObservableList<Node> children = rootGroup.getChildren();
		children.add(topicText);
		children.add(leftControlsTopicText);
		children.add(leftControlsText);
		children.add(rightControlsTopicText);
		children.add(rightControlsText);
		children.add(proceedInstructionsText);
		children.add(firstPlayerName);
		children.add(player1Name);
		children.add(secondPlayerName);
		children.add(player2Name);


		setOnKeyReleased(x -> {
			if (x.getCode() == KeyCode.ENTER) {
				try {
					String p1Name = player1Name.getText();
					String p2Name = player2Name.getText();

					if (p1Name.isEmpty() || p2Name.isEmpty()) {
						//Display Error Message
						Text errorMessage = new Text("Text boxes cannot be empty! Please Enter Player Names");
						errorMessage.setFont(SMALL_FONT);
						errorMessage.setFill(Color.ORANGERED);
						errorMessage.setLayoutX(RESOLUTION_WIDTH - errorMessage.prefWidth(-1) / 0.73);
						errorMessage.setLayoutY(proceedInstructionsText.getLayoutY() + 45);
						((Group) getRoot()).getChildren().add(errorMessage);
					} else {
						// if valid input
						write1(p2Name);
						write2(p1Name);
						//move into the court scene
						Stage primaryStage = application.getPrimaryStage();
						primaryStage.setScene(new CourtScene(application));

					}
				} catch (NullPointerException e) {
					e.printStackTrace();
				}
			}
		});


		setFill(Color.BLACK);
	}

//	String name1 = player1Name.getText();
//	String name2 = player2Name.getText();

//	public String getName1() {
//		return name1;
//	}
//
//	public String getName2() {
//		return name2;
//	}


//	public String getPlayer1Name(){
//		return player1Name.getText();
//	}
//
//	public String getPlayer2Name(){
//		return player2Name.getText();
//	}


	@Override
	public void tick() {
		// ... nothing to do ...
	}


	public void write1(String name) {
		try {
			FileWriter fileWriter = new FileWriter("p1Name.txt");
			fileWriter.write(name);
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void write2(String name) {
		try {
		FileWriter fileWriter = new FileWriter("p2Name.txt");
		fileWriter.write(name);
		fileWriter.close();
	} catch (IOException e) {
		e.printStackTrace();
	}

   }
}
