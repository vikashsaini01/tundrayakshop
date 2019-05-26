package com.yakshop;

import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.yakshop.model.Yak;
import com.yakshop.model.YakProduce;

/**
 * Unit test for simple App.
 */
public class AppTest {

	@Test
	public void testAppConstructorAndDisplay() throws URISyntaxException {
		URL resource = this.getClass().getClassLoader().getResource("BasicHerd.xml");
		String path = Paths.get(resource.toURI()).toString();
		App app = new App(path);
		List<Yak> yaks = new ArrayList<Yak>();
		app.displayHerd(yaks);
		app.displayStock(new YakProduce(BigDecimal.ZERO, 0));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAppMainInvalid() throws URISyntaxException {
		App.main(null);
	}
}
