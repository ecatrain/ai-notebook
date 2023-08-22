# AI Notebook Application

## Requirements

  * OpenJDK 17
  * Set JAVA17_HOME env variable pointing to jdk 17
  ```shell	
  export JAVA17_HOME=PATH_TO_JDK_17
  ```
  * Install [gradle](https://gradle.org/install)
  * Install [docker](https://docs.docker.com/engine/install/)
  * Install [docker-compose](https://docs.docker.com/compose/install/)

## Run the project

Please add this line to your /etc/hosts :
```shell
127.0.0.1 altheadev.net
```

Add your OpenAI API Key to properties :
```shell
openai.api.key=OPENAI_API_KEY
```

Then run this command :
```shell
make run
```

You should now have 6 running containers :

* **ai_notebook_core** - java spring boot backend application
* **ai_notebook_nginx** - nginx container to serve JS frontend and handle reverse proxying

[Gravitee Access Management](https://www.gravitee.io/platform/access-management) containers used to handle OAuth 2.0 Authenication (Gravitee AM acts as an Authorization Server) :

* **gio_am_webui**
* **gio_am_management**
* **gio_am_gateway**
* **gio_am_mongodb**

If you want to stop all containers :
```shell
make down
```

If you want to rebuild **ai_notebook_core** image and restart only java backend container :
```shell
make restart
```

## AI Notebook App

AI Notebook App is accessible via this URL :
```shell
https://altheadev.net/ai-notebook/app
```

