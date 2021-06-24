package test;

import static org.junit.Assert.assertTrue;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import test.dao.IExaminationQuestionsDao;
import test.utils.DaoFactory;
import test.vo.ExaminationQuestions;

import java.io.IOException;


public class AppTest {

    SqlSession sqlSession = null;
    {
        try {
            sqlSession = DaoFactory.getSqlSession();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void addExaminationQuestions(){
        IExaminationQuestionsDao examinationQuestionsDao = sqlSession.getMapper(IExaminationQuestionsDao.class);
        String kind = "false";
        String title = "江泽民在哪次会议上全面阐述了\"三个代表\"重要思想的科学内涵和基本内容?";
        String content = "A.党的十四大 B.党的十五大 C.党的十三大 D.庆祝中国共产党成立80周年大会";
        String answer = "D";
        ExaminationQuestions examinationQuestion = new ExaminationQuestions(kind,title,content,answer);
        examinationQuestionsDao.insertExaminationQuestion(examinationQuestion);
        examinationQuestionsDao.dropId();
        examinationQuestionsDao.addId();
        examinationQuestionsDao.setPrimaryKey();
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void updateExaminationQuestions(){
        IExaminationQuestionsDao examinationQuestionsDao = sqlSession.getMapper(IExaminationQuestionsDao.class);
        int id = 5;
        String kind = "false";
        String title = "江泽民在哪次会议上全面阐述了\"三个代表\"重要思想的科学内涵和基本内容?";
        String content = "A.党的十四大 B.党的十五大 C.党的十三大 D.庆祝中国共产党成立80周年大会";
        String answer = "D";
        ExaminationQuestions examinationQuestion = new ExaminationQuestions(id,kind,title,content,answer);
        examinationQuestionsDao.insertExaminationQuestion(examinationQuestion);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void deleteExaminationQuestions(){
        IExaminationQuestionsDao examinationQuestionsDao = sqlSession.getMapper(IExaminationQuestionsDao.class);
        int id = 5;
        examinationQuestionsDao.deleteExaminationQuestionById(id);
        examinationQuestionsDao.dropId();
        examinationQuestionsDao.addId();
        examinationQuestionsDao.setPrimaryKey();
        sqlSession.commit();
        sqlSession.close();
    }
}
