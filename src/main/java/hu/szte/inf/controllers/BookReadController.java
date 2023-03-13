package hu.szte.inf.controllers;

import hu.szte.inf.models.Book;
import hu.szte.inf.repositories.BookRepository;
import hu.szte.inf.services.BookTableQueryService;
import hu.szte.inf.utils.Functional;
import hu.szte.inf.utils.fx.TableViewSupport;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class BookReadController {

    private final BookRepository repository = new BookRepository();
    private final BookTableQueryService tableService = BookTableQueryService.getInstance();

    @FXML
    private TableView<Book> tableView;
    @FXML
    private TextField selectedIdTextField;

    @FXML
    private void initialize() {
        TableViewSupport.createSchema(tableView, Book.class);
        tableView.itemsProperty().bind(tableService.modelProperty());
        tableView.getSelectionModel().selectedItemProperty().addListener((observableValue, t, t1) -> {
            if (t1 != null) {
                selectedIdTextField.setText(t1.getId().toString());
            }
            else {
                selectedIdTextField.clear();
            }
        });
    }

    @FXML
    private void onDelete() {
        var selection = tableView.getSelectionModel().getSelectedItem();
        if (selection == null) {
            var alert = new Alert(Alert.AlertType.INFORMATION, "No model was selected!", ButtonType.OK);
            alert.showAndWait();
            return;
        }

        var alert = new Alert(
                Alert.AlertType.CONFIRMATION,
                "Are you sure you want to delete model with ID: " +
                        tableView.getSelectionModel().getSelectedItem().getId(),
                ButtonType.YES,
                ButtonType.NO);
        var result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.YES) {
            repository.deleteById(selection.getId());
            tableService.setModel(Functional.iterableToObservableList(repository.findAll()));
        }
    }

    @FXML
    private void onClear() {
        tableView.getSelectionModel().clearSelection();
    }
}
