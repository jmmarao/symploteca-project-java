package br.edu.ifsp.domain.entities.user;

public class IllegalNumberOfCheckOutItensException extends RuntimeException{
    public IllegalNumberOfCheckOutItensException(String message) {
        super(message);
    }
}
