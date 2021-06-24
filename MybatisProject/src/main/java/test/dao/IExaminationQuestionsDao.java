package test.dao;

import test.vo.ExaminationQuestions;

import java.util.ArrayList;

public interface IExaminationQuestionsDao {

    ArrayList<ExaminationQuestions> findAllExaminationQuestions();

    ExaminationQuestions findExaminationQuestionById(int id);

    void insertExaminationQuestion(ExaminationQuestions examinationQuestions);

    void updateExaminationQuestion(ExaminationQuestions examinationQuestions);

    void deleteExaminationQuestionById(int id);

    void dropId();

    void addId();

    void setPrimaryKey();
}
