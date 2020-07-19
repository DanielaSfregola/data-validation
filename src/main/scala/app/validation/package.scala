package app

import cats.data._

package object validation {

  type Validation[T] = ValidatedNec[Err, T]

}
