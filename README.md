# agoda

Basic Address Cache contains 3 files and compatible to run in multi threaded environment.

1) AddressCache.java where actual logic is defined for adding element and getting latest element etc.

2) AddressMain.java is deriving class to execute the implemented methods.

3) AddressCacheTest.java is test class written using junit covering all possible test cases.

Note : take() method of AddressCache.java pop the latest element and will wait for certain time if no element exits should be implemented at thread level by using wait() and notfiy() on particular thread executing this method .
