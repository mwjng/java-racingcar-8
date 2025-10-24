package racingcar.controller;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;
import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.domain.Names;
import racingcar.domain.TryCount;
import racingcar.dto.CarDto;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class RacingCarGame {
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    public void run() {
        Names names = readInputCarNames();
        Cars cars = Cars.from(names);
        TryCount tryCount = readInputTryCount();

        race(tryCount, cars);

        showWinners(cars);
    }

    private Names readInputCarNames() {
        outputView.requestInputCarNames();
        String inputCarNames = inputView.read();

        return Names.from(inputCarNames);
    }

    private TryCount readInputTryCount() {
        outputView.requestInputTryCount();
        String inputTryCount = inputView.read();

        return TryCount.from(inputTryCount);
    }

    private void race(TryCount tryCount, Cars cars) {
        outputView.showResultMessage();

        while (tryCount.tryNext()) {
            cars.moveAll(Randoms::pickNumberInRange);
            showAllCarsResult(cars);
        }
    }

    private void showAllCarsResult(Cars cars) {
        List<CarDto> carDtos = cars.getCars().stream()
                .map(CarDto::from)
                .toList();

        outputView.showAllCarsResult(carDtos);
    }

    private void showWinners(Cars cars) {
        List<Car> winners = cars.findWinners();
        List<String> winnerNames = winners.stream()
                .map(Car::getName)
                .toList();

        outputView.showWinners(winnerNames);
    }
}
