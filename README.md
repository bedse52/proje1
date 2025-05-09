# E-Ticaret Uygulaması (Spring Boot)

Bu uygulama temel sepet ve sipariş işlemlerini yönetebilen bir e-ticaret backend'idir. Spring Boot kullanılarak geliştirilmiştir.

Kullanılan Teknolojiler

- Java 24
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Lombok
  
## 📬 API Endpointleri

### Customer İşlemleri
-`POST /rest/apicustomer/save`: Yeni kullanıcı oluşturur.
- Request Body:
    ```json
    {
      "name" : "Batuhan Erdoğan"
    }
    ```
### Product İşlemleri
-`POST /rest/apiproduct/save`: Yeni ürün oluşturur.
- Request Body:
    ```json
    {
    "name" : "Krampon",
    "price" : 4699.99,
    "stock" :  100
}
    ```
-`GET /rest/apiproduct/list`: Ürün listesini getirir sonuna `/{productId}` eklenirse spesifik ürün gelir.
-`DEL /rest/api/product/delete/{productId}`: Ürün silinir.
-`PUT /rest/api/product/update/{productId}`: "Ürün güncellenir.
- Request Body:
    ```json
   {
    "name" : "Krampon",
    "price" : 4499.99,
    "stock" :  100
}
    ```
### Cart İşlemleri
- `GET /rest/api/cart/{customerId}`: Kullanıcının sepetini getirir.
- `POST /rest/api/cart/add/{customerId}`: Sepete ürün ekler.
  - Request Body:
    ```json
    {
      "productId": 1,
      "quantity": 2
    }
    ```
- `PATCH /rest/api/cart/remove/{customerId}`: Girilen "quantity"miktarına göre ürünü sepetten siler veya ürünün miktarını azaltır.
  - Request Body:
    ```json
    {
      "productId": 1,
      "quantity": 2
    }
    ``` 
- `DELETE /rest/api/cart/empty/{customerId}`: Sepeyi boşaltır.
- `PUT /rest/api/cart/update/{customerId}`:  Sepette tomplam fiyat-ürün uyuşmazlığı olursa giderir, ürünlerin fiyatı değişmiş ise sepetteki fiyatları günceller.


### 📦 Order İşlemleri

- `POST /rest/api/order/place/{customerId}`: Kullanıcının sepetindeki ürünlerle sipariş oluşturur.
  - Sipariş oluşturulmadan önce:
    - Sepet kontrolü yapılır (Boş mu dolu mu/ Fiyatlar güncel mi).
    - Stok kontrolü yapılır.
    - Yeterli stok varsa sipariş oluşturulur.
    - Sipariş oluşturulunca ürün stoğundan düşülür.
    - Sepet temizlenir.

- `GET /rest/api/order/{orderCode}`: Belirli kodla siparişi getirir.
- `GET /rest/api/order/list/{customerId}`: Kullanıcının tüm siparişlerini getirir.

## 🧪 Test
Postman ile test edildi. Endpointler başarılı şekilde veri tabanında karşılık bulmaktadır.

## 📌 Notlar
- Sipariş kodu `"AAAA"`, `"AAAB"` şeklinde otomatik artmaktadır.
- Ürün fiyat takip tablosu arkaplanda tutulur. Fiyat güncellendikçe tabloya yeni veri girilir.
