package br.edu.ifsp.domain.entities.book;

import java.util.Arrays;

public enum BookGender {
    ACTION ("Ação"),
    DRAMA ("Drama"),
    HISTORY ("História"),
    HORROR ("Horror"),
    SCIENCE ("Ciência"),
    TECHNICAL ("Técnico");

    private String label;

    BookGender(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }

    public static BookGender toEnum(String value) {
        return Arrays.stream(BookGender.values())
                .filter(c -> value.equals(c.toString()))
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }
}
