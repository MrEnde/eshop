#!/usr/bin/env sh

set -o errexit
set -o nounset

readonly cmd="$*"

auth_service () {
  # Check that auth-service is up and running on port `8080`:
  dockerize -wait 'tcp://auth-service:8080' -timeout 5s
}

discovery_service () {
  dockerize -wait 'tcp://discovery-service:8761' -timeout 5s
}

config_service () {
  dockerize -wait 'tcp://config-service:8421' -timeout 5s
}

secrets_service () {
  dockerize -wait 'tcp://secrets-service:8200' -timeout 5s
}

# We need this line to make sure that this container is started
# after the one with backend:
until secrets_service; do
  >&2 echo 'Secrets service is unavailable - sleeping'
done

>&2 echo 'Secrets service is up - continuing...'

until auth_service; do
  >&2 echo 'Auth service is unavailable - sleeping'
done

>&2 echo 'Auth service is up - continuing...'

until discovery_service; do
  >&2 echo 'Discovery service is unavailable - sleeping'
done

>&2 echo 'Discovery service is up - continuing...'

until config_service; do
  >&2 echo 'Config service is unavailable - sleeping'
done

>&2 echo 'Config service is up - continuing...'

## Evaluating passed command (do not touch):
exec $cmd