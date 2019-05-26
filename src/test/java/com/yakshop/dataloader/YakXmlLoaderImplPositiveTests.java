package com.yakshop.dataloader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.yakshop.model.Yak;
import com.yakshop.model.YakGender;

public class YakXmlLoaderImplPositiveTests {

	YakLoader yakLoader;
	Source source;
	
	@Before
	public void before() throws URISyntaxException {
		URL resource = YakXmlLoaderImplPositiveTests.class.getClassLoader().getResource("BasicHerd.xml");
		String path = Paths.get(resource.toURI()).toString();
		yakLoader =  new YakXmlLoaderImpl();
		source =  new Source.DefaultXmlSource(path);
	}
	
	@Test
	public void shouldLoadYaks() {
		List<Yak> yaks = yakLoader.loadYaks(source);
		assertNotNull(yaks);
		assertEquals("First yak name not equals", yaks.get(0).getName(), "Betty-1");
		assertEquals("First yak name not equals", yaks.get(0).getAge(), new BigDecimal("4"));
		assertEquals("First yak name not equals", yaks.get(0).getGender(), YakGender.F);
		
		assertEquals("Third yak name not equals", yaks.get(2).getName(), "Betty-3");
		assertEquals("Third yak name not equals", yaks.get(2).getAge(), new BigDecimal("9.5"));
		assertEquals("Third yak name not equals", yaks.get(2).getGender(), YakGender.F);
		
	}	
}
