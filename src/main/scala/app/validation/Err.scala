package app.validation

sealed trait Err {
  val code: String
  val msg: String
  val values: Seq[AnyRef]
}

case class BadRequest(code: String, msg: String) extends Err {
  val values = Seq.empty
}
case class NotFound(code: String, msg: String, values: Seq[AnyRef]) extends Err
