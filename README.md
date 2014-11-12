<h2>FEN2Chessboard</h2>
==============

This is a simple Web application that generates chess diagrams from <a href="http://en.wikipedia.org/wiki/Forsyth%E2%80%93Edwards_Notation">Forsyth-Edwards notation (FEN)</a> strings.

The core module can also be used as an autonomous library to generate chess diagrams. Please check the <a href="https://github.com/atelechev/fen2chessboard/tree/master/components/core">documentation of the module</a> for this use case.

It runs as a RESTful Web service: you provide your FEN string in the URL, as well as some other optional parameters, and you receive an image with the diagram.

For example:

The application runs at <code>http://localhost:8080/fen2chessboard-rs/fen2cb</code>

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

==============

<h3>Project Contents</h3>

The project currently contains two modules and a folder with resources. They all are placed under <code>components</code> folder:

```
.
├── components
    ├── core
    ├── service
    └── styles
```

The <code><a href="https://github.com/atelechev/fen2chessboard/tree/master/components/core">core</a></code> module contains the main logic of the application and may be re-used as a library for chess diagrams generation.

The <code><a href="https://github.com/atelechev/fen2chessboard/tree/master/components/service">service</a></code> module contains an implementation of a RESTful Web service based on <a href="https://jersey.java.net/">Jersey</a> framework.

The <code><a href="https://github.com/atelechev/fen2chessboard/tree/master/components/styles">styles</a></code> folder contains the definitions of resources featured with the application: graphic diagram styles and their configuration.

==============

<h3>Options</h3>

* Flip the board by adding <code>/b</code> or <code>/B</code> to the URL. Default is the view for White (<code>/w</code> or <code>/W</code> is implicit at the end of the URL, but may be added).

* Request a specific size of the image by adding <code>;size=X</code> to the URL, where <code>X</code> is the number of pixels for the height or the width of the diagram. Diagrams are square, so there is no need to have two separate parameters for that. <code>size=0</code> is interpreted as default size, which corresponds to the size of the source chess board image.

* Request a specific graphic style for the image by adding <code>;style=N</code> to the URL, where <code>N</code> is the name of the style.
Diagram graphic styles may be created and configured at any moment, without the need to change the code of the application. To modify an existing style or create a new one, please <a href="https://github.com/atelechev/fen2chessboard/tree/master/components/styles">check the documentation</a>.

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
{host}/fen2chessboard-rs/fen2cb/{FEN-position}[/side][;size=int][;style=string]
```

==============

<h3>Errors Management</h3>

If an error occurs, the application replies with a JSON message (<code>application/json</code> MIME type) having the following structure:
```
{
    "status": [HTTP response code],
    "message": [error message]
}
```

Typical messages for client-side error reponses are:

```
Invalid FEN string: 'rnbqkbnr/ppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR/w'

Failed to find the style 'wiky'. Please check its name.

Invalid size: 10000. Must be between 0 and 2048.
```

They are sent if at least one of the request parameters is invalid.

==============

<h3>Installation</h3>

Pre-requisites:

* Java 8
* Maven 3
* Apache Tomcat instance, or any other Web application container capable to deploy <code>.war</code> files. The application has been developed and tested using Tomcat 7.

Steps:

1) Check out the project code, as you are used to on GitHub.

2) Switch to the project folder and type

```
mvn clean install
```

The output should succeed with something like

```
[INFO] ------------------------------------------------------------------------
[INFO] Reactor Summary:
[INFO] 
[INFO] FEN to Chess Diagram Web Service .................. SUCCESS [  1.087 s]
[INFO] FEN2Chessboard Core Library ....................... SUCCESS [  8.311 s]
[INFO] FEN2Chessboard Web Service ........................ SUCCESS [  3.119 s]
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 12.642 s
[INFO] Finished at: 2014-11-11T15:58:19+01:00
[INFO] Final Memory: 22M/325M
[INFO] ------------------------------------------------------------------------
```

3) Copy the <code>components/styles</code> folder into the <code>lib/fen2chessboard</code> folder in your Tomcat. This should result in the following folders structure:

```
{TOMCAT_HOME}
├── bin
├── conf
├── lib
│   └── fen2chessboard
│       └── styles
│           ├── default
│           ├── leipzig
│           └── wiki
├── logs
├── temp
├── webapps
├── work
```

4) Put a <code>log4j.properties</code> file in <code>{TOMCAT_HOME}/lib</code>, if you want some logging features (may be useful for tracing server-side errors).

5) Ensure that your Tomcat is running. Deploy the web application from <code>components/service/target/fen2chessboard-rs.war</code> using your favorite method.
You may deploy it using the Maven command

```
mvn tomcat7:undeploy tomcat7:deploy
```
Please check the respective Maven plugin configuration in <code>components/service/pom.xml</code>.

Your application instance should be functional now.


