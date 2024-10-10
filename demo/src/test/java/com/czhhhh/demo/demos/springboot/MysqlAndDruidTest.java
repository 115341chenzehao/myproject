package com.czhhhh.demo.demos.springboot;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.czhhhh.demo.demos.springboot.jpa.*;
import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@SpringBootTest
@Slf4j
public class MysqlAndDruidTest {
    @Resource
    private JdbcTemplate jdbcTemplate;

    @Test
    void contextLoads() {
        String sql ="select * from user";
        List<User> users = jdbcTemplate.query(sql, new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                User user = new User();
                user.setId(rs.getInt(1));
                user.setAge(rs.getInt("age"));
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
                return user;
            }
        });
        log.info("查询成功");
        log.info("用户{}",users);
    }

}
