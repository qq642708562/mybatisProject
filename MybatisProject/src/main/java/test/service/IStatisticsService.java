package test.service;

import org.apache.ibatis.session.SqlSession;

public interface IStatisticsService {

    public void displayGradeStatistics();

    public void updateGradeStatistics();

    public void sqlSessionClose();
}
