package com.yakshop.model;

import java.math.BigDecimal;

import com.yakshop.util.Common;

public class LabYak implements Yak {

	private String name;
	private BigDecimal age;
	private YakGender gender;
	private BigDecimal shavedOn;
	private final BigDecimal BD_100 = new BigDecimal(100);
	private final BigDecimal BD_50 = new BigDecimal(50);
	private final BigDecimal BD_8 = new BigDecimal(8);
	private final BigDecimal BD_0_03 = new BigDecimal(0.03);
	private final BigDecimal BD_0_01 = new BigDecimal(0.01);
	private final BigDecimal MAX_AGE = new BigDecimal(10); // yak dies at 10 years
	private final BigDecimal WOOL_AGE_IN_YEARS = new BigDecimal(1); // yak produce wool after age 1 years

	public LabYak(String name, BigDecimal age, YakGender gender) {
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.shavedOn = this.age.multiply(BD_100);
	}

	public LabYak(String name, String age, String gender) {
		this(name, new BigDecimal(age), YakGender.valueOf(gender.toUpperCase()));
	}

	public BigDecimal getAge() {
		return age;
	}

	public void addDayToAge() {
		this.age = this.age.add(BD_0_01);
	}

	public BigDecimal getAgeInDays() {
		return age.multiply(BD_100);
	}

	public YakGender getGender() {
		return gender;
	}

	public String getName() {
		return name;
	}

	public boolean isAlive() {
		return (this.age.compareTo(MAX_AGE) < 0);
	}

	/**
	 * checks if too young to give wool
	 */
	public boolean isProducingWool() {
		return (this.age.compareTo(WOOL_AGE_IN_YEARS) >= 0);
	}

	/**
	 * a LabYak produces 50-D*0.03 liters of milk (D = age in days)
	 */
	public BigDecimal getMilkInLitres() {
		if (isAlive() && this.gender == YakGender.F)
			return BD_50.subtract(getAgeInDays().multiply(BD_0_03));
		else
			return BigDecimal.ZERO;
	}

	/**
	 * At most every 8+D*0.01 days you can again shave a LabYak (D = age in days)
	 */
	public int getWools() {
		// Add one as on first day all are wooled
		BigDecimal requiredDelayProbableDays = Common
				.fixDecimals(BD_8.add(this.shavedOn.multiply(BD_0_01).add(BigDecimal.ONE)));
		if (isAlive() && isProducingWool() && shavedOn.add(requiredDelayProbableDays).compareTo(getAgeInDays()) <= 0) {
			shavedOn = getAgeInDays();
			return 1;
		}
		return 0;
	}

	@Override
	public String toString() {
		return "\t" + name + " " + Common.fixDecimals(age, 2) + " years old";
	}

}