# See https://github.com/nginxinc/docker-nginx/tree/master/modules
FROM nginx:1.20.0 as module_builder

ARG ENABLED_MODULES

RUN set -ex \
    && if [ "$ENABLED_MODULES" = "" ]; then \
        echo "No additional modules enabled, exiting"; \
        exit 1; \
    fi

COPY ./docker/nginx/modules/ /modules/

RUN set -ex \
    && apt update \
    && apt install -y --no-install-suggests --no-install-recommends \
                patch make wget mercurial devscripts debhelper dpkg-dev \
                quilt lsb-release build-essential libxml2-utils xsltproc \
                equivs git g++ \
    && hg clone -r ${NGINX_VERSION}-${PKG_RELEASE%%~*} https://hg.nginx.org/pkg-oss/ \
    && cd pkg-oss \
    && mkdir /tmp/packages \
    && for module in $ENABLED_MODULES; do \
        echo "Building $module for nginx-$NGINX_VERSION"; \
        if [ -d /modules/$module ]; then \
            echo "Building $module from user-supplied sources"; \
            # check if module sources file is there and not empty
            if [ ! -s /modules/$module/source ]; then \
                echo "No source file for $module in modules/$module/source, exiting"; \
                exit 1; \
            fi; \
            # some modules require build dependencies
            if [ -f /modules/$module/build-deps ]; then \
                echo "Installing $module build dependencies"; \
                apt update && apt install -y --no-install-suggests --no-install-recommends $(cat /modules/$module/build-deps | xargs); \
            fi; \
            # if a module has a build dependency that is not in a distro, provide a
            # shell script to fetch/build/install those
            # note that shared libraries produced as a result of this script will
            # not be copied from the builder image to the main one so build static
            if [ -x /modules/$module/prebuild ]; then \
                echo "Running prebuild script for $module"; \
                /modules/$module/prebuild; \
            fi; \
            /pkg-oss/build_module.sh -v $NGINX_VERSION -f -y -o /tmp/packages -n $module $(cat /modules/$module/source); \
            BUILT_MODULES="$BUILT_MODULES $(echo $module | tr '[A-Z]' '[a-z]' | tr -d '[/_\-\.\t ]')"; \
        elif make -C /pkg-oss/debian list | grep -P "^$module\s+\d" > /dev/null; then \
            echo "Building $module from pkg-oss sources"; \
            cd /pkg-oss/debian; \
            make rules-module-$module BASE_VERSION=$NGINX_VERSION NGINX_VERSION=$NGINX_VERSION; \
            mk-build-deps --install --tool="apt-get -o Debug::pkgProblemResolver=yes --no-install-recommends --yes" debuild-module-$module/nginx-$NGINX_VERSION/debian/control; \
            make module-$module BASE_VERSION=$NGINX_VERSION NGINX_VERSION=$NGINX_VERSION; \
            find ../../ -maxdepth 1 -mindepth 1 -type f -name "*.deb" -exec mv -v {} /tmp/packages/ \;; \
            BUILT_MODULES="$BUILT_MODULES $module"; \
        else \
            echo "Don't know how to build $module module, exiting"; \
            exit 1; \
        fi; \
    done \
    && echo "BUILT_MODULES=\"$BUILT_MODULES\"" > /tmp/packages/modules.env

FROM nginx:1.20.0

COPY --from=module_builder /tmp/packages /tmp/packages

RUN set -ex \
    && apt update \
    && . /tmp/packages/modules.env \
    && for module in $BUILT_MODULES; do \
           apt install --no-install-suggests --no-install-recommends -y /tmp/packages/nginx-module-${module}_${NGINX_VERSION}*.deb; \
       done \
    && rm -rf /tmp/packages \
    && rm -rf /var/lib/apt/lists/

ENV BUILD_ONLY_PACKAGES='wget' \
  DOCKERIZE_VERSION=v0.6.1 \
  TINI_VERSION=v0.19.0

RUN apt-get update && apt-get upgrade -y \
  && apt-get install --no-install-recommends -y \
    python3-acme \
    python3-certbot \
    python3-mock \
    python3-openssl \
    python3-pkg-resources \
    python3-pyparsing \
    python3-zope.interface \
    python3-certbot-nginx \
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

COPY ./docker/nginx/entrypoint.sh /docker-entrypoint.sh

RUN chmod +x '/docker-entrypoint.sh'

RUN mkdir -p /var/nginx/client_body_temp /var/nginx/proxy_temp  \
  && chown nobody -R /var/nginx/ /var/log/ \
  && chmod -R 755 /var/nginx/ /var/log/

RUN mkdir -p /var/cache/nginx/proxy_temp /var/cache/nginx/client_temp /var/cache/nginx/fastcgi_temp /var/cache/nginx/uwsgi_temp /var/cache/nginx/scgi_temp \
  && chown nobody -R /var/cache/nginx/ \
  && chmod -R 755 /var/cache/nginx/

RUN mkdir -p /etc/letsencrypt/ /var/lib/letsencrypt \
  && chown nobody -R /etc/letsencrypt/ /var/lib/letsencrypt \
  && chmod -R 755 /etc/letsencrypt/ /var/lib/letsencrypt

RUN touch /var/run/nginx.pid \
  && chown -R nobody /var/run/nginx.pid

USER nobody

ENTRYPOINT ["tini", "--", "/docker-entrypoint.sh"]
