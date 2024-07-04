import socket

def main():
    server_port = 7896
    server_host = '172.31.46.21'
    
    try:
        # Create a TCP/IP socket
        client_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

        # Connect the socket to the server
        client_socket.connect((server_host, server_port))

        # Wrap the socket with a file-like object for easier I/O operations
        in_stream = client_socket.makefile('r')
        out_stream = client_socket.makefile('w')

        # Get user input
        data = input("Digite uma frase: ")

        # Send the data to the server
        out_stream.write(data + '\n')
        out_stream.flush()

        # Receive the response from the server
        response = in_stream.readline().strip()
        print("Received:", response)
    
    except socket.error as e:
        print(f"Socket error: {e}")
    except IOError as e:
        print(f"IO error: {e}")
    finally:
        # Close the socket
        client_socket.close()

if __name__ == "__main__":
    main()