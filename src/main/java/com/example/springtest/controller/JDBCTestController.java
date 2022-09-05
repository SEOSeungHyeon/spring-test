package com.example.springtest.controller;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.*;

@Slf4j
@RestController
@RequestMapping("")
@Api(tags = {"필터 api"})
@RequiredArgsConstructor
public class JDBCTestController {

    @GetMapping("/jdbc-test")
    public String jdbcTest() {
        try {
            Connection con = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "");
            Statement stmt = con.createStatement();

            String sql = "SELECT * FROM TEST";
            ResultSet rs = stmt.executeQuery(sql);


            while (rs.next()) {
                System.out.println(rs.getString("test"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return "넘겨";
    }
}
