package racingcar;

import racingcar.domain.Names;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class RacingCarGame {
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    public void run() {
        outputView.requestInputCarNames();
        String inputCarNames = inputView.read();
        Names names = Names.from(inputCarNames);
    }
}
