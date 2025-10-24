package racingcar.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.groups.Tuple.tuple;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class CarsTest {

    @Test
    void 자동차들을_생성한다() {
        // given
        Names names = Names.from("우테코1,우테코2");

        // when
        Cars cars = Cars.from(names);

        // then
        assertThat(cars.getCars()).hasSize(2)
                .extracting("name", "position")
                .containsExactly(
                        tuple("우테코1", 0),
                        tuple("우테코2", 0)
                );
    }

    @Test
    void 자동차가_2대미만이면_예외가_발생한다() {
        // given
        Name name = Name.of("우테코1");
        Car car = Car.of(name);
        List<Car> cars = List.of(car);

        // when & then
        assertThatThrownBy(() -> Cars.of(cars))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("자동차는 2대 이상 존재해야 합니다.");
    }

    @ParameterizedTest
    @ValueSource(ints = {4, 5, 9})
    void 자동차들을_전진한다(int number) {
        // given
        Names names = Names.from("우테코1,우테코2,우테코3");
        Cars cars = Cars.from(names);
        NumberGenerator numberGenerator = (start, end) -> number;

        // when
        cars.moveAll(numberGenerator);

        // then
        assertThat(cars.getCars()).hasSize(3)
                .extracting(Car::getName, Car::getPosition)
                .containsExactly(
                        tuple("우테코1", 1),
                        tuple("우테코2", 1),
                        tuple("우테코3", 1)
                );
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 2, 3})
    void 숫자가_4미만이면_자동차들이_전진하지_않는다(int number) {
        // given
        Names names = Names.from("우테코1,우테코2,우테코3");
        Cars cars = Cars.from(names);
        NumberGenerator numberGenerator = (start, end) -> number;

        // when
        cars.moveAll(numberGenerator);

        // then
        assertThat(cars.getCars()).hasSize(3)
                .extracting(Car::getName, Car::getPosition)
                .containsExactly(
                        tuple("우테코1", 0),
                        tuple("우테코2", 0),
                        tuple("우테코3", 0)
                );
    }
}