package racingcar.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class RaceRoundTest {

    @Test
    void 라운드를_생성한다() {
        // given
        TryCount tryCount = TryCount.of(3);

        // when
        RaceRound raceRound = RaceRound.of(tryCount);

        // then
        assertThat(raceRound).isNotNull();
    }

    @Test
    void 시도가능횟수를_초과하지_않으면_true를_반환한다() {
        // given
        TryCount tryCount = TryCount.of(3);
        RaceRound raceRound = RaceRound.of(tryCount);

        // when & then
        assertThat(raceRound.tryNext()).isTrue();
        assertThat(raceRound.tryNext()).isTrue();
        assertThat(raceRound.tryNext()).isTrue();
    }

    @Test
    void 시도가능횟수를_초과하면_false를_반환한다() {
        // given
        TryCount tryCount = TryCount.of(3);
        RaceRound raceRound = RaceRound.of(tryCount);
        raceRound.tryNext();
        raceRound.tryNext();
        raceRound.tryNext();

        // when
        boolean result = raceRound.tryNext();

        // then
        assertThat(result).isFalse();
    }
}