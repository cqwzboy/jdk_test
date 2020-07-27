package com.code.fuqinqin.jd_biz;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.io.*;

/**
 * 进线中台开发环境2 的sql变更
 *
 * @author fuqinqin3
 * @date 2020-07-27
 * */
public class JtalkIncomingDev2SqlUpdateTest {

    @Test
    public void sqlUpdate() throws IOException {
        String sqlPath = "F:\\工作\\2020\\7\\27 开发环境2相关脚本\\jtalk_incoming_dev2.sql";
        String newSqlPath = "F:\\工作\\2020\\7\\27 开发环境2相关脚本\\new_jtalk_incoming_dev2.sql";
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(sqlPath))));
        String content = "";
        StringBuilder sb = new StringBuilder();
        while ((content=br.readLine()) != null){
            if(StringUtils.isBlank(content)){
                continue;
            }
            sb.append(content.replaceAll("COLLATE utf8_bin ", "")).append("\r\n");
        }
        System.out.println(sb.toString());
        PrintWriter writer = new PrintWriter(new FileOutputStream(new File(newSqlPath)));
        writer.write(sb.toString());
        writer.flush();
    }

}
