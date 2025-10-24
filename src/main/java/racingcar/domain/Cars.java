package racingcar.domain;

import java.util.Collections;
import java.util.List;

public class Cars {
    private static final String CARS_COUNT_ERROR_MESSAGE = "자동차는 %d대 이상 존재해야 합니다.";

    private static final int MINIMUM_CARS_COUNT = 2;

    private final List<Car> cars;

    private Cars(List<Car> cars) {
        validateCount(cars);
        this.cars = cars;
    }

    public static Cars of(List<Car> cars) {
        return new Cars(cars);
    }

    public static Cars from(Names names) {
        List<Car> carList = names.getNames().stream()
                .map(Car::of)
                .toList();

        return of(carList);
    }

    public void moveAll(NumberGenerator generator) {
        cars.forEach(car -> car.move(generator));
    }

    public List<Car> findWinners() {
        int maxPosition = findMaxPosition();

        return cars.stream()
                .filter(car -> car.getPosition() == maxPosition)
                .toList();
    }

    public List<Car> getCars() {
        return List.copyOf(cars);
    }

    private void validateCount(List<Car> cars) {
        if (cars.size() < MINIMUM_CARS_COUNT) {
            throw new IllegalArgumentException(CARS_COUNT_ERROR_MESSAGE.formatted(MINIMUM_CARS_COUNT));
        }
    }

    private int findMaxPosition() {
        List<Integer> carPositions = cars.stream()
                .map(Car::getPosition)
                .toList();

        return Collections.max(carPositions);
    }
}
