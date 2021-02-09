import sys
# Import socket library
import socket

def responses(clientSocket):
    print(clientSocket.recv(1024))
    print(clientSocket.recv(1024))
    g = 1
    while True: 
        print(clientSocket.recv(1024))
        guess = input('').encode()
        clientSocket.send(guess)
        print(clientSocket.recv(1024))
        print(clientSocket.recv(1024))
    print(clientSocket.recv(1024))
    print(clientSocket.recv(1024))

def communication(serverName,serverPort):
    with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as clientSocket:
        clientSocket.connect((serverName, serverPort))
        print(clientSocket.recv(1024).decode())
        resp = input()
        clientSocket.sendall(resp.encode())
        # code to send the user details and check the validity of the user
        while True:
            server_msg = clientSocket.recv(1024).decode()
            print(server_msg) # receive for user id
            clientSocket.sendall(input().encode())
            valid_user_msg = clientSocket.recv(1024).decode()
            print(valid_user_msg)
            if "try again" in valid_user_msg:
                try_again = input("try again?")
                clientSocket.sendall(try_again.encode())
                if try_again in ('yes','y'):
                    continue
                else:
                    break
            else:
                break
        responses(clientSocket)

if __name__ == "__main__":
    serverName = '127.0.0.1'
    serverPort = 5555
    communication(serverName, serverPort)