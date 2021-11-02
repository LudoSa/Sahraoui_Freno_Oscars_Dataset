import com.github.tototoshi.csv.CSVReader

import java.io.File

case class LoadFilmsFromCSV(){

  def LoadDataFromCSV(path : String): List[List[String]] = {

    CSVReader.open(new File("src\\main\\data\\OscarsDataset.csv")).toStream.toList.drop(1)


  }

  def LoadDataFromCSVForCopy(path : String): List[List[String]] = {

    CSVReader.open(new File("src\\main\\data\\OscarsDataset.csv")).toStream.toList.drop(0)



  }

  def getFilms(value: Vector[String]): Film ={

    val name = value(1)
    val award = value(4)
    val information = value(10)
    val contentRating = value(13)
    val company = value(19)
    val tomato = Tomato(value(20), value(21), value(22))
    val audience = Audience(value(23), value(24), value(25))
    val releaseDate = value(5)
    val originalReleaseDate = value(17)
    val streamingReleaseDate = value(18)

    Film(name, award, information, contentRating, company, tomato, audience, releaseDate, originalReleaseDate, streamingReleaseDate)

  }

  def getFilmsFromCSV(filmsOfReader: List[List[String]]): List[Film] = {
    filmsOfReader.map(row => {

      getFilms(row.toVector)

    })
  }

}