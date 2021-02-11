import socket
host = '127.0.0.1'
port = 65432
with socket.socket() as s:
	s.bind((host,port))
	s.listen()
	conn, addr = s.accept()
	with conn:
		print('COnnected by',addr)
		while True:
			data = conn.recv(1024)
			if not data:
				break
			conn.sendall(data)
