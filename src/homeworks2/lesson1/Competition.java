package homeworks2.lesson1;

import homeworks2.lesson1.barriers.Barrier;
import homeworks2.lesson1.barriers.Track;
import homeworks2.lesson1.barriers.Wall;
import homeworks2.lesson1.competitors.Cat;
import homeworks2.lesson1.competitors.Man;
import homeworks2.lesson1.competitors.Robot;
import homeworks2.lesson1.interfaces.Competitor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

class Competition {

    private Competitors competitors;
    private TrackC track;

    void initCompetitors(Competitor... competitors) {
        this.competitors = new Competitors(competitors);
    }

    void initTrack(Barrier... barriers) {
        this.track = new TrackC(barriers);
    }

    class Competitors {
        private final ArrayList<Competitor> list;

        Competitors() {
            list = new ArrayList<>();
        }

        Competitors(Competitor... competitors) {
            this();
            this.list.addAll(Arrays.asList(competitors));
        }

        void addCompetitor(Competitor competitor) {
            list.add(competitor);
        }
    }

    class TrackC {
        private final ArrayList<Barrier> track;

        TrackC() {
            track = new ArrayList<>();
        }

        TrackC(Barrier... barriers) {
            this();
            this.track.addAll(Arrays.asList(barriers));
        }

        void addBarrier(Barrier barrier) {
            track.add(barrier);
        }
    }

    void start() {
        for(Competitor competitor : competitors.list) {
            for(Barrier barrier : track.track) {
                if(barrier instanceof Wall) {
                    if (!competitor.jump(((Wall) barrier).getBarrier())) {
                        setTimeCompetitor(competitor, -1.0);
                        break;
                    }
                } else {
                    double time = competitor.run(((Track) barrier).getBarrier());
                    time += getTimeCompetitor(competitor);
                    setTimeCompetitor(competitor, time);
                }
            }
        }

        sortCompetitors(competitors.list);

        finish(competitors.list);
    }

    private void sortCompetitors(ArrayList<Competitor> list) {
        list.sort(new Comparator<Competitor>() {
            @Override
            public int compare(Competitor o1, Competitor o2) {
                return (int) (getTimeCompetitor(o1) - getTimeCompetitor(o2));
            }
        });
    }

    private void finish(ArrayList<Competitor> list) {
        for(int i = 0; i < list.size(); i++) {
            Competitor c = list.get(i);
            double time = getTimeCompetitor(c);
            StringBuilder res = new StringBuilder((i + 1) + ") " + c.getClass().getSimpleName() +
                    " " + getNameCompetitor(c) +
                    " " + (time < 0 ? " выбыл " : " прошел трэк за " + time + " секунд."));
            if(i == 0 && time > 0)
                res.append(" 1 место!");
            if(i == 1 && time > 0)
                res.append(" 2 место!");
            if(i == 2 && time > 0)
                res.append(" 3 место!");
            System.out.println(res.toString());
        }
    }

    private String getNameCompetitor(Competitor c) {
        return switch(c.getClass().getSimpleName()) {
            case "Man" -> ((Man) c).getName();
            case "Cat" -> ((Cat) c).getName();
            default -> ((Robot) c).getName();
        };
    }

    private void setTimeCompetitor(Competitor c, double time) {
        switch (c.getClass().getSimpleName()) {
            case "Man" -> ((Man) c).setTime(time);
            case "Cat" -> ((Cat) c).setTime(time);
            default -> ((Robot) c).setTime(time);
        }
    }

    private double getTimeCompetitor(Competitor c) {
        return switch(c.getClass().getSimpleName()) {
            case "Man" -> ((Man) c).getTime();
            case "Cat" -> ((Cat) c).getTime();
            default -> ((Robot) c).getTime();
        };
    }
}