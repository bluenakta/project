
* 예약 -> 발권
show 생성
http post localhost:8088/shows showName="showName1" totalCount=100 remainCount=100
book 생성
http post localhost:8088/bookings showId=1 qty=10 amount=30000 showName="showName1" bookStatus="Booked"
ticket 발권
http patch localhost:8088/ticketIssuances/2 issueStatus="Issued"

* 비정상 예약
book 생성
http post localhost:8088/bookings showId=1 qty=1000 amount=30000 showName="showName1" bookStatus="Booked"
http get localhost:8088/bookings

* 예약 -> 취소
book 생성
http post localhost:8088/bookings showId=1 qty=10 amount=30000 showName="showName1" bookStatus="Booked"
book 취소
http patch localhost:8088/bookings/1 bookStatus="Cancelled"
