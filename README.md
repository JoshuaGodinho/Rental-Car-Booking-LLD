# Vehicle rental Service-LLD Design

Features:
Rental service have multiple branches throughout the city.
Each branch has limited number of different kinds of vehicles. 
Each vehicle can be booked with predefined price per unit time slot. For simplicity, current pricing model does not support dynamic pricing or update on prices based on seasonality.
Each vehicle can be booked in multiples of 1 hour time slot.
All bookings should be made before the start time of particular booking.

Requirements:
Onboard a new branch with available vehicle.
Onboard new vehicle(s) of existing type to a particular branch.
Rent vehicle for a time slot and a vehicle type (lowest price as the default choice of selection of vehicle, this should be extendable to any other strategy). While booking a vehicle if availability is not there, then it should fallback to another available branch, which is derived based on the vehicle selection strategy.
A system view should be made available, such as currently booked vehicles, available vehicles of all the branches.
 
![image](https://github.com/user-attachments/assets/f18ec8af-044d-4333-ba87-a565e43ea2c5)
