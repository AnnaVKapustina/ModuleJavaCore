package hw_lesson_1;

public class TeamMember implements Sportsmen {
    private String name;
    private int energy;
    private boolean isOnDistanse;

    public TeamMember(String name, int energy) {
        this.name = name;
        this.energy = energy;
        this.isOnDistanse = false;
    }
    public String getName() {
        return this.name;
    }
    public int getEnergy() {
        return this.energy;
    }

    @Override
    public boolean getIsOnDistance() {
        return this.isOnDistanse;
    }

    public void swim (int needEnergyForSwim) {
        energy -= needEnergyForSwim;
        if (energy >= 0) {
            System.out.println(name + " Проплыл бассейн");
            isOnDistanse = true;

        } else {
            System.out.println(name + " Не смог переплыть бассейн");
            isOnDistanse = false;
        }
    }
    public void run (int needEnergyForRun) {
        energy -= needEnergyForRun ;
        if (energy >= 0) {
            System.out.println(name + " Пробежал кросс");
            isOnDistanse = true;

        } else {
            System.out.println(name + " Не смог пробежать кросс");
            isOnDistanse = false;
        }
    }

}
