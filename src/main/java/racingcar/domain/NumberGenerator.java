package racingcar.domain;

@FunctionalInterface
public interface NumberGenerator {

    int generate(int startInclusive, int endInclusive);
}
