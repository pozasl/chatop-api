# Test register endpoint
curl -v -X POST localhost:8080/api/auth/register -d '{"email": "steve@test.com","name":"steve","password": "pass1234"}' -H 'Content-Type: application/json'

# Test login endpoint
curl -v -X POST localhost:8080/api/auth/login -d '{"email": "steve@test.com", "password": "pass1234"}' -H 'Content-Type: application/json'