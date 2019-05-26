package com.yakshop;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.yakshop.model.LabYak;
import com.yakshop.model.Yak;
import com.yakshop.model.YakProduce;
import com.yakshop.util.Common;

public class QueryYaksTest {

	QueryYaks queryYaks;

	@Before
	public void before() throws URISyntaxException {
		queryYaks = new QueryYaks();
	}

	@Test
	public void verifyProduceFor13Days() {
		List<Yak> yaks = new ArrayList<>();
		yaks.add(new LabYak("Betty-1", "4", "F"));
		yaks.add(new LabYak("Betty-2", "8", "F"));
		yaks.add(new LabYak("Betty-3", "9.5", "F"));
		YakProduce produceFor13Days = queryYaks.calculateInStockProduce(yaks, 13);
		assertEquals("Milk in litres doesn't match", Common.fixDecimals(new BigDecimal(1104.480)),
				produceFor13Days.getMilkInLitres());
		assertEquals("No Of wools doesn't match", 0, produceFor13Days.getNoOfWools());
	}

	@Test
	public void verifyProduceFor14Days() {
		List<Yak> yaks = new ArrayList<>();
		yaks.add(new LabYak("Betty-1", "4", "F"));
		yaks.add(new LabYak("Betty-2", "8", "F"));
		yaks.add(new LabYak("Betty-3", "9.5", "F"));
		YakProduce produceFor13Days = queryYaks.calculateInStockProduce(yaks, 14);
		assertEquals("Milk in litres doesn't match", Common.fixDecimals(new BigDecimal(1188.810)),
				produceFor13Days.getMilkInLitres());
		assertEquals("No Of wools doesn't match", 1, produceFor13Days.getNoOfWools());
	}
}
