Traffic Signal System
---------------------

#Requirements
------------
- Signal System at an Intersection(4 Way Junction)
- Cycle through the signals in a clock wise order
- Give way to Emergency Vehicles
- Signal Duration can be configured
- Each signal can have different durations
- Each signal Should have a pedestrian crossing signal
- Signal system has three states: Red, Yellow, Green

#Identify Entities
-----------------
- Signal (RED, YELLOW, GREEN)
- Pedestrian Signal (WALK, DONT WALK)
- Signal State - NORMAL, EMERGENCY, PEDESTRIAN
- Intersection
    - List of traffic lights
    - Signal Map with Id
- Signal Configuration
  - Duration
  - Signal Id


#Visualise Flow
---------------
Create Intersection - Add Signals to Intersection - Configure Signal Duration - Configure cycle

Normal FLow -> Cycle as per signal Cycle configured with duration
Phase - RED-> GREEN -> YELLOW -> RED

Cycle Pause - Remember current signal and duration left - Give way to Emergency Vehicle - Resume Cycle

Can configure green signal duration


#Classes Structure & Relationships
----------------------------------
Controller Layer(entry point)
    - Traffic Signal Controller
        - 


Service Layer


Domain Layer