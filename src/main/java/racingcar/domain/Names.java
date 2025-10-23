package racingcar.domain;

import java.util.Arrays;
import java.util.List;

public class Names {
    private static final String NAMES_COUNT_ERROR_MESSAGE = "자동차 이름은 %d개 이상 입력해야 합니다.";
    private static final String DUPLICATE_ERROR_MESSAGE = "중복된 이름이 존재할 수 없습니다.";

    private static final String DELIMITER = ",";
    private static final int MINIMUM_NAMES_COUNT = 2;

    private final List<Name> names;

    private Names(List<Name> names) {
        validateCount(names);
        validateNoDuplicate(names);
        this.names = names;
    }

    public static Names of(List<Name> names) {
        return new Names(names);
    }

    public static Names from(String inputCarNames) {
        List<Name> carNames = Arrays.stream(inputCarNames.split(DELIMITER, -1))
                .map(Name::of)
                .toList();

        return of(carNames);
    }

    public List<Name> getNames() {
        return List.copyOf(names);
    }

    private void validateCount(List<Name> names) {
        if (names.size() < MINIMUM_NAMES_COUNT) {
            throw new IllegalArgumentException(NAMES_COUNT_ERROR_MESSAGE.formatted(MINIMUM_NAMES_COUNT));
        }
    }

    private void validateNoDuplicate(List<Name> names) {
        if (hasDuplicate(names)) {
            throw new IllegalArgumentException(DUPLICATE_ERROR_MESSAGE);
        }
    }

    private boolean hasDuplicate(List<Name> names) {
        return names.stream()
                .distinct()
                .count() != names.size();
    }
}
