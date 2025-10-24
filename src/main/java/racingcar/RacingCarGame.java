package racingcar;

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
        outputView.requestInputCarNames();
        String inputCarNames = inputView.read();
        Names names = Names.from(inputCarNames);

        outputView.requestInputTryCount();
        String inputTryCount = inputView.read();
        TryCount tryCount = TryCount.from(inputTryCount);

        Cars cars = Cars.from(names);

        outputView.showResultMessage();
        while (tryCount.tryNext()) {
            cars.moveAll(Randoms::pickNumberInRange);

            List<CarDto> carDtos = cars.getCars().stream()
                    .map(CarDto::from)
                    .toList();

            outputView.showAllCarsResult(carDtos);
        }

        List<Car> winners = cars.findWinners();
        List<String> winnerNames = winners.stream()
                .map(Car::getName)
                .toList();

        outputView.showWinners(winnerNames);
    }
}
