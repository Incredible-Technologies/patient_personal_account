package com.personal.patient.account.models.enums;

public enum PassportGender {
    MALE("Мужской"),
    FEMALE("Женский");
    private final String value;

    PassportGender(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static PassportGender fromValue(String value) {
        for (PassportGender gender : PassportGender.values()) {
            if (gender.value.equalsIgnoreCase(value)) {
                return gender;
            }
        }
        throw new IllegalArgumentException("Некорректное значение для Gender: " + value);
    }
}
