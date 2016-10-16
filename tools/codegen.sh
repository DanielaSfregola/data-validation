#!/bin/sh
cd "$(dirname "$0")"
scala codegen.scala Accumulate > ../src/main/scala/app/validation/AccumulateArities.scala
