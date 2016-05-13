/**
 * 
 */
package controller;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import view.Axes;
import view.alerts.SaveAlert;

/**
 * @author Cezara C.
 * 
 * @version 1.0, Apr 2016
 *
 */
public class SaveButtonController implements EventHandler<ActionEvent> {
	
	private StackPane graphArea;
	private Axes axes;
	private Stage stage;
	private Desktop desktop;
	
	public SaveButtonController(Stage stage, StackPane graphArea, Axes axes) {
		
		this.graphArea = graphArea;
		this.axes = axes;
		this.stage = stage;
		
		desktop = Desktop.getDesktop();
	}

	@Override
	public void handle(ActionEvent event) {
		
		/*get points pane for testing if there are points that are not part of a graph*/
		Pane pointsPane = (Pane)axes.getChildren().get(4);
		
		if(pointsPane.getChildren().size() > 0) {
			
			SaveAlert cannotSaveAlert = SaveAlert.getInstance();
			cannotSaveAlert.showAndWait();
			
			return;
		}
		
		WritableImage image = graphArea.snapshot(new SnapshotParameters(), null);
		
		
		FileChooser fileChooser = new FileChooser();
		configureFileChooser(fileChooser);
		
		File file = fileChooser.showSaveDialog(stage);
		
		/*in case of a canceling the save action*/
		if(file != null) {
			String fileName = file.getName();
			String extension = fileName.substring(fileName.lastIndexOf(".")+1);
			extension = "png";

			try {

				/*if(extension.contentEquals("svg")) {
						StringBuilder sb = new StringBuilder();

						Graph graph = (Graph) axes.getChildren().get(5); 

						sb.append("<?xml version=\"1.0\" standalone=\"no\"?><!DOCTYPE svg PUBLIC \""
								+ "-//W3C//DTD SVG 1.1//EN\" \"http://www.w3.org/Graphics/SVG/1.1/DTD/svg11.dtd"
								+ "\">");

						sb.append("<svg width=\"")
						  .append(axes.getWidth()).append("\" height=\"")
						  .append(axes.getHeight()).append("\" "
						  		+ " xmlns=\"http://www.w3.org/2000/svg\"><path d=\"")
						  .append(graph.convertToSVG()).append("\"/></svg>");

						PrintWriter writer = new PrintWriter(file.getAbsolutePath(), "UTF-8");
						writer.println(sb.toString());
						writer.close();
					}

					else */
				ImageIO.write(SwingFXUtils.fromFXImage(image, null), extension, file);

				desktop.open(file);

				axes.setUnsaved(false);
			}

			catch (IOException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}

		}

	}

	private void configureFileChooser(FileChooser fileChooser) {
		
		fileChooser.setTitle("Save Image");
		fileChooser.setInitialDirectory(new File(Paths.get(".").toAbsolutePath().normalize().toString()));
		fileChooser.setInitialFileName("untitled");
		fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("JPG", "*.jpg"),
												 new FileChooser.ExtensionFilter("PNG", "*.png"),
												 new FileChooser.ExtensionFilter("SVG", "*.svg"));
	}
}
