### Breakout
## Future improvements
1) Move sound system to another thread
2) Refactor basic functionality of Ball and Paddle in separate abstract class
3) Add PowerUps
4) Fix small bugs with collision and impact

## Structure
Main Thread is the gameloop. Level is a manage of game entities and paddle placement.
Commands are used to move the player paddle. GObject is the main interface for game entities.
