package com.ohgiraffers.section02.preparedstatement;

import com.ohgiraffers.model.dto.EmployeeDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application3 {
    public static void main(String[] args) {

        // Scanner를 사용한 PreparedStatement
        Connection con = getConnection();

        PreparedStatement pstmt = null;

        ResultSet rset = null;


        // Scanner 사번 입력받기
        Scanner sc = new Scanner(System.in);
        System.out.println("조회할 사번을 입력해주세요 : ");
        String empId = sc.nextLine();

        String query = "select * from employee where emp_id = ?";

        EmployeeDTO selectedEmp = null;

        try {

            pstmt = con.prepareStatement(query);

            pstmt.setString(1, empId);

            rset = pstmt.executeQuery();

            if(rset.next()) {
                selectedEmp = new EmployeeDTO();

                selectedEmp.setEmpId(rset.getString("emp_Id"));
                selectedEmp.setEmpName(rset.getString("emp_Name"));
                selectedEmp.setEmpNo(rset.getString("emp_No"));
                selectedEmp.setEmail(rset.getString("email"));
                selectedEmp.setPhone(rset.getString("phone"));
                selectedEmp.setDeptCode(rset.getString("Dept_Code"));
                selectedEmp.setJobCode(rset.getString("Job_Code"));
                selectedEmp.setSalLevel(rset.getString("Sal_Level"));
                selectedEmp.setSalary(rset.getDouble("Salary"));
                selectedEmp.setBonus(rset.getDouble("Bonus"));
                selectedEmp.setManagerId(rset.getString("Manager_Id"));
                selectedEmp.setHireDate(rset.getDate("Hire_Date"));
                selectedEmp.setEntDate(rset.getDate("Ent_Date"));
                selectedEmp.setEntYn(rset.getString("Ent_YN"));

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rset);
            close(pstmt);
            close(con);
        }
        System.out.println("selectedEmp = " + selectedEmp);
    }
}
