package homeworks2.lesson1;

import homeworks2.lesson1.barriers.Track;
import homeworks2.lesson1.barriers.Wall;
import homeworks2.lesson1.competitors.Cat;
import homeworks2.lesson1.competitors.Man;

public class Main {
    public static void main(String[] args) {
        Competition competition = new Competition();
        competition.initCompetitors(new Cat("Васька"), new Man("Дональд"), new Cat("Мурка"));
        competition.initTrack(new Track(), new Wall(), new Track(), new Wall());
        competition.start();
    }
}
