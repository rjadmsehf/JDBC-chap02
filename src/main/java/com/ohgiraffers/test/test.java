package com.ohgiraffers.test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class test{
    public static void main(String[] args) {


        Connection con = getConnection();

        Statement stmt = null;

        ResultSet rset = null;

        try {
            stmt = con.createStatement();

            String empId ="202";
            String query = "SELECT EMP_ID , EMP_NAME FROM EMPLOYEE WHERE EMP_ID = '" + empId + "'";

            rset = stmt.executeQuery(query);

            if (rset.next()){
                System.out.println(rset.getString("EMP_ID") + " , "+ rset.getString("EMP_NAME"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {

            close(con);
            close(stmt);
            close(rset);
        }
    }
}
