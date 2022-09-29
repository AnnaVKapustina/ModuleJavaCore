package hw_lesson_1;

public class Main {
    public static void main(String[] args){

        Course c = new Course (new Pool(100), new Cross(70));

        Team team = new Team("Dream team",
                new TeamMember("Вася", 100),
                new TeamMember("Денис", 170),
                new TeamMember("Кузя", 90),
                new TeamMember("Коля", 200));

        team.TeamInfo();
        c.doIt(team);
        team.Results();
    }
}
