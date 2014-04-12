

void initMotor(){
 pinMode(dirData,OUTPUT);
  pinMode(dirClk,OUTPUT);
  pinMode(dirLat,OUTPUT);
  pinMode(dirEn,OUTPUT);
  digitalWrite(dirEn, LOW);
  pinMode(pwmA,OUTPUT);
  pinMode(pwmB,OUTPUT);
}

void run(byte dir, byte runSpeed, unsigned int distance ){
  digitalWrite(dirEn,LOW); 
  digitalWrite(dirLat, LOW);
  shiftOut(dirData, dirClk, MSBFIRST, dir);
  digitalWrite(dirLat, HIGH);
  analogWrite(pwmA,runSpeed);
  analogWrite(pwmB,runSpeed);
  delay(distance);
  analogWrite(pwmA,0);
  analogWrite(pwmB,0);
}
