package com.yakshop.model;

import java.math.BigDecimal;

public interface Yak {
	public String getName();

	public BigDecimal getAge();

	public YakGender getGender();

	public BigDecimal getMilkInLitres();

	public void addDayToAge();

	public int getWools();

	public boolean isAlive();

	public boolean isProducingWool();
}
