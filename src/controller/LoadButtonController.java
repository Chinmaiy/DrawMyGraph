/**
 * 
 */
package controller;

import java.io.File;
import java.nio.file.Paths;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import view.Axes;

/**
 * @author Cezara C.
 * 
 * @version 1.0, Apr 2016
 *
 */
public class LoadButtonController implements EventHandler<MouseEvent>{
	
	StackPane graphArea;
	Stage stage;

	public LoadButtonController(Stage stage, StackPane graphArea) {
		
		this.stage = stage;
		this.graphArea = graphArea;
	}

	@Override
	public void handle(MouseEvent event) {
		
		FileChooser fileChooser = new FileChooser();
		configureFileChooser(fileChooser);
		File file = fileChooser.showOpenDialog(stage);
		
		if(file != null) {
			Axes axes = (Axes)graphArea.getChildren().get(0);
			int size = axes.getChildren().size();

			/*delete everything except the axes and,labels and the pane for drawing points*/
			while(size > 5) {
				axes.getChildren().remove(5);
				size = axes.getChildren().size();
			}

			Pane pointsPane = (Pane)axes.getChildren().get(4);
			pointsPane.getChildren().clear();

			graphArea.setBackground(new Background(
					new BackgroundImage(new Image("file:" + file.getAbsolutePath()), BackgroundRepeat.NO_REPEAT,
							BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
			
			axes.setUnsaved(false);
		}
	}
	
private void configureFileChooser(FileChooser fileChooser) {
		
		fileChooser.setTitle("Load Image");
		fileChooser.setInitialDirectory(new File(Paths.get(".").toAbsolutePath().normalize().toString()));
		fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("JPG", "*.jpg"),
												 new FileChooser.ExtensionFilter("PNG", "*.png"));
	}
}
