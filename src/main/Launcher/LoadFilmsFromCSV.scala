import com.github.tototoshi.csv.CSVReader

import java.io.File

case class LoadFilmsFromCSV(){

  //Load all films with CSV Reader
  def LoadDataFromCSV(path : String): List[List[String]] = {

    CSVReader.open(new File(path)).toStream.toList.drop(1)


  }

  //Transform all lines into a film
  def getFilms(value: Vector[String]): Film ={

    //Each file value is put in a variable
    val name = value(1)
    val award = value(4)
    val information = value(10)
    val contentRating = value(13)
    val company = value(19)
    val tomato = Tomato(value(20), value(21), value(22))
    val audience = Audience(value(23), value(24), value(25))
    val imdb = IMDB(value(8), value(9))
    val releaseDate = value(5)
    val originalReleaseDate = value(17)
    val streamingReleaseDate = value(18)

    //We return the film
    Film(name, award, information, contentRating, company,imdb, tomato, audience, releaseDate, originalReleaseDate, streamingReleaseDate)

  }


  //Create a row per film of the file
  def getFilmsFromCSV(filmsReader: List[List[String]]): List[Film] = {
    filmsReader.map(row => {

      getFilms(row.toVector)

    })
  }

}