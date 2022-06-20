package com.artyawn.arty.StatsUser;

public class StatsUserClass {

    public int done;
    public int passed;
    public int work_procent;
    public String stats_group_title;

    public StatsUserClass(int done, int passed, int work_procent, String stats_group_title) {
        this.done = done;
        this.passed = passed;
        this.work_procent = work_procent;
        this.stats_group_title = stats_group_title;
    }

    public StatsUserClass(int done, int passed, int work_procent) {
        this.done = done;
        this.passed = passed;
        this.work_procent = work_procent;
    }

    public String getStats_group_title() {
        return stats_group_title;
    }

    public int getDone() {
        return done;
    }

    public int getPassed() {
        return passed;
    }

    public int getWork_procent() {
        return work_procent;
    }
}
