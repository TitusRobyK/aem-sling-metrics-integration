package com.aem.sling.metrics.integration.core.service.impl;

import org.apache.sling.commons.metrics.Counter;
import org.apache.sling.commons.metrics.Histogram;
import org.apache.sling.commons.metrics.Meter;
import org.apache.sling.commons.metrics.MetricsService;
import org.apache.sling.commons.metrics.Timer;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.aem.sling.metrics.integration.core.service.CaptureMetricsService;

@Component(immediate = true, service = CaptureMetricsService.class)
public class CaptureMetricsServiceImpl implements CaptureMetricsService {

	@Reference
	private MetricsService metricsService;

	private Counter counter;

	private Meter meter;

	private Timer timer;

	private Histogram histogram;

	@Override
	public void captureMetrics(String className) {
		counter = metricsService.counter(className + "_Counter");
		counter.increment();
		timer = metricsService.timer(className + "_Timer");
		timer.time();
		meter = metricsService.meter(className + "_Meter");
		meter.mark(1);
		histogram = metricsService.histogram(className + "_Histogram");
		histogram.update(1);
	}

}
