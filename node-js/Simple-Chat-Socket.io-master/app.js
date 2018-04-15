let app = require('express')();
let http = require('http').Server(app);
let io = require('socket.io')(http);

let clients = {}; 
let port = process.env.PORT || 3000;

app.get('/', (req, res) => {
  res.send('server is running');
});

io.on("connection", (client) => {  
  console.log(client.id + " entrou!");

  client.on("join", (name) => {
    console.log("Joined: " + name);
    clients[client.id] = name;
    client.emit("update", "You have connected to the server.");
    client.broadcast.emit("update", name + " has joined the server.")
  });

  client.on("send", (msg) => {
    console.log("Message: " + msg);
    client.broadcast.emit("chat", clients[client.id], msg);
  });

  client.on("disconnect", () => {
    console.log("Disconnect");
    io.emit("update", clients[client.id] + " has left the server.");
    delete clients[client.id];
  });
});


http.listen(port, () => {
  console.log('listening on port ' + port);
});
