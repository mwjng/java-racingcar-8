package racingcar.domain;

public class TryCount {
    private static final String COUNT_RANGE_ERROR_MESSAGE = "시도 횟수는 1회 이상이어야 합니다.";
    private static final String COUNT_FORMAT_ERROR_MESSAGE = "시도 횟수는 숫자를 입력해야 합니다.";

    private static final int MINIMUM_TRY_COUNT = 1;

    private final int tryCount;

    private TryCount(int tryCount) {
        validateRange(tryCount);
        this.tryCount = tryCount;
    }

    public static TryCount of(int tryCount) {
        return new TryCount(tryCount);
    }

    public static TryCount from(String inputTryCount) {
        try {
            int count = Integer.parseInt(inputTryCount);
            return of(count);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(COUNT_FORMAT_ERROR_MESSAGE, e);
        }
    }

    public int getTryCount() {
        return tryCount;
    }

    private void validateRange(int tryCount) {
        if (tryCount < MINIMUM_TRY_COUNT) {
            throw new IllegalArgumentException(COUNT_RANGE_ERROR_MESSAGE);
        }
    }
}
