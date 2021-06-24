package test.vo;

public class Statistics {
    private float max;
    private float min;
    private float average;
    private float bad;
    private float good;
    private float excellent;

    public Statistics() {
    }

    public Statistics(float max, float min, float average, float bad, float good, float excellent) {
        this.max = max;
        this.min = min;
        this.average = average;
        this.bad = bad;
        this.good = good;
        this.excellent = excellent;
    }

    public float getMax() {
        return max;
    }

    public float getMin() {
        return min;
    }

    public float getAverage() {
        return average;
    }

    public float getBad() {
        return bad;
    }

    public float getGood() {
        return good;
    }

    public float getExcellent() {
        return excellent;
    }

    @Override
    public String toString() {
        return "最高分为" + max + "分"+
                ", 最低分为" + min + "分"+
                ", 平均分为" + average + "分"+
                ", 不合格:" + bad + "%"+
                ", 合格:" + good + "%"+
                ", 优秀:" + excellent + "%";
    }
}
