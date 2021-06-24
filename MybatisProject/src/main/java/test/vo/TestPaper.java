package test.vo;

public class TestPaper {
    private int num;
    private String time;

    public TestPaper() {
    }

    public TestPaper(int num, String time) {
        this.num = num;
        this.time = time;
    }

    public int getNum() {
        return num;
    }

    public String getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "TestPaper{" +
                "num=" + num +
                ", time='" + time + '\'' +
                '}';
    }
}
