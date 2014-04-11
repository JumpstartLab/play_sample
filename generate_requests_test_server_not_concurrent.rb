require 'socket'

sleep_time = ENV.fetch('SLEEP_TIME', 2).to_i

begin
  socket = TCPServer.new 4567
  loop {
    connection = socket.accept
    begin
      raw_http_request = connection.recv 2048
      puts raw_http_request.lines.first # "GET /ping http1.1
      sleep sleep_time
      connection.write "HTTP/1.1 200 OK\r\n"                               \
                       "Content-Type: text/plain;charset=utf-8\r\n"        \
                       "Content-Length: 4\r\n"                             \
                       "Server: A socket in a loop <3\r\n" \
                       "Date: Fri, 11 Apr 2014 17:07:57 GMT\r\n"           \
                       "Connection: Keep-Alive\r\n"                        \
                       "\r\n"                                              \
                       "pong\r\n"
    ensure
      connection.close
    end
  }
ensure
  socket.close
end
