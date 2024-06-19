package com.ohgiraffers.practice;

import com.ohgiraffers.model.EmployeeDTO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import static com.ohgiraffers.common.EmployeeTemplate.close;
import static com.ohgiraffers.common.EmployeeTemplate.getConnection;

public class Application3 {

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

        Scanner sc = new Scanner(System.in);
        System.out.println("변경할 사원 코드를 입력하세요.");
        String empId = sc.next();

        System.out.println("변경할 사원 이름을 입력하세요.");
        String empName = sc.next();

        System.out.println("변경할 부서 코드를 입력하세요.");
        String deptCode = sc.next();

        System.out.println("변경할 급여를 입력하세요.");
        Double salary = sc.nextDouble();

        EmployeeDTO changeEmp = new EmployeeDTO();
        changeEmp.setEmpId(empId);
        changeEmp.setEmpName(empName);
        changeEmp.setDeptCode(deptCode);
        changeEmp.setSalary(salary);

        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/mapper/employee_query.xml"));

            String query = prop.getProperty("updateEmp");

            pstmt = con.prepareStatement(query);

           pstmt.setString(1, changeEmp.getEmpName());
           pstmt.setString(2, changeEmp.getDeptCode());
           pstmt.setDouble(3, changeEmp.getSalary());
           pstmt.setString(4, changeEmp.getEmpId());


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

        // 	223	송춘식	010110-1234561	mansik@greedy.com	01013244589	D8	J2	S6	1750000	0.15	214	2013-05-16		N


    }
}
