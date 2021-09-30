#!/usr/bin/env sh

set -o errexit
set -o nounset

readonly cmd="$*"

gateway_ready () {
  # Check that backend is up and running on port `5432`:
  dockerize -wait 'tcp://gateway-service:8000' -timeout 5s
}

# We need this line to make sure that this container is started
# after the one with backend:
until gateway_ready; do
  >&2 echo 'Backend is unavailable - sleeping'
done

>&2 echo 'Gateway-service is up - continuing...'

## Evaluating passed command (do not touch):
exec $cmd