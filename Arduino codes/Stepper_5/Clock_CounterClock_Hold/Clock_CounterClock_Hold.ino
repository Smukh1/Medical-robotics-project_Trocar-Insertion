#include <EEPROM.h>

// defines pins numbers
const int stepPin = 3; 
const int dirPin = 4; 

/*const int homePositionAddress = 0; // EEPROM address to store the home position

// Function declaration
void rotate(int steps, int stepDelay);
void homeMotor();*/

void setup() 
{
  //homeMotor(); //calling the homeMotor function to rotate the motor to the home position

  // Sets the two pins as Outputs
  pinMode(stepPin,OUTPUT); 
  pinMode(dirPin,OUTPUT);

  digitalWrite(dirPin,HIGH); // Enables the motor to move in a particular direction
  // Makes 200 pulses for making one full cycle rotation
  for(int x = 0; x < 600; x++) 
  {
    digitalWrite(stepPin,HIGH); 
    delayMicroseconds(5000); // 5 ms delay between steps
    digitalWrite(stepPin,LOW); 
    delayMicroseconds(5000); // 5 ms delay between steps
  }
  delay(2000); // two second delay
  
  digitalWrite(dirPin,LOW); //Changes the rotations direction
  // Makes 200 pulses for making two full cycle rotation
  for(int x = 0; x < 200; x++) 
  {
    digitalWrite(stepPin,HIGH);
    delayMicroseconds(5000); // 5 ms delay between steps
    digitalWrite(stepPin,LOW);
    delayMicroseconds(5000); // 5 ms delay between steps
  }
  delay(2000); // two second delay
}

void loop() 
{
// to keep the motor up and running(holding torque), but no spinning.
}

/*void rotate(int steps, int stepDelay) 
{
  // Pulse the step pin to move the motor
  for (int i = 0; i < steps; ++i) {
    digitalWrite(stepPin, HIGH);
    delayMicroseconds(stepDelay);
    digitalWrite(stepPin, LOW);
    delayMicroseconds(stepDelay);
  }
}

void homeMotor() 
{
  // Rotate the motor to the home position
  rotate(200, 1000); // Adjust steps and delay as needed for your setup

  // Save the current position to EEPROM as the home position
  int currentPosition = EEPROM.read(homePositionAddress);
  EEPROM.write(homePositionAddress, currentPosition + 200); // Adjust as needed

  // EEPROM.commit(); // Commit the changes to EEPROM (as in the current arduino version struct EEPROMClass' has no member named 'commit)
  // check it later

   //Using this alternate approach
  // Wait for the write to complete
  for (int i = 0; i < EEPROM.length(); i++) {
    if (EEPROM.read(i) != EEPROM.read(i)) {
      delay(10); // Adjust the delay if needed
    }
  }
}*/