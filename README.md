# Sockets

Simple networking demos are organized by protocol:

- `tcp/`
  - `SimpleServer.java`
  - `SimpleClient.java`
- `udp/`
  - `UDPServer.java`
  - `UDPClient.java`
- `Resources/`
  -`Questions/`
    -`ch22.md`
    -`c13.md`   
  - `ch22.pdf`

## Run the TCP demo

From [tcp](/Users/wuzi/Desktop/java%20workspace/Networking-Programming/Fun/tcp):

```bash
javac SimpleServer.java SimpleClient.java
java SimpleServer
```

In a second terminal from [tcp](/Users/wuzi/Desktop/java%20workspace/Networking-Programming/Fun/tcp):

```bash
java SimpleClient
```

## Run the UDP demo

From [udp](/Users/wuzi/Desktop/java%20workspace/Networking-Programming/Fun/udp):

```bash
javac UDPServer.java UDPClient.java
java UDPServer 6789
```

In a second terminal from [udp](/Users/wuzi/Desktop/java%20workspace/Networking-Programming/Fun/udp):

```bash
java UDPClient "hello" localhost 6789
```
