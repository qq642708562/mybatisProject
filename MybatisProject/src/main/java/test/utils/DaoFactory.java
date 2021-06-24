package test.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class DaoFactory {
    public static SqlSession getSqlSession() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        //读取配置文件的配置信息，利用SqlSessionFactoryBuilder创建sqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //利用sqlSessionFactory打开与数据库的会话
        SqlSession sqlSession = sqlSessionFactory.openSession();
        return sqlSession;
    }

    public static void sqlSessionCommit(SqlSession sqlSession){
        sqlSession.commit();
    }

    public static void sqlSessionClose(SqlSession sqlSession){
        sqlSession.close();
    }
}
