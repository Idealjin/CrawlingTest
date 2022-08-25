package com.sangjin.crawling.app.service;

import com.sangjin.crawling.app.app.CrwalingApplication;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest(classes = CrwalingApplication.class)
class CrwalingServiceTest {

	@Test
	void StringSum() {
		//given
		String bookTitle = "나쁜 어린이 표";
		String url = "https://openapi.naver.com/v1/search/book_adv.json?d_titl=";

		//when
		bookTitle = bookTitle.replace(" ", "");
		url = url + bookTitle + "&display=1";

		//then
		Assertions.assertThat(bookTitle).isEqualTo("나쁜어린이표");
		Assertions.assertThat(url).isEqualTo("https://openapi.naver.com/v1/search/book_adv.json?d_titl=나쁜어린이표&display=1");
	}

}
