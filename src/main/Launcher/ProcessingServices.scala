import scala.concurrent.Future
import scala.io.StdIn.readLine
import scala.util.{Failure, Random, Success}

trait ProcessingServices{

  //Add vote to films with a future, to simulate people that are voting at different moment
  def AddVoteCount(movie1 : String, movie2 : String, films : List[Film])={

    implicit val ec: scala.concurrent.ExecutionContext = scala.concurrent.ExecutionContext.global

    val m1 = films.filter(_.name.equals(movie1))

    val m2 = films.filter(_.name.equals(movie2))

    val filmList = List(m1(0), m2(0))

    val movieName1 = m1(0).name
    val movieName2 = m2(0).name


    var countM1 = filmList(0).audience.count.toDouble
    var countM2 = filmList(1).audience.count.toDouble

    def shortOperation={

      Thread.sleep(1)
      countM1 = countM1 + 1
      println(s"Number of vote for ${movieName1} : $countM1 votes")
    }

    def longOperation(time:Int=Random.nextInt(5))={
      Thread.sleep(time*1000)
      countM2 = countM2 + 1

      println(s"Number of vote for ${movieName2} : $countM2 votes")

    }

    Future(longOperation())
    Future(longOperation())
    Future(longOperation())
    Future(longOperation())
    shortOperation
    shortOperation
    shortOperation

    val f = Future(longOperation())

    f.onComplete {
      case Success(result) =>  println(s"We got $result")
      case Failure(t) => println("An error has occurred: " + t.getMessage)
    }

    Thread.sleep(4000)


  }


  def ModifyTomatoAudienceRating(movie : String, films : List[Film]): List[Film] ={

    val filmList = films.filter(_.name.equals(movie))
    val count = filmList(0).audience.count.toDouble + 1
    val rating = scala.util.Random
    val r = rating.nextInt(100)

    def userInput()={
      r match{
        case x if x > 60 => "Upright"
        case x if x < 60 => "Spilled"

      }
    }

    val audience = Audience(userInput(), r.toString, count.toString)

    val f = Film(filmList(0).name, filmList(0).award, filmList(0).information,filmList(0).contentRating, filmList(0).company, filmList(0).imdb, filmList(0).tomato, audience, filmList(0).releaseDate, filmList(0).originalReleaseDate, filmList(0).streamingReleaseDate)
    val t =  films.map(x => if(x.name == movie) f else x)

    t


  }


  def AddMovieInfo(movie : String, films : List[Film]): List[Film] ={

    val filmTest = films.filter(_.name.equals(movie))


    println("Add information to a film :")

   println("Enter the tomatometer count : ")

    //user input
    val info : String = readLine()


    val f = Film(filmTest(0).name, filmTest(0).award, info,filmTest(0).contentRating, filmTest(0).company, filmTest(0).imdb,filmTest(0).tomato, filmTest(0).audience, filmTest(0).releaseDate, filmTest(0).originalReleaseDate, filmTest(0).streamingReleaseDate)

    //Modified list with the modify film only if no info
    val t =  films.map(x => if(x.name == movie && x.information.isEmpty) f else x)

    t

  }

  //Modify the content rating of a film
  def ModifyContentRating(content : String, movie : String, films : List[Film]): List[Film] ={

    val filmList = films.filter(_.name.equals(movie))


    val f = Film(filmList(0).name, filmList(0).award, filmList(0).information,content, filmList(0).company, filmList(0).imdb, filmList(0).tomato, filmList(0).audience, filmList(0).releaseDate, filmList(0).originalReleaseDate, filmList(0).streamingReleaseDate)

    val modify = (x: Film) => if(x.name == movie  && x.information.isEmpty) f else x

    val t = films.map(modify)

    t
  }

  //Add a Tomatomer critic
  def AddTomatoCritic(movie : String, films : List[Film]): List[Film] = {

    println("Add Tomato critic :")
    val filmList = films.filter(_.name.equals(movie))

    println("Enter the tomatometer count : ")

    //user input
    val countInput : String = readLine()

    println("Enter the tomatometer rating : ")

    val ratingInput : String = readLine()

    try{
      //Tomato status is defined by the tomato rating
      def userInput() ={
        ratingInput.toInt match{
          case x if x > 75 => "Certified-Fresh"
          case x if x > 60 && x < 75 => "Fresh"
          case x if x < 60 => "Rotten"
        }
      }

      val tomato = Tomato(userInput(), ratingInput, countInput)

      //modify the film
      val f = Film(filmList(0).name, filmList(0).award, filmList(0).information,filmList(0).contentRating, filmList(0).company, filmList(0).imdb,tomato, filmList(0).audience, filmList(0).releaseDate, filmList(0).originalReleaseDate, filmList(0).streamingReleaseDate)

      //display only film name
      val t =  films.map(x => if(x.name == movie) f else x)

      t
    }
    catch{
      case ex: NumberFormatException => println("Invalid format number. Please enter numbers.")
        filmList
    }






  }

  def ModifyTomatoAndAudienceCritic(movie : String, films : List[Film]): List[Film] ={
    val test = films.filter(_.name.equals(movie))
    val count = test(0).tomato.count.toDouble + 1
    val rating = scala.util.Random
    val r = rating.nextInt(100)

    //Tomato status is defined by the tomato rating
    def userInput()={
      r match{
        case x if x > 75 => "Certified-Fresh"
        case x if x > 60 && x < 75 => "Fresh"
        case x if x < 60 => "Rotten"
      }
    }

    val tomato = Tomato(userInput(), r.toString, count.toString)

    val f = Film(test(0).name, test(0).award, test(0).information,test(0).contentRating, test(0).company, test(0).imdb, tomato, test(0).audience, test(0).releaseDate, test(0).originalReleaseDate, test(0).streamingReleaseDate)

    //Modified list with the modify film
    val t =  films.map(x => if(x.name == movie) f else x)

    t



  }

  def ModifyIMDBRatingAndCount(movie : String, films : List[Film]):List[Film] ={
    val test = films.filter(_.name.equals(movie))
    val number = test(0).imdb.count.replace(",", "")
    val count = number.toInt + 1
    val rating = scala.util.Random
    val r = rating.nextInt(10)

    val newImdb = IMDB(r.toString, count.toString)
    val f = Film(test(0).name, test(0).award, test(0).information,test(0).contentRating, test(0).company,newImdb, test(0).tomato, test(0).audience, test(0).releaseDate, test(0).originalReleaseDate, test(0).streamingReleaseDate)

    //Modified list with the modify film
    val t = films.map(x => if(x.name == movie) f else x)

    t



  }

}
