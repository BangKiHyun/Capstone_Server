package com.bang.ds_alcohol_project.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class PyUtill {


    public int execCommand(String name) {
        //실행시킬 Command 생성
        List<String> command = new ArrayList<>();

        //실행시킬 파일 명 추가
        command.add(name);

        //실행결과 저장 할 변수
        int result = 0;

        //Command 실행
        result = runCommand(command);

        switch (result) {
            case -1:
                log.info("실행 실패");
                break;
            default:
                log.info("실행 성공");
                break;
        }

        return result;
    }

    private static int runCommand(List<String> command) {

        int result = 0;
        ProcessBuilder pBuilder = new ProcessBuilder();

        //stdout과 stderr 구분 안함
        pBuilder.redirectErrorStream(true);

        pBuilder.command(command);

        BufferedReader buffReader = null;

        //출력 읽을 때 사용할 String
        String tmpLine = null;

        try {
            //프로세스 실행
            Process proc = pBuilder.start();

            //출력 버퍼 설정
            buffReader = new BufferedReader(new InputStreamReader(proc.getInputStream()));

            //tmpLine = buffReader.readLine();

            //log.info(tmpLine + " test");

            //프로세스 출력값 null 판별
//            while (tmpLine != null) {
//                log.info(tmpLine);
//            }
            //프로세스 끝날때까지 대기
            proc.waitFor();

            //프로세스가 끝날때 까지 대기
            result = proc.exitValue();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            result = -1;
        } finally {
            if (buffReader != null)
                try {
                    buffReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
        return result;
    }
}
