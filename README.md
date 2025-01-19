<테이블 총 6개>
-> user , maincategory , subcategory , likes , buy ,products 
jpa 테이블 연관관계 공부 + security(jwt-token) 를 활용한 user , admin , shop_manager 총 3개의 권한 구현 

api 총 16개

#주요 기능 
- likes 추가/삭제
- 메인카테고리에 해당하는 상품 , 서브카테고리에 해당하는 상품 다양하게 조회 가능
- user , admin , shop_manager 3개의 권한 설정 shop_manager 은 회원가입시 ROLE_PENDING_MANAGER 로 db 에 insert 되고 admin 의 허용시 shop_manager 로 update
- buy를 요청하면 RequestBuyDTO (username) 안에 BuyProductRequestDTO (상품 정보) 배열이 들어있어 dto 클래스의 재사용성과 (request/response)가독성을 향상시켜봄
  ->해당 request 를 buy 엔티티에 저장하고 다시 buyDto로 변환하여 response 전송
-product 검색기능

#추가할 기능 
- s3 를 이용한 product crud 구현(shop_manager 권한) 
- 사용자 별 디테일 한 권한 분리
- login/logout Controller 띠로 만들기
- product 페이지네이션 구현

아리가또 센세😁
