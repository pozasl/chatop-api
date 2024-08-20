# Test register endpoint
curl -v localhost:8080/api/auth/register -d '{"email": "alice@test.com","name":"alice","password": "pass1234"}' -H 'Content-Type: application/json'
# Test login endpoint
curl -v localhost:8080/api/auth/login -d '{"email": "alice@test.com", "password": "pass1234"}' -H 'Content-Type: application/json'
# Test me endpoint
curl -v http://localhost:8080/api/auth/me -H "Authorization: Bearer $TOKEN"
# Test /api/rentals POST
curl -v localhost:8080/api/rentals \
  -F "name=test" \
  -F "surface=50" \
  -F "price=200" \
  -F "picture=@/workspaces/chatop-api/src/main/resources/img/test.jpg" \
  -F "description=BlaBla bla" \
  -H "Authorization: Bearer $TOKEN"
# test /api/rentals GET
curl -v http://localhost:8080/api/rentals -H "Authorization: Bearer $TOKEN"
# test /api/rentals/;id GET
curl -v http://localhost:8080/api/rentals/8 -H "Authorization: Bearer $TOKEN"
# test /api/rentals/;id PUT
curl -v -X PUT localhost:8080/api/rentals/8 \
  -F "name=test3" \
  -F "surface=20" \
  -F "price=125" \
  -F "description=BlaBla bla 3" \
  -H "Authorization: Bearer $TOKEN"
