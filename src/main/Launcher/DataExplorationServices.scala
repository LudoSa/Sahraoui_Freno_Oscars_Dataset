import java.time.LocalDate
import java.time.temporal.ChronoUnit
import scala.io.StdIn.readLine

trait DataExplorationServices{

  /*
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

  }*/
  
  def OriginalStreamingDateDiff(films: List[Film]) ={


    println("Calculate the difference between original and streaming date of the film : (e.g : Wings)")

    val filmName = readLine()

    val filmsToUse =  films.filter(_.name.equals(filmName))

    val originalDate = LocalDate.parse(filmsToUse(0).originalReleaseDate)

    val streamingDate = LocalDate.parse(filmsToUse(0).streamingReleaseDate)


    if(filmsToUse.nonEmpty){
      if(originalDate.equals("") || streamingDate.equals("")){
        println("No difference available. Missing data")
      }
      else {

        val daysDiff = ChronoUnit.DAYS.between(originalDate, streamingDate)
        val monthsDiff = ChronoUnit.MONTHS.between(originalDate, streamingDate)
        val yearsDiff = ChronoUnit.YEARS.between(originalDate, streamingDate)

        println(s"Difference between the dates : $daysDiff DAYS | $monthsDiff MONTHS | $yearsDiff YEARS")
      }

    }
    else{
      println(s"No film's found with title $filmName")
    }


  }

  //find a film that contains a word
  def AllFilmsTitleContainsWord(films : List[Film])  = {


    println("Search a film's title that contain this word : (e.g : Love, The)")
    val word = readLine()
    val filmsWithWord =  films.filter(_.name.contains(word)).map(x => x.name)

    if(filmsWithWord.nonEmpty){
      filmsWithWord
    }
    else{
      println(s"No film's title contains the word $filmsWithWord")
    }
  }


  //Find all the winner
  def AllWinnerFilms(films : List[Film]) : List[String] = {
    films.filter(_.award.equals("Winner")).map(x => x.name)
  }

  //Find all the film of a company
  def AllFilmsOfACompany(films : List[Film])  = {
    print("Search a all the film of a company : (e.g : Fox)")
    val company = readLine()
    val filmsByCompany =  films.filter(_.company.contains(company)).map(x => x.name)

    if(filmsByCompany.nonEmpty){
      filmsByCompany
    }
    else{
      println(s"No film's found for the company $company")
    }
  }

  //Find all the film that are "Certified Fresh"
  def AllCertifiedFreshFilms(films : List[Film]) : List[String] = {
    films.filter(_.tomato.status.equals("Certified-Fresh")).map(x => x.name)
  }

  //Calculate the average of tomato rating and audiance rating
  def AverageRating(films : List[Film])  = {

    //User Input
    println("Calculate the average rating of the film : (e.g : Wings)")

    val filmName = readLine()

    val filmsToCalculate =  films.filter(_.name.equals(filmName))

    val tomatoRating = filmsToCalculate(0).tomato.rating

    val audienceRating = filmsToCalculate(0).audience.rating


    //Test if there is data
    if(filmsToCalculate.nonEmpty){
      if(tomatoRating.equals("") || audienceRating.equals("")){
        println("No average available. Missing data")
      }
      else {
        val average = (tomatoRating.toDouble + audienceRating.toDouble) / 2
        println(average)
      }

    }
    else{
      println(s"No film's found with title $filmName")
    }





  }
}