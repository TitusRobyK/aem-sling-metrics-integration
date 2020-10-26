package com.aem.sling.metrics.integration.core.service;

import java.util.ArrayList;

public interface SeriesGeneratorService {
	
	ArrayList<Integer> gererateSeries(int firstNumber,int secondNumber,int limit);

}
