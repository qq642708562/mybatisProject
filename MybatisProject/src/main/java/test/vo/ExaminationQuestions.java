package test.vo;

public class ExaminationQuestions {
    private int id;
    private String kind;
    private String title;
    private String content;
    private String answer;

    public ExaminationQuestions() {
    }

    public ExaminationQuestions(String kind, String title, String content, String answer) {
        this.kind = kind;
        this.title = title;
        this.content = content;
        this.answer = answer;
    }

    public ExaminationQuestions(int id, String kind, String title, String content, String answer) {
        this.id = id;
        this.kind = kind;
        this.title = title;
        this.content = content;
        this.answer = answer;
    }

    public int getId(){
        return id;
    }

    public String getKind() {
        return kind;
    }

    public String getTitle(){
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getAnswer() {
        return answer;
    }

    @Override
    public String toString() {
        return "ExaminationQuestions{" +
                "id=" + id +
                ", kind='" + kind + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", answer='" + answer + '\'' +
                '}';
    }
}
