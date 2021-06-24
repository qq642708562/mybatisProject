package test.service.impl;

import org.apache.ibatis.session.SqlSession;
import test.dao.IExaminationQuestionsDao;
import test.dao.IGradeTableDao;
import test.service.IStudentService;
import test.utils.CompareAnswer;
import test.utils.DaoFactory;
import test.utils.GetQuestionNumber;
import test.vo.ExaminationQuestions;
import test.vo.GradeTable;
import test.vo.TestPaper;
import test.vo.User;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class StudentServiceImpl implements IStudentService {

    private Scanner s;
    private SqlSession sqlSession;
    private SimpleDateFormat simpleDateFormat;
    private GetQuestionNumber getQuestionNumber;
    private StatisticsServiceImpl statisticsService;
    private IExaminationQuestionsDao examinationQuestionsDao;
    private IGradeTableDao gradeTableDao;

    public StudentServiceImpl() throws IOException {
        s = new Scanner(System.in);
        sqlSession = DaoFactory.getSqlSession();
        simpleDateFormat = new SimpleDateFormat("HH:mm");
        getQuestionNumber = new GetQuestionNumber();
        statisticsService = new StatisticsServiceImpl();
        examinationQuestionsDao = sqlSession.getMapper(IExaminationQuestionsDao.class);
        gradeTableDao = statisticsService.getGradeTableDao();
    }

    public Scanner getS() {
        return s;
    }

    public SqlSession getSqlSession() {
        return sqlSession;
    }

    public SimpleDateFormat getSimpleDateFormat() {
        return simpleDateFormat;
    }

    public GetQuestionNumber getGetQuestionNumber() {
        return getQuestionNumber;
    }

    public StatisticsServiceImpl getStatisticsService() {
        return statisticsService;
    }

    public IExaminationQuestionsDao getExaminationQuestionsDao() {
        return examinationQuestionsDao;
    }

    public IGradeTableDao getGradeTableDao() {
        return gradeTableDao;
    }

    public void studentOperation(TestPaper testPaper, User user){
        String name = user.getName();
        String time = testPaper.getTime();
        System.out.println("登录成功!" + user.getName() + ",考试马上开始,考试时间为:"+time);
        while (true) {
            String nowTime = simpleDateFormat.format(new Date());
            if(nowTime.equals(time)){
                System.out.println("考试开始,请答题:");
                break;
            }
        }
        int num = testPaper.getNum();
        int count = 0;
        ArrayList<Integer> results = getQuestionNumber.getQuestionNumber(num);
        float grade = 0;
        float questionScore = 100/num;
        for (int i = 0; i < num; i++) {
            ExaminationQuestions question = examinationQuestionsDao.findExaminationQuestionById(results.get(i));
            System.out.print(i + 1 + "." + question.getTitle());
            if ("true".equals(question.getKind()))
                System.out.println("(多选题)");
            else
                System.out.println("(单选题)");
            String content = question.getContent();
            String[] contents = content.split(" ");
            for(String a:contents){
                System.out.println(a);
            }
            System.out.println("请输入你的答案:");
            String answer = null;
            while (true){
                answer = s.nextLine();
                if("true".equals(question.getKind())){
                    if(answer.length()==0){
                        System.out.println("答案不能为空!\n");
                    }else if(answer.length()<=1){
                        System.out.println("请选择多个答案!\n");
                    }else{
                        if(CompareAnswer.compareAnswer(question.getAnswer().toUpperCase(),answer.toUpperCase())){
                            grade+=questionScore;
                            count++;
                        }
                        break;
                    }
                }else{
                    if(answer.length()==0){
                        System.out.println("答案不能为空!\n");
                    }else if(answer.length()>1){
                        System.out.println("只能选择一个答案!\n");
                    }else{
                        if(CompareAnswer.compareAnswer(question.getAnswer().toUpperCase(),answer.toUpperCase())){
                            grade+=questionScore;
                            count++;
                        }
                        break;
                    }
                }
            }

        }
        if(num == count)
            grade = 100.0f;
        GradeTable gradeTable = new GradeTable(name,grade);
        GradeTable g = gradeTableDao.findGradeTableByName(name);
        if(g!=null && g.getName().equals(gradeTable.getName())){
            gradeTableDao.updateGrade(gradeTable);
        }else{
            gradeTableDao.insertGrade(gradeTable);
        }
        statisticsService.updateGradeStatistics();
    }

    public void sqlSessionClose(){
        sqlSession.close();
    }

}
