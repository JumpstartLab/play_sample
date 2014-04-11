require 'sinatra'
get('/ping') { sleep 2; 'pong' }
