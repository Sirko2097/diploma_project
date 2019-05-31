package com.diploma.binpacking.service.binpackingimpl;

public class PackRating implements Comparable {

    private int binsUsed;
    private int score;
    private int overflow;

    public PackRating(int binsUsed, int score, int overflow) {
        this.binsUsed = binsUsed;
        this.score = score;
        this.overflow = overflow;
    }

    @Override
    public int compareTo(Object o) {
        PackRating other = (PackRating) o;
        if (this.overflow != other.overflow) {
            return other.overflow - this.overflow;
        }
        if (this.binsUsed != other.binsUsed) {
            return other.binsUsed - this.binsUsed;
        }
        return this.score - other.score;
    }

    public int getBinsUsed() {
        return binsUsed;
    }

    public int getScore() {
        return score;
    }

    public int getOverflow() {
        return overflow;
    }

}
