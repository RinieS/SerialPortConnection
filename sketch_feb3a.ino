#include <microDS18B20.h> 

MicroDS18B20 <A0> ds;
void setup() {
  // put your setup code here, to run once:
  Serial.begin(9600);
}

void loop() {
  // put your main code here, to run repeatedly:

  ds.requestTemp();
  
  if (ds.readTemp()){
    Serial.println(ds.getTemp());
    delay(1000);
  }
}
