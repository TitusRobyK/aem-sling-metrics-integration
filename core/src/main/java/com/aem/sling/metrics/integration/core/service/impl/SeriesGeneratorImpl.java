package com.aem.sling.metrics.integration.core.service.impl;

import java.util.ArrayList;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.aem.sling.metrics.integration.core.service.CaptureMetricsService;
import com.aem.sling.metrics.integration.core.service.SeriesGeneratorService;

@Component(immediate = true, service = SeriesGeneratorService.class)
public class SeriesGeneratorImpl implements SeriesGeneratorService {

	@Reference
	private CaptureMetricsService captureMetricsService;

	@Override
	public ArrayList<Integer> gererateSeries(int firstNumber, int secondNumber, int limit) {

		captureMetricsService.captureMetrics(this.getClass().getName());

		firstNumber = verifyAndSetParameter(firstNumber, Integer.MAX_VALUE / 2, 0);
		secondNumber = verifyAndSetParameter(secondNumber, Integer.MAX_VALUE / 2, 1);
		limit = verifyAndSetParameter(limit, 500, 500);

		ArrayList<Integer> seriesList = new ArrayList<Integer>();
		seriesList.add(firstNumber);
		seriesList.add(secondNumber);
		for (int i = 1; i <= limit; i++) {
			int thirdNumber = firstNumber + secondNumber;
			if (thirdNumber >= Integer.MAX_VALUE / 2) {
				break;
			}
			firstNumber = secondNumber;
			secondNumber = thirdNumber;
			seriesList.add(thirdNumber);
		}
		return seriesList;
	}

	private int verifyAndSetParameter(int number, int maxValue, int setValue) {
		if (number >= maxValue && number >= 0) {
			return setValue;
		} else {
			return number;
		}
	}
}
