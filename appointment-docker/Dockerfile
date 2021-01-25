FROM ubuntu:latest
RUN apt-get update
ENV DEBIAN_FRONTEND=noninteractive
RUN apt-get install -y nodejs postgresql postgresql-contrib openjdk-8-jdk maven git supervisor npm dos2unix vim telnet 

#pgsql
COPY pgsql /pgsql
RUN chmod +x /pgsql/*.sh
RUN dos2unix /pgsql/*.sh
RUN sed -i 's/port = 5432/port = 5433/' /etc/postgresql/12/main/postgresql.conf

#build frontend app
ARG GIT_USERNAME
ARG GIT_PASSWORD
COPY app /app
RUN chmod +x /app/*.sh
RUN dos2unix /app/*.sh
RUN git config --global http.sslVerify false
RUN git clone --depth 1 --single-branch --branch feature-rc4 https://${GIT_USERNAME}:${GIT_PASSWORD}@github.com/jslsolucoes/open-chatbot.git /opt/open-chatbot
RUN rm -rf /opt/open-chatbot/app/.env*
RUN npm install -g serve
RUN printf "REACT_APP_URL_BASE=https://appointment-api.jslsolucoes.com/api/v1\nREACT_APP_BOT_ID=bots\nREACT_APP_LOCALE=pt_br\n" > /opt/open-chatbot/app/.env
RUN cd /opt/open-chatbot/app && npm install && npm run build

#build engine
RUN cd /opt/open-chatbot && mvn install

#build backend appointment api and bot
COPY api /api
RUN chmod +x /api/*.sh
COPY bot /bot
RUN chmod +x /bot/*.sh
RUN dos2unix /api/*.sh
RUN dos2unix /bot/*.sh
RUN git clone --depth 1 --single-branch --branch main https://github.com/jslsolucoes/appointment /opt/appointment
RUN cd /opt/appointment && mvn install
COPY bot/env.properties /opt/appointment/appointment-bot/target/env.properties

#ports
EXPOSE 5001
EXPOSE 8080

#supervisor
COPY supervisord.conf /etc/supervisord.conf
CMD ["/usr/bin/supervisord"]
  
  