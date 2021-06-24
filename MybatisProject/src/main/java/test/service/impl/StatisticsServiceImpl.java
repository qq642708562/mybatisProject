package test.service.impl;

import org.apache.ibatis.session.SqlSession;
import test.dao.IGradeTableDao;
import test.dao.IStatisticsDao;
import test.service.IStatisticsService;
import test.utils.DaoFactory;
import test.vo.GradeTable;
import test.vo.Statistics;

import java.io.IOException;
import java.util.ArrayList;

public class StatisticsServiceImpl implements IStatisticsService {

    private SqlSession sqlSession;
    private IGradeTableDao gradeTableDao;
    private IStatisticsDao statisticsDao;

    public StatisticsServiceImpl() throws IOException {
        sqlSession = DaoFactory.getSqlSession();
        gradeTableDao = sqlSession.getMapper(IGradeTableDao.class);
        statisticsDao = sqlSession.getMapper(IStatisticsDao.class);
    }

    public SqlSession getSqlSession() {
        return sqlSession;
    }

    public void setSqlSession(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    public IGradeTableDao getGradeTableDao() {
        return gradeTableDao;
    }

    public IStatisticsDao getStatisticsDao() {
        return statisticsDao;
    }

    public void sqlSessionClose(){
        sqlSession.close();
    }

    public void displayGradeStatistics(){
        ArrayList<GradeTable> gradeTables = gradeTableDao.findAllGradeTable();
        if(gradeTables.size()<=0 ){
            System.out.println("尚未有考生录入成绩,有考生录入成绩后再来查看!\n");
            return;
        }
        ArrayList<Statistics> statistics = statisticsDao.getStatistics();
        if(statistics.size()<=0){
            System.out.println("尚未有考生录入成绩,没有统计结果!\n");
            return;
        }
        System.out.println(statistics.get(0));
    }

    public void updateGradeStatistics(){
        ArrayList<GradeTable> gradeTables = gradeTableDao.findAllGradeTable();
        if(gradeTables.size()<=0){
            System.out.println("尚未有考生录入成绩,无法进行统计!\n");
            return;
        }
        float max,min,average,bad,good,excellent;
        float sum=0;
        int badNum=0,goodNum=0,excellentNum=0;
        max=min=gradeTables.get(0).getGrade();
        int peopleNum = gradeTables.size();
        float grade;
        for(GradeTable gradeTable:gradeTables){
            grade = gradeTable.getGrade();
            if(grade>max)
                max = grade;
            if(grade<min)
                min = grade;
            if(grade<60)
                badNum++;
            else if(grade >= 60 && grade < 90) {
                goodNum++;
            }else
                excellentNum++;
            sum+=grade;
        }
        average = sum/peopleNum;
        bad = (float)badNum/peopleNum*100;
        good = (float)goodNum/peopleNum*100;
        excellent = (float)excellentNum/peopleNum*100;
        Statistics statistics = new Statistics(max,min,average,bad,good,excellent);
        statisticsDao.updateStatistics(statistics);
        DaoFactory.sqlSessionCommit(sqlSession);
        System.out.println("成绩已统计!\n");
    }
}
