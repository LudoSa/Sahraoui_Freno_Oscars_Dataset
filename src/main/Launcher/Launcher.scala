import com.github.tototoshi.csv.CSVReader

import java.io.File
import scala.collection.mutable.ListBuffer
import scala.io.BufferedSource


object Launcher {
  def main(args: Array[String]): Unit = {
    val reader = CSVReader.open(new File("C:\\Users\\ludov\\IdeaProjects\\OscarsDataset.csv"))

    println(reader.toString)

    var result = ListBuffer[String]()

    val bufferedSource = io.Source.fromFile("C:\\Users\\ludov\\IdeaProjects\\OscarsDataset.csv")

    var cols = Array[String]()

    //Queries


    //Query 1
    println(AllFilmsOfAYear("1927", bufferedSource))

    //Query 2
    println(AllFilmsTitleContainsWord("The", bufferedSource))

    //Query 3



    //Query 4



    //Query 5

    /*for (line <- bufferedSource.getLines()) {
      cols = line.split(",").map(_.trim)

      //println(s"${cols(0)}|${cols(1)}|${cols(2)}|${cols(3)}|${cols(4)}|${cols(5)}")

      //result += cols(1)


    }*/

    //println(result.filter(_ contains("W")))

  }

  def AllFilmsOfAYear (year : String, films : BufferedSource): ListBuffer[String] ={

    var filmsName = new ListBuffer[String]()

    for (line <- films.getLines()) {
      var cols = line.split(",").map(_.trim)

      if(cols(5).equals(year)){

        filmsName += cols(1)

      }

    }

    filmsName
  }

  def AllFilmsTitleContainsWord(word : String, films : BufferedSource): ListBuffer[String] = {

    var filmsName = new ListBuffer[String]()


    for (line <- films.getLines()) {
      var cols = line.split(",").map(_.trim)

      if(cols(1).contains(word)){

        filmsName += cols(1)

      }

    }

    filmsName

  }
}
