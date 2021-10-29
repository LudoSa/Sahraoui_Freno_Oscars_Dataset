import com.github.tototoshi.csv.CSVReader

import java.io.File


object Launcher {
  def main(args: Array[String]): Unit = {
    val reader = CSVReader.open(new File("src/main/OscarsDataset.csv"))
  }
}
