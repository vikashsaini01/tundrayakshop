package com.yakshop.util;

import java.math.BigDecimal;

public final class Common {
	private Common() {
	}

	/**
	 * Fix upto 3 decimals with rounding mode ROUND_HALF_EVEN
	 * @param number value to fix decimals for
	 * @return new BigDecimal value with decimals fixed
	 */
	public static final BigDecimal fixDecimals(BigDecimal number) {
		return number.setScale(3, BigDecimal.ROUND_HALF_EVEN);
	}
	
	/**
	 * Fix decimals for required numbers 
	 * 
	 * @param number value to fix decimals for, with rounding mode ROUND_HALF_EVEN
	 * @param i number of decimals to keep
	 * @return new BigDecimal value with decimals fixed
	 */
	public static final BigDecimal fixDecimals(BigDecimal number, int i) {
		return number.setScale(i, BigDecimal.ROUND_HALF_EVEN);
	}

}
