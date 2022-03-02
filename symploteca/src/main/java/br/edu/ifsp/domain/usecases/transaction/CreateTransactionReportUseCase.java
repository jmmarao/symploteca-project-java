package br.edu.ifsp.domain.usecases.transaction;

import br.edu.ifsp.domain.entities.book.Book;
import br.edu.ifsp.domain.entities.transaction.Transaction;
import br.edu.ifsp.domain.entities.user.User;
import br.edu.ifsp.domain.usecases.book.FindBookUseCase;
import br.edu.ifsp.domain.usecases.user.FindUserUseCase;
import br.edu.ifsp.domain.usecases.utils.EntityNotFoundException;

import java.time.LocalDate;
import java.util.Optional;

public class CreateTransactionReportUseCase {
    private TransactionDAO transactionDAO;
    private FindUserUseCase findUserUseCase;
    private FindBookUseCase findBookUseCase;

    public CreateTransactionReportUseCase(TransactionDAO transactionDAO, FindUserUseCase findUserUseCase, FindBookUseCase findBookUseCase) {
        this.transactionDAO = transactionDAO;
        this.findUserUseCase = findUserUseCase;
        this.findBookUseCase = findBookUseCase;
    }

    public Optional<Transaction> transactionReport(LocalDate initialDate, LocalDate finalDate, String userId, Integer bookId) {
        if (initialDate == null || finalDate == null || userId == null || bookId == null) {
            throw new IllegalArgumentException("Dates, user ID and/or Book ID are/is null.");
        }
        Book book = findBookUseCase.findOne(bookId)
                .orElseThrow(() -> new EntityNotFoundException("Cannot find a book with ID " + bookId));
        User user = findUserUseCase.findOne(userId)
                .orElseThrow(() -> new EntityNotFoundException("Cannot find a user with ID " + userId));
        return transactionDAO.findByDataRange(initialDate, finalDate, userId, bookId);
    }
}
