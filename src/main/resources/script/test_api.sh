# Test register endpoint
echo $(curl -s -w " %{response_code}" localhost:8080/api/auth/register -d '{"email": "alice@test.com","name":"alice","password": "pass1234"}' -H 'Content-Type: application/json')
# Test login endpoint
TOKEN=$(curl -s localhost:8080/api/auth/login -d '{"email": "alice@test.com","password": "pass1234"}' -H 'Content-Type: application/json' | jq -r ".token")
# Test me endpoint
echo $(curl -s -w " %{response_code}" http://localhost:8080/api/auth/me -H "Authorization: Bearer $TOKEN")
# test /api/user/1
echo $(curl -s -w " %{response_code}" http://localhost:8080/api/user/1 -H "Authorization: Bearer $TOKEN")
# test /api/user/9999
echo $(curl -s -w " %{response_code}" http://localhost:8080/api/user/9999 -H "Authorization: Bearer $TOKEN")
# Test /api/rentals POST
echo $(curl -s -w " %{response_code}" localhost:8080/api/rentals \
  -F "name=test" \
  -F "surface=50" \
  -F "price=200" \
  -F "picture=@/workspaces/chatop-api/src/main/resources/img/test.jpg" \
  -F "description=BlaBla bla" \
  -H "Authorization: Bearer $TOKEN")
# test /api/rentals GET
echo $(curl -s -w " %{response_code}" http://localhost:8080/api/rentals -H "Authorization: Bearer $TOKEN")
# test /api/rentals/1 GET
echo $(curl -s -w " %{response_code}" http://localhost:8080/api/rentals/1 -H "Authorization: Bearer $TOKEN")
# test /api/rentals/9999 GET
echo $(curl -s -w " %{response_code}" http://localhost:8080/api/rentals/9999 -H "Authorization: Bearer $TOKEN")
# test /api/rentals/1 PUT
echo $(curl -s -w " %{response_code}" -X PUT localhost:8080/api/rentals/1 \
  -F "name=test3" \
  -F "surface=20" \
  -F "price=125" \
  -F "description=BlaBla bla 3" \
  -H "Authorization: Bearer $TOKEN")
# test /api/messages POST
echo $(curl -s -w " %{response_code}" localhost:8080/api/messages \
  -d '{"message": "Hello worl!", "user_id": 1, "rental_id":1}' \
  -H 'Content-Type: application/json' \
  -H "Authorization: Bearer $TOKEN")
