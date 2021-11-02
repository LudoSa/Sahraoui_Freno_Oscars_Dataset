import com.github.tototoshi.csv.{CSVReader, CSVWriter}

import java.io.File
import java.util.Date
import scala.io.StdIn.readLine



object LauncherTest {

  case class Film(name : String, award : String, information : String, contentRating : String, company : String,
                  tomato : Tomato, releaseDate : String, originalReleaseDate : String, streamingReleaseDate : String){

  }

  case class Tomato(status : String, rating : String, count : String)

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

      val test = films.filter(_.name.equals(movie))

      //test(0).map(x => x.name).mkString(" ")

      val f = Film(test(0).name, test(0).award, info,test(0).contentRating, test(0).company, test(0).tomato, test(0).releaseDate, test(0).originalReleaseDate, test(0).streamingReleaseDate)

      //val updated = films.updated(0, f)

      //println(updated)

      //println(test.map(x => x.name).mkString(" "))

      films.filter(_.name.equals(movie)).patch(0, List(f), 1)

      //println(films.filter(_.name.equals(movie)).patch(0, List(f), 1))

      val t =  films.map(x => if(x.name == movie) f else x)
      t.foreach(println)


      //println(films.map(x => if(x.name == movie) films.filter(_.name.equals(movie)).patch(0, info, 1) else x))

      //println(t(0))

    }

    def AddContentRating(content : String, movie : String, films : List[Film]): Unit ={

      val filmToAddContent = films.filter(_.name.equals(movie))

      val currentContent = filmToAddContent(0).contentRating

      val newContent = currentContent.patch(0, content, 1)



      println(filmToAddContent)
      println(currentContent)

      println(newContent)
      //println(t)



    }

  }


  case class DataExplorationServices(){

    def AllFilmsOfAYear(films : List[Film])  = {
      print("Enter a year between 1927 and 2021 : ")
      val year = readLine()
      val filmsByYear = films.filter(_.releaseDate.equals(year)).map(x => x.name)
      if(filmsByYear.nonEmpty){
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

      if(filmsWithWord.nonEmpty){
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
      val filmsByCompany =  films.filter(_.name.contains(company)).map(x => x.name)

      if(filmsByCompany.nonEmpty){
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

    val filmsList = l.getFilmsFromCSV(l.LoadDataFromCSV("C:\\Users\\ludov\\IdeaProjects\\OscarsDataset.csv"))

    //Queries

    //Query 1
    println("-------------------------------------------------------------------------------------------------------")
    println("Query 1")
    //println(d.AllFilmsOfAYear(filmsList))
    println("-------------------------------------------------------------------------------------------------------")

    //Query 2
    println("Query 2")
    //println(d.AllFilmsTitleContainsWord(filmsList))
    println("-------------------------------------------------------------------------------------------------------")
    //Query 3
    println("Query 3")
    //println(d.AllWinnerFilms(filmsList))
    println("-------------------------------------------------------------------------------------------------------")

    //Query 4
    println("Query 4")
    //println(d.AllFilmsOfACompany(filmsList))
    println("-------------------------------------------------------------------------------------------------------")

    //Query 5
    println("Query 5")
    //println(d.AllCertifiedFreshFilms(filmsList))
    println("-------------------------------------------------------------------------------------------------------")


    //Processing services
    /*println("Copied file")
    p.copyOriginalCSV(l.LoadDataFromCSV("C:\\Users\\ludov\\IdeaProjects\\OscarsDataset.csv"))
    p.showCopiedFile()*/

    //PS 1
    println("PS1")
    p.AddMovieInfo("test", "The Broadway Melody", filmsList)
    println("-------------------------------------------------------------------------------------------------------")

    //PS 2
    println("PS2")
    //p.AddContentRating("PG-13", "The Racket", filmsList)
    println("-------------------------------------------------------------------------------------------------------")


    //PS 3



    //PS 4



    //PS 5


  }


}
