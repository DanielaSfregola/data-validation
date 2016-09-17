package com.danielasfregola.datavalidation

import cats.data.Validated.{Invalid, Valid}
import cats.data.ValidatedNel

trait DataValidation[E] extends CombineArities[E] {

  type Validation[T] = ValidatedNel[E, T]

  def success[T](t: T): Validation[T] = Valid(t)

  def failure[T](e: E): Validation[T] = Invalid(e).toValidatedNel
}


