package com.ohgiraffers.section02.preparedstatement;

import com.ohgiraffers.model.dto.EmployeeDTO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application04 {

    public static void main(String[] args) {
        Connection con = getConnection();

        PreparedStatement pstmt = null;

        ResultSet rset = null;

        EmployeeDTO row = null;    // 필기 1명의 회원 정보를 담겠다

        List<EmployeeDTO> empList = null;

        Scanner sc = new Scanner(System.in);
        System.out.print("조회할 이름의 성을 입력하세요 :  ");
        String empName =sc.nextLine();

//        String query = "SELECT * FROM EMPLOYEE WHERE EMP_NAME LIKE CONCAT(?, '%')";
        Properties prop = new Properties();

        try {
            try {
                prop.loadFromXML(
                        new FileInputStream("src/main/java/com/ohgiraffers/section02/preparedstatement/employee-query.xml")
                );
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            String query = prop.getProperty("selectEmpByFamilyName");

            pstmt = con.prepareStatement(query);

            pstmt.setString(1,empName);     //필기 2 번이면 중간이름?

            rset =  pstmt.executeQuery();

            empList = new ArrayList<>();

            while (rset.next()){

                row = new EmployeeDTO();
                row.setEmpId(rset.getString("EMP_ID"));
                row.setEmpName(rset.getString("EMP_NAME"));
                row.setEmpNo(rset.getString("EMP_NO"));
                row.setEmail(rset.getString("EMAIL"));
                row.setPhone(rset.getString("PHONE"));
                row.setDeptCode(rset.getString("DEPT_CODE"));
                row.setJobCode(rset.getString("JOB_CODE"));
                row.setSalLevel(rset.getString("SAL_LEVEL"));
                row.setSalary(rset.getInt("SALARY"));
                row.setBonus(rset.getDouble("BONUS"));
                row.setManagerId(rset.getString("MANAGER_ID"));
                row.setHireDate(rset.getDate("HIRE_DATE"));
                row.setEntDate(rset.getDate("ENT_DATE"));
                row.setEntYn(rset.getString("ENT_YN"));

                empList.add(row);

            }




        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(con);
            close(rset);
            close(pstmt);
        }

        for (EmployeeDTO emp : empList){
            System.out.println("emp = " + emp);
        }

    }
}
