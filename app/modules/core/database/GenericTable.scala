package modules.core.database

import slick.driver.MySQLDriver.api._

abstract class GenericTable[T](tag: Tag, name: String) extends Table[T](tag, name) {
  def id = column[Int]("id", O.PrimaryKey, O.AutoInc)
}
