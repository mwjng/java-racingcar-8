package racingcar.domain;

import java.util.Objects;

public class Position {
    private static final String POSITION_RANGE_ERROR_MESSAGE = "위치는 음수가 될 수 없습니다.";

    private static final int START_POSITION = 0;
    private static final int FORWARD_DISTANCE = 1;

    private final int position;

    private Position(int position) {
        validateRange(position);
        this.position = position;
    }

    public static Position of(int position) {
        return new Position(position);
    }

    public static Position start() {
        return of(START_POSITION);
    }

    public Position forward() {
        return of(position + FORWARD_DISTANCE);
    }

    public int getPosition() {
        return position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Position other = (Position) o;
        return position == other.position;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(position);
    }

    private void validateRange(int position) {
        if (position < 0) {
            throw new IllegalArgumentException(POSITION_RANGE_ERROR_MESSAGE);
        }
    }
}
