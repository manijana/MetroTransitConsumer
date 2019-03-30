MetroTransit API Consumer
=========================

### About metrotransit.org web services

* The NexTrip API is a real-time transit departure data web service for third-party application developers using Metro Transit information.
* NexTrip departure information updates every 30 seconds. Help conserve our bandwidth and server resources by writing your applications responsibly. Third party applications should not update departure information more frequently than every 30 seconds. Applications making excessive calls and updating more frequently than 30 seconds will be subject to restriction. 
* There are seven requests with four different response schema.

#### Features of MetroTransit API Consumer:

* It's a Mule application and will expose REST URI that will be used to find next bus from current route to destination bus stop.

* This API will be used metrotransit.org web services that to retrieve data from MetroTransit and return simple format of JSON/XML based on the client request.
* It will expect totally three parameters of JSON format which are BUS_ROUTE, BUS_STOP_NAME and DIRECTION.
* Here, the first parameter BUS_ROUTE basically our current location. i.e, That means which route are we standing for bus.
* Second one is BUS_STOP_NAME that destination location. i.e, location of target place.
* Third one is DIRECTION, which the third party application like GoogleMap will calculate the direction from source to destination.

#### Set up:

In order to use this library without changing anything, you need the following server:
* Mule Enterprise Server (Paid) or Mule Community Server(Free ware, but limited features available. Eg. We can't use this code for production deployment, need to pass app.env as either dev or prod when we deploy in Mule server. This available only in MEE, not MCE which is free one.)

In case of change the existing code, you may require following tools,
* Anypoint Studio 5.4.0 (Download from https://mule-studio.s3.amazonaws.com/5.4.0-NOV15/AnypointStudio-for-win-64bit-5.4.0-201511301152.zip and extract it).
* Mule Enterprise Server Or Mule Standalone Server(https://developer.mulesoft.com/download-mule-esb-runtime - Download Version 3.7.0 and zip file if your OS is Windows)
* Maven >= V3.3.3

1. Download and extract ZIP file.
2. Make sure you installed JDK, JAVA_HOME env setup and path variable setup.
3. Click on the Anypoint Studio exe and select any directory as your workspace.
4. Download this application and import it via Import -> Anypoint Studio (Anypoint Studio Project from External Location)
5. Choose your downloaded project root and check "Copy project into workspace" check box.
6. Now, wait for sometime and once downloaded all the Mule dependencies. 
7. Right click on your project root and "File -> Run As -> Mule Application with Maven". While running pass one arguments in your configuration like as "-Dapp.env=dev" which is used to select dynamic environment.
8. Right now only two environment properties are available such as dev and prod.


#### How do I run the example locally?
```xml
1. Navigate to the project's root and start your application.
2. Open any REST client(POSTMan) and pass below,
	1. REST Consumer URI(Response will be JSON): http://localhost:9999/Metro/NextBus (Or) http://localhost:9999/Metro/NextBus?format=json
	
		Method: POST
		
		Content Type: application/json
		
		Payload: 
			{
				"BUS_ROUTE" : "Brooklyn Ctr - Fremont - 26th Av - Chicago - MOA",
				"BUS_STOP_NAME" : "44th Ave  and Fremont Ave ",
				"DIRECTION" : "north"
			}
			
		Response:
			{
				"NextBus": "13 Min"
			}
			
	2. REST Consumer URI(Response will be XML): http://localhost:9999/Metro/NextBus?format=xml
	
		Method: POST
		
		Content Type: application/json
		
		Payload: 
			{
				"BUS_ROUTE" : "Brooklyn Ctr - Fremont - 26th Av - Chicago - MOA",
				"BUS_STOP_NAME" : "44th Ave  and Fremont Ave ",
				"DIRECTION" : "north"
			}
			
		Response:
			
			<linked-hash-map>
				<entry>
					<string>NextBus</string>
					<string>13 Min</string>
				</entry>
			</linked-hash-map>

			
	3. REST Consumer URI(Empty Response): http://localhost:9999/Metro/NextBus
	
		Method: POST
		
		Content Type: application/json
		
		Payload: 
			{
			"BUS_ROUTE" : "METRO Blue Line",
			"BUS_STOP_NAME" : "Target Field Station Platform 1",
			"DIRECTION" : "south"
			}
			
		Response:
			{}

	4. REST API for refresh Routes in Storage: GET http://localhost:9999/Metro/RefereshRoutes
	
		Response: After refreshed with Storage, will return back as JSON below,
			[
				{"Description":"METRO Blue Line","ProviderID":"8","Route":"901"},
				{"Description":"METRO Green Line","ProviderID":"8","Route":"902"},
				.
				.
			]

```
#### What people think of it:

> We use this Mule application service, whenever want to consume metro transit from you current location to destination.
>
> -- Manikandan Janarthanan, ApexIT India Pvt Ltd, Java / Integration Developer.
