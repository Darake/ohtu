package statistics.matcher;

import statistics.Player;
import statistics.matcher.HasAtLeast;
import statistics.matcher.Not;

public class HasFewerThan implements Matcher {
    private Matcher matcher;

    public HasFewerThan(int value, String category) {
        Matcher hasAtLeast = new HasAtLeast(value, category);
        matcher = new Not(hasAtLeast);
    }

    @Override
    public boolean matches(Player p) {
        return matcher.matches(p);
    }
}