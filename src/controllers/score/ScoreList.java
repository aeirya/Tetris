package controllers.score;

import java.util.LinkedList;

public class ScoreList implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    private final LinkedList<GameScore> list;
    private final ScoreStyle ss = new ScoreStyle();

    public ScoreList() {
        list = new LinkedList<>();
    }

    public void add(GameScore score) {
        boolean isAdded = false;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getScore() < score.getScore()) {
                list.add(i, score);
                isAdded = true;
                break;
            } 
        }
        if (!isAdded) list.addLast(score);
        ss.update(list.getFirst().getScore());
        if (list.size()>10) list.removeLast();
    }

    public String toString() {
        String result = "";
        int i = 1;
        for (GameScore score : list) {
            result = result.concat(" "+ i +". "+ ss.styled(score.getScore())+"\n");
            i++;
        }
        return result;
    }

    private class ScoreStyle implements java.io.Serializable {

        private static final long serialVersionUID = 1L;

        private int l;

        private ScoreStyle() {
            l = 0;
        }

        private int digits(int n) {
            int c = 0;
            while (n != 0) {
                n /= 10;
                c += 1;
            }
            return c;
        }

        private String zeros(int n) {
            final String zero = "0";
            String result = "";
            for (int i=0; i < n; i++) {
                result = result.concat(zero);
            }
            return result;
        }

        private void update(int n) {
            l = digits(n);
        }

        private String styled(int i) {
            return zeros(l-digits(i)) + i;
        }
    }
}