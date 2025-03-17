Vehicle Monitoring Application:

The Vehicle Monitoring Application allows for the registration, issue logging, and deactivation of vehicles and its events. 
It provides ability to monitor the health status of vehicles and their associated faults with the created time.

Few Key features below, 
1. Registering a New Vehicle:  
       A new vehicle can be registered with an initial fault count set to zero.

2.Logging an Issue: 
       A vehicle’s faults can be updated when an issue is logged used kafka to log a issue by

3. Deactivating the Vehicle: 
      Deactivates a vehicle, including the deactivation of any issues associated with that vehicle.

   --------------------------------------------------------------------------------------------------------------------

1. Registering a vehicle :-
---------------------------

The request body contains details of the vehicle to be registered, including the vehicle's name, model, VRN and its health status.

POST URL - http://localhost:8082/vehicles/create
{
    "name": "Vehicle20",
    "model": "MODEL2",
    "vrn": "VRN2",
    "vehicleHealthStatus": "OVER_HEATING"
}

returns a below response
{
    "id": 7,
    "name": "Vehicle20",
    "model": "MODEL2",
    "vrn": "VRN2",
    "vehicleHealthStatus": "OVER_HEATING",
    "vehicleStatus": true,
    "numberOfFaults": 0,
    "vehicleEvents": null
} 

Upon registering a new vehicle, a unique vehicleId is generated, VehicleStatus is set to true indicating that the vehicle is active .The initial fault cout is set to 0.
Since no event would be associated with the vehicle initially, Event list would be null


------------------------------------------------------------------------------------------------------------------------------------------------------------------------


2. Logging an Issue / Registering a vehicle event for the vehicle
-----------------------------------------------------------------

To log an issue for a vehicle, 

The vehicle issue is processed asynchronously using a Kafka producer which publishes the issue to a Kafka topic. The backend consumes the event, logs the issue and processes the fault description and health status.
A response is captured using a CompletableFuture to ensure that the frontend receives the response asynchronously.

Also, it updates the status of the vehicle with the most recent status issue looged and number of faults gets updated.

POST URL: http://localhost:8082/vehicle-events/create

{
    "vehicle":
    {
        "id": 2
    },    
    "vehicleEventHealthStatus": "OTHER",
    "faultDescription": "its been a while newwww to check"
}

response below,

{
    "vehicleEventId": 7,
    "vehicleId": 2,
    "eventStatus": true,
    "vehicleHealthStatus": "OTHER",
    "faultDescription": "its been a while newwww to check",
    "localDateTime": "2025-03-17T01:08:00.04964"
}

Flow:


Initiate Request : 

A POST request is sent to the /vehicle-events/create endpoint with the  information about the vehicle and the fault description.

Kafka producer: 

The request is sent to a Kafka producer, which publishes the issue details to a Kafka topic. 
This ensures that the logging process is handled asynchronously and that the vehicle event is captured by the backend.

Backend process:

The backend consumes the Kafka message, logs the issue, and captures the response in a CompletableFuture.

Response to Frontend:
Once the backend has processed the event, it sends the response back to the frontend with the vehicle event details,
including the unique vehicleEventId, the vehicleId, the status of the event (eventStatus), 
the health status of the vehicle (vehicleHealthStatus), and the fault description and redirected back to the vehicle events list page.

------------------------------------------------------------------------------------------------------------------------------------------------------------------



3. Deactivating a vehicle:-
----------------------------

To deactivate a vehicle, deactivates a vehicle in details page, that marks the vehicle as inactive, also deactivates any issues associated with the vehicle.

PATCH URL : http://localhost:8082/vehicles/deactivate/6

{
    "id": 6
}

returns the response

{
    "vehicleId": 6,
    "name": "testtt",
    "model": "tettttt",
    "vrn": "vrnnnn",
    "vehicleStatus": false,
    "vehicleHealthStatus": "AIR_CONDITIONING_FAULT",
    "vehicleEventsList": [

{
    {
            "vehicleEventId": 6,
            "vehicleId": 6,
            "eventStatus": false,
            "vehicleHealthStatus": "NEEDS_MAINTENANCE",
            "faultDescription": "testtttttttttttttttttttttt",
            "localDateTime": "2025-03-16T22:40:22.648175"
        },
        {
            "vehicleEventId": 8,
            "vehicleId": 6,
            "eventStatus": false,
            "vehicleHealthStatus": "AIR_CONDITIONING_FAULT",
            "faultDescription": "ssdfsdf",
            "localDateTime": "2025-03-17T01:39:11.851716"
        }
    ],
    "numberOfFaults": 2
}

-------------------------------------------------------------------------------------------------------------------------------


TO FETCH THE VEHICLES LIST to load the vehicles when the page is loading 

GET : http://localhost:8082/vehicles/

request body : 

{
    "name": "Vehicle20",
    "model": "MODEL2",
    "vrn": "VRN2",
    "vehicleHealthStatus": "OVER_HEATING"
}


response would be

[
    {
        "vehicleId": 1,
        "name": "Vehicle1",
        "model": "MODEL1",
        "vrn": "VRN1",
        "vehicleStatus": false,
        "vehicleHealthStatus": "ENGINE_FAULT",
        "vehicleEventsList": [],
        "numberOfFaults": 3
    },
    {
        "vehicleId": 2,
        "name": "Vehicle2",
        "model": "MODEL2",
        "vrn": "VRN2",
        "vehicleStatus": false,
        "vehicleHealthStatus": "OTHER",
        "vehicleEventsList": [

        {
            {
                "vehicleEventId": 7,
                "vehicleId": 2,
                "eventStatus": true,
                "vehicleHealthStatus": "OTHER",
                "faultDescription": "its been a while newwww to check",
                "localDateTime": "2025-03-17T01:08:00.04964"
            }
        ],
        "numberOfFaults": 2
    },
    {
        "vehicleId": 3,
        "name": "Vehicle3",
        "model": "MODEL3",
        "vrn": "VRN3",
        "vehicleStatus": false,
        "vehicleHealthStatus": "OTHER",
        "vehicleEventsList": [],
        "numberOfFaults": 1
    },
    {
        "vehicleId": 4,
        "name": "Vehicle4",
        "model": "MODEL4",
        "vrn": "VRN4",
        "vehicleStatus": true,
        "vehicleHealthStatus": "BREAK_SYSTEM_FAULT",
        "vehicleEventsList": [],
        "numberOfFaults": 0
    },
    {
        "vehicleId": 5,
        "name": "name",
        "model": "model",
        "vrn": "vrn",
        "vehicleStatus": true,
        "vehicleHealthStatus": "ENGINE_FAULT",
        "vehicleEventsList": [],
        "numberOfFaults": 0
    },
    {
        "vehicleId": 6,
        "name": "testtt",
        "model": "tettttt",
        "vrn": "vrnnnn",
        "vehicleStatus": false,
        "vehicleHealthStatus": "AIR_CONDITIONING_FAULT",
        "vehicleEventsList": [],
        "numberOfFaults": 2
    },
    {
        "vehicleId": 7,
        "name": "Vehicle20",
        "model": "MODEL2",
        "vrn": "VRN2",
        "vehicleStatus": true,
        "vehicleHealthStatus": "OVER_HEATING",
        "vehicleEventsList": [],
        "numberOfFaults": 0
    }
]


----------------------------------------------------- 

TO FETCH THE VEHICLE EVENTS  LIST to load while the page is loading

GET - GET : http://localhost:8082/vehicle-events/

{
    "vehicle":
    {
        "id": 2
    },
    
    "vehicleEventHealthStatus": "OTHER",
    "faultDescription": "its been a while newwww to check"
}

respone is stored with the created time

{
    "vehicleEventId": 9,
    "vehicleId": 2,
    "eventStatus": true,
    "vehicleHealthStatus": "OTHER",
    "faultDescription": "its been a while newwww to check",
    "localDateTime": "2025-03-17T02:03:25.329169"
}

-----------------------------------------------------------------------------------------------------------



