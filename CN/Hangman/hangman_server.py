import sys
import random
from socket import *
import csv
import threading
user_data = {}
WOR_D = random.choice(open('words.txt').read().split())
print(WOR_D)
# The word to be guessed
WORDGUESSED = []
LENGTH = len(WOR_D)
ALPHABET = "abcdefghijklmnopqrstuvwxyz"
# stores the letters guessed
LETTERS_GUESSED = []



def update_data():
    """
    This method update the dictionary
    """
    with open("database.csv", "r") as csv_file:
        csv_data = csv.reader(csv_file, delimiter = ",")
        for row in csv_data:
            user_data[row[0]] = row[1]
            
def add_user(user_name):
        """
        This method adds the user to the data base
        """
        data = user_name.split(",")
        with open("database.csv", "a+") as csv_file:
            writer = csv.writer(csv_file)
            writer.writerow([data[0],data[1]])
        # updating the local user dictionary
        user_data[data[0]] = data[1]
        
def start():
    """ this function greets the user.
    """
    welcome_message = '''Welcome to the Hangman Game\n Are you a new user? '''
    connectionSocket.sendall(welcome_message.encode())
    user_response = connectionSocket.recv(1024).decode()
    user_response = user_response.lower()
    user_id = ""
    if user_response in ('yes', 'y'):
        server_msg = "please enter your user id and user name: "
        message = server_msg.encode()
        connectionSocket.sendall(message)
        user_id_rsp = connectionSocket.recv(1024).decode()
        # adding the user id to database
        add_user(user_id_rsp)
        user_id = user_id_rsp.split(",")[0]
        server_msg = "Welcome to the game " + user_data[user_id]
        connectionSocket.sendall(server_msg.encode())
    elif user_response in ('no', 'n'):
        while True:
            user_id_msg = "please enter your user id: ".encode()
            connectionSocket.sendall(user_id_msg)
            user_id_rsp = connectionSocket.recv(1024).decode()
            user_id = user_id_rsp
            print(user_id_rsp)
            # check to see of the user has entered a valid user name
            if user_id_rsp not in user_data:
                connectionSocket.sendall("Username not present, try again?".encode())
                if connectionSocket.recv(1024).decode() in ('yes', 'y'):
                    continue
                else:
                    break
            else:
                break
        server_msg = "Starting the game."
        connectionSocket.sendall(server_msg.encode())
    print(user_data)
            
def is_word_guessed():
    """ This function gives the dashes which are printed.
    """
    len_word = len(WOR_D)
    while len_word > 0:
        WORDGUESSED.append('_ ')
        len_word = len_word - 1
    print("".join(WORDGUESSED))
    return("".join(WORDGUESSED))

def check(guess):
    """ This function checks whether the guess is right or not.
    """
    print("You guessed correctly")
    # checking the letter with the correct word
    for x_x in range(LENGTH):
        if WOR_D[x_x] == guess:
            WORDGUESSED[x_x] = guess
    print("".join(WORDGUESSED))
    return("".join(WORDGUESSED))
    # checking if all the spaces are filled

def get_available_letters():
    """ This function gives the remaining alphabets after removing the entered guesses.
    """
    alp = ''
    A = list(ALPHABET)
    for i in LETTERS_GUESSED:
      if i in A:
        alp = A.remove(i)
    print("".join(A))  
    return("".join(A))

def get_guessed_word():
    """ This function gives the word guessed and calls the other functions.
    """
    message = is_word_guessed()
    connectionSocket.send(message.encode())
    msg = " Your word has " + str(LENGTH) +" characters "
    print(msg)
    connectionSocket.send(msg.encode())
    guess_taken = 1
    cnt = 8
    while guess_taken <= 8:
        msg1 = " You have " + str(cnt) + " guesses left \n"
        print(msg1)
        connectionSocket.send(msg1.encode())
        msg2 = " Enter Your Guess: "
        connectionSocket.send(msg2.encode())
        guess = connectionSocket.recv(1024).decode()
        if guess == '':
            msg3 = " You did not enter anything... "
            print(msg3)
            connectionSocket.send(msg3.encode())
        elif len(guess) == 1:
            guess = guess.lower()
            if guess not in ALPHABET:
                msg4 = "Enter an alphabet between a-z"
                print(msg4)
                connectionSocket.send(msg4.encode())
            elif guess in LETTERS_GUESSED:
                msg5 = " You already guessed that letter "
                print(msg5)
                connectionSocket.send(msg5.encode())
            else:
                LETTERS_GUESSED.append(guess)
                available_letters = get_available_letters()
                connectionSocket.send(available_letters.encode())
                if guess in WOR_D:
                    chk = check(guess)
                    connectionSocket.send(chk.encode())
                    if '_ ' not in WORDGUESSED:
                        msg6 = " You Won "
                        print(msg6)
                        connectionSocket.send(msg6.encode())
                        break
                else:
                    msg7 = " Wrong guess  " + "".join(WORDGUESSED)
                    print(msg7)
                    connectionSocket.send(msg7.encode())
                    guess_taken += 1
                    cnt = cnt - 1

    if cnt == 0:
        msg8 = " Sorry, you lost "
        print(msg8)
        connectionSocket.send(msg8.encode())
    msgs = " The secret word is " + WOR_D
    print(msgs)
    connectionSocket.send(msgs.encode())
    print("\nGame Over\n")
     
host = '127.0.0.1'
serverPort = 5555
serverSocket = socket(AF_INET, SOCK_STREAM)
serverSocket.bind((host, serverPort))
serverSocket.listen()

print ("The server is ready to receive")
#update_data()
while True:
    connectionSocket, addr = serverSocket.accept()
    print("Connecter by: ", addr)
    update_data()
    print(user_data)
    start()
    threading._start_new_thread(get_guessed_word,())
    #get_guessed_word()
