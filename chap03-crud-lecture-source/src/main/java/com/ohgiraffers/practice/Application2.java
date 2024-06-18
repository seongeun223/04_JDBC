package com.ohgiraffers.practice;

import com.ohgiraffers.model.EmployeeDTO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static com.ohgiraffers.common.EmployeeTemplate.close;
import static com.ohgiraffers.common.EmployeeTemplate.getConnection;

public class Application2 {

    /*
    * Employee
    * 1. 목록조회
    * 2. 삽입
    * 3. 수정
    * 4. 삭제
    * 5. 단일조회
    * */

    public static void main(String[] args) {

        Connection con = getConnection();

        PreparedStatement pstmt = null;

        int result = 0;


        Properties prop = new Properties();

        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/mapper/employee_query.xml"));

            String query = prop.getProperty("insertEmp");

            pstmt = con.prepareStatement(query);

           pstmt.setString(1, "223");
           pstmt.setString(2, "장만식");
           pstmt.setString(3, "010110-1234561");
           pstmt.setString(4, "mansik@greedy.com");
           pstmt.setString(5, "01013244589");
           pstmt.setString(6, "D3");
           pstmt.setString(7, "J2");
           pstmt.setString(8, "S6");
           pstmt.setDouble(9, 1500000);
           pstmt.setDouble(10, 0.15);
           pstmt.setString(11, "214");
           pstmt.setDate(12, Date.valueOf("2013-05-16"));
           pstmt.setDate(13, null);
           pstmt.setString(14, "N");

           result = pstmt.executeUpdate();


        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(pstmt);
            close(con);
        }

        if(result > 0) {
            System.out.println("사원 저장 성공");
        } else {
            System.out.println("사원 저장 실패");
        }


    }
}
