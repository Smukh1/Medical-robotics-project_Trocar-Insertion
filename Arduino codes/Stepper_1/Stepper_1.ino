/*     Simple Stepper Motor Control Exaple Code
 *      
 *  by Dejan Nedelkovski, www.HowToMechatronics.com
 *  
 */

// defines pins numbers
const int stepPin = 3; 
const int dirPin = 4; 
 
void setup() 
{
  // Sets the two pins as Outputs
  pinMode(stepPin,OUTPUT); 
  pinMode(dirPin,OUTPUT);
}
void loop() 
{
  digitalWrite(dirPin,HIGH); // Enables the motor to move in a particular direction
  // Makes 200 pulses for making one full cycle rotation
  for(int x = 0; x < 200; x++) 
  {
    digitalWrite(stepPin,HIGH); 
    delayMicroseconds(3000); 
    digitalWrite(stepPin,LOW); 
    delayMicroseconds(3000); 
  }
  delay(2000); // One second delay
  
  /*digitalWrite(dirPin,HIGH); //Changes the rotations direction
  // Makes 400 pulses for making two full cycle rotation
  for(int x = 0; x < 200; x++) 
  {
    digitalWrite(stepPin,LOW);
    delayMicroseconds(5000);
    digitalWrite(stepPin,LOW);
    delayMicroseconds(5000);
  }
  delay(2000);*/

 // Hold the position with allowed holding torque
  //disableOutputs();  // This disables the motor and holds its position
  //delay(5000);  // Adjust the delay as needed

  // To resume movement or perform other actions, you can enable the outputs again
  //stepper.enableOutputs();
}
