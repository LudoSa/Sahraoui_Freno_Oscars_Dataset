import scala.io.StdIn.readLine

trait DataExplorationServices{

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