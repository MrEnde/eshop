#!/usr/bin/env sh

set -o errexit
set -o nounset

readonly cmd="$*"

## Evaluating passed command (do not touch):
exec $cmd