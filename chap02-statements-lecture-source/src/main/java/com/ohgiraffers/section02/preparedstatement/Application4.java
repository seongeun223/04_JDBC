package com.ohgiraffers.section02.preparedstatement;

import com.ohgiraffers.model.dto.DepartmentDTO;
import com.ohgiraffers.model.dto.EmployeeDTO;

import java.sql.*;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application4 {

    public static void main(String[] args) {

        /*
        * Department 테이블의 부서코드(아이디)를 입력받아서
        * 부서코드(아이디), 부서명, 지역코드를 출력하시오.
        * */

        Connection con = getConnection();

        PreparedStatement pstmt = null;

        ResultSet rset = null;

        Scanner sc = new Scanner(System.in);
        System.out.println("조회할 부서코드를 입력하세요.");
        String dept_id = sc.nextLine();

        String query = "select * from department where dept_id = ?";

        DepartmentDTO selectedEmp = null;

        try {
            pstmt = con.prepareStatement(query);

            pstmt.setString(1, dept_id);

            rset = pstmt.executeQuery();

            if(rset.next()) {
                selectedEmp = new DepartmentDTO();
                selectedEmp.setDept_id(rset.getString("dept_id"));
                selectedEmp.setDept_title(rset.getString("dept_title"));
                selectedEmp.setLocation_id(rset.getString("location_id"));
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
