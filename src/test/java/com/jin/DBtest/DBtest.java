package com.jin.DBtest;

import static org.junit.Assert.fail;

import java.sql.Connection;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)		// �׽�Ʈ �� �����ϱ� ���� SpringJUnit4ClassRunner ����
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class DBtest {

		private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
		@Autowired		// DataSource dataSource = new DataSource(); ���� �����ϰ� ���ִ� ���
		private DataSource dataSource;
		@Autowired
		private SqlSessionFactory SqlSessionFactory;
		
		@Test
		public void testConnection() {
			try(Connection con = dataSource.getConnection()){
				logger.info("Ŀ�ؼ� Ǯ ���� : " + con );
			}catch(Exception e) {
				fail(e.getMessage());
			}
		}
		@Test
		public void testMyBatis() {
			try (SqlSession session = SqlSessionFactory.openSession();
					Connection con = dataSource.getConnection()){
				
				logger.info("mybatis ����" + session);
				logger.info("Ŀ�ؼ� Ǯ ����2" + con);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
}
