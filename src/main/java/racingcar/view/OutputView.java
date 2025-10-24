package racingcar.view;

import java.util.List;
import racingcar.dto.CarDto;

public class OutputView {
    private static final String INPUT_CAR_NAMES_MESSAGE = "경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)";
    private static final String INPUT_TRY_COUNT_MESSAGE = "시도할 횟수는 몇 회인가요?";
    private static final String RESULT_MESSAGE = "실행 결과";
    
    private static final String NAME_SEPARATOR = " : ";
    private static final String POSITION_MARKER = "-";

    public void requestInputCarNames() {
        System.out.println(INPUT_CAR_NAMES_MESSAGE);
    }

    public void requestInputTryCount() {
        System.out.println(INPUT_TRY_COUNT_MESSAGE);
    }

    public void showResultMessage() {
        System.out.println("\n" + RESULT_MESSAGE);
    }

    public void showAllCarsResult(List<CarDto> carDtos) {
        carDtos.forEach(this::showCarResult);
        System.out.println();
    }

    private void showCarResult(CarDto carDto) {
        String name = carDto.getName();
        int position = carDto.getPosition();

        showCarName(name);
        showCarPosition(position);
        System.out.println();
    }

    private void showCarName(String carName) {
        System.out.print(carName + NAME_SEPARATOR);
    }

    private void showCarPosition(int position) {
        System.out.print(POSITION_MARKER.repeat(position));
    }
}
