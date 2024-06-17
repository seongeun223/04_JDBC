package com.ohgiraffers.section01.practice1;

import com.ohgiraffers.model.dto.ClassDTO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static com.ohgiraffers.common.ClassTemplate.close;
import static com.ohgiraffers.common.ClassTemplate.getConnection;

public class Application {
    public static void main(String[] args) {
        Connection con = getConnection();

        Statement stmt = null;

        ResultSet rset = null;

        List<ClassDTO> clsList = null;

        ClassDTO row = null;

        try {

            stmt = con.createStatement();

            String query = "select * from class";

            rset = stmt.executeQuery(query);

            clsList = new ArrayList<>();

            while (rset.next()) {
                row = new ClassDTO();

                row.setStuNo(rset.getInt("student_no"));
                row.setStuName(rset.getString("student_Name"));
                row.setGender(rset.getString("gender"));
                row.setGithubId(rset.getString("github_id"));
                row.setEmail(rset.getString("email"));
                row.setMbti(rset.getString("mbti"));
                row.setSubNo(rset.getString("subject_no"));

                clsList.add(row);

            }
            System.out.println(row.toString());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {

            close(con);
            close(stmt);
            close(rset);

            for(ClassDTO cls : clsList) {
                System.out.println(cls);
            }
        }
    }
}
