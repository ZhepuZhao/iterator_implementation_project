# object_oriend_project
Create an abstraction that represents a moving belt in our sushi restaurant. A belt object is created with a fixed number of positions. Plate objects can be placed on and removed from the belt. There will also be a method to retrieve a plate object (if present) from a particular position without removing it from the belt. The belt object can be told to "rotate" which means that a plate at position p will now be located at position p+1. The belt is circular, so when the belt rotates, a plate object at position size-1 (where size is the length of the belt) can subsequently be found at position 0. This assignment will also require you to implement the Iterator design pattern in several different and interesting ways. Details are below.