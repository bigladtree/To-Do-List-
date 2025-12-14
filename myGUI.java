// Winter Break Practice #1
// 12-12-2025
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;



public class myGUI extends Application {

//File Reader to save data between uses
public void saveJobsToFile(ObservableList<ListItem> listItems) {
			try(BufferedWriter brw = new BufferedWriter(new FileWriter("todolist.txt"))) {
				for (ListItem t : listItems) {
					brw.write(String.format("%s",
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
    ObservableList<ListItem> todoList = FXCollections.observableArrayList();

//File Reader to get values for list 
    try (BufferedReader br = new BufferedReader(new FileReader("todolist.txt"))) {
	    String line;	                    
		while ((line = br.readLine()) != null) {	              
		    String[] values = line.split(",");
		    if (values.length == 1) {
		        String task = values[0].trim();
		        todoList.add(new ListItem(task));
		    }
		}
	} catch (IOException e) {
		e.printStackTrace();
	}

//Declarin!!! Stuff!!!
    BorderPane pane = new BorderPane();
    TextField todoText = new TextField();
    Button todoButton = new Button("Add Item");
    Button btnDelete = new Button("Complete Item");

//Table Creation
	TableView<ListItem> tableView = new TableView<>();
	tableView.setItems(todoList);

//Table Columns
    TableColumn<ListItem, String> colTask = new TableColumn<>("Task");
			colTask.setCellValueFactory(new PropertyValueFactory<>("task"));
			colTask.setCellFactory(TextFieldTableCell.forTableColumn());
            
    tableView.getColumns().addAll(colTask);

//Add Item Button!
    todoButton.setOnAction(e -> {
                todoList.add(new ListItem(todoText.getText()));
                todoText.clear();

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
    hbox.getChildren().addAll(todoText, todoButton, btnDelete);
    pane.setCenter(tableView);
    pane.setBottom(hbox);
    Scene scene = new Scene(pane, 800, 800);

    primaryStage.setTitle("To Do List");
    primaryStage.setScene(scene);
    primaryStage.show();

}

public static void main(String[] args) {
    launch(args);
}
}