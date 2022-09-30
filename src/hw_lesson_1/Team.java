package hw_lesson_1;

public class Team {
    private String team_name;
    private Sportsmen[] sportsmens;

    public Team(String team_name) {
        this.team_name = team_name;
    }

    public Team (String name, Sportsmen ... sportsmensGiven) {
        this.team_name = name;
        this.sportsmens = sportsmensGiven;
    }

    public void TeamInfo () {
        System.out.println("Состав команды "  + this.team_name + ":");
        for (Sportsmen sportsmen : sportsmens) {
            System.out.println(sportsmen.getName());
        }
    }

    public void Results() {
        for (Sportsmen sportsmen : sportsmens) {
            if (sportsmen.getIsOnDistance()) {
                System.out.println("Итог: Спортсмен " + sportsmen.getName() + " прошел дистанцию");
            } else {
                    System.out.println("Итог: Спортсмен " + sportsmen.getName() + " не прошел дистанцию");
                }
            }
        }

    public void doIt(Obstacle obstacle) {
        for (Sportsmen sportsmen : sportsmens) {
            obstacle.doIt(sportsmen);
        }
    }
}


