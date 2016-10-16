object ErrorCode extends Enumeration {
  type ErrorCode = Value
  val InvalidEmailFormat,
  PhoneTooShort,
  PhoneTooLong,
  PhoneMustBeNumeric = Value
}

import ErrorCode._
case class Err(code: ErrorCode, msg: String)

/** **** **/

case class Data(email: String, phone: String)

import cats.data._
import cats.implicits._

def validateEmail(email: String): ValidatedNel[Err, String] = {
  val emailRegex = """^[a-zA-Z0-9\.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$""".r
  email match{
    case e if emailRegex.findFirstMatchIn(e).isDefined  => Validated.valid(e)
    case _                                              => Validated.invalidNel(Err(ErrorCode.InvalidEmailFormat, "Invalid email format"))
  }
}

def validatePhone(phone: String): ValidatedNel[Err, String] = {
  val phoneRegex = """^\+(?:[0-9] ?){6,14}[0-9]$""".r
  phone match{
    case p if phoneRegex.findFirstMatchIn(p).isDefined  => Validated.valid(p)
    case _                                              => Validated.invalidNel(Err(ErrorCode.PhoneMustBeNumeric, "Phone number must be numeric"))
  }
}

def validateData(d: Data): ValidatedNel[Err, Data] = {
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
