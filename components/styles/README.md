Diagram Styles Configuration
==============

You may configure your own styles, or even edit the existing ones at any moment, without the need to make any changes in the application code.

If you change an existing style, this will require a restart of the application, because style data are read lazily on the first access and then cached in the memory in order to avoid multiple disk I/O.

==============

<h3>Styles Conventions</h3>

==============

<h4>Style Name</h4>

To add a new style, simply create a folder under <code>fen2chessboard/styles</code>.

The name of the folder may contain only alphanumeric characters and underscores. The name 'text' is reserved for ASCII style, so it should not be used.

The name of the style is used as its identifier when you call the Web service, via the parameter <code>;style=style_name</code>.

This folder must contain images and configuration files described below.

<h4>Output Format</h4>

Currently, only raster or text output is supported by the application. The MIME types are "image/png" and "text/plain".

All image files are expected to be in <code>.png</code> format. This format is chosen because it supports transparency, which is very useful in our context.

<h4>File Names</h4>

All file names are supposed to be in lower case.

<h4>Chess Board</h4>

* <code>board.png</code> file must contain an image with an empty chess board, for example:

![Empty board](../../docs/images/diagram_empty.png?raw=true "board.png example")

* The squares must be of same size in the board image.

* The width and the height of the board image should be equal. I.e. it should be a square.

* The board may contain margins. If there is a margin at the top or at the left side, they must be declared in <code>diagram.properties</code> (see below). An example of a board with margins is in 'leipzig' style:

![Empty board with margins](../../docs/images/diagram_empty_leipzig.png?raw=true "board.png example 2")

The top-left corner of the a8 square in this board starts at pixel 16, so the top and the left margins (or paddings) should have the value of 15.

<h4>Chess Pieces</h4>

The following files must be present in the folder with the style:

```
├── black_bishop.png
├── black_king.png
├── black_knight.png
├── black_pawn.png
├── black_queen.png
├── black_rook.png
├── white_bishop.png
├── white_king.png
├── white_knight.png
├── white_pawn.png
├── white_queen.png
└── white_rook.png
```

These files contain images with chess pieces, their names are explicit.

The size of all the piece images must be the same. For example, in the default style, all the <code>black_*</code> and <code>white_*</code> images have the size of 108x108 pixels.

If this is not respected, the style will not be initialized properly.

The size of all of these images must also be the same as the size of a square in the empty board image.

<h4>Configuration File</h4>

The style definition must contain a configuration file named <code>diagram.properties</code>, with the following key-value pairs:

```
render.type={value}
padding.top={value}
padding.left={value}
overlay.x={value}
overlay.y={value}
``` 

Please check the comments for these values in any of the <code>diagram.properties</code> files in the style folders provided with the application.

<h4>Diagram Overlay</h4>

If you want to add an overlay image to your diagram (logo or watermark, like _Test diagram_ in the 'default' style), you may add a file named <code>overlay.png</code> into the folder of your style. The position of the overlay image is defined using the <code>overlay.x</code> and <code>overlay.y</code> values in the configuration file. It is calculated in pixels relatively to the top left corner of the <code>board.png</code> file.
