package br.edu.ifsp.application.controller;

import br.edu.ifsp.application.view.WindowLoader;
import br.edu.ifsp.domain.entities.book.Book;
import br.edu.ifsp.domain.entities.book.BookGender;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.IOException;

import static br.edu.ifsp.application.main.Main.createBookUseCase;
import static br.edu.ifsp.application.main.Main.updateBookUseCase;

public class BookUIController {
    @FXML
    private TextField txtName;

    @FXML
    private TextField txtAuthors;

    @FXML
    private TextField txtPublisher;

    @FXML
    private TextField txtEdition;

    @FXML
    private TextField txtIsbn;

    @FXML
    private TextField txtPages;

    @FXML
    private ComboBox<BookGender> cbGender;

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnConfirm;

    private Book book;

    @FXML
    private void initialize() {
        cbGender.getItems().setAll(BookGender.values());
    }

    public void setBook(Book book, UIMode mode) {
        if (book == null)
            throw new IllegalArgumentException("Book cannot be null");
        this.book = book;
        setEntityIntoView();

        if (mode == UIMode.VIEW)
            configureViewMode();
    }

    public void saveOrUpdate(ActionEvent actionEvent) throws IOException {
        getEntityToView();
        if (book.getId() == null)
            createBookUseCase.insert(book);
        else
            updateBookUseCase.update(book);
        WindowLoader.setRoot("BookManagementUI");
    }

    public void backToPreviousScene(ActionEvent actionEvent) throws IOException {
        WindowLoader.setRoot("BookManagementUI");
    }

    private void getEntityToView() {
        if (book == null) {
            book = new Book();
        }
        book.setName(txtName.getText());
        book.setAuthors(txtAuthors.getText());
        book.setPublisher(txtPublisher.getText());
        book.setEdition(Integer.valueOf(txtEdition.getText()));
        book.setIsbn(txtIsbn.getText());
        book.setNumberOfPages(Integer.valueOf(txtPages.getText()));
        //book.setName(txtName.getText());
        book.setGender(cbGender.getValue());
    }

    private void setEntityIntoView() {
        txtName.setText(book.getName());
        txtAuthors.setText(book.getAuthors());
        txtPublisher.setText(book.getPublisher());
        txtEdition.setText(String.valueOf(book.getEdition()));
        txtIsbn.setText(book.getIsbn());
        txtPages.setText(String.valueOf(book.getNumberOfPages()));
        cbGender.setValue(book.getGender());
    }

    private void configureViewMode() {
        btnCancel.setLayoutX(btnConfirm.getLayoutX());
        btnCancel.setLayoutY(btnConfirm.getLayoutY());
        btnCancel.setText("Fechar");
        btnConfirm.setVisible(false);
        txtName.setDisable(true);
        txtAuthors.setDisable(true);
        txtPublisher.setDisable(true);
        txtEdition.setDisable(true);
        txtIsbn.setDisable(true);
        txtPages.setDisable(true);
        cbGender.setDisable(true);
    }
}
