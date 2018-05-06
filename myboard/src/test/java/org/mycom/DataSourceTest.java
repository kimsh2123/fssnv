package org.mycom;

import java.sql.Connection;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/*
 * DataSource TEST
 * @RunWith, @ContextConfiguration 스프링 로딩위해 필요
 * @Inject 은 스프링이 생성해서 주입하므로 개발자는 다른 작업 안해도 됨
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
	locations ={"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class DataSourceTest {

	@Inject
	private DataSource ds;
	
	@Test
	public void testConection()throws Exception{
		
		try(Connection con = ds.getConnection()){
			
			System.out.println(con);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}