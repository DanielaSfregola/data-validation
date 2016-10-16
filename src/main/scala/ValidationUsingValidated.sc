import cats.data.Validated
import cats.implicits._

case class Data(email: String, phone: String)

def validateEmail(email: String): Validated[List[String], String] = {
  val emailRegex = """^[a-zA-Z0-9\.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$""".r
  email match{
    case e if emailRegex.findFirstMatchIn(e).isDefined  => Validated.valid(e)
    case _                                              => Validated.invalid(List("Invalid email format"))
  }
}

def validatePhone(phone: String): Validated[List[String], String] = {
  val phoneRegex = """^\+(?:[0-9] ?){6,14}[0-9]$""".r
  phone match{
    case p if phoneRegex.findFirstMatchIn(p).isDefined  => Validated.valid(p)
    case _                                              => Validated.invalid(List("Phone number must be numeric"))
  }
}

def validateData(d: Data): Validated[List[String], Data] = {
  val validEmail = validateEmail(d.email)
  val validPhone = validatePhone(d.phone)

  (validEmail |@| validPhone).map(Data)
}

/** *** ***/

val okEmail = "email@email.com"
val badEmail = "email.com"

val okPhone = "+1 1234567890123"
val badPhone = "not-a-valid-phone"

validateData(Data(okEmail, okPhone))
validateData(Data(badEmail, badPhone))
validateData(Data(okEmail, badPhone))
validateData(Data(badEmail, okPhone))

