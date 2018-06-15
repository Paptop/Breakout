### Breakout
## Breakout game is CAPPED TO 30 fps, android documentation suggested
## Future improvements
1) Move sound system to another thread
2) Refactor basic functionality of Ball and Paddle in separate abstract class
3) Add PowerUps
4) Fix small bugs with collision and impact
5) The tick methods of entities such as ball,paddle can be decoupled to small sections
6) Collision detection can be improved by eliminating areas where collision is not possible at all in the current moment

## Structure
Main Thread is the gameloop. Level is a manage of game entities and paddle placement.
Commands are used to move the player paddle. GObject is the main interface for game entities.
GamePanel is the wrapper for gameloop with its own states
