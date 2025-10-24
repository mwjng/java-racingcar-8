package racingcar;

import racingcar.domain.Names;
import racingcar.domain.TryCount;
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
    }
}
