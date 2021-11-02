trait ProcessingServices{


  def ModifyTomatoAudienceRating(movie : String, films : List[Film]): Unit ={

    val test = films.filter(_.name.equals(movie))
    val count = test(0).audience.count.toInt + 1
    val rating = scala.util.Random
    val r = rating.nextInt(100)

    def userInput()={
      r match{
        case x if x > 60 => "Upright"
        case x if x < 60 => "Spilled"

      }
    }

    val audience = Audience(userInput(), r.toString, count.toString)
    val f = Film(test(0).name, test(0).award, test(0).information,test(0).contentRating, test(0).company, test(0).tomato, audience, test(0).releaseDate, test(0).originalReleaseDate, test(0).streamingReleaseDate)
    val t =  films.map(x => if(x.name == movie) f else x)

    t.foreach(println)


  }


  def ModifyMovieInfo(info : String, movie : String, films : List[Film]): List[Film] ={

    val test = films.filter(_.name.equals(movie))


    val f = Film(test(0).name, test(0).award, info,test(0).contentRating, test(0).company, test(0).tomato, test(0).audience, test(0).releaseDate, test(0).originalReleaseDate, test(0).streamingReleaseDate)


    //Poser la question au prof demain si c'est du High Order
    val modify = (x: Film) => if(x.name == movie && x.information.isEmpty) f else x

    //val t =  films.map(x => if(x.name == movie && x.information.isEmpty) f else x)
    val t =  films.map(modify)


    t

  }

  def ModifyContentRating(content : String, movie : String, films : List[Film]): List[Film] ={
    val test = films.filter(_.name.equals(movie))
    val f = Film(test(0).name, test(0).award, test(0).information,content, test(0).company, test(0).tomato, test(0).audience, test(0).releaseDate, test(0).originalReleaseDate, test(0).streamingReleaseDate)
    val modify = (x: Film) => if(x.name == movie  && x.information.isEmpty) f else x
    //val t =  films.map(x => if(x.name == movie  && x.information.isEmpty) f else x)
    val t = films.map(modify)

    t
  }


  def ModifyTomatoAndAudienceCritic(movie : String, films : List[Film]): Unit ={
    val test = films.filter(_.name.equals(movie))
    val count = test(0).tomato.count.toInt + 1
    val rating = scala.util.Random
    val r = rating.nextInt(100)

    def userInput()={
      r match{
        case x if x > 75 => "Certified-Fresh"
        case x if x > 60 && x < 75 => "Fresh"
        case x if x < 60 => "Rotten"
      }
    }

    val tomato = Tomato(userInput(), r.toString, count.toString)
    val f = Film(test(0).name, test(0).award, test(0).information,test(0).contentRating, test(0).company, tomato, test(0).audience, test(0).releaseDate, test(0).originalReleaseDate, test(0).streamingReleaseDate)
    val t =  films.map(x => if(x.name == movie) f else x)

    t.foreach(println)

  }

}
