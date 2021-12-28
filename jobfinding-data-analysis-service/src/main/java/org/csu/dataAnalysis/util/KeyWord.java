package com.h3c.jobhunter.util;

/**
 * Created by Administrator on 2018/3/11 0011.
 */
public class KeyWord  implements Comparable<KeyWord>{
    private String name;
    private double score;

    public KeyWord (String name,double score){
        this.name=name;
        this.score=score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return this.name + "\t" + score;
    }

    @Override
    public int compareTo(KeyWord o) {
        // TODO Auto-generated method stub
        if (this.score < o.score) {
            return 1;
        }
        else {
            return -1;
        }
    }
}

