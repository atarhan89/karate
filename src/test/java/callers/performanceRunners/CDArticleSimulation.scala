package callers.performanceRunners

import com.intuit.karate.gatling.PreDef._
import io.gatling.core.Predef._
import scala.concurrent.duration._

class CDArticleSimulation extends Simulation {

  val protocol = karateProtocol()

 // protocol.nameResolver = (req, ctx) => req.getHeader("karate-name")
 val usersCount = System.getProperty("usersCount")
  val duration = System.getProperty("duration")
  val featureName = System.getProperty("featureName")
  val tagName = System.getProperty("tagName")

  val createArticle = scenario("Create An Article").feed(article).feed(tokenFeeder).exec(karateFeature("classpath:features/performanceFeatures/" +featureName +".feature@"+tagName+""))


  // mvn clean test-compile gatling:test -Dgatling.simulationClass=performanceRunners.CDArticleSimulation
  // mvn clean test-compile gatling:test -Dgatling.simulationClass=src.test.java.performanceRunners.CDArticleSimulation

  setUp(
    createArticle.inject(rampUsers(usersCount.toInt) during Duration(duration.toInt, SECONDS)).protocols(protocol)
  );
  // mvn clean test-compile gatling:test -Dgatling.simulationClass=performanceRunners.CDArticleSimulation
  // mvn clean test-compile gatling:test -DsimulationClass=performanceRunners.CDArticleSimulation



}