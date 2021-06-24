package test.utils;

import org.apache.ibatis.session.SqlSession;
import test.dao.*;
import test.service.impl.StudentServiceImpl;
import test.service.impl.TeacherServiceImpl;
import test.vo.*;

import java.io.IOException;
import java.util.Scanner;

public class ExamSystem {

    private Scanner s;
    private SqlSession sqlSession;
    private TeacherServiceImpl teacherService;
    private StudentServiceImpl studentService;
    private ITestPaperDao testPaperDao;
    private IUserDao userDao;

    public ExamSystem() throws IOException {
        s = new Scanner(System.in);
        sqlSession = DaoFactory.getSqlSession();
        teacherService = new TeacherServiceImpl();
        studentService = new StudentServiceImpl();
        testPaperDao = teacherService.getTestPaperDao();
        userDao = sqlSession.getMapper(IUserDao.class);
    }

    public void start(){
        while(true){
            System.out.println("欢迎进入考试系统,请输入用户名(如需退出系统,请输入exit):");
            String name = s.nextLine();
            if("exit".equals(name)){
                break;
            }
            System.out.println("请输入密码:");
            String password = s.nextLine();
            User user = userDao.findUserByName(name);
            TestPaper testPaper = testPaperDao.getTestPaper().get(0);
            if(user == null){
                System.out.println("不存在此用户,请重新输入!\n");
                continue;
            }else if(!user.getPassword().equals(password)){
                System.out.println("密码错误,请重新输入!\n");
                continue;
            }else{
                String role = user.getRole();
                if("student".equals(role)) {
                    studentService.studentOperation(testPaper,user);
                    studentService.sqlSessionClose();
                    studentService.getStatisticsService().sqlSessionClose();
                    teacherService.sqlSessionClose();
                    teacherService.getStatisticsService().sqlSessionClose();
                    break;
                }else{
                    teacherService.teacherOperation(user);
                    studentService.sqlSessionClose();
                    studentService.getStatisticsService().sqlSessionClose();
                    teacherService.sqlSessionClose();
                    teacherService.getStatisticsService().sqlSessionClose();
                    break;
                }
            }
        }
    }













}
