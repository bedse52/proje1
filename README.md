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
`POST customer/save`: Yeni kullanıcı oluşturur.
- Request Body:
    ```json
    {
      "name" : "Batuhan Erdoğan"
    }
    ```
### Product İşlemleri
`POST customer/save`: Yeni kullanıcı oluşturur.
`GET customer/save`: Yeni kullanıcı oluşturur.
`DEL customer/save`: Yeni kullanıcı oluşturur.
`PULL customer/save`: Yeni kullanıcı oluşturur.
### Cart İşlemleri

- `POST /cart/add`: Sepete ürün ekler.
  - Request Body:
    ```json
    {
      "productId": 1,
      "quantity": 2
    }
    ```
- `DELETE /cart/remove/{inCartId}`: Ürünü sepetten siler.
- `PUT /cart/removeQuantity`: Ürünün miktarını azaltır.
- `GET /cart/{customerId}`: Kullanıcının sepetini getirir.

### 📦 Order İşlemleri

- `POST /order/place/{customerId}`: Kullanıcının sepetindeki ürünlerle sipariş oluşturur.
  - Sipariş oluşturulmadan önce:
    - Stok kontrolü yapılır.
    - Yeterli stok varsa sipariş oluşturulur.
    - Sipariş oluşturulunca ürün stoğundan düşülür.
    - Sepet temizlenir.

- `GET /order/code/{orderCode}`: Belirli kodla siparişi getirir.
- `GET /order/customer/{customerId}`: Kullanıcının tüm siparişlerini getirir.

## 🧪 Test
Postman ile test edildi. Endpointler başarılı şekilde veri tabanında karşılık bulmaktadır.

## 📌 Notlar
- Sipariş kodu `"AAAA"`, `"AAAB"` şeklinde otomatik artmaktadır.
- Ürün fiyatı değişse de sepetteki itemlerin fiyatı sabit kalır.
