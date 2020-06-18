ad6762bb91ed346bc9d6d4f867c4ec17-1084540014.ap-northeast-2.elb.amazonaws.com:8080/shows

* 예약 -> 발권

show 생성

http post ad6762bb91ed346bc9d6d4f867c4ec17-1084540014.ap-northeast-2.elb.amazonaws.com:8080/shows showName="showName1" totalCount=100 remainCount=100

book 생성
http post ad6762bb91ed346bc9d6d4f867c4ec17-1084540014.ap-northeast-2.elb.amazonaws.com:8080/bookings showId=1 qty=10 amount=30000 showName="showName1" bookStatus="Booked"

ticket 발권

http patch ad6762bb91ed346bc9d6d4f867c4ec17-1084540014.ap-northeast-2.elb.amazonaws.com:8080/ticketIssuances/1 issueStatus="Issued"


* 비정상 예약

book 생성

http post ad6762bb91ed346bc9d6d4f867c4ec17-1084540014.ap-northeast-2.elb.amazonaws.com:8080/bookings showId=1 qty=10 amount=30000 showName="showName1" bookStatus="Booked"

http get localhost:8088/bookings


* 예약 -> 취소

book 생성

http post ad6762bb91ed346bc9d6d4f867c4ec17-1084540014.ap-northeast-2.elb.amazonaws.com:8080/bookings showId=1 qty=10 amount=30000 showName="showName1" bookStatus="Booked"


book 취소

http patch ad6762bb91ed346bc9d6d4f867c4ec17-1084540014.ap-northeast-2.elb.amazonaws.com:8080/bookings/2 bookStatus="Cancelled"

eyJhbGciOiJSUzI1NiIsImtpZCI6IiJ9.eyJpc3MiOiJrdWJlcm5ldGVzL3NlcnZpY2VhY2NvdW50Iiwia3ViZXJuZXRlcy5pby9zZXJ2aWNlYWNjb3VudC9uYW1lc3BhY2UiOiJrdWJlLXN5c3RlbSIsImt1YmVybmV0ZXMuaW8vc2VydmljZWFjY291bnQvc2VjcmV0Lm5hbWUiOiJla3MtYWRtaW4tdG9rZW4tajY5NWIiLCJrdWJlcm5ldGVzLmlvL3NlcnZpY2VhY2NvdW50L3NlcnZpY2UtYWNjb3VudC5uYW1lIjoiZWtzLWFkbWluIiwia3ViZXJuZXRlcy5pby9zZXJ2aWNlYWNjb3VudC9zZXJ2aWNlLWFjY291bnQudWlkIjoiYjRlN2U3ZmYtMDRkYi00Zjk1LThkZGQtZmY4NzIyNjMzNTZjIiwic3ViIjoic3lzdGVtOnNlcnZpY2VhY2NvdW50Omt1YmUtc3lzdGVtOmVrcy1hZG1pbiJ9.WZOqN3i1Ot_I2dSr67m6-xWLlNVPap5fENQzAvAMHBXrJWv7Y55S5cslW_X3mo6bjKdcWVtMGCJE9RTnD08yITFNqfjoTu2uhrm_q91vFSMkoH_HHgnMRtZ4HZ9EQjnH2gnqIAEb9K0fLMOS9Uc3fK3xRC714wz-N-RMbJTAh0hrW_VK0hDOGOszJRMxm67CMh9mP47vnHNh0oI34JVR1K8OYWLXesbNDiLosaDB7eGdtuO2kKLmq6RRKYjt0AhKuhtVQ8hsSf4h4PzlqdIgRlgmtpm9zTX2hZ8s2X8-c_QcjKxFNjXeWdaofgDimTehnEc4B0jc3dJn3AtFC5kh5w
