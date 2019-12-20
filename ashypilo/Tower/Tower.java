package ashypilo.Tower;

import ashypilo.Flayable.Flyable;
import java.util.ArrayList;

public class Tower {

    private ArrayList<Flyable> observes = new ArrayList<>();

    public void register(Flyable flyable) {
        observes.add(flyable);
    }

    public void unregister(Flyable flyable) {
        observes.remove(flyable);
    }

    protected void conditionsChanged() {
        int old_size = 0;
        for (int i = 0; i < observes.size(); i++) {
            old_size = observes.size();
            observes.get(i).updateConditions();
            if (old_size != 0 && old_size != observes.size())
                i--;
        }
    }
}
