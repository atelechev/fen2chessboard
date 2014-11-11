<h2>FEN2Chessboard</h2>
==============

This is a simple Web application that provides chess position diagrams generated from <a href="http://en.wikipedia.org/wiki/Forsyth%E2%80%93Edwards_Notation">Forsyth-Edwards notation (FEN)</a> strings.

It runs as a RESTful Web service: you provide your FEN string in the URL, as well as some other optional parameters, and you receive an image with the diagram.

For example:

The application runs at http://localhost:8080/fen2chessboard-rs

The FEN string for the starting position in chess is

```
rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR
```
So you type in your browser:

```
http://localhost:8080/fen2chessboard-rs/fen2cb/rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR
```

And you get the image:

*TODO*: add the image

Please note that only the part of FEN describing the position is used in the URL. The side to move ('w' or 'b') element may be used to flip the view. Other FEN items (castling, en passant) are not used.

<h3>Options</h3>

* Flip the board by adding <code>/b</code> or <code>/B</code> to the URL. Default is the view for White (<code>/w</code> or <code>/W</code> is implicit at the end of the URL).

* Request a specific size of the image by adding <code>;size=X</code> to the URL, where <code>X</code> is the number of pixels for the height or the width of the diagram. Diagrams are square, so there is no need to have two parameters for that.

* Request a specific graphic style for the image by adding <code>;style=N</code> to the URL, where <code>N</code> is the name of the style.
Diagram graphic styles may be created and configured at any moment, without the need to change the code of the application. Please check the respective section below.

The following styles are provided:
1) default style (the diagram above)
2) 'wiki' style:

*TODO*: add wiki image style

3) 'leipzig' style:

*TODO*: add leipzig image style

4) text style - ASCII representation of the chess board

```
---------------------------------
| r | n | b | q | k | b | n | r |
---------------------------------
| p | p | p | p | p | p | p | p |
---------------------------------
|   |   |   |   |   |   |   |   |
---------------------------------
|   |   |   |   |   |   |   |   |
---------------------------------
|   |   |   |   |   |   |   |   |
---------------------------------
|   |   |   |   |   |   |   |   |
---------------------------------
| P | P | P | P | P | P | P | P |
---------------------------------
| R | N | B | Q | K | B | N | R |
---------------------------------
```

