package com.yakshop.dataloader;

import static org.junit.Assert.fail;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.List;

import org.junit.Test;

import com.yakshop.model.Yak;

public class YakXmlLoaderImplNegativeTests {

	YakLoader yakLoader;
	Source source;
	
	
	
		
	@Test(expected = NumberFormatException.class)
	public void shouldThrowExceptionIfAgeNotValid() throws URISyntaxException {
		URL resource = YakXmlLoaderImplNegativeTests.class.getClassLoader().getResource("InvalidAgeHerd.xml");
		String path = Paths.get(resource.toURI()).toString();
		yakLoader =  new YakXmlLoaderImpl();
		source =  new Source.DefaultXmlSource(path);
		List<Yak> yaks = yakLoader.loadYaks(source);
		fail("Yak loading should have failed because of NumberFormatException");
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowExceptionIfGenderNotValid() throws URISyntaxException {
		URL resource = YakXmlLoaderImplNegativeTests.class.getClassLoader().getResource("InvalidGenderHerd.xml");
		String path = Paths.get(resource.toURI()).toString();
		yakLoader =  new YakXmlLoaderImpl();
		source =  new Source.DefaultXmlSource(path);
		List<Yak> yaks = yakLoader.loadYaks(source);
		fail("Yak loading should have failed because of IllegalArgumentException");
	}
	
	
}
