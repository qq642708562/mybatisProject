package test.dao;

import test.vo.Statistics;

import java.util.ArrayList;

public interface IStatisticsDao {

    ArrayList<Statistics> getStatistics();

    void updateStatistics(Statistics statistics);
}
