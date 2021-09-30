FROM openjdk:16-slim-buster AS build

ARG MODULES
ARG PROJECT_NAME

COPY . /home/code/

WORKDIR /home/code/

RUN ./mvnw -q clean compile install package -pl ${MODULES} -am

FROM openjdk:16-slim-buster

ARG SERVICE_NAME
ARG PROJECT_NAME

ENV PROJECT_NAME=${PROJECT_NAME} \
  BUILD_ONLY_PACKAGES='wget' \
  DOCKERIZE_VERSION=v0.6.1 \
  TINI_VERSION=v0.19.0

# System deps:
RUN apt-get update && apt-get upgrade -y \
  && apt-get install --no-install-recommends -y \
    # Defining build-time-only dependencies:
    $BUILD_ONLY_PACKAGES \
  # Installing `dockerize` utility:
  # https://github.com/jwilder/dockerize
  && wget "https://github.com/jwilder/dockerize/releases/download/${DOCKERIZE_VERSION}/dockerize-linux-amd64-${DOCKERIZE_VERSION}.tar.gz" \
  && tar -C /usr/local/bin -xzvf "dockerize-linux-amd64-${DOCKERIZE_VERSION}.tar.gz" \
  && rm "dockerize-linux-amd64-${DOCKERIZE_VERSION}.tar.gz" && dockerize --version \
  # Installing `tini` utility:
  # https://github.com/krallin/tini
  && wget -O /usr/local/bin/tini "https://github.com/krallin/tini/releases/download/${TINI_VERSION}/tini" \
  && chmod +x /usr/local/bin/tini && tini --version \
  # Removing build-time-only dependencies:
  && apt-get remove -y $BUILD_ONLY_PACKAGES \
  # Cleaning cache:
  && apt-get purge -y --auto-remove -o APT::AutoRemove::RecommendsImportant=false \
  && apt-get clean -y && rm -rf /var/lib/apt/lists/*

WORKDIR /code

# This is a special case. We need to run this script as an entry point:
COPY ./docker/service/scripts/${PROJECT_NAME}/entrypoint.sh /docker-entrypoint.sh
COPY ./docker/service/server /usr/local/bin/server
COPY --from=build /home/code/${PROJECT_NAME}/target/${PROJECT_NAME}-exec.jar /code/${PROJECT_NAME}.jar

# Setting up proper permissions:
RUN chmod +x '/docker-entrypoint.sh' \
  && chmod +x '/usr/local/bin/server' \
  && groupadd -r ${SERVICE_NAME} && useradd -d /code -r -g ${SERVICE_NAME} ${SERVICE_NAME} \
  && chown ${SERVICE_NAME}:${SERVICE_NAME} -R /code

# Running as non-root user:
USER ${SERVICE_NAME}

# We customize how our app is loaded with the custom entrypoint:
ENTRYPOINT ["tini", "--", "/docker-entrypoint.sh"]