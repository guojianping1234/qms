package MvService

class MyWebServer {
    private ServerSocket server;

    MyWebServer(ServerSocket server) {
        try {
            this.server = new ServerSocket(8080);
        } catch (Exception e) {
           System.out.println("启动失败")
        }

    }

    private void start() {
        try {
            while (true) {
                System.out.println("等待客户端连接")
                Socket socket = server.accept();
                OutputStream os = socket.getOutputStream();
                os.write("hello ..".getBytes());
                os.flush();

            }

        } catch (Exception e) {


        }
    }

    public static void main(String[] args) {
        MyWebServer wb = new MyWebServer();
        wb.start();

    }
}
