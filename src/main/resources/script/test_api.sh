# Test register endpoint
curl -v -X POST localhost:8080/api/auth/register -d '{"email": "alice@test.com","name":"alice","password": "pass"}' -H 'Content-Type: application/json'