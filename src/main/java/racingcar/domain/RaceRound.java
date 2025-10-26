package racingcar.domain;

public class RaceRound {
    private final TryCount totalTryCount;
    private int currentTryCount = 0;

    private RaceRound(TryCount totalTryCount) {
        this.totalTryCount = totalTryCount;
    }

    public static RaceRound of(TryCount totalTryCount) {
        return new RaceRound(totalTryCount);
    }

    public boolean tryNext() {
        if (hasNext()) {
            currentTryCount++;
            return true;
        }
        return false;
    }

    private boolean hasNext() {
        return currentTryCount < totalTryCount.getTryCount();
    }
}
