import com.github.tototoshi.csv.{CSVReader, CSVWriter}

import java.io.{File, FileNotFoundException, IOException}
import java.util.Date
import scala.io.StdIn.readLine
import scala.util.{Failure, Success}



object LauncherTest {

  case class Film(name : String, award : String, information : String, contentRating : String, company : String,
                  tomato : Tomato, releaseDate : String, originalReleaseDate : String, streamingReleaseDate : String){



  }

  case class Tomato(status : String, rating : String, count : String)

  case class LoadFilmsFromCSV(){

    def LoadDataFromCSV(path : String): List[List[String]] = {

      CSVReader.open(new File("C:\\Users\\Gab\\Desktop\\Scala\\Projet\\OscarsDataset.csv")).toStream.toList.drop(1)


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

    val writer = CSVWriter.open(copyOriginalCSV("C:\\Users\\Gab\\Desktop\\Scala\\Projet\\OscarsDataset.csv"))

    def test() = {

      writer.writeRow(List("a", "b", "c"))

    }

    def copyOriginalCSV(path : String): File ={

      val f = new File("out.csv")

      val copy = f

      copy
    }


  }


  case class DataExplorationServices(){


    def AllFilmsOfAYear(films : List[Film])  = {

      print("Enter a year between 1927 and 2021 : ")
      val year = readLine()

      val filmsByYear = films.filter(_.releaseDate.equals(year)).map(x => x.name)

      if(filmsByYear.length > 0){
        filmsByYear
      }
      else{
        println(s"No film found for year $year")
      }


    }

    def AllFilmsTitleContainsWord(films : List[Film])  = {

      print("Search a film's title that contain this word : ")
      val word = readLine()

      val filmsWithWord =  films.filter(_.name.contains(word)).map(x => x.name)


      if(filmsWithWord.length > 0){
        filmsWithWord
      }
      else{
        println(s"No film's title contains the word $filmsWithWord")
      }



    }

    def AllWinnerFilms(films : List[Film]) : List[String] = {

      films.filter(_.award.equals("Winner")).map(x => x.name)

    }

    def AllFilmsOfACompany(films : List[Film])  = {

      print("Search a all the film of a company : ")
      val company = readLine()
1945
      val filmsByCompany =  films.filter(_.name.contains(company)).map(x => x.name)


      if(filmsByCompany.length > 0){
        filmsByCompany
      }
      else{
        println(s"No film's found for the company $company")
      }

    }

    def AllCertifiedFreshFilms(films : List[Film]) : List[String] = {

      films.filter(_.tomato.status.equals("Certified-Fresh")).map(x => x.name)

    }

  }


  def main(args: Array[String]): Unit = {

    val d = new DataExplorationServices()

    val l = new LoadFilmsFromCSV()

    val p = new ProcessingServices()

    val filmsList = l.getFilmsFromCSV(l.LoadDataFromCSV("C:\\Users\\Gab\\Desktop\\Scala\\Projet\\OscarsDataset.csv"))

    try {
      //Queries

      //Query 1
      println("-------------------------------------------------------------------------------------------------------")
      println("Query 1")
      println(d.AllFilmsOfAYear(filmsList))
      println("-------------------------------------------------------------------------------------------------------")

      //Query 2
      println("Query 2")
      println(d.AllFilmsTitleContainsWord(filmsList))
      println("-------------------------------------------------------------------------------------------------------")
      //Query 3
      println("Query 3")
      println(d.AllWinnerFilms(filmsList))
      println("-------------------------------------------------------------------------------------------------------")

      //Query 4
      println("Query 4")
      println(d.AllFilmsOfACompany(filmsList))
      println("-------------------------------------------------------------------------------------------------------")

      //Query 5
      println("Query 5")
      println(d.AllCertifiedFreshFilms(filmsList))
      println("-------------------------------------------------------------------------------------------------------")


      //Processing services

      //PS 1
      println("PS1 5")
      p.test()
      println("-------------------------------------------------------------------------------------------------------")

      //PS 2


      //PS 3



      //PS 4



      //PS 5


    } catch {
      case ex: FileNotFoundException => {
        println("File not found")
      }
      case ex: IOException => {
        println("IO Exception")
      }
    }




  }


}