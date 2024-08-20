# Test register endpoint
curl -v -X POST localhost:8080/api/auth/register -d '{"email": "alice@test.com","name":"alice","password": "pass1234"}' -H 'Content-Type: application/json'
# Test login endpoint
curl -v -X POST localhost:8080/api/auth/login -d '{"email": "alice@test.com", "password": "pass1234"}' -H 'Content-Type: application/json'
# Test me endpoint
curl -v http://localhost:8080/api/auth/me -H "Authorization: Bearer $TOKEN"
# Test /api/rentals POST
curl -v localhost:8080/api/rentals \
  -F "name=test" \
  -F "surface=50" \
  -F "price=200" \
  -F "picture=@/workspaces/chatop-api/src/main/resources/img/test.jpg" \
  -H "Authorization: Bearer $TOKEN"