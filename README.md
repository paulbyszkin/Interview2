Implementation of Market Price Handler Exercice.

I made a few assumptions here:

Prices are handled using doubles, faster than BigDecimal and easier on GC but trickier with rounding.

Immutable entities and reusing where possible - Adjusted is reusing Raw.

Didn't add logging to make it simple.

Assuming that listener is single threaded so parser might be a little heavy. Possible to move it to executor pool if needed.
If feeds will be poor quality and we will have lots of exceptions then it will be slow + big pressure on GC.
We will need to add guards to parser. Possibly we could avoid String (heap) and use ByteBuffer directly from socket?

Code is unit tested apart from Resource - I could verify Option handling for cache otherwise it will make more
sense to write functional test.

REST resource is decoupled from listener and only share cache. We could use
Google's Guava loading cache if more sophisticated solution will be needed.

In general I wanted to keep it small and simple.

I am assuming that parameters are non-nulls by default and it will be sorted out
by wiring i.e. Spring etc. 