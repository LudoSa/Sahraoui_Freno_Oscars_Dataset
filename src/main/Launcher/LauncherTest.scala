import com.github.tototoshi.csv.{CSVReader, CSVWriter}

import java.io.File
import java.util.Date
import scala.io.StdIn.readLine
import java.io.{File, FileNotFoundException, IOException}
import java.util.Date



object LauncherTest {


  def main(args: Array[String]): Unit = {

    //val d = new DataExplorationServices()

    val l = new LoadFilmsFromCSV()

    //val p = new ProcessingServices()

    val s = new Services()

    val filmsList = l.getFilmsFromCSV(l.LoadDataFromCSV("src\\main\\data\\OscarsDataset.csv"))

    try {
    //Queries

    //Query 1
    println("-------------------------------------------------------------------------------------------------------")
    println("Query 1")
    //println(s.AllFilmsOfAYear(filmsList))
    println("-------------------------------------------------------------------------------------------------------")

    //Query 2
    println("Query 2")
    //println(s.AllFilmsTitleContainsWord(filmsList))
    println("-------------------------------------------------------------------------------------------------------")
    //Query 3
    println("Query 3")
    //println(s.AllWinnerFilms(filmsList))
    println("-------------------------------------------------------------------------------------------------------")

    //Query 4
    println("Query 4")
    //println(s.AllFilmsOfACompany(filmsList))
    println("-------------------------------------------------------------------------------------------------------")

    //Query 5
    println("Query 5")
    //println(s.AllCertifiedFreshFilms(filmsList))
    println("-------------------------------------------------------------------------------------------------------")

    //Query 5
    println("Query 5")                                                                                                  
    println(s.AverageRating(filmsList))
    println("-------------------------------------------------------------------------------------------------------")



    //Processing services

    //PS 1
    println("PS 1")
    //s.ModifyMovieInfo("test", "Wings", filmsList)
    println("-------------------------------------------------------------------------------------------------------")

    //PS 2
    println("PS 2")

    println("-------------------------------------------------------------------------------------------------------")


    //PS 3
    println("PS 3")
    //s.AddTomatoAndAudienceCritic("The Racket", filmsList)
    println("-------------------------------------------------------------------------------------------------------")


    //PS 4
    println("PS 4")
    //s.ModifyTomatoAudienceRating("Wings", filmsList)
    println("-------------------------------------------------------------------------------------------------------")


    //PS 5
    println("PS 5")
    //s.ModifyTomatoAndAudienceCritic("Wings", filmsList)
    println("-------------------------------------------------------------------------------------------------------")


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
