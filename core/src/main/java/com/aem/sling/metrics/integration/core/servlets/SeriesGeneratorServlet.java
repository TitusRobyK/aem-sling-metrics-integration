package com.aem.sling.metrics.integration.core.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.servlets.ServletResolverConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.aem.sling.metrics.integration.core.service.CaptureMetricsService;
import com.aem.sling.metrics.integration.core.service.SeriesGeneratorService;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;

@Component(service = Servlet.class, property = { ServletResolverConstants.SLING_SERVLET_PATHS + "=/bin/series",
		ServletResolverConstants.SLING_SERVLET_METHODS + "=" + HttpConstants.METHOD_GET })
public class SeriesGeneratorServlet extends SlingAllMethodsServlet {

	private static final long serialVersionUID = 1L;

	int firstNumber;
	int secondNumber;
	int limit;

	@Reference
	private CaptureMetricsService captureMetricsService;
	
	@Reference
	private SeriesGeneratorService seriesGenerateService;

	@Override
	protected void doGet(final SlingHttpServletRequest request, final SlingHttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<Integer> seriesLs = new ArrayList<Integer>();
		captureMetricsService.captureMetrics(this.getClass().getName());
		try {

			firstNumber = verifyAndSetParameter(request, "firstNumber", 0);
			secondNumber = verifyAndSetParameter(request, "secondNumber", 1);
			limit = verifyAndSetParameter(request, "limit", 500);
			seriesLs = seriesGenerateService.gererateSeries(firstNumber, secondNumber, limit);
		} catch (NumberFormatException e) {
			response.getWriter().write("Parameter passed is not an Integer ");
			response.setStatus(422);
		}
		String responseString = seriesLs.stream().map(Object::toString).collect(Collectors.joining(", "));
		response.setStatus(200);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(responseString);
	}

	private int verifyAndSetParameter(SlingHttpServletRequest request, String parameterName, int setValue)
			throws NumberFormatException {
		if (null != request.getParameter(parameterName))
			return Integer.parseInt(request.getParameter(parameterName));
		else {
			return setValue;
		}
	}

}
