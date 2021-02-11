import socket
host = '127.0.0.1'
port = 65432
with socket.socket() as s:
	s.connect((host,port))
	s.sendall(b'hello')
	data = s.recv(1024)
print('received',repr(data))
