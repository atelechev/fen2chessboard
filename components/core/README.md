<h2>Core Module</h2>
======

This module implements the routines for chess diagrams generation.

======

<h3>Usage Example</h3>

Pre-requisites:

* a valid style definition, as in <a href="https://github.com/atelechev/fen2chessboard/tree/master/components/styles">styles</a> folder, featured with the application or customized ad hoc.
* a <code>fen2chessboard-core-{version}.jar</code> file on the classpath. You can find this file in <code>target</code> folder of this module after a successful project build with Maven.


Typical usage:

```
// this is the Path to your 'styles' folder
Path pathStyles = Paths.get("path_to_my_styles_folder");

// this is the FEN string for the initial chess game position
String strFen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR"; // you may also append "/w" or "/b" to set the view for White or Black

// this is the width(or height) of your diagram in pixels
int size = 400;

// create a Fen object from this string
Fen fen = Fen.fromPath(strFen); // may throw IllegalFenException if strFen is not valid

// we'll generate the diagram with 'leipzig' style, which is a RasterStyle
RasterStyle style = (RasterStyle) DiagramStyleFactory.buildStyle("leipzig", pathStyles);

// create a renderer for diagrams with this style
RasterRenderer renderer = new RasterRenderer(style);

// render the diagram
BufferedImage diagram = renderer.renderDiagram(fen, size);

// do whatever you want with the 'diagram' object.

```

======

<h3>Hints</h3>

* invoking <code>fen.toString()</code> will return an ASCII representation of the diagram, like when you call the service with <code>;style=text</code> parameter.
