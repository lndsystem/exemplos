server {
        listen  80;
        server_name     "";
        location /web-inspire/ {
                proxy_pass http://localhost:8080/web-inspire/;
                proxy_set_header Host $host;
                proxy_set_header X-Real-IP $remote_addr;
        }

        location / {
                proxy_pass http://localhost:8080/web-inspire/;
                proxy_set_header Host $host;
                proxy_set_header X-Real-IP $remote_addr;
        }
}
