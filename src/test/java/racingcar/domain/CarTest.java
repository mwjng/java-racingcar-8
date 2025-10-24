package racingcar.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class CarTest {

    @Test
    void 자동차를_생성한다() {
        // given
        String inputName = "우테코";
        Name name = Name.of(inputName);

        // when
        Car car = Car.of(name);

        // then
        assertThat(car.getName()).isEqualTo(inputName);
        assertThat(car.getPosition()).isZero();
    }

    @ParameterizedTest
    @CsvSource({"1,0", "2,0", "3,0"})
    void 숫자가_4미만이면_전진하지_않는다(int number, int expectedPosition) {
        // given
        Car car = Car.of(Name.of("우테코"));
        NumberGenerator numberGenerator = (start, end) -> number;

        // when
        car.move(numberGenerator);

        // then
        assertThat(car.getPosition()).isEqualTo(expectedPosition);
    }

    @ParameterizedTest
    @CsvSource({"4,1", "5,1", "9,1"})
    void 숫자가_4이상이면_전진한다(int number, int expectedPosition) {
        // given
        Car car = Car.of(Name.of("우테코"));
        NumberGenerator numberGenerator = (start, end) -> number;

        // when
        car.move(numberGenerator);

        // then
        assertThat(car.getPosition()).isEqualTo(expectedPosition);
    }
}