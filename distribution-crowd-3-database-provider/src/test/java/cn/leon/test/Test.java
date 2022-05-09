package cn.leon.test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author Leon
 * @create 2022-05-10 1:04
 * IntelliJ IDEA
 * cn.leon.test
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class Test {

    @Resource
    private DataSource dataSource;

    @org.junit.Test
    public void test() throws SQLException {
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
    }
}
