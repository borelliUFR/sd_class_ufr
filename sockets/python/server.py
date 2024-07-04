import socket
import threading

class Connection(threading.Thread):
    def __init__(self, client_socket):
        threading.Thread.__init__(self)
        self.client_socket = client_socket
        self.in_stream = client_socket.makefile('r')
        self.out_stream = client_socket.makefile('w')
        self.start()

    def run(self):
        try:
            data = self.in_stream.readline().strip()
            print(data)
            self.out_stream.write(data.upper() + '\n')
            self.out_stream.flush()
        except (socket.error, IOError) as e:
            print(f"Connection error: {e}")
        finally:
            self.client_socket.close()

def main():
    server_port = 7896
    server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    server_socket.bind(('0.0.0.0', server_port))
    server_socket.listen(5)
    print("Server running!")

    try:
        while True:
            client_socket, addr = server_socket.accept()
            Connection(client_socket)
    except (socket.error, IOError) as e:
        print(f"Listen error: {e}")
    finally:
        server_socket.close()

if __name__ == "__main__":
    main()