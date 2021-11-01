import com.github.tototoshi.csv.{CSVReader, CSVWriter}

import java.io.File
import java.util.Date



object LauncherTest {

  case class Film(name : String, award : String, information : String, contentRating : String, company : String,
                  tomato : Tomato, releaseDate : String, originalReleaseDate : String, streamingReleaseDate : String){

  }

  case class Tomato(status : String, rating : String, count : String)

  case class LoadFilmsFromCSV(){

    def LoadDataFromCSV(path : String): List[List[String]] = {

      CSVReader.open(new File("C:\\Users\\ludov\\IdeaProjects\\OscarsDataset.csv")).toStream.toList.drop(1)


    }

    def LoadDataFromCSVForCopy(path : String): List[List[String]] = {

      CSVReader.open(new File("C:\\Users\\ludov\\IdeaProjects\\OscarsDataset.csv")).toStream.toList.drop(0)


    }

    def getFilms(value: Vector[String]): Film ={

      val name = value(1)
      val award = value(4)
      val information = value(10)
      val contentRating = value(13)
      val company = value(19)
      val tomato = Tomato(value(20), value(21), value(22))
      val releaseDate = value(5)
      val originalReleaseDate = value(17)
      val streamingReleaseDate = value(18)

      Film(name, award, information, contentRating, company, tomato, releaseDate, originalReleaseDate, streamingReleaseDate)

    }

    def getFilmsFromCSV(filmsOfReader: List[List[String]]): List[Film] = {
      filmsOfReader.map(row => {

        getFilms(row.toVector)

      })
    }

  }

  case class ProcessingServices(){

    val writer = CSVWriter.open("out.csv")

    val l = new LoadFilmsFromCSV()

    def test() = {

      writer.writeRow(List("a", "b", "c"))

    }

    def showCopiedFile():Unit ={

      val filmsList = l.getFilmsFromCSV(l.LoadDataFromCSVForCopy("out.csv"))

      println(filmsList.foreach(fields => println(fields)))


    }

    def copyOriginalCSV(data : List[List[String]]): Unit  ={

      writer.writeAll(data);

    }

    def TomatoAudienceRating(): Unit ={



    }


    def AddTomatoAndAudienceCritic(): Unit ={



    }

    def CalculateYearsBetweenOriginalAndStreaming(): Unit ={



    }


    def AddMovieInfo(info : String, movie : String, films : List[Film]): Unit ={


      val filmToAddInfo = films.filter(_.name.equals(movie))

      val addInfo = Map(filmToAddInfo.lift(10) -> info)

      println(filmToAddInfo)

      //writer.writeRow(addInfo)


      //val modifiedFilmsList = filmToAddInfo.map{case _. }


    }

    def AddContentRating(): Unit ={



    }

  }


  case class DataExplorationServices(){


    def AllFilmsOfAYear(year : String, films : List[Film]) : List[String] = {

      films.filter(_.releaseDate.equals(year)).map(x => x.name)

    }

    def AllFilmsTitleContainsWord(word : String, films : List[Film]) : List[String] = {

      films.filter(_.name.contains(word)).map(x => x.name)

    }

    def AllWinnerFilms(films : List[Film]) : List[String] = {

      films.filter(_.award.equals("Winner")).map(x => x.name)


    }

    def AllFilmsOfACompany(company : String, films : List[Film]) : List[String] = {

      films.filter(_.company.equals(company)).map(x => x.name)


    }

    def AllCertifiedFreshFilms(films : List[Film]) : List[String] = {

      films.filter(_.tomato.status.equals("Certified-Fresh")).map(x => x.name)

    }

  }


  def main(args: Array[String]): Unit = {

    val d = new DataExplorationServices()

    val l = new LoadFilmsFromCSV()

    val p = new ProcessingServices()

    val filmsList = l.getFilmsFromCSV(l.LoadDataFromCSV("C:\\Users\\ludov\\IdeaProjects\\OscarsDataset.csv"))

    //Queries

    //Query 1
    println("-------------------------------------------------------------------------------------------------------")
    println("Query 1")
    println(d.AllFilmsOfAYear("1927", filmsList))
    println("-------------------------------------------------------------------------------------------------------")

    //Query 2
    println("Query 2")
    println(d.AllFilmsTitleContainsWord("The", filmsList))
    println("-------------------------------------------------------------------------------------------------------")
    //Query 3
    println("Query 3")
    println(d.AllWinnerFilms(filmsList))
    println("-------------------------------------------------------------------------------------------------------")

    //Query 4
    println("Query 4")
    println(d.AllFilmsOfACompany("Warner Bros.", filmsList))
    println("-------------------------------------------------------------------------------------------------------")

    //Query 5
    println("Query 5")
    println(d.AllCertifiedFreshFilms(filmsList))
    println("-------------------------------------------------------------------------------------------------------")


    //Processing services
    /*println("Copied file")
    p.copyOriginalCSV(l.LoadDataFromCSV("C:\\Users\\ludov\\IdeaProjects\\OscarsDataset.csv"))
    p.showCopiedFile()*/

    //PS 1
    println("PS1 5")
    p.AddMovieInfo("test", "Wings", filmsList)
    println("-------------------------------------------------------------------------------------------------------")

    //PS 2


    //PS 3



    //PS 4



    //PS 5


  }


}
