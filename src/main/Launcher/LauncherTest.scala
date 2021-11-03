import com.github.tototoshi.csv.{CSVReader, CSVWriter}

import java.io.File
import java.util.Date
import scala.io.StdIn.readLine
import java.io.{File, FileNotFoundException, IOException}
import java.util.Date



object LauncherTest {


  def main(args: Array[String]): Unit = {

    val l = LoadFilmsFromCSV()

    val s = Services()

    val filmsList = l.getFilmsFromCSV(l.LoadDataFromCSV("src\\main\\data\\OscarsDataset.csv"))

    try {
    //Queries

    //Query 1
    println("-------------------------------------------------------------------------------------------------------")
    println("Query 1")
    println(s.OriginalStreamingDateDiff(filmsList))
    s.OriginalStreamingDateDiff(filmsList)
    println("-------------------------------------------------------------------------------------------------------")

    //Query 2
    println("Query 2")
    println(s.AllFilmsTitleContainsWord(filmsList))
    println("-------------------------------------------------------------------------------------------------------")
    //Query 3
    println("Query 3")
    println("All the films that won an Oscar :")
    println(s.AllWinnerFilms(filmsList))
    println("-------------------------------------------------------------------------------------------------------")

    //Query 4
    println("Query 4")
    println(s.AllFilmsOfACompany(filmsList))
    println("-------------------------------------------------------------------------------------------------------")

    //Query 5
    println("Query 5")
    println(s.AverageRating(filmsList))
    println("-------------------------------------------------------------------------------------------------------")



    //Processing services

    //PS 1
    println("PS 1")
      s.displayFilm(s.AddMovieInfo("7th Heaven", filmsList))
    println("-------------------------------------------------------------------------------------------------------")

    //PS 2
    println("PS 2")
      s.displayFilm(s.ModifyIMDBRatingAndCount("Wings", filmsList))
    println("-------------------------------------------------------------------------------------------------------")


    //PS 3
    println("PS 3")
      s.displayFilm(s.AddTomatoCritic("The Racket", filmsList))
    println("-------------------------------------------------------------------------------------------------------")


    //PS 4
    println("PS 4")
      s.displayFilm(s.ModifyTomatoAudienceRating("Wings", filmsList))
    println("-------------------------------------------------------------------------------------------------------")


    //PS 5
    println("PS 5")
    s.displayFilm(s.ModifyTomatoAndAudienceCritic("Wings", filmsList))
    println("-------------------------------------------------------------------------------------------------------")

    //PS 5
    println("PS 5")
    s.AddVoteCount("Wings", "The Broadway Melody", filmsList)
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
