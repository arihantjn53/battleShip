# BattleShip Game

Battleship game is a war game played on ocean by two players. Each player own his battle area and each player will get same number of ships where each ship may have different size placed at some position in non-overlapping fashion. Note, players cannot see each others ship's location.

# Usage

# Input

- First line of the input contains dimensions of battle area having width and height separated by space.
- Then the next line will have number (B) of battleships each player has.
- Then in the next line battleship type, dimensions (width and height) & positions (Y coordinate and X coordinate) for Player-1 and then for Player-2 will be given separated by space.
- And then in the next line Player-1’s sequence (separated by space) of missiles target location coordinates (Y and X) will be given and then for sequence for Player-2.

# Sample Input

```
5 E
2
Q 1 1 A1 B2 A5
P 2 1 D4 C3 D1
A1 B2 B2 B3
A1 B2 B3 A1 D1 E1 D4 D4 D5 D5
```

# File Structure

```
- model
  |_ board
    |_ Board
      - 2D array of BoardItems
      - width
      - height
    |_ BoardItem
      - type
  |_ game
    |_ Game
      - Array of Players
  |_ player
    |_ Player
      - playerId
      - board
      - moves
      - moveNum

controller
|_ BoardController
  - createBoard
  - setupPlayerBoard
  - addShip
  - makeHit
|_ GameController
  - createGame
  - startGame
  - getWinner
|_ PlayerController
  - createPlayer
  - makeMove
  - getNextMove
  - takeHit
  - anyShipsLeft
  - firstPlayer
  - pickNextPlayer
  - anyMovesLeft
```