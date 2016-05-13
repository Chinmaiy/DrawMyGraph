/**
 * 
 */
package view;

import controller.ColorPickerController;
import controller.DrawButtonController;
import controller.DrawFromPointsButtonController;
import controller.FunctionFieldController;
import controller.GraphAreaClickedController;
import controller.LoadButtonController;
import controller.MainController;
import controller.ResetButtonController;
import controller.SaveButtonController;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

/**
 * @author Cezara C.
 * 
 * @version 1.0, Apr 2016
 *
 */
public class MainWindow {
	
	private final int MIN_WIDTH  = 800; //400 - 600
	private final int MIN_HEIGHT = 700; //400 - 500
	private final int AXES_DIM = 600;
	
	private final String TITLE = "DrawMyGraph";
	private final String MAIN_ICON = "images/DrawMyGraph.jpg";
	private final String PENCIL_CURSOR = "images/pencil.png";
	private final String BRUSH_CURSOR = "images/brush.png";
	
	private MainController controller;
	private Stage mainWindow;
	private SplitPane mainSplitPane = new SplitPane();
	
			private Label functionLabel;
			private TextField functionField;
			
			private Label strokeWidthLabel;
			private Slider strokeWidthSlider;
			
			private ColorPicker colorPicker;
			
			private Line sampleLine;
			
			private Button drawButton;
			private Button drawPointsButton;
			private Button resetButton;
			private Button saveButton;
			private Button loadButton;
			
			private StackPane graphArea;
			private Axes axes;
			private Pane pointsPane;
	
	public MainWindow(Stage stage) {
		
		this.mainWindow = stage;
		
		this.decorateTitleBar();
		this.setMinSize();
		
		this.initFunctionField();
		this.initFunctionLabel();
		VBox function = this.createSmallVBox(functionLabel, functionField);
		
		this.initStrokeWidthLabel();
		this.initStrokeWidthSlider();
		VBox stroke = this.createSmallVBox(strokeWidthLabel, strokeWidthSlider);
		
		this.initColorPicker();
		
		this.initSampleLine();
		
		this.initButtons();	
		
		VBox controlsArea = this.createBigVBox(function,
											   stroke, colorPicker, sampleLine,
											   drawButton, drawPointsButton, resetButton,
											   saveButton, loadButton);
		
		this.initGraphArea();
		
		axes = new Axes(AXES_DIM, AXES_DIM);
		graphArea.getChildren().add(axes);
		
		this.initPointsPane(AXES_DIM, AXES_DIM);
		axes.getChildren().add(pointsPane);
		
		mainSplitPane.getItems().addAll(controlsArea, graphArea);
		
		this.addEvents();
		
		Scene mainScene = new Scene(mainSplitPane);
		
		mainSplitPane.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
		
		mainWindow.setScene(mainScene);
	}

	private void decorateTitleBar() {
		
		mainWindow.setTitle(TITLE);
		mainWindow.getIcons().add(new Image(getClass().
				getResource(MAIN_ICON).toExternalForm()));
	}
	
	private void setMinSize() {

		mainWindow.setMinWidth(MIN_WIDTH);
		mainWindow.setMinHeight(MIN_HEIGHT);
	}
	
	private VBox createSmallVBox(Node... nodes) {
	
		VBox vBox = new VBox();
		
		vBox.setSpacing(5);
		vBox.setAlignment(Pos.CENTER);
		
		vBox.getChildren().addAll(nodes);
		
		return vBox;
	}
	
	private VBox createBigVBox(Node... nodes) {
		
		VBox vBox = new VBox();
		
		vBox.setAlignment(Pos.TOP_CENTER);
		vBox.setPrefWidth(140);
		vBox.setMinWidth(140);
		vBox.setMaxWidth(200);
		vBox.setSpacing(20);
		vBox.setPadding(new Insets(10,5,0,10));
		vBox.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, null, null)));
		
		vBox.getChildren().addAll(nodes);
		
		return vBox;
	}
	
	private void addEvents() {
		functionField.textProperty().addListener(new FunctionFieldController(functionField));
		
		colorPicker.valueProperty().addListener(new ColorPickerController(sampleLine));
		
		drawButton.setOnMouseClicked(new DrawButtonController(functionField, strokeWidthSlider, colorPicker,axes));
		drawPointsButton.setOnMouseClicked(new DrawFromPointsButtonController
											(pointsPane, strokeWidthSlider, colorPicker, axes));
		
		drawButton.disableProperty().bind(Bindings.not(
				functionField.getTooltip().textProperty().isEqualTo("Go ahead and draw.")));
		drawPointsButton.disableProperty().bind(Bindings.size(pointsPane.getChildren()).lessThan(2));
		
		resetButton.setOnMouseClicked(new ResetButtonController(graphArea, axes, pointsPane, saveButton));
		
		saveButton.setOnAction(new SaveButtonController(mainWindow, graphArea, axes));
		loadButton.setOnMouseClicked(new LoadButtonController(mainWindow, graphArea));
		
		pointsPane.setOnMouseClicked(new GraphAreaClickedController(pointsPane));
	}
	
	private void initFunctionLabel() {
		functionLabel = new Label("Your function:");
	}
	
	private void initFunctionField() {

		functionField = new TextField();
		functionField.setPromptText("Enter function");
		
		Tooltip functionTooltip = new Tooltip("Enter function.");
		functionField.setTooltip(functionTooltip);
	}
	

	private void initStrokeWidthLabel() {
		
		strokeWidthLabel = new Label("Stroke width:");
	}
	
	private void initStrokeWidthSlider() {
		
		strokeWidthSlider = new Slider(1, 25, 2);
		strokeWidthSlider.setCursor(Cursor.CLOSED_HAND);
	}
	
	private void initColorPicker() {
		
		colorPicker = new ColorPicker();
		colorPicker.setValue(Color.BLACK);
		colorPicker.cursorProperty().set(new ImageCursor(new Image(
				getClass().getResource(BRUSH_CURSOR).toExternalForm()), 102,378));
	}
	
	private void initSampleLine() {
		
		sampleLine = new Line(0,40,80,0);
		sampleLine.strokeWidthProperty().bind(strokeWidthSlider.valueProperty());
	}
	
	private void initButtons() {

		drawButton = new Button("Draw Function");
		drawButton.setCursor(new ImageCursor(new Image(
				getClass().getResource(BRUSH_CURSOR).toExternalForm()), 102,378));
		
		drawPointsButton = new Button("Draw From Points");
		drawPointsButton.setCursor(new ImageCursor(new Image(
				getClass().getResource(BRUSH_CURSOR).toExternalForm()), 102,378));
		
		resetButton = new Button("Reset");
		
		saveButton = new Button("Save");
		
		loadButton = new Button("Load");
	}
	
	public void initGraphArea() {
		
		graphArea = new StackPane();
		graphArea.setBackground(new Background(new BackgroundFill(Color.WHITE, null,null)));
		graphArea.setPadding(new Insets(20));
		
		graphArea.setCursor(new ImageCursor(new Image(getClass().
								getResource(PENCIL_CURSOR).toExternalForm()), 42,194));
	}

	private void initPointsPane(int width, int height) {
		
		pointsPane = new Pane();
		pointsPane.setMinSize(Pane.USE_PREF_SIZE, Pane.USE_PREF_SIZE);
        pointsPane.setPrefSize(width, height);
        pointsPane.setMaxSize(Pane.USE_PREF_SIZE, Pane.USE_PREF_SIZE);
        
	}

	/**
	 * @return the controller
	 */
	public MainController getMainController() {
		return controller;
	}

	/**
	 * @param controller the controller to set
	 */
	public void setMainController(MainController mainController) {
		this.controller = mainController;
	}	
}
