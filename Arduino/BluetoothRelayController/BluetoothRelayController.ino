/* Created by Daniel Coutts. https://github.com/DanielCoutts */

const int OUT[] = {3, 4, 5, 6, 7, 8, 9, 10};

const int ON = LOW;
const int OFF = HIGH;

char command;

void setup() {
  setUpOutputs();
  Serial.begin(9600);
}

void loop() {
  while (!Serial.available()); // Wait for serial

  command = Serial.read();
  executeCommand(command);
}

void executeCommand(char command) {

  switch(command) {
    case '0':
      turnOnSwitch(0);
      break;
    case '1':
      turnOnSwitch(1);
      break;
    case '2':
      turnOnSwitch(2);
      break;
    case '3':
      turnOnSwitch(3);
      break;
    case '4':
      turnOnSwitch(4);
      break;
    case '5':
      turnOnSwitch(5);
      break;
    case '6':
      turnOnSwitch(6);
      break;
    case '7':
      turnOnSwitch(7);
      break;
    case '8':
      turnOffSwitch(0);
      break;
    case '9':
      turnOffSwitch(1);
      break;
    case 'A':
      turnOffSwitch(2);
      break;
    case 'B':
      turnOffSwitch(3);
      break;
    case 'C':
      turnOffSwitch(4);
      break;
    case 'D':
      turnOffSwitch(5);
      break;
    case 'E':
      turnOffSwitch(6);
      break;
    case 'F':
      turnOffSwitch(7);
      break;
    case 'Z':
      delay(50);
      break;
  }
}

void setUpOutputs() {
  for(int i = 0; i < sizeof(OUT); i++) {
    pinMode(OUT[i], OUTPUT);
    turnOffSwitch(i);
  }
}

void turnOnSwitch(int switchNumber) {
  digitalWrite(OUT[switchNumber], ON);
}

void turnOffSwitch(int switchNumber) {
  digitalWrite(OUT[switchNumber], OFF);
}
