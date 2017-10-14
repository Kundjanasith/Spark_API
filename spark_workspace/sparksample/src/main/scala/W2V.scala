import org.apache.spark.mllib.feature.{Word2Vec, Word2VecModel}
import org.apache.spark._

object W2V{
    def main ( args : Array[String] ){
	val sc = new SparkContext(new SparkConf().setAppName("W2V").set("spark.executor.cores","4"))
        val input = sc.textFile(args(0)).map(line => line.split(" ").toSeq)
        val word2vec = new Word2Vec()
        val model = word2vec.fit(input)
        val synonyms = model.findSynonyms("A", 5)
        for((synonym, cosineSimilarity) <- synonyms) {
  		println(s"$synonym $cosineSimilarity")
 	}
    }
}
