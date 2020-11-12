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

class Competition {

    private Competitors competitors;    // участники
    private TrackC track;               // полоса препятствий

    // внутренний класс, инкапсулирует участников
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

    // внутренний класс, инкапсулирует составляющие полосы препятствий
    // почему то Idea очень хочет сделать из них статические
    class TrackC {
        private final ArrayList<Barrier> list;
        TrackC() {
            list = new ArrayList<>();
        }
        TrackC(Barrier... barriers) {
            this();
            this.list.addAll(Arrays.asList(barriers));
        }
        void addBarrier(Barrier barrier) {
            list.add(barrier);
        }
    }

    // Инициализируем участников
    void initCompetitors(Competitor... competitors) {
        this.competitors = new Competitors(competitors);
    }
    // Добавляем участника, если надо
    void addCompetitor(Competitor competitor) { competitors.addCompetitor(competitor); }
    // Выводим в консоль инфу про участников
    void printInfoCompetitors() {
        System.out.print("\n----- Участники -----");
        competitors.list.forEach(Competitor::printInfo);
        System.out.println();
    }

    // Инициализируем полосу препятствий
    void initTrack(Barrier... barriers) { this.track = new TrackC(barriers); }
    // Добавляем препятствие, если надо
    void addBarrier(Barrier barrier) { track.addBarrier(barrier); }
    // Выводим в консоль инфу о полосе
    void printInfoTrack() {
        System.out.print("\n----- Полоса препятсвий -----");
        track.list.forEach(Barrier::printInfo);
        System.out.println();
    }

    // Начало забега
    void start() {
        // цикл по участникам
        for(Competitor competitor : competitors.list) {
            // цикл по барьерам
            for(Barrier barrier : track.list) {
                // если барьер это стена
                if(barrier instanceof Wall) {
                    // если участник не смог перепрыгнуть стену
                    if (!competitor.jump(((Wall) barrier).getBarrier())) {
                        // ставим его время прохождения полосы как -1
                        setTime(competitor, -1.0);
                        // дальше этот участник не соревнуется
                        break;
                    }
                // если барьер это дорожка
                } else {
                    // вычисляем время прохождения дорожки, в зависимости от скорости участника
                    double time = competitor.run(((Track) barrier).getBarrier());
                    // добавляем к времени участника
                    time += getTime(competitor);
                    // устанавливаем время участника
                    setTime(competitor, time);
                }
            }
        }
        // сортируем список участников в зависимости от времени прохождения
        sortCompetitors(competitors.list);
        // выявляем победителей и выводим в консоль
        finish(competitors.list);
    }

    /*
    В принципе, так как имена методов в этих классах совпадает
    можно было привести к любому из них, но так думаю неправильно
     */

    // устанавливает время участника
    private void setTime(Competitor c, double time) {
        switch (c.getClass().getSimpleName()) {
            case "Man" -> ((Man) c).setTime(time);
            case "Cat" -> ((Cat) c).setTime(time);
            default -> ((Robot) c).setTime(time);
        }
    }
    // берем время участника
    private double getTime(Competitor c) {
        return switch(c.getClass().getSimpleName()) {
            case "Man" -> ((Man) c).getTime();
            case "Cat" -> ((Cat) c).getTime();
            default -> ((Robot) c).getTime();
        };
    }
    // сортируем по времени прохождения
    private void sortCompetitors(ArrayList<Competitor> list) {
        list.sort((o1, o2) -> {
            int t1 = (int) getTime(o1);
            int t2 = (int) getTime(o2);
            if(t1 == -1)
                return 1;
            if(t2 == -1)
                return -1;
            return t1 - t2;
        });
    }

    private void finish(ArrayList<Competitor> list) {
        System.out.println("\n----- Итоги -----");
        for(int i = 0; i < list.size(); i++) {
            Competitor c = list.get(i);
            double time = getTime(c);
            StringBuilder res = new StringBuilder((i + 1) + ") " + c.getClass().getSimpleName() +
                    " " + getName(c) + " " + (time < 0 ?
                    " выбыл " : " прошел трэк за " + String.format("%.2f",time) + " секунд."));
            if(i == 0 && time > 0)
                res.append(" 1 место!");
            if(i == 1 && time > 0)
                res.append(" 2 место!");
            if(i == 2 && time > 0)
                res.append(" 3 место!");
            System.out.println(res.toString());
        }
    }

    private String getName(Competitor c) {
        return switch(c.getClass().getSimpleName()) {
            case "Man" -> ((Man) c).getName();
            case "Cat" -> ((Cat) c).getName();
            default -> ((Robot) c).getName();
        };
    }
}