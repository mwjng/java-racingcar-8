package racingcar.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class TryCountTest {

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 10})
    void 시도횟수가_1_이상이면_정상적으로_생성된다(int inputTryCount) {
        // when
        TryCount tryCount = TryCount.of(inputTryCount);

        // then
        assertThat(tryCount.getTryCount()).isEqualTo(inputTryCount);
    }

    @ParameterizedTest
    @ValueSource(ints = {-10, -1, 0})
    void 시도횟수가_1_미만이면_예외가_발생한다(int inputTryCount) {
        // when & then
        assertThatThrownBy(() -> TryCount.of(inputTryCount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("시도 횟수는 1회 이상이어야 합니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"1", "2", "10"})
    void 시도횟수가_숫자면_정상적으로_생성된다(String inputTryCount) {
        // when
        TryCount tryCount = TryCount.from(inputTryCount);

        // then
        assertThat(tryCount.getTryCount()).isEqualTo(Integer.parseInt(inputTryCount));
    }

    @ParameterizedTest
    @ValueSource(strings = {"a", "a1", "abc"})
    void 시도횟수에_숫자가_아닌_값을_입력하면_예외가_발생한다(String inputTryCount) {
        // when & then
        assertThatThrownBy(() -> TryCount.from(inputTryCount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("시도 횟수는 숫자를 입력해야 합니다.");
    }
}