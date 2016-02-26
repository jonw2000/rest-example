# rest-example

## Areas for Improvement

### Tests

1. Complete and beef up the unit tests around the TradeProcessor and TradeService
2. Add integration tests which actually require the server to be running
3. Add tests to verify the JSON representation of the model objects

### Trade POJO and Representation

The Trade pojo and how it is representd in XML and JSON can be improved. It should be
possible to us @JsonProperty(name) to annotate Trade to produce JSON with different 
field names, but I didn't manage to get this working in the time given. Instead I've
used an ObjectMapper and separate TradeSerializer to do this, and a toJson() method.

### TradeToken

I had to implement set() methods in order to get all the fields returning in the JSON.
Clearly this is not elegant in a production system and this should be fixed so set()
methods are not necessary.

### Caches

I've used Guava caches here as they provide a means to expire keys based on time.
I also thought about EhCache as well but prefer to use the more lightweight 
solutions where possible.

### Persistence

The Caches are interfaces meaning a persistent layer could be swapped in which provides
a near cache and a write-through to disk or database.

### REST API

The API is very basic and responses could be handled better and more generically.
Versioning should also be handled properly and made explicit.
