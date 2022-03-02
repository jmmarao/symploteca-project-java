package br.edu.ifsp.domain.entities.book;

import java.util.Arrays;

public enum BookStatus {
    AVAILABLE ("Disponível"),
    CHECKED_OUT ("Emprestado");

    private String label;

    BookStatus(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }

    public static BookStatus toEnum(String value) {
        return Arrays.stream(BookStatus.values())
                .filter(c -> value.equals(c.toString()))
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }
}
