package modules.core.database

import play.api.Play
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfig}
import slick.driver.JdbcProfile
import slick.lifted.{Rep, Compiled, TableQuery}
import slick.driver.MySQLDriver.api._
import scala.collection.immutable.Range.Inclusive
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

import scala.async.Async.{async, await}

trait Crud[C <: GenericTable[T], T] extends HasDatabaseConfig[JdbcProfile] {

  protected val table: TableQuery[C]
  type Model = C#TableElementType

  override protected val dbConfig = DatabaseConfigProvider.get[JdbcProfile](Play.current)

  private val queryById = Compiled((id: Rep[Int]) => table.filter(_.id === id))

  def all: Future[Seq[Model]] = db.run(table.result)

  def paginated[L <: slick.lifted.Rep[Boolean]]
    (filter: Option[C => L] = None, page: Int = 0, itemsPerPage : Int = 20): Future[Paginated] = async {

    val tableQuery = if (filter.isDefined) { table.filter(filter.get) } else { table }

    val count: Future[Int] = db.run(Compiled(tableQuery.length).result)

    val items: Future[Seq[Model]] = db.run(Compiled(tableQuery.drop(itemsPerPage * page).take(itemsPerPage)).result)

    new Paginated(await(items), page, itemsPerPage, await(count))
  }

  def create(c: Model): Future[Int] = db.run(table += c)

  def read(id: Int): Future[Option[Model]] = db.run(queryById(id).result.headOption)

  def update(id: Int, c: Model): Future[Int] = db.run(queryById(id).update(c))

  def delete(id: Int): Future[Int] = db.run(queryById(id).delete)

  case class Paginated(items : Seq[Model], page: Int, itemsPerPage: Int, totalItems: Int) {

    val lastPage = Math.ceil(totalItems.toDouble / itemsPerPage).toInt

    val pageRange: Inclusive = FirstPage to lastPage

    val nextPage : Int = if (page + 1 <= lastPage) page + 1 else FirstPage

    val previousPage : Int = if (page - 1 < FirstPage) lastPage else page - 1

  }

  private val FirstPage = 0
}

