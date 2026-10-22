/* =======================================================
   DỰ ÁN: HỘP BẢO MẬT SIÊU THÔNG MINH - WEMOS D1 (V2.0 PRO)
   Đã tối ưu: Tiết kiệm điện chân D0/D8, Giảm độ nhạy Siêu âm, Mượt Keypad
   ======================================================= */

#include <Wire.h>
#include <LiquidCrystal_I2C.h>
#include <Keypad_I2C.h>
#include <Adafruit_Fingerprint.h>
#include <SoftwareSerial.h>
#include <Servo.h>
#include <EEPROM.h>

// --------------------------------------------------------
// KHAI BÁO CHÂN (Định dạng chuẩn WeMos D1 R2)
// --------------------------------------------------------
#define FINGER_RX   D5  
#define FINGER_TX   D6  
#define SERVO_PIN   D7  
#define ALARM_PIN   D4  // Còi & LED Đỏ (Bắt buộc Active-Low)
#define TRIG_PIN    D8  
#define ECHO_PIN    D0  
#define SUCCESS_LED D3  // LED Xanh (Bắt buộc Active-Low)

LiquidCrystal_I2C lcd(0x27, 16, 2); 

const byte ROWS = 4; 
const byte COLS = 4; 
char keys[ROWS][COLS] = {
  {'1','2','3','A'},
  {'4','5','6','B'},
  {'7','8','9','C'},
  {'*','0','#','D'}
};
byte rowPins[ROWS] = {0, 1, 2, 3}; 
byte colPins[COLS] = {4, 5, 6, 7}; 
int keypad_i2c_address = 0x20;     

Keypad_I2C customKeypad(makeKeymap(keys), rowPins, colPins, ROWS, COLS, keypad_i2c_address);

SoftwareSerial mySerial(FINGER_RX, FINGER_TX);
Adafruit_Fingerprint finger = Adafruit_Fingerprint(&mySerial);
Servo boxServo; 

// Các biến trạng thái thông minh
String correctPassword = ""; 
String inputPassword = "";
long duration;
int distance = 999;
int wrongAttempts = 0;      
bool isBoxOpen = false;     
unsigned long openTime = 0; 

// === BIẾN TỐI ƯU TIẾT KIỆM ĐIỆN & CHỐNG NHIỄU ===
unsigned long lastPingTime = 0;
const unsigned long pingInterval = 500;  // Đo khoảng cách mỗi 500ms (Giảm hao điện chân D0/D8)
unsigned long lastPersonTime = 0;        // Thời gian cuối cùng phát hiện có người đứng trước hộp

// Biến chống nhấp nháy màn hình (0: Chờ/An toàn, 1: Có người/Nhập mã, 2: Hộp đang mở)
int lastDisplayState = -1; 

void setup() {
  Serial.begin(115200); 
  Wire.begin(D2, D1); // Cấu hình chuẩn SDA=D2, SCL=D1 cho ESP8266

  EEPROM.begin(32);
  loadPasswordFromEEPROM();
  
  pinMode(ALARM_PIN, OUTPUT);
  pinMode(SUCCESS_LED, OUTPUT);
  pinMode(TRIG_PIN, OUTPUT);
  pinMode(ECHO_PIN, INPUT);
  
  // Thiết lập mức HIGH ban đầu để TẮT thiết bị Active-Low lúc khởi động
  digitalWrite(ALARM_PIN, HIGH);
  digitalWrite(SUCCESS_LED, HIGH);

  boxServo.attach(SERVO_PIN);
  boxServo.write(0); // Trạng thái KHÓA ban đầu

  lcd.init();
  lcd.backlight();
  lcd.setCursor(0, 0);
  lcd.print("Hop Bao Mat V2");
  lcd.setCursor(0, 1);
  lcd.print("Dang khoi dong..");

  customKeypad.begin();
  finger.begin(57600);
  delay(5);
  
  if (!finger.verifyPassword()) {
    lcd.clear();
    lcd.setCursor(0, 0);
    lcd.print("Loi Van Tay!");
    delay(2000);
  }
  lcd.clear();
}

void loop() {
  // TRƯỜNG HỢP 1: NẾU HỘP ĐANG MỞ -> Chờ người dùng khóa lại
  if (isBoxOpen) {
    if (lastDisplayState != 2) {
      lcd.clear();
      lcd.setCursor(0, 0);
      lcd.print("Hop dang mo!     ");
      lcd.setCursor(0, 1);
      lcd.print("Bam * de khoa   ");
      lastDisplayState = 2; 
    }

    char key = customKeypad.getKey();
    if (key == '*' || (millis() - openTime > 30000)) {
      lockBox();
    }
    return; 
  }

  // TRƯỜNG HỢP 2: NẾU HỘP ĐANG KHÓA -> Kiểm tra siêu âm theo chu kỳ định sẵn
  if (millis() - lastPingTime >= pingInterval) {
    lastPingTime = millis();
    distance = getDistance(); // Gọi hàm đo khoảng cách đã tối ưu
  }
  
  // Logic kiểm tra khoảng cách an toàn
  if (distance > 0 && distance < 50) {
    lastPersonTime = millis(); // Cập nhật liên tục thời gian có người đứng trước hộp

    if (lastDisplayState != 1) {
      lcd.clear();
      lcd.setCursor(0, 0);
      lcd.print("Quet VT hoac nhap");
      lcd.setCursor(0, 1);
      lcd.print("Ma: ");
      lastDisplayState = 1;
    }

    int fingerID = getFingerprintID();
    char key = customKeypad.getKey();

    // Xử lý Vân tay
    if (fingerID > 0) { 
      grantAccess("Van tay hop le!");
    } 
    else if (fingerID == -2) { 
      denyAccess("Van tay sai!");
    }

    // Xử lý Bàn phím
    if (key) {
      if (key == '#') { 
        if (inputPassword == correctPassword) {
          grantAccess("Mat khau dung!");
        } else {
          denyAccess("Sai mat khau!");
        }
        inputPassword = ""; 
        lastDisplayState = -1; 
      } 
      else if (key == '*') { 
        inputPassword = "";
        lcd.setCursor(4, 1);
        lcd.print("            ");
        lcd.setCursor(4, 1);
      }
      else if (key == 'A') { 
        inputPassword = "";
        changePasswordRoutine();
        lastDisplayState = -1; 
      }
      else if (key >= '0' && key <= '9') { 
        if (inputPassword.length() < 6) { 
          inputPassword += key;
          lcd.setCursor(4 + (inputPassword.length() - 1), 1);
          lcd.print("*");
        }
      }
    }
  } 
  else {
    // Không có ai ở gần hộp - Chỉ thực sự reset nếu đã quá 3 giây (tránh cảm biến bị chập chờn)
    if (millis() - lastPersonTime > 3000) {
      if (lastDisplayState != 0) {
        lcd.clear();
        lcd.setCursor(0, 0);
        lcd.print("Hop dang khoa   ");
        lcd.setCursor(0, 1);
        lcd.print("An toan         ");
        lastDisplayState = 0;
      }
      inputPassword = ""; 
      wrongAttempts = 0; 
    }
  }
  
  delay(20); // Giảm delay tổng xuống 20ms để các phím bấm nhạy bén hơn nữa
}

// ========================================================
// CÁC HÀM XỬ LÝ NÂNG CAO
// ========================================================

// HÀM ĐO KHOẢNG CÁCH ĐÃ ĐƯỢC TỐI ƯU HÓA TIẾT KIỆM ĐIỆN
int getDistance() {
  digitalWrite(TRIG_PIN, LOW);
  delayMicroseconds(2);
  digitalWrite(TRIG_PIN, HIGH);
  delayMicroseconds(10);
  digitalWrite(TRIG_PIN, LOW);
  
  // TỐI ƯU: Giảm timeout từ 30000 xuống 3000 microgiây (giới hạn đo khoảng ~50cm)
  // Chân ECHO sẽ dừng lắng nghe sớm, tiết kiệm cực kỳ nhiều điện năng
  duration = pulseIn(ECHO_PIN, HIGH, 3000); 
  
  if (duration == 0) return 999; // Trả về khoảng cách lớn nếu không có ai ở gần (quá timeout)
  return duration * 0.034 / 2; 
}

void grantAccess(String message) {
  wrongAttempts = 0; 
  lcd.clear();
  lcd.setCursor(0, 0);
  lcd.print(message);
  lcd.setCursor(0, 1);
  lcd.print("Dang mo chot...");
  
  digitalWrite(SUCCESS_LED, LOW); 
  boxServo.write(90); 
  delay(1500);
  
  digitalWrite(SUCCESS_LED, HIGH);
  isBoxOpen = true;
  openTime = millis(); 
  lastDisplayState = -1;
  lcd.clear();
}

void lockBox() {
  lcd.clear();
  lcd.setCursor(0, 0);
  lcd.print("Dang khoa hop...");
  boxServo.write(0); 
  delay(1500);
  isBoxOpen = false;
  inputPassword = "";
  lastDisplayState = -1; 
  distance = 999; // Reset khoảng cách ảo để tính toán lại chu kỳ mới
  lcd.clear();
}

void denyAccess(String message) {
  wrongAttempts++;
  lcd.clear();
  lcd.setCursor(0, 0);
  lcd.print(message);
  lcd.setCursor(0, 1);
  lcd.print("Sai lan: ");
  lcd.print(wrongAttempts);
  
  for(int i=0; i<3; i++) { 
    digitalWrite(ALARM_PIN, LOW);
    delay(100);
    digitalWrite(ALARM_PIN, HIGH);
    delay(100);
  }
  
  if (wrongAttempts >= 3) {
    lcd.clear();
    lcd.setCursor(0, 0);
    lcd.print("KHOA CHONG TROM!");
    
    digitalWrite(ALARM_PIN, LOW);
    for(int i = 30; i > 0; i--) {
      lcd.setCursor(0, 1);
      lcd.print("Mo lai sau: ");
      lcd.print(i);
      lcd.print("s ");
      delay(1000); 
    }
    digitalWrite(ALARM_PIN, HIGH); 
    wrongAttempts = 0; 
  }
  lastDisplayState = -1; 
  lcd.clear();
}

void changePasswordRoutine() {
  lcd.clear();
  lcd.setCursor(0, 0);
  lcd.print("Doi Mat Khau");
  lcd.setCursor(0, 1);
  lcd.print("Pass cu: ");
  
  String oldP = getMenuInput();
  if (oldP == correctPassword) {
    lcd.clear();
    lcd.setCursor(0, 0);
    lcd.print("Nhap Pass Moi");
    lcd.setCursor(0, 1);
    lcd.print("=> ");
    
    String newP = getMenuInput();
    if (newP.length() > 0) {
      correctPassword = newP;
      for(int i=0; i<newP.length(); i++) {
        EEPROM.write(i, newP[i]);
      }
      EEPROM.write(newP.length(), '\0'); 
      EEPROM.commit();
      
      lcd.clear();
      lcd.setCursor(0, 0);
      lcd.print("Thanh cong!");
      delay(2000);
    }
  } else {
    lcd.clear();
    lcd.setCursor(0, 0);
    lcd.print("Sai Pass Cu!");
    delay(2000);
  }
  lcd.clear();
}

String getMenuInput() {
  String temp = "";
  while(true) {
    char k = customKeypad.getKey();
    if (k) {
      if (k == '#') return temp;
      if (k == '*') { 
        temp = ""; 
        lcd.setCursor(9, 1); 
        lcd.print("       "); 
        lcd.setCursor(9, 1); 
      }
      else if (k >= '0' && k <= '9' && temp.length() < 6) {
        temp += k;
        lcd.print('*');
      }
    }
    yield(); 
  }
}

void loadPasswordFromEEPROM() {
  correctPassword = "";
  for(int i=0; i<6; i++) {
    char c = EEPROM.read(i);
    if (c == '\0') break; 
    if (c >= '0' && c <= '9') {
      correctPassword += c;
    }
  }
  if (correctPassword.length() < 1) {
    correctPassword = "1234"; 
  }
}

int getFingerprintID() {
  uint8_t p = finger.getImage();
  if (p != FINGERPRINT_OK)  return -1; 
  
  p = finger.image2Tz();
  if (p != FINGERPRINT_OK)  return -1; 
  
  p = finger.fingerFastSearch();
  if (p != FINGERPRINT_OK)  return -2; 
  
  return finger.fingerID; 
}