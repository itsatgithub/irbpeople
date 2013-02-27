package utils;

import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import bussineslogic.controlers.UseCaseUtils;

public class TestDateRanges {

	Date[] rango1;
	Date[] rango2;

	Date[] rango3;
	Date[] rango4;

	Date[] rangoA;
	Date[] rangoB;

	Date[] rangoA2;
	Date[] rangoB2;

	@Before
	public void setUp() {
		rango1 = new Date[] { new GregorianCalendar(2009, 1, 28).getTime(), new GregorianCalendar(2009, 2, 15).getTime() };
		rango2 = new Date[] { new GregorianCalendar(2009, 2, 16).getTime(), new GregorianCalendar(2009, 2, 20).getTime() };

		rango3 = new Date[] { new GregorianCalendar(2010, 1, 28).getTime(), new GregorianCalendar(2010, 2, 15).getTime() };
		rango4 = new Date[] { new GregorianCalendar(2009, 2, 16).getTime(), new GregorianCalendar(2009, 2, 20).getTime() };

		rangoA = new Date[] { new GregorianCalendar(2010, 1, 28).getTime(), new GregorianCalendar(2010, 2, 15).getTime() };
		rangoB = new Date[] { new GregorianCalendar(2010, 2, 14).getTime(), new GregorianCalendar(2010, 2, 20).getTime() };

		rangoA2 = new Date[] { new GregorianCalendar(2010, 1, 28).getTime(), new GregorianCalendar(2010, 2, 15).getTime() };
		rangoB2 = new Date[] { new GregorianCalendar(2010, 2, 15).getTime(), new GregorianCalendar(2010, 2, 20).getTime() };
	}

	@Test
	public void rangosNoSeSolapan() {

		Assert.assertFalse(UseCaseUtils.dateRangesOverlap(rango1, rango2));
	}

	@Test
	public void rangosNoSeSolapanInverso() {
		Assert.assertFalse(UseCaseUtils.dateRangesOverlap(rango2, rango1));
	}

	@Test
	public void rangosNoSeSolapanDifAños() {
		Assert.assertFalse(UseCaseUtils.dateRangesOverlap(rango3, rango4));
	}

	@Test
	public void rangosNoSeSolapanDifAñosInverso() {
		Assert.assertFalse(UseCaseUtils.dateRangesOverlap(rango4, rango3));
	}

	@Test
	public void rangosSonElMismo() {
		Assert.assertTrue(UseCaseUtils.dateRangesOverlap(rango2, rango2));
	}

	@Test
	public void rangosSeSolapan() {
		Assert.assertTrue(UseCaseUtils.dateRangesOverlap(rangoA, rangoB));
	}

	@Test
	public void rangosSeSolapanInverso() {
		Assert.assertTrue(UseCaseUtils.dateRangesOverlap(rangoB, rangoA));
	}

	@Test
	public void rangosSeSolapanUltimoDia() {
		Assert.assertTrue(UseCaseUtils.dateRangesOverlap(rangoA2, rangoB2));
	}

	@Test
	public void rangosSeSolapanUltimoDiaInverso() {
		Assert.assertTrue(UseCaseUtils.dateRangesOverlap(rangoB2, rangoA2));
	}

	@Test
	public void combinarRangosAyB() {
		Date[] nuevoRango = UseCaseUtils.mergeDateRanges(rangoA, rangoB);

		Assert.assertEquals(new Date[] { rangoA[0], rangoB[1] }, nuevoRango);
	}

	@Test
	public void combinarRangosA2yB2() {
		Date[] nuevoRango = UseCaseUtils.mergeDateRanges(rangoB2, rangoA2);

		Assert.assertEquals(new Date[] { rangoA2[0], rangoB2[1] }, nuevoRango);
	}
}
