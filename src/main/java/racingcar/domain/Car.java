package racingcar.domain;

public class Car {
    private static final int MINIMUM_NUMBER = 0;
    private static final int MAXIMUM_NUMBER = 9;
    private static final int MOVING_THRESHOLD = 4;

    private final Name name;
    private Position position = Position.start();

    private Car(Name name) {
        this.name = name;
    }

    public static Car of(Name name) {
        return new Car(name);
    }

    public void move(NumberGenerator generator) {
        if (canMove(generator)) {
            position = position.forward();
        }
    }

    public String getName() {
        return name.getName();
    }

    public int getPosition() {
        return position.getPosition();
    }

    private boolean canMove(NumberGenerator generator) {
        int generatedNumber = generator.generate(MINIMUM_NUMBER, MAXIMUM_NUMBER);
        return generatedNumber >= MOVING_THRESHOLD;
    }
}
