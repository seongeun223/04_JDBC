package com.ohgiraffers.common;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class ClassTemplate {
    public static Connection getConnection() {

        Connection con = null;

        Properties prop = new Properties();

        try {
            prop.load(new FileReader("src/main/java/com/ohgiraffers/config/connection-class.properties"));


            // key-value로 원하는 값 찾아오기
            String driver = prop.getProperty("driver");
            String url = prop.getProperty("url");
            String user = prop.getProperty("user");
            String password = prop.getProperty("password");

            // 사용할 드라이버 목록
            Class.forName(driver);

            // DriverManger를 통해서 Connection 객체 생성
            con = DriverManager.getConnection(url, prop);


        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);


        }
        return con;
    }
    public static void close(Connection con) {
        try {
            if (con != null && con.isClosed()) {

                con.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void close(Statement stmt) {
        try {
            if (stmt != null && !stmt.isClosed()) {

                stmt.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public static void close(ResultSet rset) {
        try {
            if (rset != null && !rset.isClosed()) {

                rset.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}