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

public class Application1 {

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

        ResultSet rset = null;

        EmployeeDTO row = null;
        List<EmployeeDTO> empList = null;

        Properties prop = new Properties();

        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/mapper/employee_query.xml"));

            String query = prop.getProperty("employee");

            pstmt = con.prepareStatement(query);

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

        } catch (IOException e) {
            throw new RuntimeException(e);
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
