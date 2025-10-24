package racingcar.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class PositionTest {

    @ParameterizedTest
    @ValueSource(ints = {-1, -10})
    void 위치값이_음수면_예외가_발생한다(int inputPosition) {
        // when & then
        assertThatThrownBy(() -> Position.of(inputPosition))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("위치는 음수가 될 수 없습니다.");
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 10})
    void 위치값이_음수가_아니면_정상적으로_생성된다(int inputPosition) {
        // when
        Position position = Position.of(inputPosition);

        // then
        assertThat(position.getPosition()).isEqualTo(inputPosition);
    }

    @Test
    void start_호출시_위치값은_0이다() {
        // when
        Position position = Position.start();

        // then
        assertThat(position.getPosition()).isZero();
    }

    @ParameterizedTest
    @CsvSource({"1,2", "5,6", "10,11"})
    void 위치를_앞으로_이동한다(int startPosition, int expectedPosition) {
        // given
        Position position = Position.of(startPosition);

        // when
        Position forwardedPosition = position.forward();

        // then
        assertThat(forwardedPosition.getPosition()).isEqualTo(expectedPosition);
    }
}