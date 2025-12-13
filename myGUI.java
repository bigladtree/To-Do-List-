// Winter Break Practice #1
// 12-12-2025
// To Do List with CSS to make it PRETTAY !!!!! :-))))
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;



public class myGUI extends Application {
//File Reader to get saved tasks
private void saveJobsToFile(ObservableList<ListItem> listItems) {
			try(BufferedWriter brw = new BufferedWriter(new FileWriter("student_data.csv"))) {
				for (ListItem t : listItems) {
					brw.write(String.format("%s, %s, %s, %.1f, %s",
							t.getTask()
							));
					brw.newLine();
				}
				} catch (IOException e) {
				e.printStackTrace();
			}
			}
@Override
public void start(Stage primaryStage) throws Exception {

    BorderPane pane = new BorderPane();
    ListView todoList = new ListView();
    TextField todoText = new TextField();
    Button todoButton = new Button("Add Item");

    todoButton.setOnAction(e -> {

        String result = todoText.getText();
        todoList.getItems().add(result);
        todoText.clear();

    });

    HBox hbox = new HBox();
    hbox.getChildren().addAll(todoText, todoButton);
    pane.setCenter(todoList);
    pane.setBottom(hbox);
    Scene scene = new Scene(pane, 300, 300);

    primaryStage.setTitle("To Do List");
    primaryStage.setScene(scene);
    primaryStage.show();

}

 public static void main(String[] args) {
    launch(args);
 }
}