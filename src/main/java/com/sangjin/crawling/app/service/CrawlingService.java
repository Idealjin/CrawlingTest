package com.sangjin.crawling.app.service;

import com.sangjin.crawling.app.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@AllArgsConstructor
@Service
public class CrawlingService {

    private static BookRepository bookRepository;

    @Transactional
    public static Object crawling() {

        //School_test에서 title,isbn 1개만 가져오기(네이버 api 하루 제한 수-25000-때문에)
        //SchoolTest schoolBookList = bookRepository.getSchoolBookList();

        String bookTitle = "나쁜 어린이 표";
        String base_url = "https://openapi.naver.com/v1/search/book_adv.json?d_titl=나쁜 어린이 표&display=1";
        StringBuilder sb = new StringBuilder();
        try {
            URL url = new URL(base_url);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("HOST", "openapi.naver.com");
            con.setRequestProperty("X-Naver-Client-Id", "XgTmsuF_h7cw9guw4TZ9");
            con.setRequestProperty("X-Naver-Client-Secret", "dJYEbql8FN");

            con.setDoOutput(true);
            System.out.println("에러시점 확인용");
            //데이터 읽어오기
            InputStream is = con.getInputStream();


            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line;

            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            con.disconnect();

            JSONObject result;
            result = (JSONObject) new JSONParser().parse(sb.toString());
            StringBuilder out = new StringBuilder();
            out.append(result.get("status") + " : " + result.get("status_message") + "\n");

            JSONObject title = (JSONObject) result.get("title");

            String printTest = " " + title;
            System.out.println(printTest);


        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static Object justTest() {
        System.out.println("Test Success");
        return "success";
    }
}

