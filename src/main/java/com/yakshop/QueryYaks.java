package com.yakshop;

import java.math.BigDecimal;
import java.util.List;

import com.yakshop.model.Yak;
import com.yakshop.model.YakProduce;
import com.yakshop.util.Common;

public class QueryYaks {

	/**
	 * Takes list of yak as input and calculate total produce for the days passed
	 * @param yaks List of yaks
	 * @param daysElapsed
	 * @return YakProduce
	 */
	public YakProduce calculateInStockProduce(List<Yak> yaks, int daysElapsed) {
		BigDecimal totalMilk = BigDecimal.ZERO;
		int totalWools = 0;
		for (int i = 0; i < daysElapsed; i++) {
			YakProduce daysProduce = yaks.stream().map(yak -> {
				BigDecimal milkInLitres = yak.getMilkInLitres();
				int noOfWools = yak.getWools();
				yak.addDayToAge();
				return (new YakProduce(milkInLitres, noOfWools));
			}).reduce(new YakProduce(BigDecimal.ZERO, 0), (YakProduce subTotalDaysProduce, YakProduce element) -> {
				subTotalDaysProduce
						.setMilkInLitres(subTotalDaysProduce.getMilkInLitres().add(element.getMilkInLitres()));
				subTotalDaysProduce.setNoOfWools(subTotalDaysProduce.getNoOfWools() + element.getNoOfWools());
				return subTotalDaysProduce;
			});

			totalMilk = totalMilk.add(daysProduce.getMilkInLitres());
			totalWools += daysProduce.getNoOfWools();
		}
		totalMilk = Common.fixDecimals(totalMilk);
		return new YakProduce(totalMilk, totalWools);
	}
}
