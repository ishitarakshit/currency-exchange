# currency-exchange


Conversion Table : 

| Source | Target | Date      | Rate  |
|--------|--------|-----------|-------|
|  USD   |  EUR   |08/22/2015 | 0.70  | 
|  USD   |  EUR   |08/20/2016 | 0.75  | 
|  USD   |  EUR   |02/19/2017 | 0.60  | 
|  USD   |  INR   |08/22/2015 | 45.00 | 
|  USD   |  INR   |08/21/2016 | 46.00 | 
|  USD   |  INR   |04/25/2017 | 47.00 | 

Example :
If we want to convert an amount of USD $30 to INR 

Using postman - 

Url : http://localhost:8080/currency-exchange/api/convert/30.00/USD/INR

Authorization - 
  
  Type: Basic Auth
  
  User: admin
  
  Password: admin

Json result : 
{"amount":1410.00,"currency":"INR"}
