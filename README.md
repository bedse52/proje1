# E-Ticaret Uygulaması (Spring Boot)

Bu uygulama temel sepet ve sipariş işlemlerini yönetebilen bir e-ticaret backend'idir. Spring Boot kullanılarak geliştirilmiştir.

Kullanılan Teknolojiler

- Java 24
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Lombok
  
## API Endpointleri

### Customer İşlemleri
-`POST /rest/apicustomer/save`: Yeni kullanıcı oluşturur. Buna bağlı olarak  kullanıcı ile ilişkili sepeti de oluşturur.
  - Request Body:
    ```json
    {
    "name" : "Ela Erdoğan"
    }
    ```
  - ![customer](https://github.com/user-attachments/assets/30dffc32-b69e-4631-b6dc-c1962cebc453)
  - ![cart](https://github.com/user-attachments/assets/9dc862af-4793-4042-be5b-def141ee168e)
### Product İşlemleri
- `POST /rest/apiproduct/save`: Yeni ürün oluşturur.
  - Request Body:
    ```json
    {
    "name" : "Atkı",
    "price" : 299.99,
    "stock" :  50
    }
    ```
  - ![product](https://github.com/user-attachments/assets/8ed23274-9b7c-4bc2-8591-a26abd3511f1)
  - ![price history](https://github.com/user-attachments/assets/c7c8eaeb-3a57-4c98-a899-26763e4a0938)

    
- `GET /rest/apiproduct/list`: Ürün listesini getirir sonuna `/{productId}` eklenirse spesifik ürün gelir.
- `PUT /rest/api/product/update/{productId}`: "Ürün güncellenir.
  - Request Body:
    ```json
     {
    "name" : "Atkı",
    "price" : 199.99,
    "stock" :  100
     }
    ```
  - ![product](https://github.com/user-attachments/assets/b7a428a7-ec52-450b-b542-64aae4e35a74)
  - ![pricehistory](https://github.com/user-attachments/assets/9a2327e6-2a9b-4412-bffc-9c68b6a6afe3)
     
- `DELETE /rest/api/product/delete/{productId}`: Ürün silinir.
  - ![product](https://github.com/user-attachments/assets/d736a147-b922-472e-a249-a39d164324f2)
  - ![price history](https://github.com/user-attachments/assets/f28465ae-9557-43fc-bd65-03c00c7f799e)
  
### Cart İşlemleri
- `GET /rest/api/cart/{customerId}`: Kullanıcının sepetini getirir.
- `POST /rest/api/cart/add/{customerId}`: Sepete ürün ekler.
  - Request Body:
    ```json
    {
    "productId" : 4,
    "quantity" : 3
    }
    ```
    - ![cart](https://github.com/user-attachments/assets/721f5ff3-5f35-4698-9d4a-bdbcdfcb9b37)
    - ![incart](https://github.com/user-attachments/assets/81ac5c9f-9f48-4097-8c9f-0f6337fd3640)
    
- `PUT /rest/api/cart/update/{customerId}`:  Sepette toplam fiyat-ürün uyuşmazlığı olursa giderir, ürünlerin fiyatı değişmiş ise sepetteki fiyatları günceller.
  - ![cart](https://github.com/user-attachments/assets/68e6d686-094d-496a-92ca-3a2928909331)
    
- `PATCH /rest/api/cart/remove/{customerId}`: Girilen "quantity"miktarına göre ürünü sepetten siler veya ürünün miktarını azaltır.
  - ![incart](https://github.com/user-attachments/assets/9c1acd0a-3612-4a8d-b5f4-6613521298ae)

  - Request Body:
    ```json
    {
      "productId": 4,
      "quantity": 1
    }
    ```
  - ![incart1](https://github.com/user-attachments/assets/1032a217-611b-48bf-a0ab-e5e29b650de9)

  - Request Body: 
     ```json
    {
      "productId": 4,
      "quantity": 2
    }
    ```
    - ![incart3](https://github.com/user-attachments/assets/6cff2f70-da3c-4f01-8064-e48f93ec2a08)

- `DELETE /rest/api/cart/empty/{customerId}`: Sepeti boşaltır. Databaseden müşterinin bağlı olduğu sepetteki tüm ürünleri InCart tablosundan siler.
  - ![cart1](https://github.com/user-attachments/assets/bc5a1a2c-a499-4677-b0d9-8d90046ef34f)
  - ![incart1](https://github.com/user-attachments/assets/fc143480-cac2-40a9-8efb-c0d92a8ab901)
  - ![cart2](https://github.com/user-attachments/assets/5b3983bb-0933-41e4-815d-ed2632ff6c79)
  - ![incart2](https://github.com/user-attachments/assets/097afab4-4631-49dc-83f3-28062f46c1e4)



### Order İşlemleri

- `POST /rest/api/order/place/{customerId}`: Kullanıcının sepetindeki ürünlerle sipariş oluşturur.
  - Sipariş oluşturulmadan önce:
    - Sepet kontrolü yapılır (Boş mu dolu mu/ Fiyatlar güncel mi).
    - Stok kontrolü yapılır.
    - Yeterli stok varsa sipariş oluşturulur.
    - Sipariş oluşturulunca ürün stoğundan düşülür.
    - Sepet temizlenir.
  - ![cart](https://github.com/user-attachments/assets/39767602-2db6-4c3a-93d9-41e74b1c18f2)
  - ![incart](https://github.com/user-attachments/assets/6259580a-5804-484a-bcef-9a2d9fa4f2e9)
  - ![order](https://github.com/user-attachments/assets/45e17296-53bb-4bc4-b29c-3e5b2387207c)
  - ![order items](https://github.com/user-attachments/assets/bbfbcc4a-ccee-4b60-905e-933656573714)
  - ![cart1](https://github.com/user-attachments/assets/3cdcb897-6d85-40a9-acbb-d5a73bcbbb4f)
  - ![incart1](https://github.com/user-attachments/assets/0b82a37f-ac98-47a9-838e-8c344480d8aa)
  - ![order1](https://github.com/user-attachments/assets/329dc96f-e04d-4133-a2d8-6ecf01e47d59)
  - ![orderitem1](https://github.com/user-attachments/assets/d8e37d7c-3b5c-4d4d-a32b-24e2be2d4317)

- `GET /rest/api/order/{orderCode}`: Belirli kodla siparişi getirir.
- `GET /rest/api/order/list/{customerId}`: Kullanıcının tüm siparişlerini getirir.

## Test
Postman ile test edildi. Endpointler başarılı şekilde veri tabanında karşılık bulmaktadır.

##  Notlar
- Sipariş kodu `"AAAA"`, `"AAAB"` şeklinde otomatik artmaktadır.
- Ürün fiyat takip tablosu arkaplanda tutulur. Fiyat güncellendikçe tabloya yeni veri girilir.
- Tüm Entityler (Customer, Cart, Product vb) bir BaseEntityi miras alır idleri otomatik olarak oluşturulur. Oluşturulma ve güncellenme tarihleri tablolara kayıt edilir.
