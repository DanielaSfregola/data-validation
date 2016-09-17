#!/bin/sh
cd "$(dirname "$0")"
scala codegen.scala Combine > ../src/main/scala/com/danielasfregola/datavalidation/CombineArities.scala
