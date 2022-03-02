package br.edu.ifsp.domain.usecases.transaction;

import br.edu.ifsp.domain.entities.book.Book;
import br.edu.ifsp.domain.entities.book.BookStatus;
import br.edu.ifsp.domain.entities.transaction.Transaction;
import br.edu.ifsp.domain.entities.user.User;
import br.edu.ifsp.domain.usecases.book.FindBookUseCase;
import br.edu.ifsp.domain.usecases.book.UpdateBookUseCase;
import br.edu.ifsp.domain.usecases.user.FindUserUseCase;
import br.edu.ifsp.domain.usecases.user.UpdateUserUseCase;
import br.edu.ifsp.domain.usecases.utils.EntityNotFoundException;

public class CheckOutTransactionUseCase {
    private TransactionDAO transactionDAO;
    private FindUserUseCase findUserUseCase;
    private FindBookUseCase findBookUseCase;
    private UpdateUserUseCase updateUserUseCase;
    private UpdateBookUseCase updateBookUseCase;

    public CheckOutTransactionUseCase(TransactionDAO transactionDAO, FindUserUseCase findUserUseCase, FindBookUseCase findBookUseCase, UpdateUserUseCase updateUserUseCase, UpdateBookUseCase updateBookUseCase) {
        this.transactionDAO = transactionDAO;
        this.findUserUseCase = findUserUseCase;
        this.findBookUseCase = findBookUseCase;
        this.updateUserUseCase = updateUserUseCase;
        this.updateBookUseCase = updateBookUseCase;
    }

    public Transaction checkOutABook(String userId, Integer bookId){
        if (userId == null || bookId == null) {
            throw new IllegalArgumentException("User ID and/or Book ID are/is null.");
        }
        Book book = findBookUseCase.findOne(bookId)
                .orElseThrow(() -> new EntityNotFoundException("Cannot find a book with ID " + bookId));
        User user = findUserUseCase.findOne(userId)
                .orElseThrow(() -> new EntityNotFoundException("Cannot find a user with ID " + userId));
        if (book.getStatus() == BookStatus.CHECKED_OUT){
            throw new TransactionNotAllowedException("The book with ID " + bookId + " is unavailable.");
        }
        if (!user.isAbleToCheckOut()){
            throw new TransactionNotAllowedException("The user with ID " + userId + " exceeded the check-out limit.");
        }

        Transaction checkOutTransaction = new Transaction(book, user, user.getCheckOutTimeLimitInDays());
        Integer transactionId = transactionDAO.create(checkOutTransaction);

        book.setStatus(BookStatus.CHECKED_OUT);
        updateBookUseCase.update(book);

        user.increaseNumberOfBooksCheckedOut();
        updateUserUseCase.update(user);

        return transactionDAO.findOne(transactionId).get();
    }
}
