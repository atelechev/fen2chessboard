<h2>FEN2Chessboard</h2>
==============

This is a simple Web application that provides chess position diagrams generated from <a href="http://en.wikipedia.org/wiki/Forsyth%E2%80%93Edwards_Notation">Forsyth-Edwards notation (FEN)</a> strings.

It runs as a RESTful Web service: you provide your FEN string in the URL, as well as some other optional parameters, and you receive an image with the diagram.

For example:

The application runs at http://localhost:8080/fen2chessboard-rs/fen2cb

The FEN string for the starting position in chess is

```
rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR
```
So you type in your browser:

```
http://localhost:8080/fen2chessboard-rs/fen2cb/rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR
```

And you get the image:

![Default diagram style](/docs/images/diagram_default.png?raw=true "Default Diagram Style")

Please note that only the part of FEN that describes the position is used in the URL. The side to move ('w' or 'b') element may be used to flip the view. Other FEN items (castling, en passant) are not used.


<h3>Options</h3>

* Flip the board by adding <code>/b</code> or <code>/B</code> to the URL. Default is the view for White (<code>/w</code> or <code>/W</code> is implicit at the end of the URL, but may be added).

* Request a specific size of the image by adding <code>;size=X</code> to the URL, where <code>X</code> is the number of pixels for the height or the width of the diagram. Diagrams are square, so there is no need to have two parameters for that.

* Request a specific graphic style for the image by adding <code>;style=N</code> to the URL, where <code>N</code> is the name of the style.
Diagram graphic styles may be created and configured at any moment, without the need to change the code of the application. Please check the respective section below.

The following styles are provided:

1) <code>default</code> style (the diagram above). This style is used if the <code>style</code> parameter is not set or empty, or if it is explicitly set to 'default'.

2) <code>wiki</code> style:

![Wiki diagram style](/docs/images/diagram_wiki.png?raw=true "Wiki Diagram Style")

3) <code>leipzig</code> style:

![Leipzig diagram style](/docs/images/diagram_leipzig.png?raw=true "Leipzig Diagram Style")

4) <code>text</code> style - ASCII representation of the chess board

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


Thus, the URL pattern to call the service is the following:
```
{host}/fen2chessboard-rs/fen2cb/{FEN-position}/{side}[;size=int][;style=string]
```

<h3>Errors Management</h3>

The following client-side errors are intercepted by the application:

1) Invalid FEN in the URL. In this case, the application will return a message like

```
Invalid FEN string: 'rnbqkbnr/ppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR/w'
```

2) Invalid style identifier. In this case, the application will return a message like

```
Failed to find the style 'wiky'. Please check its name.
```

3) Invalid size value. In this case, the application will return a message like

```
Invalid size: 10000. Must be between 0 and 2048.
```

<code>size=0</code> is interpreted as default size, which corresponds to the size of the source chess board image.

