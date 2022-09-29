package hw_lesson_1;

public class Cross extends Obstacle{
    private int crossNeedEnergy;

    public Cross(int crossNeedEnergy) {
        this.crossNeedEnergy = crossNeedEnergy;
    }

    @Override
    public void doIt(Sportsmen sportsmen) {
        sportsmen.run(crossNeedEnergy);
    }
}
