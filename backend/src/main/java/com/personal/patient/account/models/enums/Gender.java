package com.personal.patient.account.models.enums;

public enum Gender {
    MALE("Мужской"),
    FEMALE("Женский"),
    OTHER("Другой");
    private String value;

    Gender(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Gender fromValue(String value) {
        for (Gender gender : Gender.values()) {
            if (gender.value.equalsIgnoreCase(value)) {
                return gender;
            }
        }
        throw new IllegalArgumentException("Некорректное значение для Gender: " + value);
    }
}
