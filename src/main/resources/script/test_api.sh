# Test register endpoint
curl -v -X POST localhost:8080/api/auth/register -d '{"email": "alice@test.com","name":"alice","password": "pass1234"}' -H 'Content-Type: application/json'
# Test login endpoint
curl -v -X POST localhost:8080/api/auth/login -d '{"email": "alice@test.com", "password": "pass1234"}' -H 'Content-Type: application/json'
# Test me endpoint
curl -v http://localhost:8080/api/auth/me -H 'Authorization: Bearer eyJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJzZWxmIiwic3ViIjoiYWxpY2VAdGVzdC5jb20iLCJleHAiOjE3MjQwNjE1MjAsImlhdCI6MTcyNDA1NzkyMCwic2NvcGUiOiJST0xFX1VTRVIifQ.H4Gyjl7SKAOs0rRyMYYjZMI71zGrB79ogAqt1rJ4DW6dNxMXpkCtE-arW-mTMAT5zY-8A1ld1qaqG1gO0Pv-23RZUnFYhqjuGoGNf4JcsARgSYjiBpw5WWTYz_bQiAS33x9yANNr65OWPgXjdv54GMsK3xHYUfE1IvSEJKxuXQpv1nlRnMT2D8zwFXn-6tzHRltS0eVg22jqSC0JHDWtakNWObtJ4a9lf0NVW2cjPC3HStOXF7SA5BdMsF8vgtq34PKYQCDZWaDrWhlse_agQWvsTV6pg1XKOHncGr7UAM8bYKTyM5TZOD7Ai38XxeZM458-SEMLqKnPo6qhJwQ4iQ'
# Test /api/rentals POST
curl -v -X POST localhost:8080/api/rentals  -H 'Content-Type: application/json' -H "Authorization: Bearer $TOKEN"