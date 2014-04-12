#include <MeetAndroid.h>
#include <Servo.h>
//#include <SPI.h>
#include <NewPing.h>

#define M_F 0b00001010
#define M_B 0b00000101
#define M_L 0b00001001
#define M_R 0b00000110
#define MOTOR_L 0
#define MOTOR_R 1
#define MAX_COMMAND 40
/**
 * Sona define 
 **/
#define TRIGGER_PIN  10  // Arduino pin tied to trigger pin on the ultrasonic sensor.
#define ECHO_PIN     9  // Arduino pin tied to echo pin on the ultrasonic sensor.
#define MAX_DISTANCE 200 // Maximum distance we want to ping for (in centimeters). Maximum sensor distance is rated at 400-500cm.
byte sona_Trig = 10;
byte sonaEcho = 9;
NewPing sonar(TRIGGER_PIN, ECHO_PIN, MAX_DISTANCE); // NewPing setup of pins and maximum distance.

/**
 * define for servo motor
 **/
byte servoPin = 5;
Servo myServo;

/**
 *  define for Shield Motor 298 of chipfc
 **/
byte dirData = 8;
byte dirClk =4;
byte dirLat =12;
byte dirEn =7;
byte pwmA =6;  // can be 9, 6, 11
byte pwmB =3;  // can be 5, 10, 3

/**
 *  define for MeetAndroid 
 **/
MeetAndroid android;

//byte dirControl;
byte inSpeed;
byte lastDistance;
long startTime;
byte command[2][MAX_COMMAND];
byte indexCommand = 0;
byte runningState = 0;
byte runFlag =0;
/**
 *  Setup Function
 **/
void setup(){
//+  initServo;
myServo.attach(servoPin);
  initMotor();
  initComunication();
  inSpeed = 150;
  startTime = millis();
  android.registerFunction(start,'B');
  android.registerFunction(forward, 'W');
  android.registerFunction(backward, 'S');
  android.registerFunction(left, 'A');
  android.registerFunction(right, 'D');
  android.registerFunction(stand, 'P');
  android.registerFunction(stoprun, 'Y');
  android.registerFunction(search, 'O');
  android.registerFunction(sSpeed, 'I');
  //    android.registerFunction(test, 'Q')
}

/**
 *  Loop function
 **/
void loop(){ 
  processRun();
  android.receive();
  //  getCommandSerial();
  delay(10);
}

void processRun(){
  unsigned int uS = sonar.ping();
  unsigned distance = uS/US_ROUNDTRIP_CM;
  if(runFlag>0){
    if(distance <= 20 && distance >5 ){  // Running and meet obstacle
      run(M_B,inSpeed, 1000);
      run(M_R,inSpeed, 2000);
    }
    else{
      if(runningState <= indexCommand){
        if(command[1][runningState]>0){
          command[1][runningState] --;
          run(command[0][runningState], inSpeed,1000 );//* command[1][indexCommand]);
        }
        else runningState++;
      }
      else runFlag = 0;
    }
  }
}




