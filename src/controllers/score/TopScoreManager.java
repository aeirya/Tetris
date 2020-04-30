package controllers.score;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import util.file.save.Save;

public class TopScoreManager {

    private static final String FILE_PATH = "scores.ser";
    private ScoreList list;

    private static final TopScoreManager instance = new TopScoreManager();
    private SlideAnimation animation = new SlideAnimation();

    public static TopScoreManager getInstance() { return instance; }

    public TopScoreManager() {
        load();
    }

    private void load() {
        list = (ScoreList) Save.load(FILE_PATH);
        if (list == null) list = new ScoreList();
    }

    private void save() {
        Save.save(list, FILE_PATH);
    }

    public void addScore(GameScore score) {
        list.add(score);
        save();
    }
    
    public String getText() {
        return "Top 10:\n------\n"+ animation.on(list.toString());
    }
    
    private class SlideAnimation {
        
        private int i=0;
        private int j=0;
        
        private String on(String string) {
            final List<String> split = Arrays.asList(string.split("\n"));
            final int n = split.size()-1;
            final int x = i(n);
            final List<String> result = new ArrayList<>();
            result.addAll(split.subList(x, n));
            result.addAll(split.subList(0, x));
            return String.join("\n", result);
        }
        
        private int i(int n) {
            if ( n==0 ) return 0;
            //so the first rank stays on top for more time
            if (i == 0) {
                j = (j+1) % 3;
            }
            if (j == 0) {
                i = (i+1) % n;
            }
            return i;
        }
    }
}