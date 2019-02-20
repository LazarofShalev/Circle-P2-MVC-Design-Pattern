
import java.util.ArrayList;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class MultiplyCircleMVC2 extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {

		ArrayList<CircleController2> circleControllerList = new ArrayList<CircleController2>();
		ArrayList<CircleView2> circleViewList = new ArrayList<CircleView2>();
		ArrayList<CircleModel2> circleModelList = new ArrayList<CircleModel2>();

		SimpleIntegerProperty circleCounter = new SimpleIntegerProperty(0);

		Button btControllerView = new Button("Show Controller and View");

		HBox controllerViewPane = new HBox();
		controllerViewPane.setSpacing(10);
		controllerViewPane.setAlignment(Pos.CENTER);

		controllerViewPane.getChildren().add(btControllerView);

		btControllerView.setOnAction(e -> {

			circleModelList.add(new CircleModel2(new SimpleIntegerProperty(circleCounter.getValue())));

			/** Controller **/

			circleControllerList.add(new CircleController2(new SimpleIntegerProperty(circleCounter.intValue())));
			circleControllerList.get(circleCounter.intValue()).setModel(circleModelList.get(circleCounter.intValue()));

			Stage controllerStage = new Stage();
			Scene controllerScene = new Scene(circleControllerList.get(circleCounter.intValue()), 300, 180);

			controllerStage.setTitle("Controller number " + (circleCounter.intValue() + 1));
			controllerStage.setScene(controllerScene);
			controllerStage.show();
			controllerStage.setAlwaysOnTop(true);
			controllerStage.setResizable(false);

			/** View **/

			circleViewList.add(new CircleView2(new SimpleIntegerProperty(circleCounter.getValue())));
			circleViewList.get(circleCounter.intValue()).setModel(circleModelList.get(circleCounter.intValue()));

			Stage viewStage = new Stage();
			Scene viewScene = new Scene(circleViewList.get(circleCounter.intValue()), 400, 150);

			viewStage.setTitle("View number " + (circleCounter.intValue() + 1));
			viewStage.setScene(viewScene);
			viewStage.show();
			viewStage.setAlwaysOnTop(true);

			circleCounter.setValue(circleCounter.intValue() + 1);

		});

		Scene mainScene = new Scene(controllerViewPane, 200, 350);
		primaryStage.setTitle("MultiplyCircleMVC2");
		primaryStage.setScene(mainScene);
		primaryStage.show();
		primaryStage.setAlwaysOnTop(true);
		primaryStage.setResizable(false);
		primaryStage.setOnCloseRequest(e -> Platform.exit());

	}

	public static void main(String[] start) {
		launch(start);
	}
}