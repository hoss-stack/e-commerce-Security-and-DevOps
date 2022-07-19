package com.example.demo;

import com.example.demo.controllers.CartControllerTests;
import com.example.demo.controllers.ItemControllerTests;
import com.example.demo.controllers.OrderControllerTests;
import com.example.demo.controllers.UserControllerTests;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(Suite.class)
@Suite.SuiteClasses({
		CartControllerTests.class,
		ItemControllerTests.class,
		OrderControllerTests.class,
		UserControllerTests.class
})
@SpringBootTest
public class SareetaApplicationTests {

	@Test
	public void contextLoads() {
	}

}
