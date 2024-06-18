package com.ohgiraffers.section02;

import com.ohgiraffers.model.dto.EmployeeDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application5 {

    public static void main(String[] args) {

        // 연결객체 만들기
        Connection con = getConnection();

        PreparedStatement pstmt = null;

        ResultSet rset = null;

        // 조회할 employee의 이름의 성을 받아서 찾기
        Scanner sc = new Scanner(System.in);

        System.out.println("조회할 이름의 성을 입력하세요 : ");

        String empName = sc. nextLine();

        // concat(?, '%') => ?로 시작하는 것
        String query = "select * from employee where emp_name like concat(?, '%')";

        EmployeeDTO row = null;
        List<EmployeeDTO> empList = null;

        try {
            pstmt = con.prepareStatement(query);

            pstmt.setString(1, empName);

            rset = pstmt.executeQuery();

            empList = new ArrayList<>();

            while (rset.next()) {
                row = new EmployeeDTO();

                row.setEmpId(rset.getString("emp_Id"));
                row.setEmpName(rset.getString("emp_Name"));
                row.setEmpNo(rset.getString("emp_No"));
                row.setEmail(rset.getString("email"));
                row.setPhone(rset.getString("phone"));
                row.setDeptCode(rset.getString("Dept_Code"));
                row.setJobCode(rset.getString("Job_Code"));
                row.setSalLevel(rset.getString("Sal_Level"));
                row.setSalary(rset.getDouble("Salary"));
                row.setBonus(rset.getDouble("Bonus"));
                row.setManagerId(rset.getString("Manager_Id"));
                row.setHireDate(rset.getDate("Hire_Date"));
                row.setEntDate(rset.getDate("Ent_Date"));
                row.setEntYn(rset.getString("Ent_YN"));

                empList.add(row);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rset);
            close(pstmt);
            close(con);
        }

        for(EmployeeDTO emp : empList) {
            System.out.println(emp);
        }
    }
}
