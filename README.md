# mirror_game
## This is a game designed to assess the reasoning ability, it's a simple game: 
- The user has a grid or table of n rows per m columns, within which there are k mirrors that are not visible.
users shoot a laser beam across the table, the mirrors reflect the laser by changing its direction depending on the orientation of the mirror,
the user sees where the laser is coming out, and has to figure out where and how the mirrors are positioned inside the table.
The game also has an option to see the players and scores.

- there is a cheat mode that displays the all the mirrors in the board. To activate or deactivate, enter the word "cheat", while playing.

## Scores calculation:
- The maximum score per game is the number of rows plus the number of columns plus the number of mirrors.
- If a mistake is made when finding a mirror, the score decreases by 1, if the player finishes the game before finding all the mirrors, each mirror missing decreases the score by 2.
- If the player finds at least one mirror, the minimum score, is the number of mirrors founded.
- If the player finishes the game without finding any mirrors the score is 0.

## Technical aspects:

- Fully coded on java 1.8.0 on windows 10 using Eclipse workspace.
- Find the documentation here [Documentation](https://github.com/SeanQO/mirror_game/tree/main/doc), [Class diagram](https://github.com/SeanQO/mirror_game/blob/main/doc/class_diagram.pdf), [Funcional Requirements](https://github.com/SeanQO/mirror_game/blob/main/doc/functional_requirements.pdf)
