# Game of Life

## What's it all about

Fascinated by Joscha Bach's descriptions in [this podcast](https://alternativlos.org/42/) (he is just as entertaining in [english](https://lexfridman.com/joscha-bach-3/)) about [Conway's Game of Life](https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life), I started to read up on the subject.

Four simple rules describe how cells behave in a 2-dimensional grid. With each new generation, the cells change their state according to the rules. They only know the states "alive" and "dead". The 4 rules are as follows:
- Any live cell with fewer than two live neighbors dies, as if by underpopulation.
- Any live cell with two or three live neighbors lives on to the next generation.
- Any live cell with more than three live neighbors dies, as if by overpopulation.
- Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.

Regardless of the fact that this set of rules has certainly been implemented often enough *, I was tempted to try it myself. Watching the result generate ever new patterns or trying it with known patterns has a kind of hypnotic effect.

<sup><sub>*In fact, Google even shows moving cell patterns in the results list for Game of life at the time of writing this</sub></sup>

## Usage

As this project is just a private gimmick, I have decided against release management.

If you want to see the project in action yourself, check it out and simply start it. It should be compatible with any JDK from version 11 and doesn't use any other dependencies.

Once the application is started, you can activate single cells by clicking on them. As soon as all cells are prepared, the game of life can be started with the "start" button, which automatically becomes a "pause" button to stop further state changes.

Next to the "start/pause" button there is a "reset" button, which clears all active cells, resets the counter and stops the whole game.

The slider on the right side can be used to change the speed of the game.

## Possible improvements

Since there are different variations of the game that modify the rules or even the geometry of the universe, it is conceivable to map these variations as different models. The architecture of the application makes this quite simple. It also allows to provide different user interfaces.

Another exciting extension would be to add various known patterns by a single click, since this can be really tedious at the moment.

A rather low-hanging fruit is the detection of situations in which there are only still lifes and oscillators, i.e. no long-term change in the state of the universe is possible.