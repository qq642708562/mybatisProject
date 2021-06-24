package test.utils;

import org.apache.ibatis.session.SqlSession;
import test.dao.IExaminationQuestionsDao;
import test.dao.IGradeTableDao;

import java.io.IOException;
import java.util.ArrayList;

public class GetQuestionNumber {

    private SqlSession sqlSession;
    private IExaminationQuestionsDao examinationQuestionsDao;

    public GetQuestionNumber() throws IOException {
        sqlSession = DaoFactory.getSqlSession();
        examinationQuestionsDao = sqlSession.getMapper(IExaminationQuestionsDao.class);
    }
    //返回随机生成的题号数组
    public ArrayList<Integer> getQuestionNumber(int num){
        int min = 1;
        int max = examinationQuestionsDao.findAllExaminationQuestions().size();
        ArrayList<Integer> results = null;
        results = new ArrayList<>();
        int count = 0;
        while(count<num){
            int questionNumber = (int) (Math.random() * max) + min;
            if(count == 0){
                results.add(questionNumber);
                count++;
            }
            boolean flag = true;
            for(int i=0;i<results.size();i++){
                if(results.get(i) == questionNumber)
                    flag = false;
            }
            if(flag){
                results.add(questionNumber);
                count++;
            }
        }
        return results;
    }
}
