package com.ohgiraffers.section01.statement;

import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application01 {

    public static void main(String[] args) {

        /* 목표 Statement 에 대해 이해하고 사용할 수 있다. */

        /* 필기
            Statement 란?
            SQL문을 저장하고 실행한 뒤 결과를 받아 주는
            메소드들이 묶여있는 타입의 클래스
         */

        Connection con = getConnection();

        /* 필기 쿼리문을 저장하고 실행하는 기능을 하는 용도의 인터페이스 */
        Statement stmt = null;

        /* 필기. select 조회의 결과 집합을 받아 올 용도의 인터페이스 */
        ResultSet rset = null;

        try {
            /* 필기 connection을 이용해서 statement 인스턴스 생성 */
            stmt = con.createStatement();

            /* 필기 executeQuery() 메소드를 호출해서 SQL문을 수행(SQL문을 STring 형태로 전달)*/
            rset = stmt.executeQuery("SELECT EMP_ID, EMP_NAME FROM EMPLOYEE");

            while (rset.next()) {

                /* 필기. next() : Rseultset 의 커서 위치를 하나씩 내리면 행이 존재하면 true*/
                System.out.println(rset.getString("EMP_ID") + " , " + rset.getString("EMP_NAME"));
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
