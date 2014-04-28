require 'sinatra'
sleep_time = ENV.fetch('SLEEP_TIME', 2).to_i

get('/ping') {
  sleep sleep_time
  'pong'
}
