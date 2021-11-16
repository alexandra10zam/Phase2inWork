package game;

import java.io.Serializable;

public class Record implements Comparable<Record>, Serializable{
    private String name;
    private int score;

    public Record(String name, int score){
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public int compareTo(Record record) {
        return record.score - this.score;
    }
}
