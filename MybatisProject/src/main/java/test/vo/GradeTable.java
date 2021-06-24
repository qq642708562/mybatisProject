package test.vo;

public class GradeTable {
    private String name;
    private float grade;

    public GradeTable() {
    }

    public GradeTable(String name, float grade) {
        this.name = name;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public float getGrade() {
        return grade;
    }

    @Override
    public String toString() {
        return "name:" + name + ", grade:" + grade;
    }
}
