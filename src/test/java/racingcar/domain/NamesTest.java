package racingcar.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class NamesTest {

    @ParameterizedTest
    @CsvSource(value = {"ab,abc;2", "abc,abcd,abcde;3"}, delimiter = ';')
    void 이름의_개수가_2개_이상이고_중복이_존재하지_않으면_정상적으로_생성된다(String inputNames, int expectedCount) {
        // when
        Names names = Names.from(inputNames);

        // then
        assertThat(names.getNames()).hasSize(expectedCount);
    }

    @Test
    void 이름의_개수가_2개_미만이면_예외가_발생한다() {
        // given
        String inputNames = "abcde";

        // when & then
        assertThatThrownBy(() -> Names.from(inputNames))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("자동차 이름은 2개 이상 입력해야 합니다.");
    }

    @Test
    void 중복된_이름이_존재하면_예외가_발생한다() {
        // given
        String inputNames = "abc,abcd,abc";

        // when & then
        assertThatThrownBy(() -> Names.from(inputNames))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("중복된 이름이 존재할 수 없습니다.");
    }
}