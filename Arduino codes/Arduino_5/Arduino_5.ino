#include <EEPROM.h>

const int stepPin = 3;
const int dirPin = 4;
const int homePositionAddress = 0; // EEPROM address to store the home position

// Function declarations
void rotate(int steps, int stepDelay);
void homeMotor();

void setup() {
  // Set the step and direction pins as OUTPUT using direct port manipulation
  pinMode(stepPin, OUTPUT);
  pinMode(dirPin, OUTPUT);

  // Set initial direction (clockwise)
  digitalWrite(dirPin, HIGH);

  // Uncomment the following line if you want to set the home position during setup
  // setAndSaveHomePosition();

  // Uncomment the following line if you want to perform homing at the start of each loop
  // homeMotor();
}

void loop() {
  // Perform homing at the start of each loop
  homeMotor();

  // Rotate 3 full revolutions clockwise
  rotate(600, 1000); // 600 steps for 3 full revolutions, 1000 µs delay between steps

  // Delay for 1 second
  delay(1000);

  // Change direction to counterclockwise
  digitalWrite(dirPin, LOW);
  delay(10); // Small delay to ensure proper direction change
  digitalWrite(stepPin, LOW); // Stop the motor briefly

  // Delay for 1 second
  delay(1000);

  // Rotate 1 full revolution counterclockwise
  rotate(200, 1000);

  // Delay for 1 second
  delay(1000);

  // Stop the motor by keeping both pins low
  digitalWrite(stepPin, LOW);
  digitalWrite(dirPin, LOW);

  // You can add additional code here if needed
}

void rotate(int steps, int stepDelay) {
  // Pulse the step pin to move the motor
  for (int i = 0; i < steps; ++i) {
    digitalWrite(stepPin, HIGH);
    delayMicroseconds(stepDelay);
    digitalWrite(stepPin, LOW);
    delayMicroseconds(stepDelay);
  }
}

void homeMotor() {
  // Rotate the motor to the home position
  rotate(200, 1000); // Adjust steps and delay as needed for your setup

  // Save the current position to EEPROM as the home position
  int currentPosition = EEPROM.read(homePositionAddress);
  EEPROM.write(homePositionAddress, currentPosition + 200); // Adjust as needed

  // Wait for the write to complete
  for (int i = 0; i < EEPROM.length(); i++) {
    if (EEPROM.read(i) != EEPROM.read(i)) {
      delay(10); // Adjust the delay if needed
    }
  }
}
