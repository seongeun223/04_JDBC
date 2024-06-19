package com.ohgiraffers.practice;

import com.ohgiraffers.model.EmployeeDTO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import static com.ohgiraffers.common.EmployeeTemplate.close;
import static com.ohgiraffers.common.EmployeeTemplate.getConnection;

public class Application4 {

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

        Scanner sc = new Scanner(System.in);
        System.out.println("ID를 입력하세요 : ");
        String empId = sc.nextLine();
        System.out.println("회원 이름을 입력하세요 : ");
        String empName = sc.nextLine();

        String query = "select * from employee where emp_id =? and emp_name =?";
        System.out.println(query);

        Properties prop = new Properties();

        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/mapper/employee_query.xml"));




            pstmt = con.prepareStatement(query);
            pstmt.setString(1, empId);


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
            System.out.println("사원 삭제 성공");
        } else {
            System.out.println("사원 삭제 실패");
        }




    }
}
