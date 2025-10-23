package racingcar.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class NameTest {

    @ParameterizedTest
    @ValueSource(strings = {"a", "ab", "abc", "abcd", "abcde"})
    void 이름의_길이가_1글자_이상_5글자_이하면_정상적으로_생성된다(String inputName) {
        // when
        Name name = Name.of(inputName);

        // then
        assertThat(name.getName()).isEqualTo(inputName);
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "abcdef", "abcdefghijk"})
    void 이름이_비어있거나_5글자를_초과하면_예외가_발생한다(String inputName) {
        // when & then
        assertThatThrownBy(() -> Name.of(inputName))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이름의 길이는 최소 1글자 이상 5글자 이하여야 합니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {" abc", "abcd ", "ab cd", "a  bc"})
    void 이름에_공백을_포함하면_예외가_발생한다(String inputName) {
        // when & then
        assertThatThrownBy(() -> Name.of(inputName))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이름에 공백을 포함할 수 없습니다.");
    }
}