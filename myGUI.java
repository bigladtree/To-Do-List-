// Winter Break Practice #1
// Week 1
// To Do List with CSS to make it PRETTAY !!!!! :-))))

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;



public class myGUI extends Application {

//File Reader to save data between uses
public void saveJobsToFile(ObservableList<ListItem> listItems) {
			try(BufferedWriter brw = new BufferedWriter(new FileWriter("todolist.txt"))) {
				for (ListItem t : listItems) {
					brw.write(String.format("%s, %s, %d, %d",
							t.getTask(),
                            t.getDeadline(),
                            t.getPoints(),
                            t.getImportance()
							));
					brw.newLine();
				}
				} catch (IOException e) {
				e.printStackTrace();
			}
			}

@Override
public void start(Stage primaryStage) throws Exception {
    ObservableList<ListItem> todoList = FXCollections.observableArrayList();

//File Reader to get values for list 
    try (BufferedReader br = new BufferedReader(new FileReader("todolist.txt"))) {
	    String line;	                    
		while ((line = br.readLine()) != null) {	              
		    String[] values = line.split(",");
		    if (values.length == 4) {
		        String task = values[0].trim();
                String deadline = values[1].trim();
                int points = Integer.parseInt(values[2].trim());
                int importance = Integer.parseInt(values[3].trim());
		        todoList.add(new ListItem(task, deadline, points, importance));
		    }
		}
	} catch (IOException e) {
		e.printStackTrace();
	}

//Declarin!!! Stuff!!!
    BorderPane pane = new BorderPane();
    TextField todoText = new TextField();
    todoText.setPromptText("ex: Wash Dishes...");
    TextField deadlineText = new TextField();
    deadlineText.setPromptText("Enter Time Here");
    Button todoButton = new Button("Add Item");
    Button btnDelete = new Button("Complete Item");

    ComboBox <Integer> pointsBox = new ComboBox();
    pointsBox.getItems().addAll(100, 200, 300, 400, 500);

    ComboBox<Integer> importanceBox = new ComboBox();
    importanceBox.getItems().addAll(1, 2, 3, 4, 5);

//Table Creation
	TableView<ListItem> tableView = new TableView<>();
	tableView.setItems(todoList);

//Table Columns
    TableColumn<ListItem, String> colTask = new TableColumn<>("Task");
			colTask.setCellValueFactory(new PropertyValueFactory<>("task"));
			colTask.setCellFactory(TextFieldTableCell.forTableColumn());

    TableColumn<ListItem, String> colDeadline = new TableColumn<>("Deadline");
			colDeadline.setCellValueFactory(new PropertyValueFactory<>("deadline"));
			colDeadline.setCellFactory(TextFieldTableCell.forTableColumn());

        TableColumn<ListItem, Integer> colPoints = new TableColumn<>("Points");
			colPoints.setCellValueFactory(new PropertyValueFactory<>("points"));
			colPoints.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

        TableColumn<ListItem, Integer> colImportance = new TableColumn<>("Importance");
			colImportance.setCellValueFactory(new PropertyValueFactory<>("importance"));
			colImportance.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
    
    colTask.setPrefWidth(300);
    colDeadline.setPrefWidth(100);
    colPoints.setPrefWidth(100);
    colImportance.setPrefWidth(100);
    tableView.getColumns().addAll(colTask, colDeadline, colPoints, colImportance);

//Add Item Button!
    todoButton.setOnAction(e -> {
                todoList.add(new ListItem(todoText.getText(), deadlineText.getText(), pointsBox.getValue(), importanceBox.getValue()));
                todoText.clear();
                deadlineText.clear();
                saveJobsToFile(todoList);

    });

//Complete Task Button!
    btnDelete.setOnAction(e -> {
        ListItem selectedItem = tableView.getSelectionModel().getSelectedItem();
        if (selectedItem != null)
        {
            todoList.remove(selectedItem);

            saveJobsToFile(todoList);
        }
    });

//settin everything up to a cool box to actually be visible
    HBox hbox = new HBox();
    hbox.getChildren().addAll(todoText, deadlineText, pointsBox, importanceBox, todoButton, btnDelete);
    hbox.setSpacing(5);
    pane.setCenter(tableView);
    pane.setBottom(hbox);
    Scene scene = new Scene(pane, 700, 500);
    scene.getStylesheets().add(getClass().getResource("stylesheet.css").toExternalForm());


    primaryStage.setTitle("To Do List");
    primaryStage.setScene(scene);
    primaryStage.show();

}

public static void main(String[] args) {
    launch(args);
}
}