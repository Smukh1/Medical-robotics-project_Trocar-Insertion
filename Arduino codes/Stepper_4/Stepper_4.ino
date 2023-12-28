#include <AccelStepper.h>

// Define the stepper motor connections
#define stepPin 3
#define dirPin 4

// Assuming your motor has 200 steps per revolution, adjust this based on your motor
#define STEPS_PER_REVOLUTION 200

// Create an instance of the AccelStepper class
AccelStepper stepper(1, stepPin, dirPin);

void setup() {
  // Set the maximum speed and acceleration
  stepper.setSpeed(200.0);
  stepper.setAcceleration(500.0);

  // Set the initial position to 0
  stepper.setCurrentPosition(0);
}

void loop() {
  // Rotate 3 complete revolutions clockwise
  stepper.moveTo(1 * STEPS_PER_REVOLUTION);
  while (stepper.distanceToGo() != 0) {
    stepper.runSpeedToPosition();
  }

  // Delay for 2 seconds
  delay(2000);

  // Rotate 3 complete revolutions counterclockwise
  stepper.moveTo(-1 * STEPS_PER_REVOLUTION);
  while (stepper.distanceToGo() != 0) {
    stepper.runSpeedToPosition();
  }

  // Hold the position with allowed holding torque
  stepper.disableOutputs();  // This disables the motor and holds its position
  delay(5000);  // Adjust the delay as needed

  // To resume movement or perform other actions, you can enable the outputs again
  //stepper.enableOutputs();
}
