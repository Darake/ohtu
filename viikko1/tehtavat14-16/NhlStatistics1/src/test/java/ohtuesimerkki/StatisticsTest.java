package ohtuesimerkki;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class StatisticsTest {

    Player kurri = new Player("Kurri",   "EDM", 37, 53);

    Reader readerStub = new Reader() {

        public List<Player> getPlayers() {
            List<Player> players = new ArrayList<>();

            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(kurri);
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));

            return players;
        }
    };

    Statistics stats;

    @Before
    public void setUp(){
        // luodaan Statistics-olio joka käyttää "stubia"
        stats = new Statistics(readerStub);
    }

    @Test
    public void searchReturnsTheRightPlayer() {
        Player player = stats.search("Kurri");

        assertEquals(kurri, player);
    }

    @Test
    public void searchReturnsNullIfNoPlayerFound() {
        Player player = stats.search("Trump");

        assertEquals(null, player);
    }

    @Test
    public void teamReturnsRightSizedListOfPlayersOfSearchedTeam() {
        List<Player> players = stats.team("EDM");

        assertEquals(3, players.size());
    }

    @Test
    public void topScoresHasTheRightPlayerFirstInList() {
        List<Player> topScores = stats.topScorers(3);

        assertEquals("Gretzky", topScores.get(0).getName());
    }
}