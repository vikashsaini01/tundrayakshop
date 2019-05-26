package com.yakshop.model;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import com.yakshop.util.Common;

public class LabYakTest {

	@Before
	public void before() {
	}

	@Test
	public void constructorShouldWorksForStringInputs() {
		Yak labYak = new LabYak("Yak-1", "1.4", "M");
		assertEquals("name doesnt match", "Yak-1", labYak.getName());
		assertEquals("Age doesnt match", Common.fixDecimals(new BigDecimal(1.4)), Common.fixDecimals(labYak.getAge()));
		assertEquals("Gender doesnt match", YakGender.M, labYak.getGender());
	}

	@Test
	public void testAddDaysToAge() {
		Yak labYak = new LabYak("Yak-1", new BigDecimal(1.4), YakGender.M);
		labYak.addDayToAge();
		assertEquals("Age doesnt match after add", Common.fixDecimals(new BigDecimal(1.41)),
				Common.fixDecimals(labYak.getAge()));
	}

	@Test
	public void testIsAlive() {
		Yak labYak = new LabYak("Yak-1", new BigDecimal(9.99), YakGender.M);
		assertEquals("is alive is wrong", true, labYak.isAlive());
		labYak.addDayToAge();
		assertEquals("is alive is wrong", false, labYak.isAlive());
	}

	@Test
	public void testIsProducingWool() {
		Yak labYak = new LabYak("Yak-1", new BigDecimal(0.99), YakGender.M);
		assertEquals("is producing wool is wrong", false, labYak.isProducingWool());
		labYak.addDayToAge();
		assertEquals("is producing wool is wrong", false, labYak.isProducingWool());
		labYak.addDayToAge();
		assertEquals("is producing wool is wrong", true, labYak.isProducingWool());
	}

	@Test
	public void testMilkZeroForGenderM() {
		Yak labYak = new LabYak("Yak-1", new BigDecimal(0.99), YakGender.M);
		assertEquals("Milk quantity", BigDecimal.ZERO, labYak.getMilkInLitres());
	}

	@Test
	public void testMilkQunatity() {
		Yak labYak = new LabYak("Yak-1", new BigDecimal(4), YakGender.F);
		assertEquals("Milk quantity", Common.fixDecimals(new BigDecimal(38)),
				Common.fixDecimals(labYak.getMilkInLitres()));
		labYak.addDayToAge();
		assertEquals("Milk quantity", Common.fixDecimals(new BigDecimal(37.970)),
				Common.fixDecimals(labYak.getMilkInLitres()));
	}

	@Test
	public void testWoolCountFor4YearOldAfter13Days() {
		Yak labYak = new LabYak("Yak-1", new BigDecimal(4), YakGender.F);
		assertEquals("no of wools is wrong", 0, labYak.getWools());
		for (int i = 0; i < 13; i++) {
			labYak.addDayToAge();
		}
		assertEquals("no of wools is wrong", 1, labYak.getWools());
	}
	
	@Test
	public void testToString() {
		Yak labYak = new LabYak("Yak-1", new BigDecimal(4), YakGender.F);
		assertEquals("LabYak toString value doesnt match", labYak.toString(), "	Yak-1 4.00 years old");
	}
}
