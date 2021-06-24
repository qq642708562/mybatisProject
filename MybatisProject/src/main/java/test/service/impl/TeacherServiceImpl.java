package test.service.impl;

import org.apache.ibatis.session.SqlSession;
import test.dao.IExaminationQuestionsDao;
import test.dao.IGradeTableDao;
import test.dao.IStatisticsDao;
import test.dao.ITestPaperDao;
import test.service.ITeacherService;
import test.utils.DaoFactory;
import test.utils.IsValidDate;
import test.utils.Mune;
import test.vo.ExaminationQuestions;
import test.vo.GradeTable;
import test.vo.Statistics;
import test.vo.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TeacherServiceImpl implements ITeacherService {

    private Scanner s;
    private StatisticsServiceImpl statisticsService;
    private SqlSession sqlSession;
    private IExaminationQuestionsDao examinationQuestionsDao;
    private ITestPaperDao testPaperDao;
    private IGradeTableDao gradeTableDao;
    private IStatisticsDao statisticsDao;
    private StudentServiceImpl studentService;

    public TeacherServiceImpl() throws IOException {
        s = new Scanner(System.in);
        statisticsService = new StatisticsServiceImpl();
        sqlSession = statisticsService.getSqlSession();
        examinationQuestionsDao = sqlSession.getMapper(IExaminationQuestionsDao.class);
        testPaperDao = sqlSession.getMapper(ITestPaperDao.class);
        gradeTableDao = statisticsService.getGradeTableDao();
        statisticsDao = statisticsService.getStatisticsDao();
        studentService = new StudentServiceImpl();
    }

    public Scanner getS() {
        return s;
    }

    public StatisticsServiceImpl getStatisticsService() {
        return statisticsService;
    }

    public SqlSession getSqlSession() {
        return sqlSession;
    }

    public IExaminationQuestionsDao getExaminationQuestionsDao() {
        return examinationQuestionsDao;
    }

    public ITestPaperDao getTestPaperDao() {
        return testPaperDao;
    }

    public IGradeTableDao getGradeTableDao() {
        return gradeTableDao;
    }

    public IStatisticsDao getStatisticsDao() {
        return statisticsDao;
    }

    public void teacherOperation(User user){
        System.out.println("登陆成功!欢迎您,"+user.getLastName()+"老师");
        boolean flag = true;
        boolean flag2 = true;
        boolean flag3 = true;
        boolean flag4 = true;
        while(flag){
            Mune.teacherMune();
            int choice = s.nextInt();
            switch (choice){
                case 1:
                    flag2 = true;
                    while (flag2){
                        Mune.examinationQuestionsMune();
                        int choice2 = s.nextInt();
                        switch (choice2){
                            case 1:
                                System.out.println("输入要添加的题目信息,格式为(题目类型,题目问题,题目内容,题目答案)");
                                System.out.println("题目属性之间用,分隔,类型只能输入true和false,表示该题是否为多选题,题目内容的每个选项用空格分隔(输入exit退出):");
                                s.nextLine();
                                String examQuestion = s.nextLine();
                                if("exit".equals(examQuestion))
                                    break;
                                String[] data = examQuestion.split(",");
                                String kind = data[0];
                                String title = data[1];
                                String content = data[2];
                                String answer = data[3];
                                ExaminationQuestions examinationQuestions = new ExaminationQuestions(kind,title,content,answer);
                                examinationQuestionsDao.insertExaminationQuestion(examinationQuestions);
                                reorderId();
                                DaoFactory.sqlSessionCommit(sqlSession);
                                System.out.println("添加成功!\n");
                                break;
                            case 2:
                                displayExaminationquestions();
                                System.out.println("输入要修改的题目信息,格式为(题号,题目类型,题目问题,题目内容,题目答案)");
                                System.out.println("题目属性之间用,分隔,类型只能输入true和false,表示该题是否为多选题,题目内容的每个选项用空格分隔(输入exit退出):");
                                s.nextLine();
                                String examQuestion2 = s.nextLine();
                                if("exit".equals(examQuestion2))
                                    break;
                                String[] data2 = examQuestion2.split(",");
                                int id = Integer.parseInt(data2[0]);
                                String kind2 = data2[1];
                                String title2 = data2[2];
                                String content2 = data2[3];
                                String answer2 = data2[4];
                                ExaminationQuestions examinationQuestions2 = new ExaminationQuestions(id,kind2,title2,content2,answer2);
                                examinationQuestionsDao.updateExaminationQuestion(examinationQuestions2);
                                DaoFactory.sqlSessionCommit(sqlSession);
                                System.out.println("修改成功!\n");
                                break;
                            case 3:
                                int delNum = 0;
                                int sum = examinationQuestionsDao.findAllExaminationQuestions().size();
                                int titleNum = testPaperDao.getTestPaper().get(0).getNum();
                                if(sum<=titleNum){
                                    System.out.println("删除题库会导致试卷题目不够,请先增加考试题目数量!\n");
                                    break;
                                }
                                while(true){
                                    displayExaminationquestions();
                                    System.out.println("输入要删除题目的题号(输入0退出):");
                                    delNum = s.nextInt();
                                    if (delNum == 0)
                                        break;
                                    if(delNum>sum || delNum<1)
                                        System.out.println("请输入1~"+examinationQuestionsDao.findAllExaminationQuestions().size()+"的数字!");
                                    else
                                        break;
                                }
                                examinationQuestionsDao.deleteExaminationQuestionById(delNum);
                                reorderId();
                                DaoFactory.sqlSessionCommit(sqlSession);
                                System.out.println("删除成功!\n");
                                break;
                            case 4:
                                System.out.println("已退出题库管理!\n");
                                flag2 = false;
                                break;
                            default:
                                System.out.println("请输入1~4的数字!\n");
                        }

                    }
                    break;
                case 2:
                    flag3 = true;
                    while (flag3){
                        Mune.examSetMune();
                        int choice3 = s.nextInt();
                        switch (choice3){
                            case 1:
                                int num = 0;
                                int sum = examinationQuestionsDao.findAllExaminationQuestions().size();
                                while(true){
                                    System.out.println("设置本次考试题目数量为:");
                                    num = s.nextInt();
                                    if(num>sum || num<1)
                                        System.out.println("请输入1~"+examinationQuestionsDao.findAllExaminationQuestions().size()+"的数字!");
                                    else
                                        break;
                                }
                                testPaperDao.updateTestPaperNum(num);
                                DaoFactory.sqlSessionCommit(sqlSession);
                                System.out.println("设置考试题目数量成功!\n");
                                break;
                            case 2:
                                s.nextLine();
                                String time = null;
                                while (true){
                                    System.out.println("设置本次考试时间为(格式为HH:mm):");
                                    time = s.nextLine();
                                    if(IsValidDate.isValidDate(time)){
                                        break;
                                    }else {
                                        System.out.println("设置时间格式有误,请输入如13:30这样的时间,数字范围为0~9!\n");
                                    }
                                }
                                testPaperDao.updateTestPaperTime(time);
                                DaoFactory.sqlSessionCommit(sqlSession);
                                System.out.println("设置考试时间成功!\n");
                                break;
                            case 3:
                                System.out.println("已退出考试管理!\n");
                                flag3 = false;
                                break;
                            default:
                                System.out.println("请输入1~3的数字!\n");
                                break;
                        }
                    }
                    break;
                case 3:
                    flag4 = true;
                    while (flag4){
                        Mune.queryMune();
                        int choice4 = s.nextInt();
                        switch (choice4){
                            case 1:
                                String examineeName = null;
                                boolean nameFlag = false;
                                GradeTable gradeTable = null;
                                s.nextLine();
                                while (true){
                                    System.out.println("输入要查询的考试姓名(输入exit退出):");
                                    examineeName = s.nextLine();
                                    if ("exit".equals(examineeName))
                                        break;
                                    gradeTable = gradeTableDao.findGradeTableByName(examineeName);
                                    if (gradeTable != null)
                                        break;
                                    else
                                        System.out.println("查无此人,请重新输入姓名!\n");
                                }
                                if(gradeTable != null){
                                    System.out.println(gradeTable);
                                    System.out.println();
                                }
                                break;
                            case 2:
                                ArrayList<GradeTable> gradeTables= gradeTableDao.findAllGradeTable();
                                if(gradeTables.size() <= 0){
                                    System.out.println("没有数据!\n");
                                }else {
                                    for(GradeTable gradeTablee:gradeTables){
                                        System.out.println(gradeTablee);
                                    }
                                    System.out.println();
                                }
                                break;
                            case 3:
                                String examineeName2 = null;
                                GradeTable gradeTable2 = null;
                                int count = 0;
                                while (true){
                                    System.out.println("输入要删除的考试姓名(输入exit退出):");
                                    if(count == 0){
                                        s.nextLine();
                                    }
                                    count++;
                                    examineeName2 = s.nextLine();
                                    if ("exit".equals(examineeName2)){
                                        examineeName2 = null;
                                        break;
                                    }
                                    gradeTable2 = gradeTableDao.findGradeTableByName(examineeName2);
                                    if (gradeTable2 != null)
                                        break;
                                    else
                                        System.out.println("查无此人,请重新输入姓名!\n");
                                }
                                if(examineeName2!=null){
                                    gradeTableDao.deleteGradeByName(examineeName2);
                                    statisticsService.updateGradeStatistics();
                                    DaoFactory.sqlSessionCommit(sqlSession);
                                    System.out.println("删除成绩成功!\n");
                                }
                                break;
                            case 4:
                                System.out.println("成绩查询已退出!");
                                flag4 = false;
                                break;
                            default:
                                System.out.println("请输入1~3的数字!\n");
                                break;
                        }
                    }
                    break;
                case 4:
                    //成绩统计
                    statisticsService.displayGradeStatistics();
                    break;
                case 5:
                    System.out.println("系统已退出!\n");
                    flag = false;
                    break;
                default:
                    System.out.println("请输入1~5的数字!\n");
                    break;
            }
        }
    }

    public void sqlSessionClose(){
        sqlSession.close();
    }

    public void reorderId(){
        examinationQuestionsDao.dropId();
        examinationQuestionsDao.addId();
        examinationQuestionsDao.setPrimaryKey();
    }

    public void displayExaminationquestions(){
        ArrayList<ExaminationQuestions> questions = examinationQuestionsDao.findAllExaminationQuestions();
        int size = questions.size();
        for(int i=0;i<size;i++){
            ExaminationQuestions question = examinationQuestionsDao.findExaminationQuestionById(i+1);
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
            System.out.println(question.getAnswer());
        }
    }

}
