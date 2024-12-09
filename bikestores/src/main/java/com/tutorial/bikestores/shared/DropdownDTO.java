package com.tutorial.bikestores.shared;

public class DropdownDTO {
    public final String value;
    public final String text;

    public DropdownDTO(Object value, Object text) {
        this.value = value.toString();
        this.text = text.toString();
    }

    public String getValue() {
        return value;
    }

    public String getText() {
        return text;
    }
}
