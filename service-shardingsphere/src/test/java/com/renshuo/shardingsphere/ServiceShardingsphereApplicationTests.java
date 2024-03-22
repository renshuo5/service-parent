package com.renshuo.shardingsphere;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.renshuo.shardingsphere.course.domain.Course;
import com.renshuo.shardingsphere.course.mapper.CourseMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class ServiceShardingsphereApplicationTests {

	@Resource
	private CourseMapper courseMapper;

	@Test
	public void contextLoads() {
		for (int i = 0; i < 20; i++) {
			Course co = new Course();
//			co.setId(String.valueOf(i+1));
			co.setName("语文");
			co.setUserid(String.valueOf(i+1000));
			co.setStatus("1");
			courseMapper.insert(co);

		}



	}

	@Test
	public void queryCourse() {
		QueryWrapper qw = new QueryWrapper();
//		qw.eq("id",1669219855560007681l);
		qw.in("id", Arrays.asList(1669219855409012741l,1669219855409012737l,1669219855622922242l));
//		qw.between("id",1669219855262212099l,1669219855409012741l);
		List<Course> courses = courseMapper.selectList(qw);
		log.info(courses.size()+"----");
		courses.forEach(course -> {log.info(String.valueOf(course.getId()));});


	}

}
