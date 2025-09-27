# java-vs-rust-api

I want to compare code and memory usage of two applications.

The first application, [java-app](./java-app) is written in Java, uses SpringBoot.

The second application, [rust-app](./rust-app) is written in Rust.

## Results:
WIP

## Building docker image

For rust-app it takes over 30 seconds to compile it.
For java-app it takes 3 seconds for maven build, total maven execution from start is less than 5 seconds.
Docker build takes less than 6 second.

containers:
```shell
PS C:\Dev\workspaces\training\java-vs-rust-api> docker ps
CONTAINER ID   IMAGE          COMMAND                  CREATED         STATUS         PORTS                                         NAMES
1e3aba6da2b9   rust-app       "./rust-app"             2 minutes ago   Up 2 minutes   0.0.0.0:8081->8081/tcp, [::]:8081->8081/tcp   cranky_einstein
7227bf30007e   1c1bed78e444   "java -jar java-app.…"   2 hours ago     Up 2 hours     0.0.0.0:8080->8080/tcp, [::]:8080->8080/tcp   suspicious_almeida
```

Stats:
```shell
CONTAINER ID   NAME                 CPU %     MEM USAGE / LIMIT     MEM %     NET I/O           BLOCK I/O        PIDS 
1e3aba6da2b9   cranky_einstein      0.18%     8.934MiB / 31.27GiB   0.03%     3.46kB / 3.47kB   2.33MB / 0B      22 
7227bf30007e   suspicious_almeida   0.09%     254.6MiB / 31.27GiB   0.79%     5.7kB / 4.88kB    2.1MB / 1.56MB   51 
```