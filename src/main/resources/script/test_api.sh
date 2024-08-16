# Test register endpoint
curl -v -X POST localhost:8080/api/auth/register -d '{"email": "alice@test.com","name":"alice","password": "pass1234"}' -H 'Content-Type: application/json'
# Test login endpoint
curl -v -X POST localhost:8080/api/auth/login -d '{"email": "alice@test.com", "password": "pass1234"}' -H 'Content-Type: application/json'