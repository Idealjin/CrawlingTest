package com.sangjin.crawling.app.app.service;

import com.sangjin.crawling.app.app.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

@AllArgsConstructor
@Service
public class CrawlingService {

    private static BookRepository bookRepository;


    public static Object crawling() {

        //School_test에서 title,isbn 1개만 가져오기(네이버 api 하루 제한 수-25000-때문에)
        //SchoolTest schoolBookList = bookRepository.getSchoolBookList();


        StringBuilder sb = new StringBuilder();
        try {
            String bookTitle = "나쁜 어린이 표";
            bookTitle = bookTitle.replace(" ", "");
            bookTitle = URLEncoder.encode(bookTitle, "utf-8");
            String base_url = "https://openapi.naver.com/v1/search/book_adv.json?d_titl="+ bookTitle +"&display=1";
            System.out.println(base_url);
            URL url = new URL(base_url);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("X-Naver-Client-Id", "ID");
            con.setRequestProperty("X-Naver-Client-Secret", "PW");

            con.setDoOutput(true);
            System.out.println("에러시점 확인용");
            //데이터 읽어오기
            if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
                System.out.println("HTTP_OK 확인");
                BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));

                String line;

                while ((line = br.readLine()) != null) {
                    sb.append(line);
                    System.out.println("Line 확인");
                }
                br.close();
                con.disconnect();
            } else{
                System.err.println(con.getResponseMessage());
            }
            JSONObject result;
            System.out.println("여기는 오나");

            result = (JSONObject) new JSONParser().parse(sb.toString());
            StringBuilder out = new StringBuilder();
            out.append(result.get("status") + " : " + result.get("status_message"));

            JSONArray items = (JSONArray) result.get("items");
            //JSONArray에서 title,isbn 추출
            JSONObject item = (JSONObject) items.get(0);
            String title = (String) item.get("title");
            String isbn = (String) item.get("isbn");
            String printTest = " " + items;
            System.out.println(printTest);
            System.out.println(title);
            System.out.println(isbn);



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

