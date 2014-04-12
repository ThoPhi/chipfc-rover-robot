
void initComunication(){
  Serial.begin(9600);
  //  Serial.println("Begin");
}

void getCommandSerial(){
  if(Serial.available()){
    byte tmp = Serial.read();    
    switch(tmp){
    case 'w':
      Serial.println("^");
      run(M_F, inSpeed, 2000);      
      break;
    case 'a':
      run(M_L, inSpeed, 2000);
      Serial.println("<");
      break;
    case 's':
      run(M_B, inSpeed, 2000);
      Serial.println("J");
      break;
    case 'd':
      run(M_R, inSpeed, 2000);
      Serial.println(">");
      break;
    case '0' ...'9':
      tmp = tmp - '0';    
      tmp = map(tmp,0,9,0,255);
      inSpeed = tmp;
      Serial.println(tmp);
      break;
    case 'u':
      myServo.write(0);
      break;
    case 'i':
      myServo.write(90);
      break;
    case 'o':
      myServo.write(179);
      break;
    }
  }
}

void start(byte flag, byte numOfValues){
  int v = android.getInt();  
  //  android.send(v);  
  runFlag =1;
}

void forward(byte flag, byte numOfValues){
  int v = android.getInt();
  //  android.send(v);  
  if(indexCommand<MAX_COMMAND) indexCommand++;
  command[0][indexCommand] = M_F;
  command[1][indexCommand] = v;
}

void backward(byte flag, byte numOfValues){
  int v = android.getInt();
  //  android.send(v);  
  if(indexCommand<MAX_COMMAND) indexCommand++;
  command[0][indexCommand] = M_B;
  command[1][indexCommand] = v;
}

void left(byte flag, byte numOfValues){
  int v = android.getInt();
  //  android.send(v);  
  if(indexCommand<MAX_COMMAND) indexCommand++;
  command[0][indexCommand] = M_L;
  command[1][indexCommand] = v;
}

void right(byte flag, byte numOfValues){
  int v = android.getInt();
  //  android.send(v);  
  if(indexCommand<20) indexCommand++;
  command[0][indexCommand] = M_R;
  command[1][indexCommand] = v;
}

void stand(byte flag, byte numOfValues){
  int v = android.getInt();
  //  android.send(v);  
  //    if(indexCommand<MAX_COMMAND) indexCommand++;
  //  command[0][indexCommand] = M_F;
  //  command[1][indexCommand] = v;
  runFlag = 0;
}

void search(byte flag, byte numOfValues){
  int v = android.getInt();
  android.send(v);  
  if(v<=9  && v>=0){
    v= map(v,0,9,0,179);
    myServo.write(v);  
  }
}

void stoprun(byte flag, byte numOfValues){
  int v = android.getInt();
  android.send(v);  
  for(int i = 0;i<MAX_COMMAND;i++){
    command[0][i] = 0;
    command[1][i] = 0;  
  }
  runFlag = 0;
}

void sSpeed(byte flag, byte numOfValues){
  int v = android.getInt();
  android.send(v);  
  if(v>0 && v<=255) inSpeed = v;
}


