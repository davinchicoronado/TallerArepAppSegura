FROM openjdk:8

WORKDIR /usrapp/bin

ENV PORT 6000

COPY /keyscerts  /usrapp/bin/keyscerts
COPY /target/classes /usrapp/bin/classes
COPY /target/dependency /usrapp/bin/dependency

CMD ["java","-cp","./classes:./dependency/*","edu.escuelaing.arep.app.ServerNews"]