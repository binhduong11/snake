package test_02;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Nhập thông tin của điểm bắt đầu và kết thúc
        System.out.println("Nhập tọa độ điểm bắt đầu");
        System.out.println("x = ");
        int XBatDau = scanner.nextInt();
        System.out.println("y = ");
        int YBatDau = scanner.nextInt();
        ToaDo batdau = new ToaDo("Bắt đầu", XBatDau, YBatDau);
        System.out.println("Nhập tọa độ điểm kết thúc");
        System.out.println("x = ");
        int XKetThuc = scanner.nextInt();
        System.out.println("y = ");
        int YKetThuc = scanner.nextInt();
        ToaDo KetThuc = new ToaDo("Kết thúc", XKetThuc, YKetThuc);
        
        // Nhập số lượng thức ăn và thông tin tọa độ
        System.out.println("Nhập số lượng thức ăn");
        int soluong = scanner.nextInt();
        List<ToaDo> z = new ArrayList<>();
        for (int i = 0; i < soluong; i++) {
            System.out.println("Nhập tọa độ thức ăn số " + (i + 1));
            System.out.println("x = ");
            int x = scanner.nextInt();
            System.out.println("y = ");
            int y = scanner.nextInt();
            ToaDo itemp = new ToaDo("thức ăn số " + i, x, y);

            z.add(itemp);

        }
        
        var sapxep = listToaDo(batdau, KetThuc, z);
        
        System.out.println("Đường đi ngắn nhất là :");
        for (int i = 0; i < sapxep.size(); i++) {
            System.out.println(sapxep.get(i).getTen() + " ==> ");
        }

        for (int i = 0; i < sapxep.size() - 1; i++) {
            sapxep.get(i).DuongDi(sapxep.get(i + 1));
        }
    }

    // hàm tìm đường đi ngắn nhất bằng phương pháp so sánh khoảng cách từ điểm xuất phát đến điểm lân cận gần nhất 
    public static List<ToaDo> listToaDo(ToaDo batdau, ToaDo ketthuc, List<ToaDo> listtoado) {
                     
        listtoado.add(0, batdau);
        
        // Sử dụng thuật toán sắp xếp nổi bọt
        for (int i = 0; i < listtoado.size() - 1; i++) {
            for (int j = i + 1; j <= listtoado.size() - 1 ; j++) {
                
                // so sánh d(i,j) với d(i,i+1) nếu phát hiện khoảng cách d(i,j) < thì đổi chỗ i với j =< kết thúc vòng lặp thứ 2
                // điểm có khoảng cách gần nhất sẽ ở vị trí i +1 , tiếp tục so sánh đến khi kết thúc vòng lặp thứ nhất
                if ( listtoado.get(i).KhoangCach(listtoado.get(j)) <  listtoado.get(i).KhoangCach(listtoado.get( i +1))) {
                    ToaDo min = listtoado.get(j);
                    listtoado.set(j, listtoado.get(i +1));
                    listtoado.set(i +1, min); 
                }

            }
                       
        }
        listtoado.add(ketthuc);
        return listtoado;
    }
}
