package com.ohgiraffers.section02.preparedstatement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application02 {

    public static void main(String[] args) {

        /* 필기. 위치홀더 : (?) 에 대해 알 수 있다. */

        /* 필기.
            java 쿼리문은 파싱(번역)을 통해 컴파일 되어 DataBase 에서 쿼리를 수행해 결과 값을
            가지고 오는 구조
            Statement 는 SQL 문 실행 시 쿼리문을 전달하므로 , 매번 새로운 쿼리로 인식하기
            때문에 조건값에 따라 컴파일을 매번 새로 해야된다.
            PreparedStatement 는 조건 값을 ? 로 두고 쿼리를 준비 시킨 뒤
            쿼리는 변경하지 않고 바인딩 되는 변수만 바꿔서 조회해준다.
         */

        Connection con = getConnection();

        PreparedStatement pstmt = null;

        ResultSet rset = null;

        String empId = "200"; // 스캐너로 받아도딤 내생각에 ?

        try {
            pstmt = con.prepareStatement("SELECT EMP_ID , EMP_NAME FROM EMPLOYEE WHERE EMP_ID = ?");

            pstmt.setString(1, empId);

            rset = pstmt.executeQuery();

            if (rset.next()) {

                System.out.println(rset.getString("EMP_ID") + " , " + rset.getString("EMP_NAME"));
            }else {
                System.out.println("없는 사원번호 입니다");
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(pstmt);
            close(con);
            close(rset);
        }


    }
}
