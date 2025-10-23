package racingcar.domain;

import java.util.Objects;

public class Name {
    private static final String NAME_LENGTH_ERROR_MESSAGE = "이름의 길이는 최소 1글자 이상 %d글자 이하여야 합니다.";
    private static final String SPACE_INPUT_ERROR_MESSAGE = "이름에 공백을 포함할 수 없습니다.";

    private static final int MAXIMUM_NAME_LENGTH = 5;
    private static final String SPACE = " ";

    private final String name;

    private Name(String name) {
        validateLength(name);
        validateNotContainsWhiteSpace(name);
        this.name = name;
    }

    public static Name of(String name) {
        return new Name(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Name other = (Name) o;
        return Objects.equals(name, other.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    public String getName() {
        return name;
    }

    private void validateLength(String name) {
        if (isInvalidLength(name)) {
            throw new IllegalArgumentException(NAME_LENGTH_ERROR_MESSAGE.formatted(MAXIMUM_NAME_LENGTH));
        }
    }

    private boolean isInvalidLength(String name) {
        return name.isEmpty()
                || name.length() > MAXIMUM_NAME_LENGTH;
    }

    private void validateNotContainsWhiteSpace(String name) {
        if (name.contains(SPACE)) {
            throw new IllegalArgumentException(SPACE_INPUT_ERROR_MESSAGE);
        }
    }
}
