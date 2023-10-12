package ra.bt;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        /*
        Tạo một class User có thể Serializable có các trường sau:
        int id
        String username
        String password
        boolean status
        Thực hiện ghi object này vào 1 file là 'user.dat'
        Thực hiện đọc dữ liệu từ file này và in ra màn hình
        Thực hiện đọc dữ liệu file này nhưng ghi nội dung text vào file user.txt
        */
            List<User> users = new ArrayList<>();
            users.add(new User(1, "Minh", "123456", true));
            users.add(new User(2, "Dũng", "234567", true));
            users.add(new User(3, "Nam", "345678", true));
            users.add(new User(4, "Tiến", "456789", true));

        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("user.dat"));
        ) {
            for (User user : users) {
                objectOutputStream.writeObject(user);
            }
        } catch (IOException e) {
            System.out.println("Đã hoàn tất việc ghi file");
        }

        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("user.dat"))) {
            while (true) {
                System.out.println(objectInputStream.readObject());
            }
        } catch (IOException e) {
            System.out.println("Đã hoàn tất việc đọc file");
        } catch (ClassNotFoundException e) {
            System.out.println("Kiểu dữ liệu không đúng");
        }
        long fileSize = 0;
        File file = new File("user.dat");
        if (file.exists()) {
            fileSize = file.length();
        } else {
            System.out.println("Tệp không tồn tại");
        }
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("user.dat"));
             BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("user.txt"));){
//            file => stream (byte)
//            stream => file (character)
            while (true) {
                bufferedWriter.write(objectInputStream.readObject().toString());
            }

        } catch (IOException e) {
            System.out.println("Đã hoàn tất việc đọc file");
            System.out.println("File có kích thước là " + fileSize + " bytes");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
