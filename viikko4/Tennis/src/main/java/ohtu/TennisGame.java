package ohtu;

public class TennisGame {

    private String[] scoreStrings = {"Love", "Fifteen", "Thirty", "Forty"};
    private int player1Score = 0;
    private int player2Score = 0;
    private String player1Name;
    private String player2Name;

    public TennisGame(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public void wonPoint(String playerName) {
        if (playerName == player1Name)
            player1Score++;
        else
            player2Score++;
    }

    private String getEvenScore() {
        if (player1Score < 4) {
            return scoreStrings[player1Score] + "-All";
        } else {
            return "Deuce";
        }
    }

    private String getNearEndScore() {
        int scoreDifference = player1Score - player2Score;
        if (scoreDifference == 1) {
            return "Advantage " + player1Name;
        } else if (scoreDifference == -1) {
            return "Advantage " + player2Name;
        } else if (scoreDifference >= 2) {
            return "Win for " + player1Name;
        } else {
            return "Win for " + player2Name;
        }
    }

    public String getScore() {
        if (player1Score == player2Score) {
            return getEvenScore();
        } else if (player1Score >=4 || player2Score >=4) {
            return getNearEndScore();
        } else {
            return scoreStrings[player1Score] + "-" + scoreStrings[player2Score];
        }
    }

}