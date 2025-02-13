

### Run application
This is library application based on Maven, the main class is `Scoreboard`
To build project: `mvn clean install`
Example use: `Main`


The library is not designed for a multithreading environment.


Due to 'TreeSet' limitations(https://stackoverflow.com/questions/31334698/understanding-treeset-when-compareto-returns-0),
I assumed that there would be an interval of at least 1 nanosecond when adding another new match.
To protect against this, I added 'IllegalStateException', which is why I introduced 'Clock' 
so that the tests would not be dependent on the speed of the machine on which the tests are run.

In edge cases, I throw 'IllegalArgumentException' exceptions with appropriate messages.

To improve, it can be considered changing class 'Match' to immutable, but I didn't have more time for it.

I allow that 'updateScore' method to accept score 0-0 because I can imagine a situation where a goal is scored, 
the method is called with arguments such as 0-1 and after a few dozen seconds, 
when the VAR (Video assistant referee) is viewed by the referee, the goal is not recognized and the result returns to 0-0.