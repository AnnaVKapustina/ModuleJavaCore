package hw_lesson_1;

public class Pool extends Obstacle {
    private int poolNeedEnergy;

    public Pool(int poolNeedEnergy) {
        this.poolNeedEnergy = poolNeedEnergy;
    }

    @Override
    public void doIt(Sportsmen sportsmen) {
        sportsmen.swim(poolNeedEnergy);
    }
}
