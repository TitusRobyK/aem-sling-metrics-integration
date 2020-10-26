### AEM & Sling Metrics integaration

Simple project showcasing AEM &amp; [Sling-metrics](https://sling.apache.org/documentation/bundles/metrics.html) integration. Sling Metrics bundle provides integration with [Dropwizard Metrics](https://github.com/dropwizard/metrics) library which provides a toolkit to capture runtime performance statistics in your application.

### Steps

* Get the latest aem archetype version from the [Adobe-AEM Archetype Repo](https://github.com/adobe/aem-project-archetype). At the time of creation of this project, Command that has been used to create this project is 
```
mvn -B archetype:generate \
 -D archetypeGroupId=com.adobe.aem \
 -D archetypeArtifactId=aem-project-archetype \
 -D archetypeVersion=24 \
 -D appTitle="AEM Sling Metrics Integration" \
 -D appId="aem-sling-metrics-integration" \
 -D groupId="com.aem.sling.metrics.integration" \
 -D amp="n" \
 -D aemVersion=6.5.0 \
 -D includeDispatcherConfig=n \
 -D frontendModule="none" \
 -D includeCommerce=n \
 -D datalayer=n
 ```
* Run the following maven command to successfully download the dependencies and to deploy the same in to the local aem 6.5 instance
```
mvn clean install -PautoInstallPackage -Padobe-public
```
* In the pom.xml , add the following dependencies
```
<dependency>
    <groupId>org.apache.sling</groupId>
    <artifactId>org.apache.sling.commons.metrics</artifactId>
    <version>1.0.0</version>
</dependency>
```
