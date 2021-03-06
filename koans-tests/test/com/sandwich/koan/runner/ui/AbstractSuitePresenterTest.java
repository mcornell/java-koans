package com.sandwich.koan.runner.ui;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import com.sandwich.koan.KoanSuiteResult;
import com.sandwich.koan.KoanSuiteResult.KoanResultBuilder;
import com.sandwich.koan.suite.OneFailingKoan;
import com.sandwich.koan.ui.AbstractSuitePresenter;

public class AbstractSuitePresenterTest {

	@Test
	public void testForwardingOneHundredPercentSuccess() throws Exception {
		final int state[] = new int[]{0};
		AbstractSuitePresenter presenter = new AbstractSuitePresenter() {
			public void displayAllSuccess(KoanSuiteResult result) {
				assertEquals(0, state[0]);
				state[0] = 1;
			}
			public void displayChart(KoanSuiteResult result) {
				assertEquals(1, state[0]);
				state[0] = 2;
			}
			public void displayPassingFailing(KoanSuiteResult result) {
				assertEquals(2, state[0]);
				state[0] = 3;
			}
			public void displayHeader(KoanSuiteResult result) {
				assertEquals(3, state[0]);
				state[0] = 4;
			}
			public void displayOneOrMoreFailure(KoanSuiteResult result) {
				fail();
			}
		};

		KoanSuiteResult kr = new KoanResultBuilder().build();
		presenter.displayResult(kr);
		assertEquals(4, state[0]);
	}
	
	@Test
	public void testForwardingOneOrMoreFails() throws Exception {
		final int state[] = new int[]{0};
		AbstractSuitePresenter presenter = new AbstractSuitePresenter() {
			public void displayOneOrMoreFailure(KoanSuiteResult result) {
				assertEquals(0, state[0]);
				state[0] = 1;
			}
			public void displayChart(KoanSuiteResult result) {
				assertEquals(1, state[0]);
				state[0] = 2;
			}
			public void displayPassingFailing(KoanSuiteResult result) {
				assertEquals(2, state[0]);
				state[0] = 3;
			}
			public void displayHeader(KoanSuiteResult result) {
				assertEquals(3, state[0]);
				state[0] = 4;
			}
			public void displayAllSuccess(KoanSuiteResult result) {
				fail();
			}
		};

		KoanSuiteResult kr = new KoanResultBuilder().failingCase(OneFailingKoan.class).build();
		presenter.displayResult(kr);
		assertEquals(4, state[0]);
	}
}
