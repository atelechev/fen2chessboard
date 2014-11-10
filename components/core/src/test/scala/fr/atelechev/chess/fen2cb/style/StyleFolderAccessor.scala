package fr.atelechev.chess.fen2cb.style

import java.nio.file.Paths

trait StyleFolderAccessor {

  def getFolderForStyle(styleName : String) = {
    val stylesFolderName = classOf[DiagramStyle].getClassLoader().getResource("styles").getFile
    if (styleName == null) {
      Paths.get(stylesFolderName)
    }
    else {
      Paths.get(stylesFolderName).resolve(styleName)
    }
  }
  
}