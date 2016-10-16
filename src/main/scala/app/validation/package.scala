package app

import cats.data._

package object validation {

  type Validation[T] = ValidatedNel[Err, T]

}
