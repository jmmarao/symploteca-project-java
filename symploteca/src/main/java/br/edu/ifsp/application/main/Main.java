package br.edu.ifsp.application.main;

import br.edu.ifsp.application.repository.sqlite.DatabaseBuilder;
import br.edu.ifsp.application.repository.sqlite.SqliteBookDAO;
import br.edu.ifsp.application.repository.sqlite.SqliteTransactionDAO;
import br.edu.ifsp.application.repository.sqlite.SqliteUserDAO;
import br.edu.ifsp.application.view.WindowLoader;
import br.edu.ifsp.domain.usecases.book.*;
import br.edu.ifsp.domain.usecases.user.*;
import br.edu.ifsp.domain.usecases.transaction.CheckOutTransactionUseCase;
import br.edu.ifsp.domain.usecases.transaction.FindTransactionUseCase;
import br.edu.ifsp.domain.usecases.transaction.ReturnTransactionUseCase;
import br.edu.ifsp.domain.usecases.transaction.TransactionDAO;

public class Main {

    public static CreateBookUseCase createBookUseCase;
    public static FindBookUseCase findBookUseCase;
    public static UpdateBookUseCase updateBookUseCase;
    public static RemoveBookUseCase removeBookUseCase;

    public static CreateUserUseCase createUserUseCase;
    public static FindUserUseCase findUserUseCase;
    public static UpdateUserUseCase updateUserUseCase;
    public static RemoveUserUseCase removeUserUseCase;

    public static CheckOutTransactionUseCase checkOutTransactionUseCase;
    public static ReturnTransactionUseCase returnTransactionUseCase;
    private static FindTransactionUseCase findTransactionUseCase;

    public static void main(String[] args) {
        configureInjection();
        setupDatabase();
        WindowLoader.main(args);
    }

    private static void setupDatabase() {
        DatabaseBuilder dbBuilder = new DatabaseBuilder();
        dbBuilder.buildDatabaseIfMissing();
    }

    private static void configureInjection() {
        BookDAO bookDAO = new SqliteBookDAO();
        createBookUseCase = new CreateBookUseCase(bookDAO);
        updateBookUseCase = new UpdateBookUseCase(bookDAO);
        findBookUseCase = new FindBookUseCase(bookDAO);
        removeBookUseCase = new RemoveBookUseCase(bookDAO);

        UserDAO userDAO = new SqliteUserDAO();
        createUserUseCase = new CreateUserUseCase(userDAO);
        updateUserUseCase = new UpdateUserUseCase(userDAO);
        findUserUseCase = new FindUserUseCase(userDAO);
        removeUserUseCase = new RemoveUserUseCase(userDAO);

        TransactionDAO transactionDAO = new SqliteTransactionDAO();
        checkOutTransactionUseCase = new CheckOutTransactionUseCase(
                transactionDAO, findUserUseCase, findBookUseCase, updateUserUseCase, updateBookUseCase);
        returnTransactionUseCase = new ReturnTransactionUseCase(
                transactionDAO, findUserUseCase, findBookUseCase, updateUserUseCase, updateBookUseCase);
        findTransactionUseCase = new FindTransactionUseCase(transactionDAO);
    }
}
