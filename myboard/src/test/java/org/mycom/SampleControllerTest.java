package org.mycom;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.mycom.SampleControllerTest;

/*
 * 컨트롤러 테스트
 * @WebAppConfiguration 쓰는 것이 기존 스프링 테스트와 다른 점
 * 가상의 요청과 응답을 처리하기 위해 before에서 MockMvc 객체를 만듬
 * testDoA() 에서 perform() 메소드를 실행해 get, post 방식을 호출함
 * pom.xml에 WAS없이 스프링MVC 테스트 위해 버전 변경 javax.servlet-api, 3.1.0
 */

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(
		locations ={"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class SampleControllerTest {
	
	private static final Logger logger = 
			LoggerFactory.getLogger(SampleControllerTest.class);

	@Inject
    private WebApplicationContext wac;

    private MockMvc mockMvc;
    
    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
        logger.info("setup............");
    }
    
    @Test
    public void testDoA() throws Exception{
    	mockMvc.perform(MockMvcRequestBuilders.get("/doA"));
    }

}
